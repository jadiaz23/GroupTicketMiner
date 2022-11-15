import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * @author Jose Armando Diaz
 * */

public class openFiles {
    /**
     * constructor is empty
     */
    public openFiles(){}

    /**
     *
     * @return LinkedHashMap with another LinkedHashMap of events
     * Creates a LinkedHashmap with the first key being the type of Event(e.g. Sport, Concert)
     * Second key is the name of the event(e.g. UTEP Football 1)
     */
    public LinkedHashMap<String, LinkedHashMap<String, Event>> openEventList(){ //Key(typeOfEvent, key(nameOfEvent)
        LinkedHashMap<String, LinkedHashMap<String, Event>> eventType = new LinkedHashMap<>(); //Key is of type Sport, Concert, Special
        LinkedHashMap<String, Event> sport = new LinkedHashMap<>(); //Will hold Sport
        LinkedHashMap<String, Event> concert = new LinkedHashMap<>(); //hold concert
        LinkedHashMap<String, Event> special = new LinkedHashMap<>(); //hold special

        eventType.put("Sport", sport);
        eventType.put("Concert", concert);
        eventType.put("Special", special);

        try {
            FileReader file = new FileReader("Event_List_PA1.csv");
            Scanner scanning = new Scanner(file);
            String line = scanning.nextLine();

            while (line != null){
                line = scanning.nextLine();
                String[] ans = line.split(",");
                if (ans[1].equalsIgnoreCase("Sport")){
                    Event e = new Sport(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]); //e holds type of sport
                    sport.put(ans[0], e); //Key is name of event
                }
                else if (ans[1].equalsIgnoreCase("Concert")){
                    Event c = new Concert(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]); //c holds type Concert
                    concert.put(ans[0],c); //Key is name of event
                }
                else if (ans[1].equalsIgnoreCase("Special")){
                    Event s = new Special(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]);
                    special.put(ans[0],s);
                }


            }



        }
        catch (Exception e){

        }

        return eventType;
}

    /**
     *
     * @return LinkedHashMap of CustomerList
     * creates a LinkedHashMap of the customers
     */
    public LinkedHashMap<String, Customer> openCustomerList(){
        LinkedHashMap<String, Customer> customer = new LinkedHashMap<>(); //Key is username
        try {
            String customerFile;
            if (Files.exists(Path.of("New_Customer_ListPA1.csv"))) {
                customerFile = "New_Customer_ListPA1.csv";
            } else {
                customerFile = "Customer_List_PA1.csv";
            }
            FileReader file = new FileReader(customerFile);
            Scanner scanning = new Scanner(file);

            String line = scanning.nextLine();
            while (line != null){
                line = scanning.nextLine();
                String[] ans = line.split(",");
                customer.put(ans[3], new Customer(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7]));
            }

        }
        catch (Exception e){

        }

    return customer;
    }

    /**
     *
     * @return LinkedHashMap of venues
     * Creates a LInkedHashMap of venues provided
     */
    public LinkedHashMap<String, Venue> openVenueList(){//Key is the ID of venue
        LinkedHashMap<String, Venue> venue = new LinkedHashMap<>();
        Venue v;
        try{
            FileReader file = new FileReader("Venue_List_PA1.csv");
            Scanner scanning = new Scanner(file);

            String line = scanning.nextLine();
            while(line != null){
                line = scanning.nextLine();
                String[] ans = line.split(",");
                if(ans[2].equalsIgnoreCase("Arena")){
                    Arena a = new Arena(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = a;
                    venue.put(ans[0], v);
                }
                else if(ans[2].equalsIgnoreCase("Auditorium")){
                    Auditorium aud = new Auditorium(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = aud;
                    venue.put(ans[0], v);
                }
                else if(ans[2].equalsIgnoreCase("Open Air")){
                    OpenAir o = new OpenAir(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = o;
                    venue.put(ans[0], v);
                }
                else if(ans[2].equalsIgnoreCase("Stadium")){
                    Stadium s = new Stadium(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = s;
                    venue.put(ans[0], v);
                }
            }

        }
        catch (Exception e){

        }

        return venue;
    }

    /**
     *
     * @return new LinkedHashMap of events
     * Creates a nwe LinkedHashMap of events assuming there's already an existing event list
     */
    public LinkedHashMap<String, LinkedHashMap<String, Event>> openNewEventList(){//Assuming it already exists
        LinkedHashMap<String, LinkedHashMap<String, Event>> eventType = new LinkedHashMap<>(); //Key is of type Sport, Concert, Special
        LinkedHashMap<String, Event> sport = new LinkedHashMap<>(); //Will hold Sport
        LinkedHashMap<String, Event> concert = new LinkedHashMap<>(); //hold concert
        LinkedHashMap<String, Event> special = new LinkedHashMap<>(); //hold special

        eventType.put("Sport", sport);
        eventType.put("Concert", concert);
        eventType.put("Special", special);

        try {
            FileReader file = new FileReader("New_Event_List_PA1.csv");
            Scanner scanning = new Scanner(file);
            String line = scanning.nextLine();

            while (line != null){
                line = scanning.nextLine();
                String[] ans = line.split(",");
                if (ans[1].equalsIgnoreCase("Sport")){//Hardcode percents
                    Event e = new Sport(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]); //e holds type of sport
                    sport.put(ans[0], e); //Key is name of event
                    if(ans[2].contains("Football")){//20
                        sport.get(ans[0]).venue = new Stadium(ans[10],ans[11],ans[12],ans[13],ans[14],ans[15],"5","10","15","20","45","5");
                        sport.get(ans[0]).venue.vipSold = (Integer.parseInt(ans[16]));
                        sport.get(ans[0]).venue.goldSold = (Integer.parseInt(ans[17]));
                        sport.get(ans[0]).venue.silverSold = (Integer.parseInt(ans[18]));
                        sport.get(ans[0]).venue.bronzeSold = (Integer.parseInt(ans[19]));
                        sport.get(ans[0]).venue.genAdmiSold = (Integer.parseInt(ans[20]));
                    }
                    else if(ans[2].contains("Basketball")){
                        sport.get(ans[0]).venue = new Arena(ans[10],ans[11],ans[12],ans[13],ans[14],ans[15],"5","10","15","20","45","5");
                        sport.get(ans[0]).venue.vipSold = (Integer.parseInt(ans[16]));
                        sport.get(ans[0]).venue.goldSold = (Integer.parseInt(ans[17]));
                        sport.get(ans[0]).venue.silverSold = (Integer.parseInt(ans[18]));
                        sport.get(ans[0]).venue.bronzeSold = (Integer.parseInt(ans[19]));
                        sport.get(ans[0]).venue.genAdmiSold = (Integer.parseInt(ans[20]));
                    }

                }
                else if (ans[1].equalsIgnoreCase("Concert")){
                    Event c = new Concert(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]); //c holds type Concert
                    concert.put(ans[0],c); //Key is name of event
                    concert.get(ans[0]).venue = new Auditorium(ans[10],ans[11],ans[12],ans[13],ans[14],ans[15],"5","10","15","20","45","5");
                    concert.get(ans[0]).venue.vipSold = (Integer.parseInt(ans[16]));
                    concert.get(ans[0]).venue.goldSold = (Integer.parseInt(ans[17]));
                    concert.get(ans[0]).venue.silverSold = (Integer.parseInt(ans[18]));
                    concert.get(ans[0]).venue.bronzeSold = (Integer.parseInt(ans[19]));
                    concert.get(ans[0]).venue.genAdmiSold = (Integer.parseInt(ans[20]));
                }
                else if (ans[1].equalsIgnoreCase("Special")){
                    Event s = new Special(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]);
                    special.put(ans[0],s);
                    special.get(ans[0]).venue = new OpenAir(ans[10],ans[11],ans[12],ans[13],ans[14],ans[15],"5","10","15","20","45","5");
                    special.get(ans[0]).venue.vipSold = (Integer.parseInt(ans[16]));
                    special.get(ans[0]).venue.goldSold = (Integer.parseInt(ans[17]));
                    special.get(ans[0]).venue.silverSold = (Integer.parseInt(ans[18]));
                    special.get(ans[0]).venue.bronzeSold = (Integer.parseInt(ans[19]));
                    special.get(ans[0]).venue.genAdmiSold = (Integer.parseInt(ans[20]));
                }


            }


        }
        catch (Exception e){

        }

        return eventType;
    }

    /**
     *
     * @param customer stores customer info
     * @throws IOException
     * Creates and writes a csv file called "TicketSales" which holds new and
     * current tickets that have been bought
     */
    public void writingToTicketSales(LinkedHashMap<String,Customer> customer) throws IOException {
        FileWriter fw = new FileWriter("TicketSales.csv");
        try {
            fw.append("Username,Confirmation Number,Event,Venue,Date,Time,TypeOfTicket,Cost");
            fw.append("\n");

            for (Map.Entry<String, Customer> cust : customer.entrySet()) {
                Customer c = cust.getValue();
                if (c.purchased.size() >= 1) {
                    for (Map.Entry<Integer, Ticket> t : c.purchased.entrySet()) {
                        Ticket tick = t.getValue();
                        fw.append(c.getUsername()+","+tick.confNum+","+tick.event+","+tick.venue+","+tick.date+","+tick.time+","+tick.type+","+tick.cost+"\n");
                    }
                }
            }
            fw.close();
        }
        catch (Exception e){

        }

    }

    /**
     *
     * @param customerList stores list of customers
     * @param eventList
     * Reads TicketSales.csv and checks for duplicates in eventList and customerList
     */
    public void readTicketSales(LinkedHashMap<String,Customer> customerList, LinkedHashMap<String, LinkedHashMap<String, Event>> eventList){//save to hashmap in customer and event
        try{
            FileReader file = new FileReader("TicketSales.csv");
            Scanner scanning = new Scanner(file);
            String line = scanning.nextLine();

            while (line != null){
                line = scanning.nextLine();
                String[] ans = line.split(","); //0 is now username
                Ticket ticket = new Ticket(Integer.parseInt(ans[1]), ans[2],ans[3],ans[4],ans[5],ans[6],Double.parseDouble(ans[7]));
                customerList.get(ans[0]).purchased.put(Integer.parseInt(ans[1]),ticket);

                boolean exit = false;
                for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
                    HashMap<String, Event> events = list.getValue();
                    for (Map.Entry<String, Event> entry : events.entrySet()) {
                        Event event = entry.getValue();
                        if (event.name.equalsIgnoreCase(ans[2])) {
                            event.tickets.put(Integer.parseInt(ans[1]),ticket);
                            exit = true;
                            break;
                        }
                    }
                    if (exit) {
                        break;
                    }

                }

            }

        }
        catch (Exception e){}

    }

}
