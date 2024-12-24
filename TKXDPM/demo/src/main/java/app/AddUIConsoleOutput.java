
package app;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AddUIConsoleOutput implements OutputBoundary {
    // fields
    private PrintWriter stdOut = null;

    public AddUIConsoleOutput() {
        this.stdOut = new PrintWriter(new OutputStreamWriter(System.out), true);
    }

    @Override
    public void outputSuccess(ResponseData responseData) {
        success(responseData);
    }

    @Override
    public void outputError(ResponseData responseData) {
        error(responseData);
    }

    // implementation - support

    // Xử lý khi có lỗi
    private void error(ResponseData responseData) {
        stdOut.println("Error: " + responseData.content); // In thông báo lỗi
        stdOut.println("Input number1: " + responseData.strNumber1);
        stdOut.println("Input number2: " + responseData.strNumber2);
        stdOut.flush();
    }

    // Xử lý khi thành công, chỉ in kết quả phép toán đã chọn
    private void success(ResponseData responseData) {
        stdOut.println("Calculation Result:");
        switch (responseData.operation) { // Giả sử operation là tên phép toán (add, sub, mul, div)
            case "add":
                stdOut.println("Addition result: " + responseData.sumResult);
                break;
            case "sub":
                stdOut.println("Subtraction result: " + responseData.subResult);
                break;
            case "mul":
                stdOut.println("Multiplication result: " + responseData.mulResult);
                break;
            case "div":
                stdOut.println("Division result: " + responseData.divResult);
                break;
            default:
                stdOut.println("Invalid operation.");
        }
        stdOut.flush();
    }
}
