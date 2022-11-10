import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class RunTicketMiner {
    private static HashMap<String, HashMap<String, Event>> typeOfEvent = new HashMap<>();
    private  static  HashMap<String, Customer> customer = new HashMap<>();
    private  static HashMap<String, Venue> venue = new HashMap<>();
    private static openFiles f;
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
        RunTicketMiner runProgram = new RunTicketMiner();
        runProgram.loginCheck();
    }
    public void loginCheck(){
        Scanner scan = new Scanner(System.in);
        int authenticate= -1;
        while (authenticate==-1) {
            System.out.println("Hello\n*****Welcome to Ticker Miner***");
            System.out.println("Please enter your username and password. \nNote: This is CASE SENSITIVE");
            System.out.println("Username: ");
            String userInputUsername = scan.nextLine();
            System.out.println("Password: ");
            String userInputPassword = scan.nextLine();
            //check if username and password are true
            if((userInputPassword.toLowerCase()).equals("admin") || (userInputUsername.toLowerCase()).equals("admin")){
                admin();
            }
            if(customer.containsKey(userInputUsername) && customer.get(userInputUsername).getPassword().equals("Fun!23")){
                purchaseTickets(customer);
            }
            if((userInputPassword.toLowerCase()).equals("exit") || (userInputUsername.toLowerCase()).equals("exit")) {
            System.out.println("Exiting System!");
            break;
            }
            System.out.println("\n*****Information not found please try again******\n");




        }

    }

    /**
     * Purchase Tickets Menu
     * @param user
     */
    public void purchaseTickets(HashMap<String, Customer> user){
        Scanner input = new Scanner(System.in);
        int countTickets=0;
        do {
            System.out.println("Would you like to confirm ticket purchase? Enter Yes or No.");
            System.out.println();
            String confirm = input.nextLine();
            System.out.println("Enter Event Name (Case Sensitive)");
           String adminEventInput = input.nextLine();
            System.out.println("Enter Type of Event (Case Sensitive)\nSport\nConcert\nSpecial");
            String event_Type=input.nextLine();
            if (confirm.equalsIgnoreCase("yes")&&typeOfEvent.containsKey(event_Type) && typeOfEvent.get(event_Type).containsKey(adminEventInput)) {
                System.out.println("Select ticket type");
                System.out.println();
                System.out.println("A. VIP");
                System.out.println("B. Gold: ");
                System.out.println("C. Silver: ");
                System.out.println("D. Bronze: ");
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


    /** Admin main menu */

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
