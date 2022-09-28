import java.text.DecimalFormat;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println("Enter a number");
        String n1 = System.console().readLine();
        System.out.println("Enter an operation");
        String o = System.console().readLine();
        System.out.println("Enter another number");
        String n2 = System.console().readLine();
        c.setOperand1(Double.parseDouble(n1));
        c.setOperand2(Double.parseDouble(n2));
        c.setOperator(o);
        c.performOperation();
        DecimalFormat df = new DecimalFormat("#");
        System.out.println("Result: " + df.format(c.getResult()));
    }
}
