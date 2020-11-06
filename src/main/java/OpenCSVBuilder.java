import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder{

    @Override
    public List getCSVFileList(Reader reader, Class csvClass) throws CensusAnalyserException {
        return this.getCSVBean(reader, csvClass).parse();
    }

    private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CensusAnalyserException("Exception due to incorrect delimiter position", CensusAnalyserException.ExceptionType.NO_SUCH_FIELD);
        } catch (RuntimeException runtimeException) {
            throw new CensusAnalyserException("Exception due to incorrect delimiter position", CensusAnalyserException.ExceptionType.NO_SUCH_FIELD);
        }
    }

}
