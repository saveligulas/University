package org.example.ub3.twov2;

/**
 * Allows you to see if a operation relating to a MarriageBureau was successful. Also gives you the reason why the operation failed and allows you to see the Error and the ErrorMessage.
 */
public class MarriageBureauResponse {
    private boolean _success;
    private Error _errorCode;
    private String _message;

    /**
     * Constructor creates a unsuccessful response with Unexpected Error
     */
    public MarriageBureauResponse() {
        _success = false;
        _errorCode = Error.UNEXPECTED_ERROR;
        _message = "";
    }

    /**
     *
     */
    public void wasSuccess() {
        _success = true;
        _errorCode = null;
    }

    public void setErrorCode(Error errorCode) {
        _errorCode = errorCode;
        _message = errorCode.message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public boolean isSuccess() {
        return _success;
    }

    public Error getErrorCode() {
        return _errorCode;
    }

    public String getMessage() {
        return _message;
    }
}
