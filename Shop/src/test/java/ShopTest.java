import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTest {

    public static final String NAME_OF_BOTTLE_OF_WATER = "Bottle of water";
    public static final int PRICE_OF_WATER = 2;
    public static final Item BOTTLE_OF_WATER = new Item(NAME_OF_BOTTLE_OF_WATER, PRICE_OF_WATER);
    private Shop shop;

    private List<Item> listOfItems;
    private DiscountService discountService;
    private SoldItemsDBI ourDatabase;

    @Before //Method annotated like this will be called before every test
    public void setUp() throws Exception {
        listOfItems = new ArrayList<>();
        ourDatabase = Mockito.mock(SoldItemsDBI.class);
        shop = new Shop(listOfItems, ourDatabase);
    }

    @Test //This annotation mark our method as a test.
    public void shouldCreateNonNullObject() {
        assertThat(shop).isNotNull();
    }
    
    @Test
    public void shouldRemoveItemWhenItWasSold() {
        shop.addProduct(BOTTLE_OF_WATER);
        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);
        assertThat(result).isEqualTo(BOTTLE_OF_WATER);
        assertThat(listOfItems).doesNotContain(BOTTLE_OF_WATER);
    }

    @Test
    public void shouldReturnNullWhenItemNotExists() {
        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);

        assertThat(result).isNull();
    }

    @Test
    public void shouldSaveSoldItemToDatabaseWhenSellItemCalled() {
        shop.addProduct(BOTTLE_OF_WATER);
        Item result = shop.sellItem(NAME_OF_BOTTLE_OF_WATER);

        Mockito.verify(ourDatabase).saveItem(ArgumentMatchers.any(Item.class));
    }

    @Test
    public void shouldReturnTrueWhenItemWasSold() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(BOTTLE_OF_WATER)).thenReturn(true);
        boolean result = shop.wasItemSold(BOTTLE_OF_WATER);
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenItmWasNotSold(){
        boolean result = shop.wasItemSold(BOTTLE_OF_WATER);
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenIsInDatabaseReturnFalse() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(BOTTLE_OF_WATER)).thenReturn(false);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenConnectionToDBWasLost() throws IOException {
        Mockito.when(ourDatabase.isInDatabase(ArgumentMatchers.any(Item.class)))
                .thenThrow(IllegalStateException.class);

        shop.wasItemSold(BOTTLE_OF_WATER);
    }

    @Test
    public void shouldReturnMinusOneWhenDiscountCheckerReturnedMinusOne(){
        Mockito.when(discountService.getDiscount(BOTTLE_OF_WATER)).thenReturn(10);
        int discount = discountService.getDiscount(BOTTLE_OF_WATER);


    }

}