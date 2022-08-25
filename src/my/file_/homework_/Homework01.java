package my.file_.homework_;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

@SuppressWarnings({"all"})
public class Homework01 {
    private static final String path = "G:\\学习\\testPath\\";
    private static final String datFolder = "datFolder\\";//存储序列化文件用
    private static final String datPath = "src\\";

    @Test
    public void exercise01() throws Exception{
        String floder = "mytemp\\";
        File file = new File(path + floder);
        String fileName = "hellow.txt";
        BufferedWriter bw = null;
        if(!file.exists()){
            file.mkdirs();
            System.out.println("文件夹" + floder + "不存在,创建成功");
        }else{
            System.out.println("文件夹" + floder + "已经存在");
        }
        file = new File(path + floder + fileName);
        if(file.exists()){
            System.out.println("文件"+fileName+"已经存在");
        }else{
            file.createNewFile();
            System.out.println("文件"+fileName+"创建成功");
        }

        /*bw = new BufferedWriter(new FileWriter(path + floder + fileName));
        bw.flush();
        bw.close();*/



    }

    @Test
    public void exercise02() throws Exception{
        BufferedReader br = null;

        String fileName = "tt.txt";
        br = new BufferedReader(new FileReader(path + fileName));
        String str;
        int count = 0;
        ArrayList<String> strings = new ArrayList<>();
        while ((str = br.readLine()) != null){
            count ++;
            str = count + "-" + str;
            strings.add(str);
        }
        br.close();
        System.out.println("文件读取成功");

        ////////////////////////////////////////
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(path + fileName));
        for (String s:strings) {
            bw.write(s);
            bw.newLine();
        }
        System.out.println("行号写入成功");
        bw.close();
        //////////////////////////////////////
        System.out.println("文件内容（已加行号）");
        System.out.println(strings);

    }


    @Test
    public void exercise021() throws Exception{
        BufferedReader br = null;

        String fileName = "tt.txt";
        br = new BufferedReader(new InputStreamReader(new FileInputStream(path + fileName),"utf-8"));
        String str;
        int count = 0;
        ArrayList<String> strings = new ArrayList<>();
        while ((str = br.readLine()) != null){
            count ++;
            //str = count + "-" + str;
            strings.add(str);
        }
        br.close();
        System.out.println("文件读取成功");


        //////////////////////////////////////
        System.out.println("文件内容（已加行号）");
        System.out.println(strings);

    }

    @Test
    public void exercise03() throws IOException{
        Properties properties = new Properties();
        ObjectOutputStream oos = null;
           try {
               properties.load(new FileReader("src\\my\\file_\\homework_\\dog.properties"));
               Dogg1 dogg1 = new Dogg1(properties.getProperty("name"),Integer.parseInt(properties.getProperty("age")),properties.getProperty("color"));
               System.out.println(dogg1);

               //新路径--存储序列化文件用
               String fileName = "dogg1.dat";
               File file = new File(path + datFolder);
               if(file.exists()){
                   System.out.println("文件夹" + datFolder + "已经存在无需创建");
               }else{
                   file.mkdirs();
                   System.out.println("文件夹" + datFolder + "创建成功");
               }
               file = new File(path + datFolder +fileName);
               if(file.exists()){
                   System.out.println("文件" + fileName + "已经存在无需创建");
               }else{
                   System.out.println("文件" + fileName + "创建成功");
               }
               //序列化到本地
               oos = new ObjectOutputStream(new FileOutputStream(file));
               oos.writeObject(dogg1);
               String className = dogg1.getClass().getName();
               className = className.substring(className.lastIndexOf(".") + 1,className.length());
               setClassInfo(className);
               System.out.println(dogg1.getClass() + "序列化成功");
           }catch (IOException e){
               e.printStackTrace();
           }finally {
               oos.close();
           }
    }


    @Test
    public void backSerial() throws IOException{
        ObjectInputStream ois = null;
        String fileName = "dogg1.dat";
        try {
            ois = new ObjectInputStream(new FileInputStream(path + datFolder + fileName));
            Object obj;
            int count = 0;
            String className = new Dogg1().getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1,className.length());
            if((count = getClassInfo(className)) != 0){
                for (int i = 0; i < count; i++) {
                    obj = ois.readObject();
                    System.out.println(obj);
                }
            }
            System.out.println(count);
            System.out.println(fileName + "反序列化成功");

        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
        }
    }

    /**
     * 记录序列化对象个数
     * @param className
     * @throws IOException
     */
    public void setClassInfo(String className) throws IOException{
        String src = "src\\datInfo.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(src));
        String value = "";
        int valueInt = 0;
        if((value = properties.getProperty(className)) != null){
            valueInt = Integer.parseInt(value);
        }
        valueInt ++;
        value = valueInt + "";
        properties.setProperty(className,value);
        properties.store(new FileWriter(src),null );
    }

    /**
     * 通过class名获取当前class存有多少个序列化对象
     * @param className
     * @return
     * @throws IOException
     */
    public int getClassInfo(String className) throws IOException{
        String src = "src\\datInfo.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(src));
        String str;
        if((str = properties.getProperty(className)) != null){
            return Integer.parseInt(str);
        }else{
            return 0;
        }
    }

}


