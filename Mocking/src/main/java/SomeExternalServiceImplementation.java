import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class SomeExternalServiceImplementation {

    private SomeExternalService service;

    public SomeExternalServiceImplementation(SomeExternalService service) {
        this.service = service;
    }

    public List<String> retrieveFilteredDataFromDB(String id) {
        List<String> filteredData;
        List<String> allData = service.retrieveSomeDataFromDB(id);

        filteredData = allData.stream()
                .filter(string -> string.contains("A")) //some irrelevant filter
                .collect(Collectors.toList());

        return filteredData;
    }

}
