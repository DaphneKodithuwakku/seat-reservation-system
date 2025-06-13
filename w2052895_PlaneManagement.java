import java.util.Scanner;

public class w2052895_PlaneManagement {
    public static void setSeats(){
        seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
    }
    // Constants to represent seat states.
    static final int available = 0;
    static final int sold = 1;
    // Seat data
    static int[][] seats;
    static final char[] seatRowLetters = {'A', 'B', 'C', 'D'}; // Rows
    static final int[] seatsPerRow = {14, 12, 12, 14}; // Number of seats in each row.
    static final String[] seatNumbers = {"01","02","03","04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14"};
    static Ticket[] tickets = new Ticket [52]; //52 is the maximum number of seats that can be sold
    static  int ticketCount = 0; // to check the number of tickets sold
    public static double calculatePrice(int seatNumber){
        double price;
        if (seatNumber >= 1 && seatNumber <= 5) {
            price = 200.0;
        }

        else if (seatNumber >= 6 && seatNumber <= 9) {
            price = 150.0;
        }

        else if (seatNumber >= 10 && seatNumber <= 14) {
            price = 180.0;
        }

        else{
            price = 0.0;
            System.out.println("Invalid seat number...");
        }

        return price;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        setSeats();
        // Main loop for menu selection.
        while (true) {
            System.out.println("\n           Welcome to the Plane Management application          \n");
            System.out.println("*******************************************************************");
            System.out.println("*                         MENU OPTIONS                             *");
            System.out.println("*******************************************************************");
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating plan");
            System.out.println("5) Printing tickets information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("*******************************************************************");
            System.out.print("Please select an option: ");

            int option = scanner.nextInt();
            switch (option) {
                case 0:
                    System.out.println("Program terminated.");
                    return; // Use return to exit the main method, effectively ending the program.
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketInfo();
                    break;
                case 6:
                    searchTicket();
                    break;
                // Add other cases as needed for your application.
                default:
                    System.out.println("Invalid option. Please try again.");
            }


        }

    }

    // Method for buying a seat.
    public static void buySeat() {
        if (ticketCount >= tickets.length){
            System.out.println("Tickets are sold out!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------- | Buy Seat |--------------");
        System.out.print("Enter the seat row (A,B,C,D): ");
        String rowInput = scanner.next().toUpperCase();
        int rowIndex = new String(seatRowLetters).indexOf(rowInput);

        if (rowIndex == -1) {
            System.out.println("Invalid row. Rows are (A,B,C,D)");
            return;
        }
        System.out.print("Enter your seat number: ");
        int seatNumber = scanner.nextInt();
        // Validate seat number.
        if (seatNumber < 1 || seatNumber > seatsPerRow[rowIndex]) {
            System.out.println("Invalid seat number for the chosen row.");
            return;
        }

        // Check if the seat is available and mark it as sold if it is.
        if (seats[rowIndex][seatNumber - 1] == available) {
            //personal information
            System.out.print("Enter your name: ");
            String name = scanner.next();
            System.out.print("Enter you surname: ");
            String surname = scanner.next();
            System.out.print("Enter your email: ");
            String email = scanner.next();

            Person person = new Person(name,surname,email); //creating and object for person
            Ticket ticket = new Ticket(seatRowLetters[rowIndex],seatNumber,calculatePrice(seatNumber),person); //creating an object for tickets
            tickets[ticketCount++] = ticket; // a variable to store the ticket

            seats[rowIndex][seatNumber - 1] = sold;
            ticket.save();
            System.out.println("Purchase confirmed. Thank you for purchasing.");
        } else {
            System.out.println("Seat is already taken. Please choose another seat.");
        }

    }
    public static void cancelSeat(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------- | Cancel Seat |--------------");
        System.out.print("Enter the seat row (A,B,C or D) : ");
        char rowInput = scanner.next().toUpperCase().charAt(0);
        int rowIndex = new String(seatRowLetters).indexOf(rowInput);

        if (rowIndex == -1){
            System.out.println("Invalid row. Rows are (A,B,C,D)");
            return;
        }
        System.out.print("Enter your seat number: ");
        int seatNumber = scanner.nextInt();

        if (seatNumber < 1 || seatNumber > seatsPerRow[rowIndex]) {
            System.out.println("Invalid seat number for the chosen row.");
            return;
        }
        if (seats[rowIndex][seatNumber -1] == sold){
            seats[rowIndex][seatNumber -1] = available; //Marking the seat as available
            for (int i = 0; i < ticketCount; i++) {
                if (tickets[i].getRow() == seatRowLetters[rowIndex] && tickets[i].getSeat() ==seatNumber){
                    for (int j = i; j < ticketCount -1 ; j++) {
                        tickets[j] = tickets[j+1];
                    }
                tickets[ticketCount -1] = null;
                ticketCount --;
                System.out.println("Booking has been cancelled. The seat is now available.");
                return;
                }
            }
            System.out.println("Ticket not found. It might have already been cancelled.");
        } else {
            System.out.println("This seat is already available.");
        }
    }
    public static void findFirstAvailable(){
        System.out.println("-------- | First available seat |--------------");
        for (int i = 0; i < seatRowLetters.length; i++) {
            for (int j = 0; j < seatsPerRow.length ; j++) {
                if (seats[i][j] == available) {
                    System.out.println("Seat  " + seatRowLetters[i] + (j+1) + "  is available");
                    return;
                }

            }

        }
        System.out.println("There are no available seats..");
    }
    public static void showSeatingPlan(){
        System.out.println("-------- | Seating Plan |--------------");
        System.out.print("   ");
        for (String c : seatNumbers) {
            System.out.print(c + "  ");
        }

        System.out.println();
        for (int i = 0; i < seatRowLetters.length; i++){
            System.out.print(" " + seatRowLetters[i] + "  ");
            for (int j = 0; j < seats[i].length; j++) {

                char status = (seats [i][j] == available) ? 'O' : 'X';
                System.out.print(status + "   ");
            }
            System.out.println();
        }
    }
    public static void printTicketInfo(){
        System.out.println("-------- | Ticket Information |--------------");
        if (ticketCount == 0){
            System.out.println("No tickets have been sold.");
            return;
        }
        double totalAmount = 0;
        System.out.println("Tickets Sold: " + ticketCount);
        for (int i = 0; i < ticketCount ; i++) {
            Ticket ticket = tickets[i];
            System.out.println("Ticket -  " + (i+1) + " ");
            System.out.println("-------- | Personal Information |--------------");
            Person person = ticket.getPerson();
            person.personInfo();
            totalAmount += ticket.getPrice(); //to add the price of tickets to the total
        }
        System.out.println("-------- | Total Sales |--------------");
        System.out.println("Total sales = £" + totalAmount);
    }

    public static void searchTicket(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------- | Search Ticket |--------------");
        System.out.print("Enter the seat row you want to search (A,B,C,D): ");
        String rowInput = scanner.next().toUpperCase();
        int rowIndex = "A,B,C,D".indexOf(rowInput);

        if (rowIndex == -1 ) {
            System.out.println("Invalid row. Rows are (A,B,C,D)");
            return;
        }
        System.out.print("Enter the seat number you want to search: ");
        int seatNumber = scanner.nextInt();

        boolean isSeatFound = false;

        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = tickets[i];
            if (ticket.getRow() == rowInput.charAt(0) && ticket.getSeat() == seatNumber) {
                System.out.println("Your ticket: ");
                System.out.println("\nSeat Information: ");
                System.out.println("Row: " + tickets[i].getRow());
                System.out.println("Seat: " + tickets[i].getSeat());
                System.out.println("Ticket price: " + tickets[i].getPrice());
                System.out.println("\nPersonal information: ");
                System.out.println("Name: " + tickets[i].getPerson().getName());
                System.out.println("Surname: " + tickets[i].getPerson().getSurname());
                System.out.println("Email: " + tickets[i].getPerson().getEmail());
                isSeatFound = true;
                break;
            }
        }
        if (!isSeatFound) {
            System.out.println("This seat is available to purchase");
        }
    }
}
