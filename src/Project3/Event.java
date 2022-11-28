package Project3;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * The type Event.
 */
abstract class Event {

    /**
     * The Id.
     */
    public int ID;
    /**
     * The Type.
     */
    public String type;
    /**
     * The Name.
     */
    public String name;
    /**
     * The Date.
     */
    public String date;
    /**
     * The Time.
     */
    public String time;
    /**
     * The Vip price.
     */
    public double vipPrice;
    /**
     * The Gold price.
     */
    public double goldPrice;
    /**
     * The Silver price.
     */
    public double silverPrice;
    /**
     * The Bronze price.
     */
    public double bronzePrice;
    /**
     * The General ad price.
     */
    public double generalAdPrice;

    /**
     * The Venue.
     */
    public Venue venue;
    /**
     * The Tickets.
     */
    public LinkedHashMap<Integer, Ticket> tickets = new LinkedHashMap<Integer, Ticket>();

    /**
     * Instantiates a new Event.
     *
     * @param ID           the id
     * @param type         the type
     * @param name         the name
     * @param date         the date
     * @param time         the time
     * @param generalPrice the general price
     */
// for EventFactory
    public Event(String ID, String type, String name, String date, String time, Double generalPrice) {
        this.ID = Integer.parseInt(ID);
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.generalAdPrice = generalPrice;
    }

