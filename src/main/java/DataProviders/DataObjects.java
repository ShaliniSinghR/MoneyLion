package DataProviders;

public class DataObjects {

    private static String response;
    private static int statusCode;
    private static String URL;
    private static String requestPayload;
    private static String testName;
    private static String typeOfMethod;


    public static String getTypeOfMethod() {
        return typeOfMethod;
    }

    public static void setTypeOfMethod(String typeOfMethod) {
        DataObjects.typeOfMethod = typeOfMethod;
    }

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        DataObjects.testName = testName;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public static void setRequestPayload(String requestPayload) {
        DataObjects.requestPayload = requestPayload;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        DataObjects.response = response;
    }

    public static int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        DataObjects.statusCode = statusCode;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        DataObjects.URL = URL;
    }

}
