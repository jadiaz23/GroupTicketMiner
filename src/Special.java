/**
 * The type Special.
 */
class Special extends Event {

    /**
     * Instantiates a new Special.
     *
     * @param id      the id
     * @param type    the type
     * @param name    the name
     * @param date    the date
     * @param time    the time
     * @param vip     the vip
     * @param gold    the gold
     * @param silver  the silver
     * @param bronze  the bronze
     * @param general the general
     */
    public Special(String id, String type, String name, String date, String time, String vip, String gold, String silver, String bronze, String general) {
        super(id, type, name, date, time, vip, gold, silver, bronze, general);
    }
}
