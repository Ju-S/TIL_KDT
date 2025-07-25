package client.session;

public class LoginInfo {
    private String id;
    private String name;
    private boolean loginStatus;

    public LoginInfo() {
    }

    public LoginInfo(String id, String name, boolean loginStatus) {
        this.id = id;
        this.name = name;
        this.loginStatus = loginStatus;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}
