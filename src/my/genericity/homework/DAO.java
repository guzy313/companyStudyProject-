package my.genericity.homework;

import org.junit.jupiter.api.Test;

import java.util.*;

@SuppressWarnings({"all"})
public class DAO<T> {
    private Map<String,T> map = new HashMap<>();

    public void save(String id,T entity){
        this.map.put(id,entity);
    }

    public T get(String id){
        /*for (Map.Entry<String,T> o:map.entrySet()) {
            if(id.equals(o.getKey()) ){
                return o.getValue();
            }
        }*/

        return map.get(id);
    }

    public void update(String id,T entity){
        this.map.put(id,entity);
    }

    public List<T> list(){
        List<T> x = new ArrayList<>();
        for (String o:this.map.keySet()) {
            x.add(map.get(o));
            //x.add(get(o));
        }
        return x;
    }

    public void delete(String id){
        map.remove(id);
    }

    public Map<String, T> getMap() {
        return map;
    }

    @Test
    public void test(){
        DAO<User> stringUserDAO = new DAO<>();
        for (int i = 0; i < 10; i++) {
            stringUserDAO.save(i+"",new User(i,i+i,"测试"+i));
        }
        System.out.println(stringUserDAO.list());
        stringUserDAO.delete("3");
        System.out.println("after delete "+stringUserDAO.list());

        System.out.println(stringUserDAO.get("0"));

        System.out.println(stringUserDAO.getMap());

    }

}
