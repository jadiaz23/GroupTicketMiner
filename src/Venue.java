import java.util.LinkedHashMap;

abstract class Venue {

    public int ID;
    public String name;
    public String type;
    public int capacity;
    public int concertCapacity;
    public double cost;
    public int vipPercent;
    public int goldPercent;
    public int silverPercent;
    public int bronzePercent;
    public int generalAdPercent;
    public int reservedExPercent;

    public int vipSold;
    public int goldSold;
    public int silverSold;
    public int bronzeSold;
    public int genAdmiSold;


    public Venue(String ID, String name, String type, String capacity, String concertCapacity, String cost, String vipPercent, String goldPercent, String silverPercent, String bronzePercent, String generalAdPercent, String reservedExPercent){
        this.ID = Integer.parseInt(ID);
        this.name = name;
        this.type = type;
        this.capacity = Integer.parseInt(capacity);
        this.concertCapacity = Integer.parseInt(concertCapacity);
        this.cost = Double.parseDouble(cost);
        this.vipPercent = Integer.parseInt(vipPercent);
        this.goldPercent = Integer.parseInt(goldPercent);
        this.silverPercent = Integer.parseInt(silverPercent);
        this.bronzePercent = Integer.parseInt(bronzePercent);
        this.generalAdPercent = Integer.parseInt(generalAdPercent);
        this.reservedExPercent = Integer.parseInt(reservedExPercent);
    }

    /////Getters/////

    public int getID(){

        return ID;
    }

    public String getName(){

        return name;
    }

    public String getType(){

        return type;
    }

    public int getCapacity(){

        return capacity;
    }

    public int getConcertCapacity(){

        return concertCapacity;
    }

    public double getCost(){

        return cost;
    }

    public int getVipPercent(){

        return vipPercent;
    }

    public int getGoldPercent(){

        return goldPercent;
    }

    public int getSilverPercent(){

        return silverPercent;
    }

    public int getBronzePercent(){

        return bronzePercent;
    }

    public int getGeneralAdPercent(){

        return generalAdPercent;
    }

    public int getReservedExPercent(){

        return reservedExPercent;
    }

    /////Setters/////

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setConcertCapacity(int ConcertCapacity) {
        this.concertCapacity = concertCapacity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setVipPercent(int vipPercent) {
        this.vipPercent = vipPercent;
    }

    public void setGoldPercent(int goldPercent) {
        this.goldPercent = goldPercent;
    }

    public void setSilverPercent(int silverPercent) {
        this.silverPercent = silverPercent;
    }

    public void setBronzePercent(int bronzePercent) {
        this.bronzePercent = bronzePercent;
    }

    public void setGeneralAdPercent(int generalAdPercent) {
        this.generalAdPercent = generalAdPercent;
    }

    public void setReservedExPercent(int reservedExPercent) {
        this.reservedExPercent = reservedExPercent;
    }


    /**
     * Buy vip boolean.
     *
     * @return the boolean
     */
    public boolean BuyVip() {
        if (vipSold < Math.round(vipPercent * capacity)) {
            ++vipSold;
            return true;
        }
        return false;
    }

    /**
     * Buy gold boolean.
     *
     * @return the boolean
     */
    public boolean BuyGold() {
        if (goldSold < Math.round(goldPercent * capacity)) {
            ++goldSold;
            return true;
        }
        return false;
    }

    /**
     * Buy silver boolean.
     *
     * @return the boolean
     */
    public boolean BuySilver() {
        if (silverSold < Math.round(silverPercent * capacity)) {
            ++silverSold;
            return true;
        }
        return false;
    }

    /**
     * Buy bronze boolean.
     *
     * @return the boolean
     */
    public boolean BuyBronze() {
        if (bronzeSold < Math.round(bronzePercent * capacity)) {
            ++bronzeSold;
            return true;
        }
        return false;
    }

    /**
     * Buy gen admi boolean.
     *
     * @return the boolean
     */
    public boolean BuyGenAdmi() {
        if (genAdmiSold < Math.round(generalAdPercent * capacity)) {
            ++genAdmiSold;
            return true;
        }
        return false;
    }

    /**
     * Total tickets sold int.
     *
     * @return the int
     */
    public int totalTicketsSold(){
        return vipSold + goldSold + silverSold + bronzeSold + genAdmiSold ;
    }

    public void print(){
        System.out.println("ID: "+ getID());
        System.out.println("Name of building: "+ getName());
        System.out.println("Type of building: "+getType());
        System.out.println("Building capacity: "+ getCapacity());
        System.out.println("Concert capacity: "+ getConcertCapacity());
        System.out.println("Cost: "+ getCost());
        System.out.println("VIP percent: "+ getVipPercent());
        System.out.println("Gold percent: "+ getGoldPercent());
        System.out.println("Silver percent: "+ getSilverPercent());
        System.out.println("Bronze percent: "+ getBronzePercent());
        System.out.println("General Admission Percent: "+getGeneralAdPercent());
        System.out.println("Reserved Extra Percent: " +getReservedExPercent());
    }

}