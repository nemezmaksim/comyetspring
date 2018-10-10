package beans;

import org.springframework.stereotype.Component;

public class Client {

    String id;
    String fullName;

//    public Client(String i, String n) {
//        this.id = i;
//        this.fullName = n;
//    }

    public void setGreeting(String greeting){
        System.out.println(greeting);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
