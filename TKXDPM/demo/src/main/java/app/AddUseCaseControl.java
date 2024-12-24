package app;
public class AddUseCaseControl implements InputBoundary {

  // fields
  private CalculationEntity calculationEntity = null;
  // methods

  private OutputBoundary ob = null;

  public AddUseCaseControl() {
    ob = new AddUIConsoleOutput();
  }

  @Override
  public void execute(RequestData requestData) {
    String strNumber1 = requestData.strNumber1;
    String strNumber2 = requestData.strNumber2;

    if (isThamDinh(strNumber1) && isThamDinh(strNumber2)) {
      int numNumber1 = Integer.parseInt(strNumber1);
      int numNumber2 = Integer.parseInt(strNumber2);

      ResponseData responseData = new ResponseData();
      responseData.strNumber1 = strNumber1;
      responseData.strNumber2 = strNumber2;
      responseData.operation = requestData.operation; // Lưu phép tính được chọn

      // Chọn phép toán dựa trên operation
      switch (requestData.operation) {
        case "add":
          calculationEntity = new CalculationEntity(numNumber1, numNumber2);
          float sumResult = calculationEntity.add();
          responseData.sumResult = sumResult;
          ob.outputSuccess(responseData);
          break;
        case "sub":
          calculationEntity = new CalculationEntity(numNumber1, numNumber2);
          float subResult = calculationEntity.sub();
          responseData.subResult = subResult;
          ob.outputSuccess(responseData);
          break;
        case "div":
          calculationEntity = new CalculationEntity(numNumber1, numNumber2);
          float divResult = calculationEntity.div();
          responseData.divResult = divResult;
          ob.outputSuccess(responseData);
          break;
        case "mul":
          calculationEntity = new CalculationEntity(numNumber1, numNumber2);
          float mulResult = calculationEntity.mul();
          responseData.mulResult = mulResult;
          ob.outputSuccess(responseData);
          break;
        default:
          responseData.content = "Invalid operation";
          ob.outputError(responseData);
          break;
      }

    } else {
      // Dữ liệu đầu vào không hợp lệ
      ResponseData resError = new ResponseData();
      resError.content = "ERROR_INPUT";
      resError.strNumber1 = strNumber1;
      resError.strNumber2 = strNumber2;
      ob.outputError(resError);
    }
  }

  private boolean isThamDinh(String str) {

    if (str == null || str.isEmpty()) {
      return false;
    }
    try {
      Integer.parseInt(str); // cố gắng chuyển chuỗi sang số nguyên
      return true; // nếu thành công thì chuỗi là số nguyên
    } catch (NumberFormatException e) {
      return false; // nếu có lỗi NumberFormatException thì không phải số nguyên
    }

  }

}
