import java.text.DecimalFormat;
import java.util.Objects;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator c = new Calculator();
        String response;
        do {
            c.performOperation();
            System.out.println("Result: " + c.getResult());
            System.out.println("Thanks for calculating! Again? [y/n]");
            response = System.console().readLine();
        } while (Objects.equals(response, "y"));
    }
}
