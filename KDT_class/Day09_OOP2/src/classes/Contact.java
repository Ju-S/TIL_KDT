package classes;

public class Contact {
    String name;
    String phNo;

    public Contact(String name, String phNo) {
        this.name = name;
        this.phNo = phNo;
    }

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
