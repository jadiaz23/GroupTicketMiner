package Project3;

/**
 * Creates Admin instance from Person Interface
 */
public class Admin implements Person {

    /**
     * The User type.
     */
    public String userType;

    private int ID;


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
    public String getUsername() { // always admin
        return "admin";
    }

    @Override
    public void setUsername(String username) {
    }

    @Override
    public String getPassword() { // always admin
        return "admin";
    }

    @Override
    public void setPassword(String password) {
    }
}
