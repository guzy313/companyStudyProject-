package my.arrays_;

import java.util.Arrays;

public class Arrays02 {
    public static void main (String[] args){
        Integer[] arr = {1,3,6,775,3131};

        int index = Arrays.binarySearch(arr,775);

        System.out.println(index);


        Integer[] newarr = Arrays.copyOf(arr,arr.length + 1);

        System.out.println(Arrays.toString(newarr));

        Arrays.fill(arr,777);
        System.out.println(Arrays.toString(arr));



    }
}
