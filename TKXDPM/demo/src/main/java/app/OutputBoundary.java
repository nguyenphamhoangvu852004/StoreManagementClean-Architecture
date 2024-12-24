package app;
public interface OutputBoundary {
    void outputSuccess(ResponseData responseData);

    void outputError(ResponseData responseData);
}
