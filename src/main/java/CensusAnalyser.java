import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<IndianCensusCSV> indianCensusCSVList = csvBuilder.getCSVFileList(reader, IndianCensusCSV.class);
            return getCount(indianCensusCSVList);
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICommonsCSVBuilder commonsCSVBuilder = CommonsCSVBuilderFactory.createCSVBuilder();
            List<CSVRecord> stateCodesCSVList = commonsCSVBuilder.getCSVFileList(reader);
            return stateCodesCSVList.size();
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    private int getCount(Iterable iterable) {
        return (int) StreamSupport.stream(iterable.spliterator(), false).count();
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> iterable = () -> iterator;
        return (int) StreamSupport.stream(iterable.spliterator(), false).count();
    }

}
