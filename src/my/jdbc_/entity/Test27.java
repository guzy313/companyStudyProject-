package my.jdbc_.entity;

public class Test27 {
    private int id;
    private String name;

    public Test27() {
    }

    public Test27(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "\t" + name;
    }
}
