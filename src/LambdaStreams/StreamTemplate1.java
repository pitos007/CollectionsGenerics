/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;


/**
 *
 * @author UPatryk
 */


public class StreamTemplate1 {
   public static void main(String[] args) {
      List<Person> personList = Arrays.asList(
          new Person(2009, "Patryk", 34, "male", "London"),
          new Person(2008, "Iwona", 34, "female", "New York"),
          new Person(2008, "Oliwia", 1, "female", "Cambridge"),
          new Person(2013, "Pawel", 21, "male", "Oxford"),
          new Person(2016, "Gawel", 23, "male", "St Ives"),
          new Person(2009, "Bolek", 12, "male", "London"),
          new Person(2012, "Lolek", 12, "male", "Cambridge"),
          new Person(2012, "Jola", 70, "female", "Southampton"),
          new Person(2011, "Ala", 5, "female", "Northampton"));
      
      // lambda
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
      System.out.println("\nstream option 1:");
      List<String> strList = personList.stream()
               .map(p -> 
                       p.getAge() >= 18 ? p.getName() + " is an adult" :
                       p.getAge() <= 8 ? p.getName() + " is a child" :
                       p.getName() + " is an adult")
               .collect(Collectors.toList());
      strList.forEach(System.out::println);
      
      
      System.out.println("\nList adult males:");
      List<String> maleAdult = personList.stream()
              .filter(p -> p.getAge() >= 18)  // Stream<T> filter(Predicate<? super T> predicate);
              .filter(p -> p.getSex().equals("male")) 
              .map(Person::getName) // <R> Stream<R> map(Function<? super T, ? extends R> mapper);
              .collect(toList());  // <R, A> R collect(Collector<? super T, A, R> collector);
      maleAdult.forEach(System.out::println);
      
       
      
      
      
              
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
              
       System.out.println("\nToo long name Java 1.8:");
       List<String> nameL2 = personList.stream()
               .map(Person::getName)
               .map(n -> n.length() > 5 ? n + " is too long" : n)
               .collect(toList());
       nameL2.stream()
               .forEach(System.out::println);
       
       
       
       System.out.println("\nfindAny, ifPresent:");
       personList.stream()
               .filter(Person::isAdult)
               // findAny returns Optional<Person>
               .findAny()
               // ifPresent returns true if Optional contains a value, false otherwise
               .ifPresent(p -> System.out.println("first occurance :" + p.getName()));
       
       
       System.out.println("\nanyMatch");
       if(personList.stream().anyMatch(Person::isAdult)){
           System.out.println("There is at least one adult");
       }
      
       System.out.println("\nreduce with initial value:");
       int ageSum = personList.stream()
           .filter(p -> p.sex.equals("male"))
           .map(Person::getAge)
           .reduce(0, Integer::sum);
           //.reduce(0, (a, b) -> a + b);
        System.out.println(ageSum);
        
        
       System.out.println("\nreduce without ininital value:");
        Optional<Integer> sum = personList.stream()
                .filter(p -> p.sex.equals("male"))
                .map(Person::getAge)
                .reduce((a,b) -> (a+b));
                //.reduce(Integer::sum);
        System.out.println(sum);
        
        System.out.println("\nmapToInt + sum:");
        int maleSumAge = personList.stream()
                .filter(p -> p.sex.equals("male"))
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("male sum age is " + maleSumAge);
        
        
        System.out.println("\nreduce using max:");
        Optional<Integer> maxNum = personList.stream()
                .map(Person::getAge)
                .reduce(Integer::max);
        System.out.println("maximum age in the list is " + maxNum);
        
        System.out.println("\nmax:");
        Optional<Person> youngestPerson = personList.stream()
                .min(comparing(Person::getAge));
        System.out.println("the youngest person is " + youngestPerson);
        
        
        System.out.println("\nsorted example");
        List<Person> trans11 = personList.stream()
            .filter(t -> t.getAge() >= 18 )
            .sorted(comparing(Person::getAge))
            //.sorted((v1,v2) -> Integer.compare(v1.getAge(), v2.getAge()))
            .collect(toList());
        trans11.stream()
            .forEach(e -> System.out.println(e));
      
   }
   
   
      
   
   public static class Person{
       String name;
       int age;
       String sex;
       String town;
       int year;

        public Person(int id, String name, int age, String sex, String town) {
            this.name = name;
            this.age = age;
            this.sex = sex;
            this.town = town;
            this.year = id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getSex() {
            return sex;
        }

        public String getTown() {
            return town;
        }

        public int getId() {
            return year;
        }
        
        

        @Override
        public String toString() {
            return "Person{" + "name: " + name + ", age: " + age + ", sex: " + sex + '}';
        }
        
        public boolean isAdult(){
            return getAge() >= 18;
        }
   }
   
   
   
   interface PersonPredicate{
       public boolean test(Person p);
   }
   
   
}
