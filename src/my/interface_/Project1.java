package my.interface_;

public class Project1 {


      public void run(DbInterface dbInterface){
          dbInterface.connect();
          dbInterface.close();
      }


}
