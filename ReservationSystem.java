package OnlineReservationSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReservationSystem {
    private HashMap<String, User> users;
    private ArrayList<Reservation> reservations;
    private User loggedInUser;

    public ReservationSystem() {
        users = new HashMap<>();
        reservations = new ArrayList<>();
        loggedInUser = null;

        // Adding some default users
        users.put("user1", new User("user1", "password1"));
        users.put("user2", new User("user2", "password2"));
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean makeReservation(String details) {
        if (loggedInUser != null) {
            reservations.add(new Reservation(details, loggedInUser.getUsername()));
            return true;
        }
        return false;
    }

    public boolean cancelReservation(int id) {
        if (loggedInUser != null) {
            for (Reservation reservation : reservations) {
                if (reservation.getId() == id && reservation.getUser().equals(loggedInUser.getUsername())) {
                    reservations.remove(reservation);
                    return true;
                }
            }
        }
        return false;
    }

    public void viewReservations() {
        if (loggedInUser != null) {
            System.out.println("Reservations for " + loggedInUser.getUsername() + ":");
            for (Reservation reservation : reservations) {
                if (reservation.getUser().equals(loggedInUser.getUsername())) {
                    System.out.println("ID: " + reservation.getId() + ", Details: " + reservation.getDetails());
                }
            }
        } else {
            System.out.println("Please log in to view reservations.");
        }
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Cancel a Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    if (system.login(username, password)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 2:
                    System.out.print("Reservation Details: ");
                    String details = scanner.nextLine();
                    if (system.makeReservation(details)) {
                        System.out.println("Reservation made.");
                    } else {
                        System.out.println("Please log in to make a reservation.");
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (system.cancelReservation(id)) {
                        System.out.println("Reservation canceled.");
                    } else {
                        System.out.println("Reservation not found or login required.");
                    }
                    break;
                case 4:
                    system.viewReservations();
                    break;
                case 5:
                    system.logout();
                    System.out.println("Logged out.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
