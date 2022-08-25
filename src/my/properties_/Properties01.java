package my.properties_;

import org.junit.jupiter.api.Test;

import java.io.*;

public class Properties01 {
    private static final String PATH = "G:\\学习\\testPath\\";
    public static Properties01 properties = new Properties01();


    public static void main(String[] args) throws IOException{

        String userName = new Properties01().testGetInfoFromFile("dt.properties","userName");
        String password = new Properties01().testGetInfoFromFile("dt.properties","password");
        System.out.println(userName);
        System.out.println(password);
    }

    public  String testGetInfoFromFile(String fileName,String target ) throws IOException{
        //String fileName = "dt.properties";
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH + fileName)));
        String str,strFinal = null;
        while ((str = br.readLine()) != null){
            if((strFinal = returnV(str,target)) != null){
                break;
            }
        }
        br.close();
        return strFinal;
    }
    public String returnV(String string,String target){
        if(string.indexOf(target) != -1){
           return string.substring(string.indexOf("=")+1,string.length());
        }else{
            return null;
        }
    }

}
