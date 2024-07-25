package OnlineReservationSystem;
public class Reservation {
    private static int counter = 0;
    private int id;
    private String details;
    private String user;

    public Reservation(String details, String user) {
        this.id = ++counter;
        this.details = details;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public String getUser() {
        return user;
    }
}
