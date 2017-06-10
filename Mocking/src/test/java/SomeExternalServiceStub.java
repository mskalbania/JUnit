import java.util.Arrays;
import java.util.List;

public class SomeExternalServiceStub implements SomeExternalService{

    @Override
    public List<String> retrieveSomeDataFromDB(String someId) {
        return Arrays.asList("ABC", "BC", "BNA", "ACV", "WXG");
    }
}
