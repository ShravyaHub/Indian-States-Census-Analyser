public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        NO_SUCH_FILE, NO_SUCH_FIELD
    }

    ExceptionType exceptionType;

    public CensusAnalyserException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

}
