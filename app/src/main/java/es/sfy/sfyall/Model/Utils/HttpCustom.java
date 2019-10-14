package es.sfy.sfyall.Model.Utils;

import okhttp3.Response;

public class HttpCustom extends RuntimeException {

    private int code;

    public HttpCustom(Response response) {
        this.code = response.code();
    }

    public String getErrorMessage(){

    String message;

    if (code == 404){
        message = "The resource you requested could not be found.";
    }else if (code == 401){
        message = "Invalid API key: You must be granted a valid key";
    } else {
        message = "Unknown error";
    }
    return message;
    }
}
