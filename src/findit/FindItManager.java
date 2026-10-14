package findit;

import java.util.ArrayList;
import java.util.HashMap;

public class FindItManager {

    // Collection Framework
    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<Integer, Item> itemMap = new HashMap<>();

    // Automatic item ID
    private int nextId = 101;

    // Add item to both collections
    private void addItem(Item item) {
        items.add(item);
        itemMap.put(item.getId(), item);
    }

    // Report a lost item
    public int reportLostItem(String name, String description,
                              String location, String reporterName) {

        LostItem item = new LostItem(
            nextId++, name, description, location, reporterName
        );

        addItem(item);
        return item.getId();
    }

    // Report a found item
    public int reportFoundItem(String name, String description,
                               String location, String reporterName) {

        FoundItem item = new FoundItem(
            nextId++, name, description, location, reporterName
        );

        addItem(item);
        return item.getId();
    }

    // Search item by ID
    public Item searchById(int id) throws ItemNotFoundException {

        Item item = itemMap.get(id);

        if (item == null) {
            throw new ItemNotFoundException(
                "Item with ID " + id + " not found!"
            );
        }

        return item;
    }

    // Search items by name
    public ArrayList<Item> searchByName(String name) {

        ArrayList<Item> foundItems = new ArrayList<>();

        for (Item item : items) {
            if (item.getName().toLowerCase()
                    .contains(name.toLowerCase())) {

                foundItems.add(item);
            }
        }

        return foundItems;
    }

    // Get all items
    public ArrayList<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    // Claim a found item
    public void claimFoundItem(int id)
            throws ItemNotFoundException {

        Item item = searchById(id);

        if (!(item instanceof FoundItem)) {
            throw new IllegalArgumentException(
                "This item is not reported as FOUND."
            );
        }

        FoundItem foundItem = (FoundItem) item;

        if (foundItem.isClaimed()) {
            throw new IllegalStateException(
                "This item has already been claimed."
            );
        }

        foundItem.claimItem();
    }

    // Remove an item
    public void removeItem(int id)
            throws ItemNotFoundException {

        Item item = searchById(id);

        items.remove(item);
        itemMap.remove(id);
    }
}