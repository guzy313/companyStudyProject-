package my.bignum;

import java.math.BigInteger;

public class BigInteger01 {
    public static void main (String[] args){
        //long l = 9999999999999999999999999999999999999;
        BigInteger bigInteger = new BigInteger("9999999999999999999999999999999999999");
        System.out.println(bigInteger);
        bigInteger = bigInteger.add(new BigInteger("1"));
        System.out.println(bigInteger);
        bigInteger = bigInteger.subtract(new BigInteger("555555555555555"));
        System.out.println(bigInteger);
        bigInteger = bigInteger.multiply(new BigInteger("2"));
        System.out.println(bigInteger);
        bigInteger = bigInteger.divide(bigInteger);
        System.out.println(bigInteger);
    }

}
