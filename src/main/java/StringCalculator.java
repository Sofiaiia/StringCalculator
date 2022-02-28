public class StringCalculator {

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        }else if(numbers.contains(",")){
            String[] array = numbers.split(",|\n");
            int total = 0;
            int index = 0;
            for (String s: array) {
                total += toInteger(array[index]);
                index++;
            }
            return total;
        }else{
            return toInteger(numbers);
        }
    }

    private int toInteger(String s){
        return Integer.parseInt(s);
    }
}
