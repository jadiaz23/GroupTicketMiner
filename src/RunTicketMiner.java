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
        }

        // Start UI

        boolean reenter = true;
        do {
            do {
                Person user = loginCheck();

                if (user == null) {
                    reenter = false;
                    break;
                } else if (Objects.equals(user.getUsername(), "admin")) {
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

                        if (adminInput.equalsIgnoreCase("sport")) {
                            eventInfo.put("type", "Sport");
                            break;
                        } else if (adminInput.equalsIgnoreCase("concert")) {
                            eventInfo.put("type", "Concert");
                            break;
                        } else if (adminInput.equalsIgnoreCase("special")) {
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
                    System.out.println("Which Venue");
                    String venueInfo=sc.nextLine();
                    System.out.println("Please enter General Admission Price. (below $500)");
                    String genAdmPrice =sc.nextLine();

                    //double ID;
                    //do {
                    //     ID =Math.round(Math.random()*10);
                    //} while (typeOfEvent.containsKey(confNum));

                    //ID =Math.round(Math.random()*10);

                    // construct event
                    break;
                default:
                    if (adminInput.equalsIgnoreCase("exit")){
                        System.out.println("Exiting admin menu...");
                        loginCheck();
                        break;

                    }
            }
        }
        sc.close();
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
                String type;
                double typeCost;
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
                        typeCost = event.vipPrice;
                        break;
                    } else if (type.equalsIgnoreCase("b")) {
                        type = "gold";
                        typeCost = event.goldPrice;
                        break;
                    } else if (type.equalsIgnoreCase("c")) {
                        type = "silver";
                        typeCost = event.silverPrice;
                        break;
                    } else if (type.equalsIgnoreCase("d")) {
                        type = "bronze";
                        typeCost = event.bronzePrice;
                        break;
                    } else if (type.equalsIgnoreCase("e")) {
                        type = "general";
                        typeCost = event.generalAdPrice;
                        break;
                    } else {
                        System.out.println("Input not recognized.");
                    }
                } while (true);

                //FIXME implement buy*Type* here (boolean)

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
            for (Map.Entry<String, Event> entry : events.entrySet()) {              // Venue ID numbers
                Event event = entry.getValue();                                     // 1. Don Haskins Center
                if (event.type.equalsIgnoreCase("sport")) {              // 2. Sun Bowl Stadium
                    if (event.name.toLowerCase().contains("basketball")) {          // 3. Magoffin Auditorium
                        event.venue = venueList.get("1");                           // 4. San Jacinto Plaza
                    } else if (event.name.toLowerCase().contains("football")) {     // 5. El Paso County Coliseum
                        event.venue = venueList.get("2");                           // 6. Centenial Plaza
                    }
                } else if (event.type.equalsIgnoreCase("concert")) {
                    event.venue = venueList.get("3");
                } else { // Special
                    event.venue = venueList.get("4");
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
    }

    /**
     * Read files.
     *
     * @throws IOException the io exception
     */
    /*public static void readFiles() throws IOException {
        String[] files = {"Files/Customer_List_PA1.csv", "Files/Event_List_PA1.csv", "Files/Venue_List_PA1.csv"};

        for (String var : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(var))) {
                String header = reader.readLine();
                String info = reader.readLine();
                String[] splitHeader = header.split(",");

                while (info != null) {
                    HashMap<String, String> temp = new HashMap<>();
                    String[] splitInfo = info.split(",");
                    int i = 0;
                    for (String label : splitHeader) {
                        temp.put(label.toLowerCase(), splitInfo[i]); // adds header as key to values
                        ++i;
                    }
                    switch (var) {
                        case "Files/Customer_List_PA1.csv" -> {
                            Customer customer = new Customer(temp.get("id"), temp.get("first name"), temp.get("last name"), temp.get("username"), temp.get("password"), temp.get("money available"), temp.get("ticketminer membership"), temp.get("concerts purchased"));
                            customerList.put(temp.get("first name").toLowerCase() + " " + temp.get("last name").toLowerCase(), customer);
                        }
                        case "Files/Event_List_PA1.csv" -> {
                            Event event = null;
                            if (temp.get("type").equalsIgnoreCase("sport")) {
                                event = new Sport(temp.get("id"), temp.get("type"), temp.get("name"), temp.get("date"), temp.get("time"), temp.get("vip price"), temp.get("gold price"), temp.get("silver price"), temp.get("bronze price"), temp.get("general admission price"));
                            }
                            //FIXME add other event types
                            eventList.put(temp.get("id"), event);
                        }
                        case "Files/Venue_List_PA1.csv" -> {
                            Venue venue = null;
                            if (temp.get("type").equalsIgnoreCase("arena")) {
                                venue = new Arena(temp.get("id"), temp.get("name"), temp.get("type"), temp.get("capacity"), temp.get("concert capacity"), temp.get("cost"), temp.get("vip percent"), temp.get("gold percent"), temp.get("silver percent"), temp.get("bronze percent"), temp.get("general admission percent"), temp.get("reserved extra percent"));
                            } else if (temp.get("type").equalsIgnoreCase("stadium")) {
                                venue = new Stadium(temp.get("id"), temp.get("name"), temp.get("type"), temp.get("capacity"), temp.get("concert capacity"), temp.get("cost"), temp.get("vip percent"), temp.get("gold percent"), temp.get("silver percent"), temp.get("bronze percent"), temp.get("general admission percent"), temp.get("reserved extra percent"));
                            }
                            venueList.put(temp.get("id"), venue);
                        }
                    }
                    info = reader.readLine();
                }
            }
        }
    }*/

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
            writer.write(header + "\n");

            for (Map.Entry<String, LinkedHashMap<String, Event>> list : eventList.entrySet()) {
                HashMap<String, Event> events = list.getValue();
                for (Map.Entry<String, Event> entry : events.entrySet()) {
                    Event event = entry.getValue();
                    writer.write()
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