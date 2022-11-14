import java.math.BigDecimal;
import java.util.LinkedHashMap;

abstract class Event {

    public int ID;
    public String type;
    public String name;
    public String date;
    public String time;
    public double vipPrice;
    public double goldPrice;
    public double silverPrice;
    public double bronzePrice;
    public double generalAdPrice;

    public Venue venue;
    public LinkedHashMap<Integer, Ticket> tickets = new LinkedHashMap<Integer, Ticket>();


    // for EventFactory
    public Event(String ID, String type, String name, String date, String time, Double generalPrice) {
        this.ID = Integer.parseInt(ID);
        this.type = type;
        this.name = name;
        this.date = date;
        this.time = time;
        this.generalAdPrice = generalPrice;
    }

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

    public int getID(){
        return ID;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public double getVipPrice(){
        return vipPrice;
    }

    public double getGoldPrice(){
        return goldPrice;
    }

    public double getSilverPrice(){
        return silverPrice;
    }

    public double getBronzePrice(){
        return bronzePrice;
    }

    public double getGeneralAdPrice(){
        return generalAdPrice;
    }

    /////Setters/////

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public void setGoldPrice(double goldPrice) {
        this.goldPrice = goldPrice;
    }

    public void setSilverPrice(double silverPrice) {
        this.silverPrice = silverPrice;
    }

    public void setBronzePrice(double bronzePrice) {
        this.bronzePrice = bronzePrice;
    }

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
        venue.capacity = venue.capacity - 1;
        if (ticket.type.equalsIgnoreCase("vip")){
            venue.vipSold = venue.vipSold + 1;
        } else if ((ticket.type.equalsIgnoreCase("gold"))) {
            venue.goldSold = venue.goldSold + 1;
        } else if ((ticket.type.equalsIgnoreCase("silver"))) {
            venue.silverSold = venue.silverSold + 1;
        } else if ((ticket.type.equalsIgnoreCase("bronze"))) {
            venue.bronzeSold = venue.bronzeSold + 1;
        } else if ((ticket.type.equalsIgnoreCase("general"))) {
            venue.genAdmiSold = venue.genAdmiSold + 1;
        }
    }

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

    }

}