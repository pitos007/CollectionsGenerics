/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author UPatryk
 */
public class Sum {
    public static void main(String[] args) {
        List<Double> doubles = Arrays.asList(2.58, 5.97, 2.44);
        Double dblV = sumNumbers(doubles);

        List<Integer> integers = Arrays.asList(1,2,3,4,5);
        countIntegers(integers, 5);
       
    }
    
    // use a super wildcard when you only put values into a structure
    // use an extends wildcard when you only get values out of a structure
    // don't use a wildcard when you both get and put
    public static double sumNumbers(Collection<? extends Number> nums){
        double d = 0.0;
        for (Number num : nums) {
            d += num.doubleValue();
        }
        System.out.println("total: " + d);
        return d;
    }
    
    public static void countIntegers(Collection<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++) ints.add(i);
}


}
