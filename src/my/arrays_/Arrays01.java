package my.arrays_;

import java.util.Arrays;
import java.util.Comparator;

public class Arrays01 {
    public static void main (String[] args){

        int[] itArray = {66,12,134,15,88};

        //sort(itArray);
        //System.out.println(Arrays.toString(itArray));
        sort1(itArray, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                int i1 = (Integer) o1;
                int i2 = (Integer) o2;
                return i2 - i1;
            }
        });

        System.out.println(Arrays.toString(itArray));


    }

    public static void sort(int[] arr){
        //从大到小
        for (int i = arr.length ; i > 0 ; i--) {
            for (int j = 0; j < i - 1; j++) {
                int x = arr[j];
                if(arr[j] < arr[j + 1]){
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                }
            }
        }

    }

    public static void sort1(int[] arr,Comparator c){
        //从大到小
        for (int i = arr.length ; i > 0 ; i--) {
            for (int j = 0; j < i - 1; j++) {
                int x = arr[j];
                if(c.compare(arr[j],arr[j + 1]) < 0){
                    arr[j] = arr[j + 1];
                    arr[j + 1] = x;
                }
            }
        }
    }

}
