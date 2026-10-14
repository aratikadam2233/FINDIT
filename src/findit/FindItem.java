package findit;

import java.util.ArrayList;
import java.util.Scanner;

public class FindItem {

    private static final Scanner sc = new Scanner(System.in);
    private static final FindItManager manager = new FindItManager();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("       FINDIT LOST & FOUND");
            System.out.println("================================");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. Search Item by ID");
            System.out.println("4. Search Item by Name");
            System.out.println("5. View All Items");
            System.out.println("6. Claim Found Item");
            System.out.println("7. Remove Item");
            System.out.println("8. Exit");
            System.out.println("================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    reportLostItem();
                    break;

                case 2:
                    reportFoundItem();
                    break;

                case 3:
                    searchById();
                    break;

                case 4:
                    searchByName();
                    break;

                case 5:
                    viewAllItems();
                    break;

                case 6:
                    claimItem();
                    break;

                case 7:
                    removeItem();
                    break;

                case 8:
                    System.out.println("Thank you for using FindIt!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 8);

        sc.close();
    }

    // Read integer safely
    private static int readInt(String message) {

        while (true) {
            System.out.print(message);
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    // Report lost item
    private static void reportLostItem() {

        System.out.println("\n--- Report Lost Item ---");

        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter lost location: ");
        String location = sc.nextLine();

        System.out.print("Enter your name: ");
        String reporterName = sc.nextLine();

        int id = manager.reportLostItem(
            name, description, location, reporterName
        );

        System.out.println("Lost item reported successfully!");
        System.out.println("Your Item ID is: " + id);
    }

    // Report found item
    private static void reportFoundItem() {

        System.out.println("\n--- Report Found Item ---");

        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter found location: ");
        String location = sc.nextLine();

        System.out.print("Enter your name: ");
        String reporterName = sc.nextLine();

        int id = manager.reportFoundItem(
            name, description, location, reporterName
        );

        System.out.println("Found item reported successfully!");
        System.out.println("Your Item ID is: " + id);
    }

    // Search by item ID
    private static void searchById() {

        System.out.println("\n--- Search Item by ID ---");
        int id = readInt("Enter item ID: ");

        try {
            Item item = manager.searchById(id);
            item.displayDetails();

        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Search by item name
    private static void searchByName() {

        System.out.println("\n--- Search Item by Name ---");

        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        ArrayList<Item> results = manager.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("No matching items found!");
        } else {
            for (Item item : results) {
                System.out.println("----------------------------");
                item.displayDetails();
            }
        }
    }

    // Display all items
    private static void viewAllItems() {

        System.out.println("\n--- All Items ---");

        ArrayList<Item> items = manager.getAllItems();

        if (items.isEmpty()) {
            System.out.println("No items available.");
        } else {
            for (Item item : items) {
                System.out.println("----------------------------");
                item.displayDetails();
            }
        }
    }

    // Claim a found item
    private static void claimItem() {

        System.out.println("\n--- Claim Found Item ---");
        int id = readInt("Enter found item ID: ");

        try {
            manager.claimFoundItem(id);
            System.out.println("Item claimed successfully!");

        } catch (ItemNotFoundException
                 | IllegalArgumentException
                 | IllegalStateException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // Remove item
    private static void removeItem() {

        System.out.println("\n--- Remove Item ---");
        int id = readInt("Enter item ID to remove: ");

        try {
            manager.removeItem(id);
            System.out.println("Item removed successfully!");

        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}