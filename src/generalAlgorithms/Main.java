package generalAlgorithms;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ShuntingYard shuntingYard = new ShuntingYard();
        String[] rpn = shuntingYard.infixToRPN("6 * 3 - (4 - 5) + 2");
        System.out.println(Arrays.toString(rpn));

        int result = shuntingYard.evaluateRPN(rpn);
        System.out.println(result);
    }
}
