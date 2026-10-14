package findit;

public abstract class Item {

    private int id;
    private String name;
    private String description;
    private String location;
    private String reporterName;

    // Constructor
    public Item(int id, String name, String description,
                String location, String reporterName) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.reporterName = reporterName;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getReporterName() {
        return reporterName;
    }

    // Abstract method
    public abstract String getType();

    // Display item details
    public void displayDetails() {

        System.out.println("Item ID: " + id);
        System.out.println("Item Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Location: " + location);
        System.out.println("Reported By: " + reporterName);
        System.out.println("Type: " + getType());
    }
}