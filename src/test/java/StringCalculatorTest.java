import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StringCalculatorTest {

    @Test
    public void EmptyString(){
        String s = "";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int empty = calc.add(s);
        Assertions.assertEquals(0,empty);
    }

    @Test
    public void oneNumber(){
        String s = "1";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int one = calc.add(s);
        Assertions.assertEquals(1,one);
    }

    @Test
    public void twoNumbers(){
        String s = "1,2";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int two = calc.add(s);
        Assertions.assertEquals(3,two);
    }

    @Test
    public void manyNumbers(){
        String s = "1,2,3";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int multi = calc.add(s);
        Assertions.assertEquals(6,multi);
    }

    @Test
    public void newLineSplit(){
        String s = "1\n2,3";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int multi = calc.add(s);
        Assertions.assertEquals(6,multi);
    }

    @Test
    public void newDelimiterSplit(){
        String s = "//;\n1;2";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        int multi = calc.add(s);
        Assertions.assertEquals(3,multi);
    }

    @Test()
    public void negativeNumber(){
        String s = "-1,2,3";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);

        NumberFormatException exeption = assertThrows(NumberFormatException.class, () ->{
            calc.add(s);
        });

        assertEquals("Negatives not allowed -1", exeption.getMessage());
    }
    @Test
    public void testLogger(){
        String s = "1001";
        Logger logger = mock(Logger.class);
        StringCalculator calc = new StringCalculator(logger);
        calc.add(s);
        verify(logger,times(1)).log(1001);
    }

    @Test
    public void welcomeText(){
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringCalculator.main(new String[]{});
        assertEquals("Welcome to our String Calculator, Enter number separated with ',' or '\n' enter an own delimiter with structure '//<delimiter>\\\\n<numbers>'",out.toString());
    }

    @Test
    public void testInput(){
        ByteArrayInputStream in = new ByteArrayInputStream("scalc '1,2,3'\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringCalculator.main(new String[]{});

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.print("Welcome to our String Calculator, Enter number separated with ',' or '\n' enter an own delimiter with structure '//<delimiter>\\\\n<numbers>'");
        printWriter.print("The result is 6");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        assertEquals(expected, out.toString().trim());
    }
    @Test
    public void testMultipleInput(){

        ByteArrayInputStream in = new ByteArrayInputStream("scalc '1,2,3'\nscalc '1,2,4'\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringCalculator.main(new String[]{});

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.print("Welcome to our String Calculator, Enter number separated with ',' or '\n' enter an own delimiter with structure '//<delimiter>\\\\n<numbers>'");
        printWriter.print("The result is 6");
        printWriter.print("The result is 7");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        assertEquals(expected, out.toString().trim());
    }
    @Test
    public void testComplexInput(){
        ByteArrayInputStream in = new ByteArrayInputStream("scalc '//[***][%%%].1***2%%%4'\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        StringCalculator.main(new String[]{});

        StringWriter expectedStringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(expectedStringWriter);

        printWriter.print("Welcome to our String Calculator, Enter number separated with ',' or '\n' enter an own delimiter with structure '//<delimiter>\\\\n<numbers>'");
        printWriter.print("The result is 7");
        printWriter.close();

        String expected = expectedStringWriter.toString();

        assertEquals(expected, out.toString().trim());
    }
}
