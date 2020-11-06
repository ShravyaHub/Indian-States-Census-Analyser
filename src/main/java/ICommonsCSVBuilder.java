import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface ICommonsCSVBuilder {
    List<CSVRecord> getCSVFileList(Reader reader) throws CensusAnalyserException;
}
