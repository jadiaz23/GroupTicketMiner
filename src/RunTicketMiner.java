import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class RunTicketMiner {
    private static HashMap<String, HashMap<String, Event>> typeOfEvent = new HashMap<>();
    private  static  HashMap<String, Customer> customer = new HashMap<>();
    private  static HashMap<String, Venue> venue = new HashMap<>();
    private static openFiles f;
    public static void main(String[] args) {
         f = new openFiles();
        typeOfEvent = f.openEventList();
        typeOfEvent.get("Sport").get("UTEP Football 1").print();
        System.out.println();

        customer = f.openCustomerList();
        customer.get("aliciajenkins").print();
        System.out.println();

        HashMap<Integer, Venue> venue = f.openVenueList();
        venue.get(6).print();
        RunTicketMiner runProgram = new RunTicketMiner();
        runProgram.loginCheck();
    }
    public void loginCheck(){
        Scanner scan = new Scanner(System.in);
        int authenticate= -1;
        while (authenticate==-1) {
            System.out.println("Hello\nWelcome to Ticker Miner");
            System.out.println("Please enter your username and password. \nNote: This is CASE SENSITIVE");
            System.out.println("Username: ");
            String userInputUsername = scan.nextLine();
            System.out.println("Password: ");
            String userInputPassword = scan.nextLine();
            //check if username and password are true
            if((userInputPassword.toLowerCase()).equals("admin") || (userInputUsername.toLowerCase()).equals("admin")){
                admin();
            }
            if(customer.containsKey(userInputPassword.toLowerCase()) && customer.containsKey(userInputUsername.toLowerCase())){

            }
            if((userInputPassword.toLowerCase()).equals("exit") || (userInputUsername.toLowerCase()).equals("exit")) {
            System.out.println("Exiting System!");
            break;
            }
            System.out.println("*****Information not found please try again******");




        }

    }

   /* public void purchaseTickets(Customer user){
        Scanner sc = new Scanner(System.in);
        System.out.println("What would you like to do?\n\n1. Buy tickets via Event ID");
        System.out.println("2. View event info via Event ID");
        String userInput = sc.next();
        int sportEventID;
        switch (userInput) {
            case "1" -> {
                System.out.print("Please enter an event ID: "); //Implements a check for an event ID
                sportEventID = sc.nextInt();
                if (sportEventID > eventArrayList.size() || sportEventID < 0) {
                    System.out.println("Sorry that Event ID does not exist. Returning to main menu");
                    break;
                }
                user.buyTicket(returnEvent(sportEventID));
            }
            case "2" -> {
                System.out.print("Please enter an event ID: "); //Implements a check for an event ID
                sportEventID = sc.nextInt();
                if (sportEventID > eventArrayList.size() || sportEventID < 0) {
                    System.out.println("Sorry that Event ID does not exist. Returning to main menu");
                    break;
                }
                printInfo(returnEvent(sportEventID), false);
                purchaseTickets(user);
            }
            default -> System.out.println("That is not an option available. Please try again, returning to main menu");
        }
        sc.close();
    }

    */
    /** Admin main menu */

    public void admin(){
        Scanner sc = new Scanner(System.in);
        String adminInput = "";
        while (!adminInput.equalsIgnoreCase("exit")) {
            System.out.println("================================================================");
            System.out.println("Hello Administrator! How would you like to inquire an event: ");
            System.out.println("\n\tA. Inquire via Event Name\n\tB. Create new event\n\tType \"exit\" to exit admin menu");
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
                    System.out.println("Adding Event");
                    break;
                default:
                    if (adminInput.equalsIgnoreCase("exit")){
                        System.out.println("Exiting admin menu...");
                    }else{
                        System.out.println("Please enter an option that is available.");
                    }
                    break;
            }
        }
        sc.close();
    }//end of admin
}
