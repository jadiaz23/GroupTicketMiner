import java.util.LinkedHashMap;

class Customer implements Person {

    public int ID;
    public String first;
    public String last;
    private String username;
    private String password;
    private double money;
    public boolean member;
    public int tickets;

    public LinkedHashMap<Integer, Ticket> purchased = new LinkedHashMap<Integer, Ticket>();

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

    public String getFirst(){
        return first;
    }

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

    public double getMoney(){
        return money;
    }

    public boolean getMember(){
        return member;
    }

    public int getTickets(){
        return tickets;
    }

    /////Setters/////

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirst(String first) {
        this.first = first;
    }

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

    public void setMoney(double money){
        this.money = money;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    /**
     * Adds ticket to those purchased.
     *
     * @param ticket the ticket
     * @return  returns false if not purchased.
     */
    public boolean buyTicket(Ticket ticket) {
        if (money >= ticket.cost) {
            money = money - ticket.cost;
            purchased.put(ticket.confNum, ticket);
            return true;
        }
        return false;
    }

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