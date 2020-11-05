import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser extends Throwable {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndianCensusCSV> iterator = csvBuilder.getCSVFileIterator(reader, IndianCensusCSV.class);
            return getCount(iterator);
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterable iterable = new CommonsCSVBuilder().getCSVFileIterator(reader);
            return getCount(iterable);
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    private int getCount(Iterable iterable) {
        int numberOfRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return numberOfRecords;
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        int numberOfRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        return numberOfRecords;
    }

}
