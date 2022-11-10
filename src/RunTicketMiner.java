import java.util.HashMap;
import java.util.Scanner;

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
        RunTicketMiner runProgram = new RunTicketMiner();
        runProgram.loginCheck();
    }
    public void loginCheck(){
        Scanner scan = new Scanner(System.in);
        int authenticate= -1;
        while (authenticate==-1) {
            System.out.println("Hello\n Welcome to Ticker Miner");
            System.out.println("Please enter your username and password. \nNote: This is CASE SENSITIVE");
            System.out.println("Username: ");
            String userInputUsername = scan.nextLine();
            System.out.println("Password: ");
            String userInputPassword = scan.nextLine();
            //check if username and password are true


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
    /*
    public void admin(){
        Scanner sc = new Scanner(System.in);
        String adminInput = "";
        while (!adminInput.equalsIgnoreCase("exit")) {
            System.out.println("================================================================");
            System.out.println("Hello Administrator! How would you like to inquire an event: ");
            System.out.println("\tA. Inquire via Event ID\n\tB. Inquire via Event Name\n\tC. Create new event\n\tType \"exit\" to exit admin menu");
            adminInput = sc.nextLine();
            String adminEventInput;
            switch(adminInput){
                case "a":
                case "A":
                    System.out.println("Enter Event ID");
                    adminEventInput = sc.nextLine();
                    adminEventInput = isStringNumber(adminEventInput);
                    if(adminEventInput.equals("-1")){
                        System.out.println("Please try entering an event ID.");
                    } else {
                        printInfo(eventArrayList.get(Integer.parseInt(adminEventInput)-1), true);
                    }
                    break;
                case "b":
                case "B":
                    System.out.println("Enter Event Name");
                    adminEventInput = sc.nextLine();
                    Event eventRequested = isEventReal(adminEventInput);
                    if (eventRequested==null){
                        System.out.println("Sorry "+adminEventInput+" does not exist in our database. Please make sure the event name is correct.");
                    }else {
                        printInfo(eventRequested, true);
                    }
                    break;
                case "c":
                case"C":

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

     */
}
