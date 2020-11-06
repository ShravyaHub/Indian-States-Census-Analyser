import com.opencsv.CSVReader;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {

    List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CensusAnalyserException;


}
