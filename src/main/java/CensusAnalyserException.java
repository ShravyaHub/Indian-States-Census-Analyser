public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        WRONG_FILE_TYPE, NO_SUCH_FILE, NO_SUCH_FIELD
    }

    ExceptionType exceptionType;

    public CensusAnalyserException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

}
