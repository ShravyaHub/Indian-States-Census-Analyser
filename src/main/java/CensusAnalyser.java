import com.google.gson.Gson;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List<IndianCensusCSV> indianCensusCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            indianCensusCSVList = csvBuilder.getCSVFileList(reader, IndianCensusCSV.class);
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

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndianCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusAsJSON = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusAsJSON;
    }

    public void sort(Comparator<IndianCensusCSV> censusCSVComparator) {
        for(int index = 0; index < indianCensusCSVList.size() - 1; index++) {
            for(int index2 = 0; index2 < indianCensusCSVList.size() - index - 1; index2++) {
                IndianCensusCSV census1 = indianCensusCSVList.get(index2);
                IndianCensusCSV census2 = indianCensusCSVList.get(index2 + 1);
                if(censusCSVComparator.compare(census1, census2) > 0) {
                    indianCensusCSVList.set(index2, census2);
                    indianCensusCSVList.set(index2 + 1, census1);
                }
            }
        }
    }
}
