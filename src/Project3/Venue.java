package Project3;

import java.util.LinkedHashMap;

/**
 * The type Venue.
 */
abstract class Venue {

    /**
     * The Id.
     */
    public int ID;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Type.
     */
    public String type;
    /**
     * The Capacity.
     */
    public int capacity;
    /**
     * The Concert capacity.
     */
    public int concertCapacity;
    /**
     * The Cost.
     */
    public double cost;
    /**
     * The Vip percent.
     */
    public int vipPercent;
    /**
     * The Gold percent.
     */
    public int goldPercent;
    /**
     * The Silver percent.
     */
    public int silverPercent;
    /**
     * The Bronze percent.
     */
    public int bronzePercent;
    /**
     * The General ad percent.
     */
    public int generalAdPercent;
    /**
     * The Reserved ex percent.
     */
    public int reservedExPercent;

    /**
     * The Vip sold.
     */
    public int vipSold;
    /**
     * The Gold sold.
     */
    public int goldSold;
    /**
     * The Silver sold.
     */
    public int silverSold;
    /**
     * The Bronze sold.
     */
    public int bronzeSold;
    /**
     * The Gen admi sold.
     */
    public int genAdmiSold;


    /**
     * Instantiates a new Venue.
     *
     * @param ID                the id
     * @param name              the name
     * @param type              the type
     * @param capacity          the capacity
     * @param concertCapacity   the concert capacity
     * @param cost              the cost
     * @param vipPercent        the vip percent
     * @param goldPercent       the gold percent
     * @param silverPercent     the silver percent
     * @param bronzePercent     the bronze percent
     * @param generalAdPercent  the general ad percent
     * @param reservedExPercent the reserved ex percent
     */
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

    /**
     * Get id int.
     *
     * @return the int
     */
    public int getID(){

        return ID;
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){

        return name;
    }

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType(){

        return type;
    }

    /**
     * Get capacity int.
     *
     * @return the int
     */
    public int getCapacity(){

        return capacity;
    }

    /**
     * Get concert capacity int.
     *
     * @return the int
     */
    public int getConcertCapacity(){

        return concertCapacity;
    }

    /**
     * Get cost double.
     *
     * @return the double
     */
    public double getCost(){

        return cost;
    }

    /**
     * Get vip percent int.
     *
     * @return the int
     */
    public int getVipPercent(){

        return vipPercent;
    }

    /**
     * Get gold percent int.
     *
     * @return the int
     */
    public int getGoldPercent(){

        return goldPercent;
    }

    /**
     * Get silver percent int.
     *
     * @return the int
     */
    public int getSilverPercent(){

        return silverPercent;
    }

    /**
     * Get bronze percent int.
     *
     * @return the int
     */
    public int getBronzePercent(){

        return bronzePercent;
    }

    /**
     * Get general ad percent int.
     *
     * @return the int
     */
    public int getGeneralAdPercent(){

        return generalAdPercent;
    }

    /**
     * Get reserved ex percent int.
     *
     * @return the int
     */
    public int getReservedExPercent(){

        return reservedExPercent;
    }

    /////Setters/////

    /**
     * Sets id.
     *
     * @param ID the id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Sets concert capacity.
     *
     * @param ConcertCapacity the concert capacity
     */
    public void setConcertCapacity(int ConcertCapacity) {
        this.concertCapacity = concertCapacity;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Sets vip percent.
     *
     * @param vipPercent the vip percent
     */
    public void setVipPercent(int vipPercent) {
        this.vipPercent = vipPercent;
    }

    /**
     * Sets gold percent.
     *
     * @param goldPercent the gold percent
     */
    public void setGoldPercent(int goldPercent) {
        this.goldPercent = goldPercent;
    }

    /**
     * Sets silver percent.
     *
     * @param silverPercent the silver percent
     */
    public void setSilverPercent(int silverPercent) {
        this.silverPercent = silverPercent;
    }

    /**
     * Sets bronze percent.
     *
     * @param bronzePercent the bronze percent
     */
    public void setBronzePercent(int bronzePercent) {
        this.bronzePercent = bronzePercent;
    }

    /**
     * Sets general ad percent.
     *
     * @param generalAdPercent the general ad percent
     */
    public void setGeneralAdPercent(int generalAdPercent) {
        this.generalAdPercent = generalAdPercent;
    }

    /**
     * Sets reserved ex percent.
     *
     * @param reservedExPercent the reserved ex percent
     */
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

    /**
     * Print.
     */
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