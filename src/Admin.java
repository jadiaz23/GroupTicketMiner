public class Admin implements Person { //FIXME use interface Person for Customer and Admin
    public String userType;
    private int ID;
    private String username;
    private String password;


    //FIXME implement methods

    @Override
    public boolean login(String login) {
        return false;
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void setID(int ID) {

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        username = "admin";
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        password = "admin";
        this.password = password;
    }
}
