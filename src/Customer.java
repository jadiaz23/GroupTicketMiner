import java.util.LinkedHashMap;

/**
 * Creates class Customer.
 */
class Customer implements Person {

    /**
     * The Id.
     */
    public int ID;
    /**
     * The First.
     */
    public String first;
    /**
     * The Last.
     */
    public String last;
    private String username;
    private String password;
    private double money;
    /**
     * The Member.
     */
    public boolean member;
    /**
     * The Tickets.
     */
    public int tickets;

    /**
     * The Purchased.
     */
    public LinkedHashMap<Integer, Ticket> purchased = new LinkedHashMap<Integer, Ticket>(); //FIXME might not need, Need to implement Ticket summary

    /**
     * Instantiates a new Customer.
     *
     * @param ID       the id
     * @param first    the first
     * @param last     the last
     * @param username the username
     * @param password the password
     * @param money    the money
     * @param member   the member
     * @param tickets  the tickets
     */
    public Customer(String ID, String first, String last, String username, String password, String money, String member, String tickets) {
        this.ID = Integer.parseInt(ID);
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.money = Double.parseDouble(money);
        this.member = Boolean.parseBoolean(member);
        this.tickets = Integer.parseInt(tickets);
    }

    /////Getters/////

    @Override
    public boolean login(String login) {
        return false;
    }

    @Override
    public int getID(){
        return ID;
    }

    /**
     * Get first string.
     *
     * @return the string
     */
    public String getFirst(){
        return first;
    }

    /**
     * Get last string.
     *
     * @return the string
     */
    public String getLast(){
        return last;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    /**
     * Get money double.
     *
     * @return the double
     */
    public double getMoney(){
        return money;
    }

    /**
     * Get member boolean.
     *
     * @return the boolean
     */
    public boolean getMember(){
        return member;
    }

    /**
     * Get tickets int.
     *
     * @return the int
     */
    public int getTickets(){
        return tickets;
    }

    /////Setters/////

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets first.
     *
     * @param first the first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Sets last.
     *
     * @param last the last
     */
    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Set money.
     *
     * @param money the money
     */
    public void setMoney(double money){
        this.money = money;
    }

    /**
     * Sets member.
     *
     * @param member the member
     */
    public void setMember(boolean member) {
        this.member = member;
    }

    /**
     * Sets tickets.
     *
     * @param tickets the tickets
     */
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    /**
     * Adds ticket to those purchased.
     *
     * @param ticket the ticket
     * @return returns false if not purchased.
     */
    public boolean buyTicket(Ticket ticket) {
        if (money >= ticket.cost) {
            money = money - ticket.cost;
            purchased.put(ticket.confNum, ticket);
            return true;
        }
        return false;
    }

    /**
     * Print.
     */
    public void print(){
        System.out.println("ID: "+getID());
        System.out.println("Name: "+getFirst()+" "+getLast());
        System.out.println("Username: "+getUsername());
        System.out.println("Password: "+getPassword());
        System.out.println("Money available: "+getMoney());
        System.out.println("Ticket Membership: "+getMember());
        System.out.println("Purchased: "+getTickets());
    }

}