package my.file_.writer_;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriter01 {
    public static void main(String[] args){

    }

    @Test
    public void fileWriter01(){
        FileWriter fw = null;
        String filePath = "G:\\学习\\testPath\\t1.txt";
        try {
            fw = new FileWriter(filePath);
            char[] ch = {'j','x','r'};
            int cLen = 0;
            fw.write(ch);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
           try {
               fw.flush();
               fw.close();
           }catch (IOException e){
              e.printStackTrace();
           }
        }
    }




}
