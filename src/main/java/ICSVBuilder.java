import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {

    Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException, CSVBuilderException, IOException;

}