    /**
     * Instantiates a new Event.
     *
     * @param ID             the id
     * @param type           the type
     * @param name           the name
     * @param date           the date
     * @param time           the time
     * @param vipPrice       the vip price
     * @param goldPrice      the gold price
     * @param silverPrice    the silver price
     * @param bronzePrice    the bronze price
     * @param generalAdPrice the general ad price
     */
    public Event(String ID, String type, String name, String date, String time, String vipPrice, String goldPrice, String silverPrice, String bronzePrice, String generalAdPrice){
        this.ID = Integer.parseInt(ID);
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.vipPrice = Double.parseDouble(vipPrice);
        this.goldPrice = Double.parseDouble(goldPrice);
        this.silverPrice = Double.parseDouble(silverPrice);
        this.bronzePrice = Double.parseDouble(bronzePrice);
        this.generalAdPrice = Double.parseDouble(generalAdPrice);

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
     * Get type string.
     *
     * @return the string
     */
    public String getType(){
        return type;
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
     * Get date string.
     *
     * @return the string
     */
    public String getDate(){
        return date;
    }

    /**
     * Get time string.
     *
     * @return the string
     */
    public String getTime(){
        return time;
    }

    /**
     * Get vip price double.
     *
     * @return the double
     */
    public double getVipPrice(){
        return vipPrice;
    }

    /**
     * Get gold price double.
     *
     * @return the double
     */
    public double getGoldPrice(){
        return goldPrice;
    }

    /**
     * Get silver price double.
     *
     * @return the double
     */
    public double getSilverPrice(){
        return silverPrice;
    }

    /**
     * Get bronze price double.
     *
     * @return the double
     */
    public double getBronzePrice(){
        return bronzePrice;
    }

    /**
     * Get general ad price double.
     *
     * @return the double
     */
    public double getGeneralAdPrice(){
        return generalAdPrice;
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
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Sets vip price.
     *
     * @param vipPrice the vip price
     */
    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * Sets gold price.
     *
     * @param goldPrice the gold price
     */
    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    /**
     * Sets silver price.
     *
     * @param silverPrice the silver price
     */
    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    /**
     * Sets bronze price.
     *
     * @param bronzePrice the bronze price
     */
    public void setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

    /**
     * Sets general ad price.
     *
     * @param generalAdPrice the general ad price
     */
    public void setGeneralAdPrice(double generalAdPrice) {
        this.generalAdPrice = generalAdPrice;
    }


    /**
     * Vip revenue double.
     *
     * @return the double
     */
    public double vipRevenue() {
        return Math.floor((venue.vipSold * vipPrice) * 100) / 100;
    }

    /**
     * Gold revenue double.
     *
     * @return the double
     */
    public double goldRevenue() {
        return Math.floor((venue.goldSold * goldPrice) * 100) / 100;
    }

    /**
     * Silver revenue double.
     *
     * @return the double
     */
    public double silverRevenue() {
        return Math.floor((venue.silverSold * silverPrice) * 100) / 100;
    }

    /**
     * Bronze revenue double.
     *
     * @return the double
     */
    public double bronzeRevenue() {
        return Math.floor((venue.bronzeSold * bronzePrice) * 100) / 100;
    }

    /**
     * General revenue double.
     *
     * @return the double
     */
    public double generalRevenue() {
        return Math.floor((venue.genAdmiSold * generalAdPrice) * 100) / 100;
    }

    /**
     * Total revenue double.
     *
     * @return the double
     */
    public double totalRevenue() {
        return vipRevenue() + goldRevenue() + silverRevenue() + bronzeRevenue() + generalRevenue();
    }

    /**
     * Expected profit big decimal.
     *
     * @return the big decimal
     */
    public BigDecimal expectedProfit() {
        BigDecimal vipTotal = BigDecimal.valueOf(Math.floor((Math.round(venue.vipPercent * venue.capacity) * vipPrice) * 100) / 100);
        BigDecimal goldTotal = BigDecimal.valueOf(Math.floor((Math.round(venue.goldPercent * venue.capacity) * goldPrice) * 100) / 100);
        BigDecimal silverTotal = BigDecimal.valueOf(Math.floor((Math.round(venue.silverPercent * venue.capacity) * silverPrice) * 100) / 100);
        BigDecimal bronzeTotal = BigDecimal.valueOf(Math.floor((Math.round(venue.bronzePercent * venue.capacity) * bronzePrice) * 100) / 100);
        BigDecimal genAdmiTotal = BigDecimal.valueOf(Math.floor((Math.round(venue.generalAdPercent * venue.capacity) * generalAdPrice) * 100) / 100);

        BigDecimal total = new BigDecimal(0);
        total = total.add(vipTotal);
        total = total.add(goldTotal);
        total = total.add(silverTotal);
        total = total.add(bronzeTotal);
        total = total.add(genAdmiTotal);

        return total.subtract(new BigDecimal(venue.cost));
    }

    /**
     * Profit double.
     *
     * @return the double
     */
    public double profit() {
        return totalRevenue() - venue.cost;
    }

    /**
     * Adds to tickets sold.
     *
     * @param ticket the ticket
     */
    public void sellTicket(Ticket ticket) {
        tickets.put(ticket.confNum, ticket);
        this.venue.capacity = venue.capacity - 1;
    }

    /**
     * Print event info.
     */
    public void print(){
        System.out.println("ID: "+getID());
        System.out.println("Sport type: "+getType());
        System.out.println("Event: "+getName());
        System.out.println("Date: "+getDate());
        System.out.println("Time: "+getTime());
        System.out.println("VIP Price: "+getVipPrice());
        System.out.println("Gold Price: "+getGoldPrice());
        System.out.println("Silver Price: "+getSilverPrice());
        System.out.println("Bronze Price: "+getBronzePrice());
        System.out.println("General Admission Price: "+getGeneralAdPrice());

        if (Firework()) {
            System.out.println("This event includes fireworks");
            System.out.println();
        } else {
            System.out.println("This event does not include fireworks");
            System.out.println();
        }

    }

    /**
     * Print detailed event info.
     */
    public void detailedPrint() {
        System.out.println("ID: " + ID + "\nName: " + name + "\nDate: " + date + "\nTime: "
                + time + "\nType: " + type + "\nCapacity: " + venue.capacity + "\nTotal Seats Sold: " + venue.totalTicketsSold()
                + "\nTotal VIP Seats Sold: " + venue.vipSold + "\nTotal Gold Seats Sold: " + venue.goldSold + "\nTotal Silver Seats Sold: "
                + venue.silverSold + "\n" + " Total Bronze Seats Sold: " + venue.bronzeSold + "\n" + " Total General Adm Seats Sold: " + venue.genAdmiSold
                + "\nTotal revenue for VIP tickets: $" + vipRevenue() + "\n" + " Total revenue for Gold tickets: $" + goldRevenue() + "\n"
                + " Total revenue for Silver tickets: $" + silverRevenue() + "\nTotal revenue for Bronze tickets: $" + bronzeRevenue() + "\n"
                + " Total revenue for General Admission tickets: $" + generalRevenue() + "\n" + " Total revenue for all tickets: $" + totalRevenue()
                + "\nExpected profit (Sell Out): $" + expectedProfit() + "\n" + " Actual profit: $" + profit() + "\n");

        if (Firework()) {
            System.out.println("This event includes fireworks");
            System.out.println();
        } else {
            System.out.println("This event does not include fireworks");
            System.out.println();
        }
    }
    /**
     * This method checks the time and decides if fireworks are included or not
     * @return boolean
     */
    private boolean Firework() {
        String time = getTime();
        int temp = Integer.parseInt(String.valueOf(time.charAt(0)));
        char zone = time.charAt(time.length()-2);
        String z = String.valueOf(zone);
        String noon = "p";

        return (temp >= 7) && (z.equalsIgnoreCase(noon));
    }



    }