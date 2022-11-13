import java.io.*;
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
    private static final LinkedHashMap<String, Customer> customerList = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Event> eventList = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Venue> venueList = new LinkedHashMap<>();

    private static final ArrayList<String> transLog = new ArrayList<>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        readFiles();
        setVenues();
        // Start UI

        boolean reenter = true;
        do {
            do {
                Customer user;
                System.out.println("Welcome, please enter your first and last name, or \"exit\" to leave.");
                String name = input.nextLine();
                if (customerList.containsKey(name.toLowerCase())) {
                    user = customerList.get(name.toLowerCase());
                } else if (name.equalsIgnoreCase("exit")) {
                    reenter = false;
                    break;
                } else {
                    System.out.println("User not found.");
                    break;
                }
                do {
                    //FIXME view balance
                    System.out.println("Welcome, " + name + ", would you like to find an event by name or ID? Choose Back to return to the previous selection.");
                    System.out.println();
                    System.out.println("A. Name");
                    System.out.println("B. ID");
                    System.out.println("C. Back");
                    String letter = input.nextLine();
                    if (letter.equalsIgnoreCase("A")) {
                        Event event = findWithName(input);
                        if (transaction(input, user, event)) break;

                    } else if (letter.equalsIgnoreCase("B")) {
                        Event event = findWithID(input);
                        if (transaction(input, user, event)) break;

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

                System.out.println("Select ticket type");
                System.out.println();
                System.out.println("A. VIP: " + event.vipPrice);
                System.out.println("B. Gold: " + event.goldPrice);
                System.out.println("C. Silver: " + event.silverPrice);
                System.out.println("D. Bronze: " + event.bronzePrice);
                System.out.println("E. General Admission: " + event.generalPrice);
                String type = input.nextLine();
                double typeCost;

                do {
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
                        typeCost = event.generalPrice;
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
        for (Map.Entry<String, Event> entry : eventList.entrySet()) {           // Venue ID numbers
            Event event = entry.getValue();                                     // 1. Don Haskins Center
            if (event.type.equalsIgnoreCase("sport")) {              // 2. Sun Bowl Stadium
                if (event.name.toLowerCase().contains("basketball")) {          // 3. Magoffin Auditorium
                    event.venue = venueList.get("1");                           // 4. San Jacinto Plaza
                } else if (event.name.toLowerCase().contains("football")) {     // 5. El Paso County Coliseum
                    event.venue = venueList.get("2");                           // 6. Centenial Plaza
                } else {
                    event.venue = venueList.get("3"); //FIXME add other types
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

            if (eventList.get(ID) != null) {
                Event event = eventList.get(ID);
                printEvent(event);
                return event;
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

            for (Map.Entry<String, Event> entry : eventList.entrySet()) { // FIXME find a better search algorithm
                Event event = entry.getValue();
                if (event.name.equalsIgnoreCase(name)) {
                    printEvent(event);
                    return event;
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
    public static void readFiles() throws IOException {
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
        try (FileWriter writer = new FileWriter("Files/newCustomer_List.csv")) {
            BufferedReader reader = new BufferedReader(new FileReader("Files/Customer_List_PA1.csv"));
            String header = reader.readLine();
            writer.write(header + "\n");
            for (Customer cust : customerList.values()) {
                writer.write(cust.ID + "," + cust.first + "," + cust.last + "," + cust.getUsername() + "," + cust.getPassword() + "," + cust.getMoney() + "," + cust.member + "," + cust.purchased.size() + "\n");
            }
        }
        try (FileWriter writer = new FileWriter("Files/Trans_Log.txt")) {
            for (String trans : transLog) {
                writer.write(trans);
            }
        }
    }
}