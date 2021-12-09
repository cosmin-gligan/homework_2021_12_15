package unittesting;

import calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
    }

    @ParameterizedTest
    @CsvSource({"5,5,5", "5,10,0"})
    @DisplayName("Tests for addNumbers")
    public void testAddNumbers(String first, String second, String third){

        int result = calculator.addNumbers(Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third));
        assertEquals(15, result);
    }

}
