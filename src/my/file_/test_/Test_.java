package my.file_.test_;


import entity.Testxx;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Test_ {
    private static final String path = "G:\\学习\\testPath\\";
    private static final String fileName = "xxx.dat";


    public static void main(String[] args) throws Exception{
        Set<String>  s = new HashSet<>();
        s.add("jj");
        s.add("j1");
        System.out.println(s);
    }



    @Test
    public void writeObject() throws IOException{
        ObjectOutputStream oos = null;


        try {
            oos = new ObjectOutputStream(new FileOutputStream(path + fileName));
            for (int i = 0; i < 10; i++) {
                oos.writeObject(new Testxx(i+"","测试"+i,1+i,"黑色","中国"));
            }
            System.out.println("文件写入成功");

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            oos.close();
        }
    }


    @Test
    public void readObject() throws IOException{
        ObjectInputStream ois = null;
        try {
            System.out.println("=========文件开始读取=========");
            ois = new ObjectInputStream(new FileInputStream(path + fileName));
            Object obj = null;
            while ((obj = ois.readObject()) != null){
                System.out.println(obj);
            }
            System.out.println("=========文件读取结束=========");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            ois.close();
        }
    }
}
