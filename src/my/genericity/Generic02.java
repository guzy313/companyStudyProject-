package my.genericity;

public class Generic02 {
    public static void main(String[] args){
        PersonX<Integer> integerPersonX = new PersonX<>(1);
        integerPersonX.setS(12);
        integerPersonX.xx(4);

    }
}

class PersonX<E>{
    E s;

    public PersonX(E s) {
        this.s = s;
    }

    public E getS() {
        return s;
    }

    public void setS(E s) {
        this.s = s;
    }

    public void xx(E x){
        System.out.println(x);
    }
}
