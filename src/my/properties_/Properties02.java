package my.properties_;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Properties02 {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileReader("src\\db.properties"));
        System.out.println(properties.getProperty("user"));
        properties.list(System.out);


    }


    @Test
    public void setP() throws Exception{
        Properties properties = new Properties();
        properties.setProperty("user","admin");
        properties.setProperty("password","321");
        properties.setProperty("jxr","gay");
        properties.store(new FileWriter("src\\db.properties",false),null);
        System.out.println("保存配置文件成功");

    }

}
