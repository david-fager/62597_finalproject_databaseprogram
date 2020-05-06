package DB;

import java.util.ArrayList;

public class ResponseObject {
    private int errorCode;
    private String statusMessage;
    private boolean success;
    private String responseString;
    private String[] responseStringArray;
    private ArrayList<String[]> responseArraylist;

    public ResponseObject(int errorCode, String statusMessage, boolean success, String responseString, String[] responseStringArray, ArrayList<String[]> responseArraylist) {
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
        this.success = success;
        this.responseString = responseString;
        this.responseStringArray = responseStringArray;
        this.responseArraylist = responseArraylist;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseString() {
        return responseString;
    }

    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    public String[] getResponseStringArray() {
        return responseStringArray;
    }

    public void setResponseStringArray(String[] responseStringArray) {
        this.responseStringArray = responseStringArray;
    }

    public ArrayList<String[]> getResponseArraylist() {
        return responseArraylist;
    }

    public void setResponseArraylist(ArrayList<String[]> responseArraylist) {
        this.responseArraylist = responseArraylist;
    }

}
