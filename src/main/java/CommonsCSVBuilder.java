import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CommonsCSVBuilder implements ICommonsCSVBuilder {

    @Override
    public List<CSVRecord> getCSVFileList(Reader reader) throws CensusAnalyserException {
        try {
            Iterable<CSVRecord> csvIterator = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
            return StreamSupport.stream(csvIterator.spliterator(), false).collect(Collectors.toList());
        } catch (IllegalArgumentException | IOException illegalArgumentException) {
            throw new CensusAnalyserException("Exception due to incorrect delimiter position", CensusAnalyserException.ExceptionType.NO_SUCH_FIELD);
        }
    }
}
