import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class openFiles {
    public openFiles(){}

    public HashMap<String, HashMap<String, Event>> openEventList(){ //Key(typeOfEvent, key(nameOfEvent)
        HashMap<String, HashMap<String, Event>> eventType = new HashMap<>(); //Key is of type Sport, Concert, Special
        HashMap<String, Event> sport = new HashMap<>(); //Will hold Sport
        HashMap<String, Event> concert = new HashMap<>(); //hold concert
        HashMap<String, Event> special = new HashMap<>(); //hold special

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

    public HashMap<String, Customer> openCustomerList(){
        HashMap<String, Customer> customer = new HashMap<>(); //Key is username
        try {
            FileReader file = new FileReader("Customer_List_PA1.csv");
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

    public HashMap<Integer, Venue> openVenueList(){//Key is the ID of venue
        HashMap<Integer, Venue> venue = new HashMap<>();
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
                    venue.put(Integer.parseInt(ans[0]), v);
                }
                else if(ans[2].equalsIgnoreCase("Auditorium")){
                    Auditorium aud = new Auditorium(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = aud;
                    venue.put(Integer.parseInt(ans[0]), v);
                }
                else if(ans[2].equalsIgnoreCase("Open Air")){
                    OpenAir o = new OpenAir(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = o;
                    venue.put(Integer.parseInt(ans[0]), v);
                }
                else if(ans[2].equalsIgnoreCase("Stadium")){
                    Stadium s = new Stadium(ans[0], ans[1], ans[2], ans[3], ans[4], ans[5], ans[6], ans[7], ans[8], ans[9], ans[10], ans[11]);
                    v = s;
                    venue.put(Integer.parseInt(ans[0]), v);
                }

            }

        }
        catch (Exception e){
            System.out.println("Rip");
        }

        return venue;
    }

}
