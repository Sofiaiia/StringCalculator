public class StringCalculator {

    public int add(String numbers){
        if(numbers.isEmpty()){
            return 0;
        }else if(numbers.contains(",")){
            String[] array = numbers.split(",",2);
            return toInteger(array[0]) + toInteger(array[1]);
        }else{
            return toInteger(numbers);
        }
    }

    private int toInteger(String s){
        return Integer.parseInt(s);
    }
}
