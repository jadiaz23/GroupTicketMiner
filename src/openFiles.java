import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class openFiles {
    public openFiles(){}

    public LinkedHashMap<String, LinkedHashMap<String, Event>> openEventList(){ //Key(typeOfEvent, key(nameOfEvent)
        LinkedHashMap<String, LinkedHashMap<String, Event>> eventType = new LinkedHashMap<>(); //Key is of type Sport, Concert, Special
        LinkedHashMap<String, Event> sport = new LinkedHashMap<>(); //Will hold Sport
        LinkedHashMap<String, Event> concert = new LinkedHashMap<>(); //hold concert
        LinkedHashMap<String, Event> special = new LinkedHashMap<>(); //hold special

        try {
            FileReader file = new FileReader("Event_List_PA1.csv");
            Scanner scanning = new Scanner(file);
            String line = scanning.nextLine();

            while (line != null){
                line = scanning.nextLine();
                String[] ans = line.split(",");
                if (ans[1].equalsIgnoreCase("Sport")){
                    Sport typeSport = new Sport(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]);
                    Event e = typeSport; //e holds type of sport
                    sport.put(ans[2], e); //Key is name of event
                    eventType.put("Sport", sport);
                }
                else if (ans[1].equalsIgnoreCase("Concert")){
                    Concert typeConcert = new Concert(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]);
                    Event c = typeConcert; //c holds type Concert
                    concert.put(ans[2],c); //Key is name of event
                    eventType.put("Concert", concert);
                }
                else if (ans[1].equalsIgnoreCase("Special")){
                    Special typeSpecial = new Special(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9]);
                    Event s = typeSpecial;
                    special.put(ans[2],s);
                    eventType.put("Special", special);
                }

            }



        }
        catch (Exception e){
            System.out.println("Rip");
        }

        return eventType;
}

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
            System.out.println("Rip");
        }

    return customer;
    }

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
            System.out.println("Rip");
        }

        return venue;
    }

}
