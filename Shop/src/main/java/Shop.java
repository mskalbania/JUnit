import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shop {
    private final List<Item> listOfItems;
    private final SoldItemsDBI ourDatabase;
    private final DiscountService discountService;

    public Shop(List<Item> listOfItems, SoldItemsDBI ourDatabase) {
        this.listOfItems = new ArrayList<>();
        this.ourDatabase = ourDatabase;
        discountService = null;
    }

    public Shop(List<Item> listOfItems, SoldItemsDBI ourDatabase, DiscountService discountService) {
        this.listOfItems = listOfItems;
        this.ourDatabase = ourDatabase;
        this.discountService = discountService;
    }

    public void addProduct(Item item) {
        listOfItems.add(item);
    }

    public Item sellItem(String nameOdSoldItem) {
        Item result = null;
        for (Item item : listOfItems) {
            if (item.getName().equals(nameOdSoldItem)) {
                result = item;
            }
        }
        ourDatabase.saveItem(result);
        listOfItems.remove(result);
        return result;
    }

    public boolean wasItemSold(Item item) {
        try {
            return ourDatabase.isInDatabase(item);
        } catch (IOException e) {
            throw new IllegalStateException("ILLEGAL STATE");
        }
    }

    int getDiscount(String item) {
        listOfItems.sort(Comparator.comparing(Item::getName));
        int index = Collections.binarySearch(listOfItems,new Item(item,0), Comparator.comparing(Item::getName));
        return discountService.getDiscount(listOfItems.get(index));
    }
}