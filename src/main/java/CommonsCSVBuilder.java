import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;

public class CommonsCSVBuilder<E>  {

    public Iterable<CSVRecord> getCSVFileIterator(Reader reader) throws CSVBuilderException, IOException {
        try {
            Iterable<CSVRecord> csvIterator = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            return csvIterator;
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CSVBuilderException("Exception due to incorrect delimiter position", CSVBuilderException.ExceptionType.NO_SUCH_FIELD);
        }
    }
}
