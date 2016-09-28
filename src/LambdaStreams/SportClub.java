/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaStreams;

import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import java.util.function.Function;

/**
 *
 * @author UPatryk
 */


public class SportClub {
   @SuppressWarnings("empty-statement")
   enum BudgetType { HIGH, MEDIUM, LOW}
   
   public static void main(String[] args) {
      
      Club c1 = new Club("RunningMushrooms", 100, 10000.00, Club.Type.RUN);
      Club c2 = new Club("HeavyRiders", 200, 8000.00, Club.Type.BIKE);
      Club c3 = new Club("FastSwimmers", 100, 9000.00, Club.Type.SWIM);
      Club c4 = new Club("RunningMonkeys", 50, 8000.00, Club.Type.RUN);
      
      List<Person> personList = Arrays.asList(
          new Person(2009, "Patryk", 34, Person.Sex.MALE, "London",c1),
          new Person(2008, "Iwona", 34, Person.Sex.FEMALE, "New York",c2),
          new Person(2008, "Oliwia", 17, Person.Sex.FEMALE, "Cambridge",c3),
          new Person(2013, "Pawel", 21, Person.Sex.MALE, "Oxford",c4),
          new Person(2016, "Gawel", 23, Person.Sex.MALE, "St Ives",c4),
          new Person(2009, "Bolek", 12, Person.Sex.MALE, "London",c1),
          new Person(2012, "Lolek", 12, Person.Sex.MALE, "Cambridge",c2),
          new Person(2012, "Jola", 70, Person.Sex.FEMALE, "Southampton",c1),
          new Person(2011, "Ala", 25, Person.Sex.FEMALE, "Northampton",c3));
      
      // lambda
      System.out.println("------------------------------------");
      personList.forEach(person ->{
          if(person.getAge() >= 18){
              System.out.println(person.getName() + " is an adult");
          }
          else if(person.getAge() <= 8){
              System.out.println(person.getName() + " is a child");
          }
          else{
              System.out.println(person.getName() + " is a teenager");
          }
          
      });
      
      
      
      // Stream
      System.out.println("------------------------------------");
      System.out.println("\nstream option 1:");
      List<String> strList = personList.stream()
               .map(p -> 
                       p.getAge() >= 18 ? p.getName() + " is an adult" :
                       p.getAge() <= 8 ? p.getName() + " is a child" :
                       p.getName() + " is an adult")
               .collect(Collectors.toList());
      strList.forEach(System.out::println);
      
      
      
      System.out.println("------------------------------------");
      System.out.println("\nList adult males:");
      List<String> maleAdult = personList.stream()
              .filter(p -> p.getAge() >= 18)  // Stream<T> filter(Predicate<? super T> predicate);
              .filter(p -> p.sex == Person.Sex.MALE) 
              .map(Person::getName) // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
              .collect(Collectors.toList());  // <R, A> R collect(Collector<? super T, A, R> collector);
      maleAdult.forEach(System.out::println);
      
       
      
      
      System.out.println("------------------------------------");
      System.out.println("\nToo lon name Java 1.7");
      List<String> nameL = new ArrayList<>();
       for (Person per : personList) {
           int i = per.getName().length();
           if (i > 5) {
               String nStr = per.getName() + " is too long";
               nameL.add(nStr);
           }
           else{
               nameL.add(per.getName());
           }
       }
       for (String string : nameL) {
           System.out.println(string);
       }
       
       
       
       System.out.println("------------------------------------");
       System.out.println("\nToo long name Java 1.8:");
       List<String> nameL2 = personList.stream()
               .map(Person::getName)
               .map(n -> n.length() > 5 ? n + " is too long" : n)
               .collect(Collectors.toList());
       nameL2.stream()
               .forEach(System.out::println);
       
       
       
       System.out.println("------------------------------------");
       System.out.println("\nfindAny, ifPresent:");
       personList.stream()
               .filter(Person::isAdult)
               // findAny returns Optional<Person>
               .findAny()
               // ifPresent returns true if Optional contains a value, false otherwise
               .ifPresent(p -> System.out.println("first occurance :" + p.getName()));
       
       
       
       System.out.println("------------------------------------");
       System.out.println("\nanyMatch");
       if(personList.stream().anyMatch(Person::isAdult)){
           System.out.println("There is at least one adult");
       }
      
       
       
       System.out.println("------------------------------------");
       System.out.println("\nsummarizing with reduce and initial value:");
       int maleAgeSum1 = personList.stream()
           .filter(p -> p.sex == Person.Sex.MALE)
           .map(Person::getAge)
           .reduce(0, Integer::sum);
           //.reduce(0, (a, b) -> a + b);
        System.out.println("Total male age is " + maleAgeSum1);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with reduce without ininital value:");
        Optional<Integer> maleAgeSum2 = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .map(Person::getAge)
                .reduce((a,b) -> (a+b));
                //.reduce(Integer::maleAgeSum2);
        System.out.println("Total male age is " + maleAgeSum2);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with reduce using reducing(identity, mapper, binaryOp):");
        int maleAgeSum3 = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .collect(reducing(0, Person::getAge, (Integer i, Integer j) -> i + j));
        System.out.println("Total male age is " + maleAgeSum3);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with reduce using reducing(identity, mapper, Integer::sum):");
        int maleAgeSum5 = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .collect(reducing(0, Person::getAge, Integer::sum));
        System.out.println("Total male age is " + maleAgeSum5);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with reduce and get:");
        int maleAgeSum7 = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .map(Person::getAge)
                .reduce(Integer::sum)
                .get();
        System.out.println("Total male age is " + maleAgeSum7);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with mapToInt and sum:");
        int maleAgeSum6 = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("Total male age is " + maleAgeSum6);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsummarizing with summingInt (no auto-unboxing):");
        int totalNumOfYears = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .collect(summingInt(Person::getAge));
        System.out.println("Total male age is: " + totalNumOfYears);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nreduce using one-argument reducing factory method:");
        Optional<Person> maleAgeSum4 = personList.stream()
                .collect(reducing((d1,d2) -> d1.getAge() > d2.getAge() ? d1 : d2));
        System.out.println("Total male age is " + maleAgeSum4);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmapToInt + sum:");
        int maleSumAge = personList.stream()
                .filter(p -> p.sex == Person.Sex.MALE)
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("male sum age is " + maleSumAge);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("Arrays.stream():");
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        int sumOfNum = Arrays.stream(numbers).sum();
   
        
        
        //int stream to integer stream
        IntStream intStream = personList.stream()
                .mapToInt(Person::getAge);
        Stream<Integer> streamInteger = intStream.boxed();
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nreduce using max:");
        Optional<Integer> maxNum = personList.stream()
                .map(Person::getAge)
                .reduce(Integer::max);
        System.out.println("maximum age in the list is " + maxNum);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmax:");
        Optional<Person> oldestPerson = personList.stream()
                .max(comparing(Person::getAge));
        System.out.println("the oldest person is " + oldestPerson);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ncomparing, maxBy:");
        Optional<Person> oldestPersn = personList.stream()
                .collect(maxBy(comparing(Person::getAge)));
        System.out.println("The oldest person is " + oldestPersn);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ncomparingInt, maxBy:");
        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        Optional<Person> oldestPrsn = personList.stream()
                .collect(maxBy(ageComparator));
        System.out.println("The oldest person is " + oldestPrsn);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\norElse:");
        OptionalInt maxAgeOpt = personList.stream()
                .mapToInt(Person::getAge)
                .max();
        int maxAgeInt = maxAgeOpt.orElse(1); // default is 1 if there is no value
        System.out.println("The oldest person is " + maxAgeInt + " years old");
        
        
        
        System.out.println("------------------------------------");
        System.out.println("counting:");
        long pplN1 = personList.stream()
                .collect(Collectors.counting());
        long pplN2 = personList.stream()
                .count();
        System.out.println("Number of people: " + pplN1 + " " + pplN2);
        
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nIntSummaryStatistics for age:");
        IntSummaryStatistics iss = personList.stream()
                .collect(summarizingInt(Person::getAge));
        System.out.println(iss);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nsorted example");
        List<Person> trans11 = personList.stream()
            .filter(t -> t.getAge() >= 18 )
            .sorted(comparing(Person::getAge))
            //.sorted((v1,v2) -> Integer.compare(v1.getAge(), v2.getAge()))
            .collect(Collectors.toList());
        trans11.stream()
            .forEach(e -> System.out.println(e));
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\niterate example:");
        Stream<Long> subnetMask = Stream.iterate(1L, m -> m * 2)
                .limit(9);
        subnetMask.forEach(System.out::println);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ngenerate example:");
        Stream<Double> doubles = Stream.generate(Math::random)
                .limit(5);
        doubles.forEach(System.out::println);
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\njoining Strings with delimeter:");
        String allNamesC = personList.stream()
                .map(Person::getName)
                .collect(joining(","));
        System.out.println(allNamesC);
        
        
        System.out.println("------------------------------------");
        System.out.println("\ngrouping Type::method");
        Map<Club, List<Person>> groupByClub = personList.stream()
                .collect(Collectors.groupingBy(Person::getClub));
                //.collect(Collectors.groupingBy(Person::getClub, toList()));
        groupByClub.forEach((c,p) ->{
            System.out.println("\n" + c);
            p.forEach(System.out::println);
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ngrouping by custom criteria:");
        Map<String, List<Person>> groupByYear = personList.stream()
                .collect(groupingBy(p -> {
                    if(p.getYear() > 2015) return "new members:";
                    else if(p.getYear() < 2009) return "senior members:";
                    else return "normal members:";
                }));
        groupByYear.forEach((k,v) ->{
            System.out.println("\n" + k);
            v.forEach(System.out::println);
        });
        
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmulti-level grouping - groupingBy()");
        Map<String, Map<Person.Sex, List<Person>>> groupByYearThenSex = personList.stream().collect(
                groupingBy(p ->{
                    if(p.getYear() > 2015) return "New members";
                    else if(p.getYear() < 2009) return "Senior members";
                    else return "Normal members";
                },
                groupingBy(Person::getSex)
                )
        );
        groupByYearThenSex.forEach((String y,Map<Person.Sex, List<Person>> m) ->{
            System.out.println("\n" + y);
            m.forEach((Person.Sex s, List<Person> p) -> {
                System.out.println("   " + s);
                p.forEach((Person prsn) -> {
                    System.out.println("      " + prsn.toString());
                });
                
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmulti-level grouping - groupingBy() + groupingBy(reducing()) without Optional type");
        Map<String, Map<Person.Sex, Person>> groupByYearThenSexOldest = personList.stream().collect(
                groupingBy(p ->{
                    if(p.getYear() > 2015) return "New members";
                    else if(p.getYear() < 2009) return "Senior members";
                    else return "Normal members";
                },
                groupingBy(Person::getSex, collectingAndThen(
                        reducing((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2), Optional::get)
                )
            )
        );
        groupByYearThenSexOldest.forEach((String y, Map<Person.Sex, Person> m) ->{
            System.out.println("\n" + y);
            m.forEach((Person.Sex s, Person p) -> {
                System.out.println("   " + s);
                System.out.println("      " + p.toString());
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmulti-level grouping - groupingBy(collectingAndThen(maxBy())) without Optional type");
        Map<String, Map<Person.Sex, Person>> groupByYearThenSexOldest2 = personList.stream().collect(
                groupingBy(p ->{
                    if(p.getYear() > 2015) return "New members";
                    else if(p.getYear() < 2009) return "Senior members";
                    else return "Normal members";
                },
                groupingBy(Person::getSex, collectingAndThen(
                        maxBy(Comparator.comparingInt(Person::getAge)), Optional::get)
                )
            )
        );
        groupByYearThenSexOldest2.forEach((String y, Map<Person.Sex, Person> m) ->{
            System.out.println("\n" + y);
            m.forEach((Person.Sex s, Person p) -> {
                System.out.println("   " + s);
                System.out.println("      " + p.toString());
            });
        });
        
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmulti-level grouping - groupingBy() + groupingBy(reducing()) with Optional type");
        Map<String, Map<Person.Sex, Optional<Person>>> groupByYearThenSexOldestOptional = personList.stream().collect(
                groupingBy(p ->{
                    if(p.getYear() > 2015) return "New members";
                    else if(p.getYear() < 2009) return "Senior members";
                    else return "Normal members";
                },
                groupingBy(Person::getSex,
                        reducing((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2)
                )
            )
        );
        groupByYearThenSexOldestOptional.forEach((String y, Map<Person.Sex, Optional<Person>> m) ->{
            System.out.println("\n" + y);
            m.forEach((Person.Sex s, Optional<Person> p) -> {
                System.out.println("   " + s);
                System.out.println("      " + p.toString());
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\nmulti-level grouping - groupingBy(collectingAndThen(maxBy())) with Optional type");
        Map<String, Map<Person.Sex, Optional<Person>>> groupByYearThenSexOldest2Optional = personList.stream().collect(
                groupingBy(p ->{
                    if(p.getYear() > 2015) return "New members";
                    else if(p.getYear() < 2009) return "Senior members";
                    else return "Normal members";
                },
                groupingBy(Person::getSex,
                        maxBy(Comparator.comparingInt(Person::getAge))
                ))
            );
        groupByYearThenSexOldest2Optional.forEach((String y, Map<Person.Sex, Optional<Person>> m) ->{
            System.out.println("\n" + y);
            m.forEach((Person.Sex s, Optional<Person> p) -> {
                System.out.println("   " + s);
                System.out.println("      " + p.toString());
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ngrouping by custom criteria - mapping()");
        Map<String, Set<String>> groupByYearToSet = personList.stream().collect(
                groupingBy(p -> {
                    if(p.getYear() > 2015) return "new members:";
                    else if(p.getYear() < 2009) return "senior members:";
                    else return "normal members:";
                },
                mapping(
                        prsn -> {
                            if(prsn.getAge() > 30) return "age: >30 " + prsn.getName();
                            else if (prsn.getAge() < 18) return "age: <18 " + prsn.getName();
                            else return "age: 19<29 " + prsn.getName();
                        },
                        toCollection(HashSet::new) )));
                        //toSet();
        groupByYearToSet.forEach((k,v) ->{
            System.out.println("\n" + k);
            v.forEach(System.out::println);
        });
        
        
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\ncounting in a group");
        Map<Club, Long> numberOfMemInGroup = personList.stream()
                .collect(groupingBy(Person::getClub, counting()));
        numberOfMemInGroup.forEach((clb, cnt) ->{
            System.out.println(clb + " - " + cnt + " members");
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\npartitioning ex1:");
        Map<Boolean, List<Person>> partitionPers = personList.stream().collect(
                partitioningBy(Person::isUnderAge));
        partitionPers.forEach((Boolean b, List<Person> p) ->{
            System.out.println("\nunder age?: " + b);
            p.forEach((Person pers) ->{
                System.out.println("   " + pers);
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\npartitioningBy ex2:");
        Map<Boolean, List<Person>> oldestInAgeGroup = personList.stream().collect(
            partitioningBy(p -> p.isAdult()));
        oldestInAgeGroup.forEach((bol, persL) -> {
            System.out.println(bol);
            persL.forEach(pers -> {
                System.out.println("adult?:   " + pers);
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\npartitioningBy, groupingBy");
        Map<Boolean, Map<Integer, List<Person>>> partitionPersGroupingBy = personList.stream().collect(
                partitioningBy(Person::isUnderAge, groupingBy(Person::getAge)));
        
        partitionPersGroupingBy.forEach((Boolean b, Map<Integer, List<Person>> m) ->{
            System.out.println("\nunder age?: " + b);
            m.forEach((Integer yi, List<Person> persLi) -> {
                System.out.println("age group: " + yi);
                persLi.forEach((Person pers) -> {
                    System.out.println("   " + pers.toString());
                });
            });
        });
        
        
        
        System.out.println("------------------------------------");
        System.out.println("\npartitioningBy, collectingAndThen, maxBy");
        Map<Boolean, Person> oldestInAgeGroup2 = personList.stream().collect(
                partitioningBy(Person::isAdult,
                    collectingAndThen(
                        maxBy(comparingInt(Person::getAge)),
                            Optional::get)));
        oldestInAgeGroup2.forEach((Boolean b, Person p) -> {
            System.out.println("adult?: " + b + ", Oldest in the group: " + p.toString());
        });
        
        

        
        System.out.println("------------------------------------");
        System.out.println("\nprint the current stream with peek");
        List<Double> dblL = Arrays.asList(25.50, 48.24, 74.68, 56.15, 19.20, 66.50);
        List<Double> solL = dblL.stream()
                .peek(n -> System.out.println("orig: " + n)).map(n -> n * 1.3)
                .peek(d -> System.out.println("uplift: " + d)).filter(d -> d >= 26.00)
                .peek(d -> System.out.println("higher than 26: " + d)).collect(toList());
        // filter so it shows the highest three values
        
        
        
   }
   
   
        
   
   public static class Person{
       String name;
       int age;
       Sex sex;
       String town;
       int year;
       Club club;
       
       

        public Person(int id, String name, int age, Sex sex, String town, Club club) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.town = town;
            this.year = id;
            this.club = club;
        }
        
        public enum Sex {MALE, FEMALE}

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        public String getTown() {
            return town;
        }

        public int getId() {
            return year;
        }

        public int getYear() {
            return year;
        }

        public Club getClub() {
            return club;
        }
        
        
        public boolean isUnderAge(){
            return this.getAge() < 18;
        }
        
        

        @Override
        public String toString() {
            return "Person{" + "name: " + name + ", age: " + age + ", sex: " + sex + '}';
        }
        
        public boolean isAdult(){
            return getAge() >= 18;
        }
   }
   
   public static class Club {
       String name;
       private int numMembers;
       private double budget;
       Type type;
       
       public enum Type{SWIM, BIKE, RUN}

        public Club(String name, int numMembers, double budget, Type type) {
            this.name = name;
            this.numMembers = numMembers;
            this.budget = budget;
            this.type = type;
        }
       
       

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumMembers() {
            return numMembers;
        }

        public void setNumMembers(int numMembers) {
            this.numMembers = numMembers;
        }

        public double getBudget() {
            return budget;
        }

        public void setBudget(double budget) {
            this.budget = budget;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Club{" + "name=" + name + ", numMembers=" + numMembers + ", budget=" + budget + ", type=" + type + '}';
        }
       
       
       

        
       
       
       
       
   }
   
   
   
   interface PersonPredicate{
       public boolean test(Person p);
   }
   
   
}
