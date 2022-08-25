package my.genericity;

public class Generic03 {
    public static void main(String[] args){

        Carr carr = new Carr();
        carr.fly("12",1);


    }
}

@SuppressWarnings("ALL")
class PS1<T,K,V>{
    private T x;
    private K y;
    private V z;

    public PS1(T x, K y, V z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T aa(){
        return x;
    }
}

class PS2 extends PS1<String,Integer,Double>{

    public PS2(String x, Integer y, Double z) {
        super(x, y, z);
    }


    @Override
    public String aa() {
        return super.aa();
    }
}
class Fish<K,V>{

}


class Carr{

    public void run(){

    }

    public<T,F> void fly(T t,F f){
        System.out.println(t);
        System.out.println(f);
    }

}