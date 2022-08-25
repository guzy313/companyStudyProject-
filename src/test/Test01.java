package test;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class Test01 {

    @Test
    public void test(){
        BigDecimal bigDecimal = new BigDecimal(100);
        bigDecimal = bigDecimal.add(new BigDecimal(100));
        System.out.println(bigDecimal);
    }
}
