public class Person {
    private String name ; //Private because the person only has the access
    private String surname;
    private String email;
   //constructor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    //getters
    public String getName(){return name;}
    public String getSurname(){
        return surname;
    }
    public String getEmail(){
        return email;
    }
    //setters
    public void setName(String newName){ this.name = newName; }
    public void setSurname(String newSurname){
        this.surname = newSurname;
    }
    public void setEmail(String newEmail){
        this.email = newEmail;
    }
    //Method to print the information from person
    public void personInfo (){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: "+ email);
    }

}
