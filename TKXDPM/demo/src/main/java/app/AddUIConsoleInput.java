
package app;
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AddUIConsoleInput {

    // fields
    private PrintWriter stdOut = null;
    private BufferedReader stdIn = null;
    private InputBoundary ib = null;

    // methods
    public AddUIConsoleInput() {
        stdOut = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(System.out)), true);
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        ib = new AddUseCaseControl();
    }

    public void input() throws IOException {
        // Hiển thị menu và yêu cầu người dùng chọn phép toán
        stdOut.println("Select operation:");
        stdOut.println("1. Addition (+)");
        stdOut.println("2. Subtraction (-)");
        stdOut.println("3. Multiplication (*)");
        stdOut.println("4. Division (/)");
        stdOut.print("Enter your choice (1/2/3/4): ");
        stdOut.flush();
        String operationChoice = stdIn.readLine();

        // Yêu cầu người dùng nhập số thứ nhất
        stdOut.print("Input number1: ");
        stdOut.flush();
        String strNumber1 = stdIn.readLine();

        // Yêu cầu người dùng nhập số thứ hai
        stdOut.print("Input number2: ");
        stdOut.flush();
        String strNumber2 = stdIn.readLine();

        // Tạo đối tượng RequestData và gán các giá trị đã nhập
        RequestData requestData = new RequestData();
        requestData.strNumber1 = strNumber1;
        requestData.strNumber2 = strNumber2;

        // Gán phép toán đã chọn vào RequestData
        switch (operationChoice) {
            case "1":
                requestData.operation = "add"; // Cộng
                break;
            case "2":
                requestData.operation = "sub"; // Trừ
                break;
            case "3":
                requestData.operation = "mul"; // Nhân
                break;
            case "4":
                requestData.operation = "div"; // Chia
                break;
            default:
                stdOut.println("Invalid choice! Defaulting to addition.");
                break;
        }

        // Gọi phương thức execute để thực thi
        ib.execute(requestData);
    }
}
