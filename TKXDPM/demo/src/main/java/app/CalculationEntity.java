package app;
public class CalculationEntity {
    private float number1;
    private float number2;

    // Constructor
    public CalculationEntity(float number1, float number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    // Phép cộng
    public float add() {
        return number1 + number2;
    }

    // Phép trừ
    public float sub() {
        return number1 - number2;
    }

    // Phép chia
    public float div() {
        if (number2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return (float) number1 / number2; // Cần ép kiểu sang float để kết quả là số thực
    }

    // Phép nhân
    public float mul() {
        return number1 * number2;
    }
}
