import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        }else{
            String[] array = splitString(numbers);
            int total = 0;
            int index = 0;
            for (String s: array) {
                total += toInteger(array[index]);
                index++;
            }
            return total;
        }
    }

    private int toInteger(String s){
        return Integer.parseInt(s);
    }

    private String[] splitString(String num){
        System.out.println(num);
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
