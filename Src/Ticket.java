public class Ticket {

    private int id;
    private String title;
    private String description;
    private String status;

    public Ticket(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = "Open";
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    public void displayTicket(){
        System.out.println("---------------------");
        System.out.println("Ticket ID: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Status: " + this.status);
        System.out.println("---------------------");
    }

}
