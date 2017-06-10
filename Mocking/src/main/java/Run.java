import java.util.Arrays;
import java.util.List;

public class Run {

    public static void main(String[] args) {

        SomeExternalServiceImplementation s = new SomeExternalServiceImplementation
                (id -> Arrays.asList("A", "BA", "C", "D", "EA", "F", "G")); //quick implementation

        List<String> retrieved = s.retrieveFilteredDataFromDB("f");

        for(String string : retrieved){
            System.out.println(string);
        }
    }
}
