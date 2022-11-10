/**
 * The type Ticket.
 */
class Ticket {
    /**
     * The Conf num.
     */
    public int confNum;
    /**
     * The Event.
     */
    public String event;
    /**
     * The Venue.
     */
    public String venue;
    /**
     * The Date.
     */
    public String date;
    /**
     * The Time.
     */
    public String time;
    /**
     * The Type.
     */
    public String type;
    /**
     * The Cost.
     */
    public Double cost;

    /**
     * Instantiates a new Ticket.
     *
     * @param confNum the conf num
     * @param event   the event
     * @param venue   the venue
     * @param date    the date
     * @param time    the time
     * @param type    the type
     * @param cost    the cost
     */
    public Ticket(int confNum, String event, String venue, String date, String time, String type, Double cost) {
        this.confNum = confNum;
        this.event = event;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.type = type;
        this.cost = cost;

    }
}
