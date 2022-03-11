import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private final Logger logger;
    private final Scanner scan;

    public StringCalculator(Logger logger){
        this.logger = logger;
        this.scan = new Scanner(System.in);
    }

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        }else{
            String[] array = splitString(numbers);
            int total = 0;
            for (String s: array) {
                 int number = Integer.parseInt(s);
                 if(number > 0){
                     total += number;
                     if (number > 1000){
                         logger.log(number);
                     }
                 }else {
                     throw new NumberFormatException("Negatives not allowed " + number);
                 }
            }
            return total;
        }
    }

    private String[] splitString(String num){
        if(num.startsWith("//")){
            Matcher matcher = Pattern.compile("//(.)\\n(.*)").matcher(num);
            matcher.matches();
            String delimiter = matcher.group(1);
            String toSplit = matcher.group(2);
            return toSplit.split(Pattern.quote(delimiter));
        }else{
            return num.split(",|\n");
        }
    }

    public void welcomeMessage(){
        System.out.print("Welcome to our String Calculator, Enter number separated with ',' or '\n' enter an own delimiter with structure '//<delimiter>\\\\n<numbers>'");
    }

    public boolean getInput(){
        if (!scan.hasNextLine()){
            return false;
        }
        String input = scan.nextLine();
        if(input.equals("")){
            return false;
        }
        String s = input.split(" ")[1];
        s = s.replace("'","");
        int result = 0;
        if(s.startsWith("//")){
            Matcher matcher = Pattern.compile("\\[([^\\]]+)").matcher(s);
            List<String> tags = new ArrayList<>();

            int pos = -1;
            while (matcher.find(pos+1)){
                pos = matcher.start();
                tags.add(matcher.group(1));
            }
            String toSplit = s.split("\\.")[1];
            for (String find:tags){
                toSplit = toSplit.replace(find,",");
            }

            result = add(toSplit);
        }else{
            result = add(s);
        }

        System.out.print("The result is " + result);
        return true;
    }

    public void loop(){
        boolean toContinue;
        do{
             toContinue = getInput();
        }while(toContinue);
    }

    public static void main(String[] args) {
        Logger logger = new LoggerStub();
        StringCalculator calc = new StringCalculator(logger);
        calc.welcomeMessage();
        calc.loop();
    }
}