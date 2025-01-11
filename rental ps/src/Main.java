import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameRental rental = new GameRental();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Game Rental Menu ===");
            System.out.println("1. Add Game");
            System.out.println("2. List Games");
            System.out.println("3. Update Game");
            System.out.println("4. Delete Game");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter game title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter game genre: ");
                    String genre = scanner.nextLine();
                    rental.addGame(title, genre);
                    break;
                case 2:
                    rental.listGames();
                    break;
                case 3:
                    System.out.print("Enter game ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new game title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new game genre: ");
                    String newGenre = scanner.nextLine();
                    rental.updateGame(updateId, newTitle, newGenre);
                    break;
                case 4:
                    System.out.print("Enter game ID to delete: ");
                    int deleteId = scanner.nextInt();
                    rental.deleteGame(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}