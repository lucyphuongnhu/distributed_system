import java.util.List;

public class Customer {
    private int id;
    private String name;
    private int age;
    private List <Account> list;

    public Customer (int id, String name, int age, List<Account> list){
        this.id = id;
        this.name = name;
        this.age = age;
        this.list = list;
    }
}
