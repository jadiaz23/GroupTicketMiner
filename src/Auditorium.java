/**
 * The type Auditorium.
 */
class Auditorium extends Venue {

    /**
     * Instantiates a new Auditorium.
     *
     * @param ID         the id
     * @param name       the name
     * @param type       the type
     * @param capacity   the capacity
     * @param concertCap the concert cap
     * @param cost       the cost
     * @param vipPer     the vip per
     * @param goldPer    the gold per
     * @param silverPer  the silver per
     * @param bronzePer  the bronze per
     * @param genAdmi    the gen admi
     * @param extra      the extra
     */
    public Auditorium(String ID, String name, String type, String capacity, String concertCap, String cost, String vipPer, String goldPer, String silverPer, String bronzePer, String genAdmi, String extra) {
        super(ID, name, type, capacity, concertCap, cost, vipPer, goldPer, silverPer, bronzePer, genAdmi, extra);
    }
}