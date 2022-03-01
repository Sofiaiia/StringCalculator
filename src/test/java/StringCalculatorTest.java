import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    @Test
    public void EmptyString(){
        String s = "";
        StringCalculator calc = new StringCalculator();
        int empty = calc.add(s);
        Assertions.assertEquals(0,empty);
    }

    @Test
    public void oneNumber(){
        String s = "1";
        StringCalculator calc = new StringCalculator();
        int one = calc.add(s);
        Assertions.assertEquals(1,one);
    }

    @Test
    public void twoNumbers(){
        String s = "1,2";
        StringCalculator calc = new StringCalculator();
        int two = calc.add(s);
        Assertions.assertEquals(3,two);
    }

    @Test
    public void manyNumbers(){
        String s = "1,2,3";
        StringCalculator calc = new StringCalculator();
        int multi = calc.add(s);
        Assertions.assertEquals(6,multi);
    }

    @Test
    public void newLineSplit(){
        String s = "1\n2,3";
        StringCalculator calc = new StringCalculator();
        int multi = calc.add(s);
        Assertions.assertEquals(6,multi);
    }

    @Test
    public void newDelimiterSplit(){
        String s = "//;\n1;2";
        StringCalculator calc = new StringCalculator();
        int multi = calc.add(s);
        Assertions.assertEquals(3,multi);
    }

    @Test()
    public void negativeNumber(){
        String s = "-1,2,3";
        StringCalculator calc = new StringCalculator();

        NumberFormatException exeption = assertThrows(NumberFormatException.class, () ->{
            calc.add(s);
        });

        assertEquals("Negatives not allowed -1", exeption.getMessage());
    }
}
