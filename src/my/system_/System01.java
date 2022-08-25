package my.system_;

import java.util.Arrays;

public class System01 {
    public static void main(String[] args){
        /*
        int count = 0;
        for (;;) {
            count ++;
            System.out.println(count);
            if(count == 10){
                System.exit(0);
            }
        }*/

        int[] array1 = {5,3,77,12,31};
        int[] array2 = new int[6];

        System.arraycopy(array1,0,array2,1,5);

        System.out.println(Arrays.toString(array2));


    }
}
