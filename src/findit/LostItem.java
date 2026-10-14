package findit;

public class LostItem extends Item {

    // Constructor
    public LostItem(int id, String name, String description,
                    String location, String reporterName) {

        super(id, name, description, location, reporterName);
    }

    // Method overriding
    @Override
    public String getType() {
        return "LOST";
    }
}