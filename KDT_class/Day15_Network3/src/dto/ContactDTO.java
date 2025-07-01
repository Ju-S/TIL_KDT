package dto;

public class ContactDTO {
    private String phno;

    public ContactDTO(){}
    public ContactDTO(String phno) {
        this.phno = phno;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
