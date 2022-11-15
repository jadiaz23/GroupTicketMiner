/**
 * The interface Person.
 */
interface Person {

    /**
     * Login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean login(String login);

    /**
     * Gets id.
     *
     * @return the id
     */
    int getID();

    /**
     * Sets id.
     *
     * @param ID the id
     */
    void setID(int ID);

    /**
     * Gets username.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Sets username.
     *
     * @param username the username
     */
    void setUsername(String username);

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Sets password.
     *
     * @param password the password
     */
    void setPassword(String password);

}
