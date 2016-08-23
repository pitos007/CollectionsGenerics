package compare;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DuckSortTestDrive { 
 
public static void main(String[] args) { 
    Duck[] ducks = {  
    new Duck("Daffy duck", 8),  
    new Duck("Dewey duck", 2), 
    new Duck("Howard duck", 7), 
    new Duck("Louie duck", 2), 
    new Duck("Donald duck", 10),  
    new Duck("Huey duck", 2) 
 }; 
 
System.out.println("Before sorting:"); 
    displayDuck(ducks); 
 
Arrays.sort(ducks); 
  
System.out.println("\nAfter sorting:"); 
displayDuck(ducks); 
                 
System.out.println("----------------------------------"); 

Pigeon[] pigeons = {  
    new Pigeon("Daffy pigeon", 8),  
    new Pigeon("Dewey pigeon", 2), 
    new Pigeon("Howard pigeon", 7), 
    new Pigeon("Louie pigeon", 2), 
    new Pigeon("Donald pigeon", 10),  
    new Pigeon("Huey pigeon", 2)                          
 }; 

List<Pigeon> pigeonList = Arrays.asList(pigeons); 
 
System.out.println("Before sorting:"); 
displayPigeon(pigeonList); 
 
Collections.sort(pigeonList); 
  
System.out.println("\nAfter sorting:"); 
displayPigeon(pigeonList);

System.out.println("----------------------------------"); 

Stork[] storks = {  
    new Stork("Daffy stork", 7),  
    new Stork("Dewey stork", 1), 
    new Stork("Howard stork", 4), 
    new Stork("Louie stork", 3), 
    new Stork("Donald stork", 12),  
    new Stork("Huey stork", 8),
 };

Pigeon[] pigeons2 = {
    new Pigeon("Daffy pigeon", 7),
    new Pigeon("Daffy pigeon", 2),
};

List<Stork> storkList = Arrays.asList(storks);
List<Pigeon> pigeons2List = Arrays.asList(pigeons2);
 
System.out.println("Before sorting:"); 
displayStork(storkList); 

Collections.sort(storkList);
  
System.out.println("\nAfter sorting:"); 
displayStork(storkList);

System.out.println("reflection example:");
System.out.println(storkList.getClass() + ", " + pigeons2List.getClass());
System.out.println(storkList.getClass() == pigeons2List.getClass());


System.out.println("----------------------------------"); 
                 
Sparrow[] sparrows = {  
    new Sparrow("Daffy sparrow", 8),  
    new Sparrow("Dewey sparrow", 2), 
    new Sparrow("Howard sparrow", 7), 
    new Sparrow("Louie sparrow", 2), 
    new Sparrow("Donald sparrow", 10),  
    new Sparrow("Huey sparrow", 2), 
    new Sparrow("Huey sparrow", 4), 
    new Sparrow("Huey sparrow", 1), 
    new Sparrow("Huey sparrow", 8), 
    new Sparrow("Huey sparrow", 3) 
 }; 
                
List<Sparrow> sparrowList = Arrays.asList(sparrows); 
 
System.out.println("Before sorting:"); 
displaySparrow(sparrowList); 
 
Collections.sort(sparrowList, new Comparator<Sparrow>() {   
    @Override 
    public int compare(Sparrow obj1, Sparrow obj2) { 
        return obj1.name.compareTo(obj2.name); 
        //return (obj1.weight < obj2.weight) ? -1: (obj1.weight > obj2.weight) ? 1 : 0; 
    } 
}); 
 
    System.out.println("\nAfter sorting:"); 
    displaySparrow(sparrowList); 
      
    System.out.println("");
    System.out.println();
                 
} 
 
    public static void displayDuck(Duck[] ducks) { 
    for (Duck duck : ducks) {
        System.out.println(duck);
    } 
    } 

    public static void displayPigeon(List<Pigeon> pigeons) { 
        for (Pigeon eachPigeon : pigeons) { 
            System.out.println(eachPigeon); 
        } 
    } 
    
    public static void displayStork(List<Stork> storks) { 
        for (Stork eachStork : storks) { 
            System.out.println(eachStork); 
        } 
    } 

    public static void displaySparrow(List<Sparrow> sparrows) { 
        for (Sparrow eachSparrow : sparrows) { 
            System.out.println(eachSparrow); 
        } 
    } 
    
    
} 