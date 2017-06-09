import java.io.IOException;
import java.util.List;

public class Shop {
    private final List<Item> listOfItems;
    private final SoldItemsDBI ourDatabase;

    public Shop(List<Item> listOfItems, SoldItemsDBI ourDatabase) {
        this.listOfItems = listOfItems;
        this.ourDatabase = ourDatabase;
    }

    public void addProduct(Item item) {
        listOfItems.add(item);
    }

    public Item sellItem(String nameOdSoldItem) {
        Item result = null;
        for(Item item: listOfItems){
            if(item.getName().equals(nameOdSoldItem)){
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
        }catch (IOException e){
            throw new IllegalStateException("ILLEGAL STATE");
        }
    }
}