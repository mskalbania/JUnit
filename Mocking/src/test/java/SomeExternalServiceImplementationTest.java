import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SomeExternalServiceImplementationTest {

    private SomeExternalService service;

    //Using Stub (bad)
    @Test
    public void shouldReturnValuesOnlyContainingLetterAWhenFilteredMethodCalled_STUB() {
        service = new SomeExternalServiceStub();
        SomeExternalServiceImplementation serviceImplementation = new SomeExternalServiceImplementation(service);
        List<String> returnedValues = serviceImplementation.retrieveFilteredDataFromDB("SomeID");
        for (String s : returnedValues) {
            assertThat(s.contains("A")).isTrue();
        }
    }

    //Using Mockito
    @Test
    public void shouldReturnValuesOnlyContainingLetterAWhenFilteredMethodCalled_MOKITO() {
        service = Mockito.mock(SomeExternalService.class);
        Mockito.when(service.retrieveSomeDataFromDB(ArgumentMatchers.anyString()))
                .thenReturn(Arrays.asList("ABC", "BC", "BNA", "ACV", "WXG"));

        SomeExternalServiceImplementation serviceImplementation = new SomeExternalServiceImplementation(service);
        List<String> returnedValues = serviceImplementation.retrieveFilteredDataFromDB("AA");

        for (String s : returnedValues) {
            assertThat(s.contains("A")).isTrue();
        }
    }

    @Test(expected = InputMismatchException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        service = Mockito.mock(SomeExternalService.class);
        Mockito.when(service.retrieveSomeDataFromDB(null))
                .thenThrow(InputMismatchException.class);

        SomeExternalServiceImplementation serviceImplementation = new SomeExternalServiceImplementation(service);
        serviceImplementation.retrieveFilteredDataFromDB(null);
    }

    @Test
    public void shouldReturnEmptyListWhenRetrievedEmptyList(){
        service = Mockito.mock(SomeExternalService.class);
        Mockito.when(service.retrieveSomeDataFromDB(ArgumentMatchers.anyString()))
                .thenReturn(Lists.emptyList());

        SomeExternalServiceImplementation serviceImplementation = new SomeExternalServiceImplementation(service);
        List<String> retrieved = serviceImplementation.retrieveFilteredDataFromDB("any");

        assertThat(retrieved.isEmpty()).isTrue();
    }
}