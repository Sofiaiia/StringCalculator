import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private final Logger logger;

    public StringCalculator(Logger logger){
        this.logger = logger;
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
}