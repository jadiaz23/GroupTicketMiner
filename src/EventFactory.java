/**
 * For Admin use
 */
public class EventFactory {

    /**
     * Create event.
     *
     * @param id       the id
     * @param type     the type
     * @param name     the name
     * @param date     the date
     * @param time     the time
     * @param genPrice the gen price
     * @param venue    the venue
     * @return the event
     */
// get input from admin and create event
    // venue should already be initialized
    public Event createEvent(String id, String type, String name, String date, String time, Double genPrice, Venue venue) {
        Event event;
        if (type.equalsIgnoreCase("sport")) {
            event = new Sport(id, type, name, date, time, genPrice);
        } else if (type.equalsIgnoreCase("concert")) {
            event = new Concert(id, type, name, date, time, genPrice);
        } else if (type.equalsIgnoreCase("special")) {
            event = new Special(id, type, name, date, time, genPrice);
        } else {
            return null;
        }
        event.venue = venue;
        prices(event, genPrice);
        return event;
    }

    // vip: 5 x gen, gold: 3 x gen, silver: 2.5 x gen, bronze: 1.5 x gen
    private void prices(Event event, Double genPrice) {
        event.vipPrice = Math.floor((5 * genPrice) * 100) / 100;
        event.goldPrice = Math.floor((3 * genPrice) * 100) / 100;
        event.silverPrice = Math.floor((2.5 * genPrice) * 100) / 100;
        event.bronzePrice = Math.floor((1.5 * genPrice) * 100) / 100;
    }
}
