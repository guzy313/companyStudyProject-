package my.String_;


public class String01 {
    public static void main(String[] args){
        /*String name = "name";
        String nm = new String("name");
        System.out.println(name == nm.intern());*/
        Stringbufferx sbfx = new Stringbufferx();
        sbfx.change(sbfx.name,sbfx.names,sbfx.intx);
        System.out.println(sbfx.name);
        System.out.println((sbfx.names)[0]);
        System.out.println((sbfx.intx)[0]);
    }
}


class Stringbufferx{
    String name = new String("hh");
    String[] names = {"tx","wy"};
    int[] intx = {1,2};

    public void change(String name,String[] names,int[] intx){
        name = "hhh";
        names[0] = "mhy";
        intx[0] = 3;
    }
}
