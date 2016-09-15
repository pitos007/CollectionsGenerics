/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


/**
 *
 * @author UPatryk
 */


public class StreamTemplate1 {
   public static void main(String[] args) {
      List<Person> personList = Arrays.asList(
          new Person("Patryk", 34, "male"),
          new Person("Iwona", 34, "female"),
          new Person("Oliwia", 1, "female"),
          new Person("Pawel", 21, "male"),
          new Person("Gawel", 23, "male"),
          new Person("Bolek", 12, "male"),
          new Person("Lolek", 12, "male"),
          new Person("Jola", 70, "female"),
          new Person("Ala", 5, "female"));
      
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
      
      
      System.out.println("\nList max character and check:");
      
              
       
      
      
      
   }
   
   
      
   
   public static class Person{
       String name;
       int age;
       String sex;

        public Person(String name, int age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
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

        @Override
        public String toString() {
            return "Person{" + "name=" + name + ", age=" + age + ", sex=" + sex + '}';
        }
   }
   
   interface PersonPredicate{
       public boolean test(Person p);
   }
   
   
}