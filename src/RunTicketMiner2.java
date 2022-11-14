/*
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RunTicketMiner2 {
    private static HashMap<String, HashMap<String, Event>> typeOfEvent = new HashMap<>();
    private  static  HashMap<String, Customer> customer = new HashMap<>();
    private  static HashMap<String, Venue> venue = new HashMap<>();
    private static openFiles f;
    private static final ArrayList<String> transLog = new ArrayList<>();
    public static void main(String[] args) {
         f = new openFiles();
        typeOfEvent = f.openEventList();
        //typeOfEvent.get("Sport").get("UTEP Football 1").print();
        System.out.println();

        customer = f.openCustomerList();
       // customer.get("aliciajenkins").print();
        System.out.println();

        HashMap<Integer, Venue> venue = f.openVenueList();
       // venue.get(6).print();

        if (!Files.exists(Path.of("New_Event_List_PA1.csv"))) {
            setVenues(); // sets a venue to the events
        }

        RunTicketMiner2 runProgram = new RunTicketMiner2();
        runProgram.loginCheck();
    }
    public void loginCheck(){
        Scanner scan = new Scanner(System.in);
        int authenticate= -1;
        while (authenticate==-1) {
            System.out.println("Hello\n*****Welcome to Ticker Miner*******");
            System.out.println("Please enter your username and password. \nNote: This is CASE SENSITIVE");
            System.out.println("Username: ");
            String userInputUsername = scan.nextLine();
            System.out.println("Password: ");
            String userInputPassword = scan.nextLine();
            //check if username and password are true
            if((userInputPassword.toLowerCase()).equals("admin") || (userInputUsername.toLowerCase()).equals("admin")){
                admin();
            }
            else if(customer.containsKey(userInputUsername) && customer.get(userInputUsername).getPassword().equals(userInputPassword)){
                purchaseTickets(customer);
            }
            else if((userInputPassword.toLowerCase()).equals("exit") || (userInputUsername.toLowerCase()).equals("exit")) {
            System.out.println("Exiting System!");
            System.exit(1);
            }
            System.out.println("\n*****Information not found please try again******\n");




        }
    }

    */
/**
     * Purchase Tickets Menu
     * @param user
     *//*

    public void purchaseTickets(HashMap<String, Customer> user){
        Scanner input = new Scanner(System.in);
        int countTickets=0;
        do {
            System.out.println("Would you like to confirm ticket purchase? Enter Yes or No.");
            System.out.println();
            String confirm = input.nextLine();
            if(confirm.equalsIgnoreCase("no")) {
                loginCheck();
                break;
            }
            System.out.println("Enter Event Name (Case Sensitive)");
           String EventInput = input.nextLine();
            System.out.println("Enter Type of Event (Case Sensitive)\nSport\nConcert\nSpecial");
            String event_Type=input.nextLine();
            if (confirm.equalsIgnoreCase("yes")&&typeOfEvent.containsKey(event_Type) && typeOfEvent.get(event_Type).containsKey(EventInput)) {
                typeOfEvent.get(event_Type).get(EventInput).print();
                System.out.println("\nSelect ticket type");
                System.out.println();
                System.out.println("A. VIP");
                System.out.println("B. Gold");
                System.out.println("C. Silver");
                System.out.println("D. Bronze");
                System.out.println("E. General Admission:");
                String type = input.nextLine();
                    if (type.equalsIgnoreCase("a")) {
                        type = "vip";
                        countTickets++;
                    } else if (type.equalsIgnoreCase("b")) {
                        type = "gold";
                        countTickets++;

                    } else if (type.equalsIgnoreCase("c")) {
                        type = "silver";
                        countTickets++;

                    } else if (type.equalsIgnoreCase("d")) {
                        type = "bronze";
                        countTickets++;

                    } else if (type.equalsIgnoreCase("e")) {
                        type = "general";
                        countTickets++;

                    }else if(countTickets>6){
                        System.out.println("Maximum Tickets Purchased");
                        break;
                    }
                    else {
                        System.out.println("Input not recognized.");
                    }

                //FIXME implement buy*Type* here (boolean)

                Random rand = new Random();
                int confNum;
                do {
                    confNum = rand.nextInt(100000);
                } while (typeOfEvent.containsKey(confNum));


            } else if (confirm.equalsIgnoreCase("no")) {
                System.out.println("Purchase Cancelled.");
                System.out.println();
            } else {
                System.out.println("Input not recognized.");
                System.out.println();
            }
        } while (true);
    }


    */
/** Admin main menu *//*


    public void admin(){
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
                    if (typeOfEvent.containsKey(event_Type) && typeOfEvent.get(event_Type).containsKey(adminEventInput)){
                        typeOfEvent.get(event_Type).get(adminEventInput).print();
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
                        System.out.println("C. Special");                                   //createEvent(String id, String type, String name, String date, String time, Double genPrice, Venue venue)
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

    */
/**
     * Saves transaction to ArrayList.
     *
     * @param user the customer information
     * @param ticket contains the purchase information
     *//*

    private static void transLog(Customer user, Ticket ticket) {
        transLog.add(user.first + " " + user.last + " purchased a " + ticket.type + " ticket for $" + ticket.cost + " Conf#: " + ticket.confNum + "\n");
    }

    private static void setVenues() {
        for (Map.Entry<String, HashMap<String, Event>> list : typeOfEvent.entrySet()) {
            HashMap<String, Event> eventList = list.getValue();
            for (Map.Entry<String, Event> entry : eventList.entrySet()) {           // Venue ID numbers
                Event event = entry.getValue();                                     // 1. Don Haskins Center
                if (event.type.equalsIgnoreCase("sport")) {              // 2. Sun Bowl Stadium
                    if (event.name.toLowerCase().contains("basketball")) {          // 3. Magoffin Auditorium
                        event.venue = venue.get("1");                               // 4. San Jacinto Plaza
                    } else if (event.name.toLowerCase().contains("football")) {     // 5. El Paso County Coliseum
                        event.venue = venue.get("2");                               // 6. Centenial Plaza
                    }
                } else if (event.type.equalsIgnoreCase("concert")) {
                    event.venue = venue.get("3");
                } else { // Special
                    event.venue = venue.get("4");
                }
            }
        }
    }
}
*/
