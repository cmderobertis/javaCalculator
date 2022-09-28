public class Calculator {
    private double operand1;
    private double operand2;
    private String operator;
    private double result;

    public Calculator() {
    }

   public void performOperation() {
        switch (this.operator) {
            case "+":
                this.result = this.operand1 + this.operand2;
                break;
            case "-":
                this.result = this.operand1 - this.operand2;
                break;
            case "*":
                this.result = this.operand1 * this.operand2;
                break;
            case "/":
                this.result = this.operand1 / this.operand2;
                break;
        }
   }

    public void setOperand1(double value) {
        this.operand1 = value;
    }

    public void setOperand2(double value) {
        this.operand2 = value;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getResult() {
        return result;
    }
}
