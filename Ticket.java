import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private char seatRow;
    private int seatNumber;
    private double price;
    private Person person; //person object
    //constructor
    public Ticket(char seatRow,int seatNumber,double price,Person person){
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;
    }
    //Getters
    public char getRow(){
        return seatRow;
    }
    public int getSeat(){
        return seatNumber;
    }
    public double getPrice(){
        return price;
    }
    public Person getPerson(){
        return this.person ;
    }
    //setters
    public void setSeatRow (char newSeatRow){this.seatRow = newSeatRow;}
    public void setSeatNumber (int newSeatNumber){ this.seatNumber = newSeatNumber; }
    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setPerson(Person newPerson) {
        this.person = newPerson;
    }
    //Method to print the information of the ticket(including thr information of the person)
    public void ticketInfo (){
        System.out.println("Ticket for: " + person.getName() + " " + person.getSurname());
        System.out.println("Email: " + person.getEmail());
        System.out.println("Seat: " + seatRow + seatNumber + " Price: €" + price);
    }
    public void save(){
        String filename = seatRow + Integer.toString(seatNumber) + ".txt";
        try {
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("Ticket Information: \n");
                writer.write("Seat Information: ");
                writer.write("Row: " + seatRow + "\n");
                writer.write("Seat Number: " + seatNumber + "\n");
                writer.write("Price: " + price + "\n");
                writer.write("\n Personal Information: \n");
                writer.write("Name: " + person.getName() + "\n");
                writer.write("Surname: " + person.getSurname() + "\n");
                writer.write("Email: " + person.getEmail() + "\n");
            }
        }  catch (IOException e){
            System.err.println("Error while saving ticket information to the file: " + e.getMessage());
        }
    }
}
