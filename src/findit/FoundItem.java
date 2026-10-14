package findit;

public class FoundItem extends Item {

    private boolean claimed;

    public FoundItem(int id, String name, String description,
                     String location, String reporterName) {

        super(id, name, description, location, reporterName);
        this.claimed = false;
    }

    @Override
    public String getType() {
        if (claimed) {
            return "CLAIMED";
        } else {
            return "FOUND";
        }
    }

    public boolean isClaimed() {
        return claimed;
    }

    public void claimItem() {
        claimed = true;
    }
}