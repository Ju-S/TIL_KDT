package dto;

public class UserDTO {
    private String password;

    public UserDTO() {}
    public UserDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
