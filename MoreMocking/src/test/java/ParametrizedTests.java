import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(JUnitParamsRunner.class)
public class ParametrizedTests {

    @Test
    @Parameters({"2,3,5", "5,-1,4","0,0,0"})
    public void firstParametrizedTest(int firstNumber, int secondNumber, int expectedResult) {

        SimpleClass simpleClass = new SimpleClass();
        int result = simpleClass.add(firstNumber, secondNumber);
        assertThat(result).isEqualTo(expectedResult);
    }

    private Object[][] dataForTesting(){
        return new Object[][] {
                {null, null, null},
                {null, 2, null},
                {2, null, null}
        };
    }

    @Test
    @Parameters(method = "dataForTesting")
    public void secondParametrizedTest(Integer firstNumber, Integer secondNumber, Integer expectedResult) {
        SimpleClass simpleClass = new SimpleClass();
        int result = simpleClass.add(firstNumber, secondNumber);
        assertThat(result).isEqualTo(expectedResult);
    }
}