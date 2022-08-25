package my.interface_;

public class DB2 implements DbInterface{
    @Override
    public void connect() {
        System.out.println(getClass()+" connect...");
    }

    @Override
    public void close() {
        System.out.println(getClass()+" close...");
    }
}
