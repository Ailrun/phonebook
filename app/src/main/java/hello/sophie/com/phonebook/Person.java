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
    //>>getView()를 오버라이드해서 사용
    //http://stackoverflow.com/questions/2265661/how-to-use-arrayadaptermyclass
}
