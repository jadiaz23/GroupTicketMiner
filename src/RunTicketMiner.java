import java.util.HashMap;
public class RunTicketMiner {
    public static void main(String[] args) {
        openFiles f = new openFiles();

        HashMap<String, HashMap<String, Event>> typeOfEvent = f.openEventList();
        typeOfEvent.get("Sport").get("UTEP Basketball 1").print();
        System.out.println();

        HashMap<String, Customer> customer = f.openCustomerList();
        customer.get("aliciajenkins").print();
        System.out.println();

        HashMap<Integer, Venue> venue = f.openVenueList();
        venue.get(6).print();
    }
}
