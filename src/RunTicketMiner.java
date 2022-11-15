import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Jose Armando Diaz, James Robinson, Bryan Mendoza, Alejandro Yanez
 * November 6, 2022
 * CS 3331 - Advanced Object-Oriented Programming - Fall 2022
 * Dr. Bhanukiran Gurijala
 * Programming Assignment 2
 * <p>
 * This work was done individually and completely on my own. I did not share, reproduce,
 * or alter any part of this assignment for any purpose. I did not share code, upload this assignment
 * online in any form, or view/received/modified code written from anyone else. All deliverables were
 * produced entirely on my own. This assignment is part of an academic course at The University of Texas
 * at El Paso and a grade will be assigned for the work I produced.
 */
public class RunTicketMiner {
    private static LinkedHashMap<String, Customer> customerList = new LinkedHashMap<>();
    private static LinkedHashMap<String, LinkedHashMap<String, Event>> eventList = new LinkedHashMap<>();
    private static LinkedHashMap<String, Venue> venueList = new LinkedHashMap<>();

    private static final ArrayList<String> transLog = new ArrayList<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        openFiles f = new openFiles();

        eventList = f.openEventList();
        //typeOfEvent.get("Sport").get("UTEP Football 1").print();
        System.out.println();

        customerList = f.openCustomerList();
        // customer.get("aliciajenkins").print();
        System.out.println();

        venueList = f.openVenueList();
        // venue.get(6).print();

        if (!Files.exists(Path.of("New_Event_List_PA1.csv"))) {
            setVenues(); // sets a venue to the events
        }else{
            eventList = f.openNewEventList();
        }

        // Start UI

        boolean reenter = true;
        do {
            do {
                Person user = loginCheck();

                if (user == null) {
                    reenter = false;
                    break;
                } else if (Objects.equals(user.getUsername(), "admin") && Objects.equals(user.getPassword(), "admin")) { // if it's an Admin object
                    admin();
                    break;
                }
                Customer customer = (Customer) user;
                do {
                    //FIXME view balance
                    System.out.println("Welcome, " + customer.first + " " + customer.last + ", would you like to find an event by name or ID? Choose Back to return to the previous selection.");
                    System.out.println();
                    System.out.println("A. Name");
                    System.out.println("B. ID");
                    System.out.println("C. Back");
                    String letter = input.nextLine();
                    if (letter.equalsIgnoreCase("A")) {
                        Event event = findWithName(input);
                        if (transaction(input, customer, event)) break;

                    } else if (letter.equalsIgnoreCase("B")) {
                        Event event = findWithID(input);
                        if (transaction(input, customer, event)) break;

                    } else if (letter.equalsIgnoreCase("C")) {
                        break;
                    } else {
                        System.out.println("Input not recognized.");
                    }
                } while (true);
            } while (true);
        } while (reenter);

