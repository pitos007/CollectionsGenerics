/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LambdaStreams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @author UPatryk
 */
public class Parallel {
    int[] nums = {25, 37, 85, 67, 97, 2, 78, 24, 44, 12, 1, 9, 77, 83, 73, 24, 
        16, 91, 29, 46, 73, 37, 19, 91, 46, 64, 
        57, 73, 95, 15, 45, 65, 81, 83, 36, 14};
    
    public static void main(String[] args) {
        
        
        
       
    }
    
    public static long parallelSum(Function<Long, Long> adder, long n){
            LongStream numsStream = Arrays.stream(nums);
            
    }
    
    
}
