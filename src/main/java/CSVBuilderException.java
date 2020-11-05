public class CSVBuilderException extends Exception{

    enum ExceptionType {
        NO_SUCH_FIELD
    }

    ExceptionType exceptionType;

    public CSVBuilderException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

}