        writeFile();

    }

    public static void admin(){
        EventFactory eventFactory= new EventFactory();
        Scanner sc = new Scanner(System.in);
        String adminInput = "";
        while (!adminInput.equalsIgnoreCase("exit")) {
            System.out.println("================================================================");
            System.out.println("Hello Administrator! How would you like to inquire an event: ");
            System.out.println("\n\tA. Inquire via Event Name and Type of Event\n\tB. Create new event\n\tType \"exit\" to exit admin menu");
            adminInput = sc.nextLine();
            String adminEventInput;
            switch(adminInput){
                case "a":
                case "A":
                    System.out.println("Enter Event Name (Case Sensitive)");
                    adminEventInput = sc.nextLine();
                    System.out.println("Enter Type of Event (Case Sensitive)");
                    String event_Type=sc.nextLine();
                    if (eventList.containsKey(event_Type) && eventList.get(event_Type).containsKey(adminEventInput)){
                        eventList.get(event_Type).get(adminEventInput).print();
                        System.out.println();
                    }
                    else{
                        System.out.println("Please try again!");
                    }
                    break;
                case "b":
                case"B":
                    HashMap<String, String> eventInfo = new HashMap<>();
                    do {

                        //Ask for user information
                        System.out.println("Please enter required information.");

                        System.out.println("Please select type of event ");
                        System.out.println("A. Sport");
                        System.out.println("B. Concert");
                        System.out.println("C. Special");  //createEvent(String id, String type, String name, String date, String time, Double genPrice, Venue venue)
                        adminInput = sc.nextLine();

                        if (adminInput.equalsIgnoreCase("a")) {
                            eventInfo.put("type", "Sport");
                            break;
                        } else if (adminInput.equalsIgnoreCase("b")) {
                            eventInfo.put("type", "Concert");
                            break;
                        } else if (adminInput.equalsIgnoreCase("c")) {
                            eventInfo.put("type", "Special");
                        } else {
                            System.out.println("Input not recognized.");
                        }
                    } while(true);

                    System.out.println("Enter Name of Event ");
                    String eventName= sc.nextLine();

                    do {
                        System.out.println("Enter date(MM/DD/YYYY)");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                        String eventDate = sc.nextLine();

                        try {
                            dateFormat.parse(eventDate);
                            eventInfo.put("date", eventDate);
                            break;

                        } catch (ParseException e) {
                            System.out.println("Wrong Format");
                        }
                    } while(true);

                    do {
                        System.out.println("Enter Time (XX:XX)");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                        String eventTime = sc.nextLine();
                        System.out.println("AM or PM?");
                        String amOrpm = sc.nextLine();

                        if (!amOrpm.equalsIgnoreCase("am") && !amOrpm.equalsIgnoreCase("pm")) {
                            System.out.println("Input not recognized.");
                        }
                        else {
                            try {
                                dateFormat.parse(eventTime);
                                eventInfo.put("time", eventTime + " " + amOrpm);
                                break;

                            } catch (ParseException e) {
                                System.out.println("Wrong Format");
                            }
                        }
                    }while (true);

                    //FiXME left off here
                    String venueInfo="";
                    Venue venue;
                    System.out.println("Please enter General Admission Price. (below $500)");
                    Double genAdmPrice =sc.nextDouble();

                    double ID=Math.round(Math.random()*eventInfo.size());
                    String strID=ID+""; //typecasting the Double to meet parameter to create Event

                    while(true) {
                        System.out.println("Which Venue");
                        venueInfo = sc.nextLine();
                        if(venueInfo.equalsIgnoreCase("OpenAir") ){
                           // OpenAir(String ID, String name, String type, String capacity, String concertCap, String cost, String vipPer, String goldPer, String silverPer, String bronzePer, String genAdmi, String extra)
                            venue= new OpenAir(strID, eventName, eventInfo.get("type"), "5000", "5000","150000", "5","10", "15", "20", "45", "5");
                        break;
                        }
                        if(venueInfo.equalsIgnoreCase("Arena") ){
                            // OpenAir(String ID, String name, String type, String capacity, String concertCap, String cost, String vipPer, String goldPer, String silverPer, String bronzePer, String genAdmi, String extra)
                            venue= new Arena(strID, eventName, eventInfo.get("type"), "5000", "5000","150000", "5","10", "15", "20", "45", "5");
                            break;
                        }
                        if(venueInfo.equalsIgnoreCase("Auditorium") ){
                            // OpenAir(String ID, String name, String type, String capacity, String concertCap, String cost, String vipPer, String goldPer, String silverPer, String bronzePer, String genAdmi, String extra)
                            venue= new Auditorium(strID, eventName, eventInfo.get("type"), "5000", "5000","150000", "5","10", "15", "20", "45", "5");
                            break;
                        }
                        if(venueInfo.equalsIgnoreCase("Stadium") ){
                            // Stadium(String ID, String name, String type, String capacity, String concertCap, String cost, String vipPer, String goldPer, String silverPer, String bronzePer, String genAdmi, String extra)
                            venue= new Stadium(strID, eventName, eventInfo.get("type"), "5000", "5000","150000", "5","10", "15", "20", "45", "5");
                            break;
                        }
                    }


                    // construct event
                    //createEvent(String id, String type, String name, String date, String time, Double genPrice, Venue venue)
                    eventFactory.createEvent(strID, eventInfo.get("type"),eventName, eventInfo.get("date"), eventInfo.get("time"),genAdmPrice, venue);
                    break;
                default:
                    if (adminInput.equalsIgnoreCase("exit")){
                        System.out.println("Exiting admin menu...");
                        break;

                    }
            }
        }
    }//end of admin

    public static Person loginCheck(){
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Hello\n*****Welcome to Ticker Miner*******");
            System.out.println("Please enter your username and password. \nNote: This is CASE SENSITIVE (enter exit for both to leave)");
            System.out.println("Username: ");
            String userInputUsername = scan.nextLine();
            System.out.println("Password: ");
            String userInputPassword = scan.nextLine();
            //check if username and password are true
            if((userInputPassword.toLowerCase()).equals("admin") || (userInputUsername.toLowerCase()).equals("admin")){
                return new Admin();
            }
            else if(customerList.containsKey(userInputUsername) && customerList.get(userInputUsername).getPassword().equals(userInputPassword)){
                return customerList.get(userInputUsername);
            }
            else if((userInputPassword.toLowerCase()).equals("exit") || (userInputUsername.toLowerCase()).equals("exit")) {
                System.out.println("Exiting System!");
                break;
            }
            System.out.println("\n*****Information not found please try again******\n");
        }
        return null;
    }

    /**
     * Confirms transaction.
     *
     * @param input the scanner
     * @param user the customer
     * @param event the event
     * @return returns false if transaction was not completed
     */
    private static boolean transaction(Scanner input, Customer user, Event event) {
        if (event == null) return true;
        Ticket ticket = purchase(input, event);
        if (ticket == null) return true;
        if (!user.buyTicket(ticket)) {
            System.out.println("Not enough funds.");
            return true;
        }
        System.out.println("Purchase Confirmation Number: " + ticket.confNum); //FIXME verify it works here
        System.out.println();

        event.sellTicket(ticket);
        transLog(user, ticket);
        return false;
    }

    /**
     * Purchase a ticket.
     *
     * @param input the scanner
     * @param event the event
     * @return returns the ticket details
     */
    private static Ticket purchase(Scanner input, Event event) {
        do {
            System.out.println("Would you like to confirm ticket purchase? Enter Yes or No.");
            System.out.println();
            String confirm = input.nextLine();
            if (confirm.equalsIgnoreCase("yes")) {
                String type = "";
                double typeCost = 0;
                do {
                    System.out.println("Select ticket type");
                    System.out.println();
                    System.out.println("A. VIP: $" + event.vipPrice);
                    System.out.println("B. Gold: $" + event.goldPrice);
                    System.out.println("C. Silver: $" + event.silverPrice);
                    System.out.println("D. Bronze: $" + event.bronzePrice);
                    System.out.println("E. General Admission: $" + event.generalAdPrice);
                    type = input.nextLine();


                    if (type.equalsIgnoreCase("a")) {
                        type = "vip";
                        if (event.venue.BuyVip()){
                            break;
                        }
                        System.out.println("Sold Out");
                    } else if (type.equalsIgnoreCase("b")) {
                        type = "gold";
                        if (event.venue.BuyGold()){
                            break;
                        }
                        System.out.println("Sold Out");
                    } else if (type.equalsIgnoreCase("c")) {
                        type = "silver";
                        if (event.venue.BuySilver()){
                            break;
                        }
                        System.out.println("Sold Out");
                    } else if (type.equalsIgnoreCase("d")) {
                        type = "bronze";
                        if (event.venue.BuyBronze()){
                            break;
                        }
                        System.out.println("Sold Out");
                    } else if (type.equalsIgnoreCase("e")) {
                        type = "general";
                        if (event.venue.BuyGenAdmi()){
                            break;
                        }
                        System.out.println("Sold Out");
                    } else {
                        System.out.println("Input not recognized.");
                    }
                } while (true);

                Random rand = new Random();
                int confNum;
                do {
                    confNum = rand.nextInt(100000);
                } while (event.tickets.containsKey(confNum));

                return new Ticket(confNum, event.name, event.venue.name, event.date, event.time, type, typeCost);
            } else if (confirm.equalsIgnoreCase("no")) {
                System.out.println("Purchase Cancelled.");
                System.out.println();
                return null;
            } else {
                System.out.println("Input not recognized.");
                System.out.println();
            }
        } while (true);
    }

    /**
     * Sets the venues to the events.
     *
     */
    private static void setVenues() {
        for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
            HashMap<String, Event> events = list.getValue();
            for (Map.Entry<String, Event> entry : events.entrySet()) {                                                                          // Venue ID numbers
                Event event = entry.getValue();                                                                                                 // 1. Don Haskins Center
                if (event.type.equalsIgnoreCase("sport")) {                                                                          // 2. Sun Bowl Stadium
                    if (event.name.toLowerCase().contains("basketball")) {                                                                      // 3. Magoffin Auditorium
                        event.venue = new Arena(Integer.toString(venueList.get("1").ID), venueList.get("1").name , venueList.get("1").type,     // 4. San Jacinto Plaza
                                Integer.toString(venueList.get("1").capacity), Integer.toString(venueList.get("1").concertCapacity),            // 5. El Paso County Coliseum
                                Double.toString(venueList.get("1").cost), Integer.toString(venueList.get("1").vipPercent),                      // 6. Centenial Plaza
                                Integer.toString(venueList.get("1").goldPercent), Integer.toString(venueList.get("1").silverPercent),
                                Integer.toString(venueList.get("1").bronzePercent), Integer.toString(venueList.get("1").generalAdPercent),
                                Integer.toString(venueList.get("1").reservedExPercent));
                    } else if (event.name.toLowerCase().contains("football")) {
                        event.venue = new Stadium(Integer.toString(venueList.get("2").ID), venueList.get("2").name , venueList.get("2").type,
                                Integer.toString(venueList.get("2").capacity), Integer.toString(venueList.get("2").concertCapacity),
                                Double.toString(venueList.get("2").cost), Integer.toString(venueList.get("2").vipPercent),
                                Integer.toString(venueList.get("2").goldPercent), Integer.toString(venueList.get("2").silverPercent),
                                Integer.toString(venueList.get("2").bronzePercent), Integer.toString(venueList.get("2").generalAdPercent),
                                Integer.toString(venueList.get("2").reservedExPercent));
                    }
                } else if (event.type.equalsIgnoreCase("concert")) {
                    event.venue = new Auditorium(Integer.toString(venueList.get("3").ID), venueList.get("3").name , venueList.get("3").type,
                            Integer.toString(venueList.get("3").capacity), Integer.toString(venueList.get("3").concertCapacity),
                            Double.toString(venueList.get("3").cost), Integer.toString(venueList.get("3").vipPercent),
                            Integer.toString(venueList.get("3").goldPercent), Integer.toString(venueList.get("3").silverPercent),
                            Integer.toString(venueList.get("3").bronzePercent), Integer.toString(venueList.get("3").generalAdPercent),
                            Integer.toString(venueList.get("3").reservedExPercent));
                } else { // Special
                    event.venue = new OpenAir(Integer.toString(venueList.get("4").ID), venueList.get("4").name , venueList.get("4").type,
                            Integer.toString(venueList.get("4").capacity), Integer.toString(venueList.get("4").concertCapacity),
                            Double.toString(venueList.get("4").cost), Integer.toString(venueList.get("4").vipPercent),
                            Integer.toString(venueList.get("4").goldPercent), Integer.toString(venueList.get("4").silverPercent),
                            Integer.toString(venueList.get("4").bronzePercent), Integer.toString(venueList.get("4").generalAdPercent),
                            Integer.toString(venueList.get("4").reservedExPercent));
                }
            }
        }
    }

    /**
     * Find event with ID.
     *
     * @param input the scanner
     * @return returns the event if found
     */
    private static Event findWithID(Scanner input) {
        do {
            System.out.println("Enter the event ID, or \"back\" to go to the previous menu.");
            System.out.println();
            String ID = input.nextLine();

            if (ID.equalsIgnoreCase("back")) return null;


            for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
                HashMap<String, Event> events = list.getValue();
                if (events.get(ID) != null) {
                    Event event = events.get(ID);
                    printEvent(event);
                    return event;
                }
            }
            System.out.println("Event not found.");
        } while (true);
    }

    /**
     * Find event with name.
     *
     * @param input the scanner
     * @return returns the event if found
     */
    private static Event findWithName(Scanner input) {
        do {
            System.out.println("Enter the event name, or \"back\" to go to the previous menu.");
            System.out.println();
            String name = input.nextLine();

            if (name.equalsIgnoreCase("back")) return null;

            for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
                HashMap<String, Event> events = list.getValue();
                for (Map.Entry<String, Event> entry : events.entrySet()) {
                    Event event = entry.getValue();
                    if (event.name.equalsIgnoreCase(name)) {
                        printEvent(event);
                        return event;
                    }
                }
            }
            System.out.println("Event not found.");
        } while (true);
    }

    /**
     * Print event details.
     *
     * @param event contains the event details
     */
    private static void printEvent(Event event) {
        System.out.println("ID: " + event.ID + "\nName: " + event.name + "\nDate: " + event.date + "\nTime: "
                + event.time + "\nType: " + event.type + "\nCapacity: " + event.venue.capacity + "\nTotal Seats Sold: " + event.venue.totalTicketsSold()
                + "\nTotal VIP Seats Sold: " + event.venue.vipSold + "\nTotal Gold Seats Sold: " + event.venue.goldSold + "\nTotal Silver Seats Sold: "
                + event.venue.silverSold + "\n" + " Total Bronze Seats Sold: " + event.venue.bronzeSold + "\n" + " Total General Adm Seats Sold: " + event.venue.genAdmiSold
                + "\nTotal revenue for VIP tickets: $" + event.vipRevenue() + "\n" + " Total revenue for Gold tickets: $" + event.goldRevenue() + "\n"
                + " Total revenue for Silver tickets: $" + event.silverRevenue() + "\nTotal revenue for Bronze tickets: $" + event.bronzeRevenue() + "\n"
                + " Total revenue for General Admission tickets: $" + event.generalRevenue() + "\n" + " Total revenue for all tickets: $" + event.totalRevenue()
                + "\nExpected profit (Sell Out): $" + event.expectedProfit() + "\n" + " Actual profit: $" + event.profit() + "\n");
        if(Firework(event)){
            System.out.println("This event includes fireworks");
            System.out.println();
        }
        else{
            System.out.println("This event does not include fireworks");
            System.out.println();
        }

    }

    private static boolean Firework(Event event) {
        String time = event.getTime();
        int temp = Integer.parseInt(String.valueOf(time.charAt(0)));
        char zone = time.charAt(time.length()-2);
        String z = String.valueOf(zone);
        String noon = "p";

        return (temp >= 7) && (z.equalsIgnoreCase(noon));
    }

    /**
     * Saves transaction to ArrayList.
     *
     * @param user the customer information
     * @param ticket contains the purchase information
     */
    private static void transLog(Customer user, Ticket ticket) {
        transLog.add(user.first + " " + user.last + " purchased a " + ticket.type + " ticket for $" + ticket.cost + " Conf#: " + ticket.confNum + "\n");
    }

    /**
     * Write file.
     *
     * @throws IOException the io exception
     */
    public static void writeFile() throws IOException { //FIXME add new event file for capacity tracking
        try (FileWriter writer = new FileWriter("New_Customer_List.csv")) {
            BufferedReader reader = new BufferedReader(new FileReader("Customer_List_PA1.csv"));
            String header = reader.readLine();
            writer.write(header + "\n");
            for (Customer cust : customerList.values()) {
                writer.write(cust.ID + "," + cust.first + "," + cust.last + "," + cust.getUsername() + "," + cust.getPassword() + "," + cust.getMoney() + "," + Boolean.toString(cust.member).toUpperCase() + "," + cust.purchased.size() + "\n");
            }
        }

        try (FileWriter writer = new FileWriter("New_Event_List_PA1.csv")) {
            BufferedReader reader = new BufferedReader(new FileReader("Event_List_PA1.csv"));
            String header = reader.readLine();
            writer.write(header + ",Venue ID,Venue name,Venue Type,Capacity,Concert Capacity,Cost,VIP Sold,Gold Sold,Silver Sold,Bronze Sold,General Admission Sold,"+"\n");

            for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
                HashMap<String, Event> events = list.getValue();
                for (Map.Entry<String, Event> entry : events.entrySet()) {
                    Event event = entry.getValue();
                    writer.write(event.ID+","+event.type+","+event.name+","+event.date+","+event.time+","+event.vipPrice+","+event.goldPrice+","+event.silverPrice+","+event.bronzePrice+","+event.generalAdPrice+
                            ","+event.venue.ID+","+event.venue.name+","+event.venue.type+","+event.venue.capacity+","+event.venue.concertCapacity+","+event.venue.cost+","+event.venue.vipSold+","+event.venue.goldSold+
                            ","+event.venue.silverSold+","+event.venue.bronzeSold+","+event.venue.genAdmiSold+"\n");
                    //event ID, type, name, date, time, vipPrice-genPrice,
                }
            }
        }

        try (FileWriter writer = new FileWriter("Trans_Log.txt")) {
            for (String trans : transLog) {
                writer.write(trans);
            }
        }
    }
}