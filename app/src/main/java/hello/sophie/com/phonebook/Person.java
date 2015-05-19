package hello.sophie.com.phonebook;

/**
 * Created by SophiesMac on 15. 5. 19..
 */
public class Person {
    private String name;
    private String phone;

    public Person(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString(){
        return name;
    }
    //todo 임기응변같은데.. 해결책은?
}
