import java.io.IOException;

public interface SoldItemsDBI {
    boolean saveItem(Item item);

    boolean isInDatabase(Item item) throws IOException;
}