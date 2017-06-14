import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class SimpleMockTest {

    @Mock
    private Set mockedSet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void someMockMethodTests() {

        //Mocking list -> creating some default List object without any useful implementation
        List mockedList = mock(List.class);

        //calling methods on mock
        mockedList.add("A");
        mockedList.add(2);

        //clearing method calls list
        Mockito.clearInvocations(mockedList);

        //again calling method add
        mockedList.add("AAA");

        //verifying if add method was called ONCE
        verify(mockedList).add(ArgumentMatchers.any());

        //Calling some other methods
        mockedList.remove(0);
        mockedList.remove(1);

        //Checking if method was called at least once
        verify(mockedList, atLeastOnce()).remove(ArgumentMatchers.anyInt());

        //Any class can be mocked
        Map mapMock = mock(Map.class);
        mapMock.put("key", "value");
        mapMock.put("key2", "value2");
        mapMock.put("key3", "value3");

        verify(mapMock, times(3)).put(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        //Using when methods
        //When method get is called on mock, it will return always specified value
        when(mapMock.get(ArgumentMatchers.anyString())).thenReturn("Default Value");
        String whatWasReturned = (String) mapMock.get("");
        assertThat(whatWasReturned).isEqualTo("Default Value");
    }

    //Testing auto mocking via @Mock @ MockitoJunitRunner or @MockitoRule
    @Test
    public void testMockedSet() {

        mockedSet.add(new NullPointerException());

        verify(mockedSet).add(ArgumentMatchers.any());
    }
}

