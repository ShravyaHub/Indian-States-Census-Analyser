import com.google.gson.Gson;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List<IndianCensusCSV> indianCensusCSVList = null;
    List<StateCodesCSV> stateCodesCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            indianCensusCSVList = csvBuilder.getCSVFileList(reader, IndianCensusCSV.class);
            return indianCensusCSVList.size();
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodesCSVList = csvBuilder.getCSVFileList(reader, StateCodesCSV.class);
            return stateCodesCSVList.size();
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public int loadDataWithCommonsCSV(String csvFilePath) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
                ICommonsCSVBuilder commonsCSVBuilder = CommonsCSVBuilderFactory.createCSVBuilder();
                List<CSVRecord> csvFileList = commonsCSVBuilder.getCSVFileList(reader);
                return csvFileList.size();
            } catch (IOException ioException) {
                throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
            }
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndianCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(indianCensusCSVList, censusComparator);
        String sortedStateCensusAsJSON = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusAsJSON;
    }

    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (stateCodesCSVList == null || stateCodesCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<StateCodesCSV> stateCodeComparator = Comparator.comparing(stateCode -> stateCode.tin);
        this.sort(stateCodesCSVList, stateCodeComparator);
        String sortedStateCodeJson = new Gson().toJson(stateCodesCSVList);
        return sortedStateCodeJson;
    }

    public String getPopulationWiseStateCensusData() throws CensusAnalyserException {
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0)
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndianCensusCSV> stateCensusComparator = Comparator.comparing(stateCensus -> stateCensus.population);
        this.sortPopulation(indianCensusCSVList, stateCensusComparator);
        String sortedStateCensusJson = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusJson;
    }

    public String getDensityWiseStateCensusData() throws CensusAnalyserException {
        if (indianCensusCSVList == null || indianCensusCSVList.size() == 0)
            throw new CensusAnalyserException("No CSV Data Found", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        Comparator<IndianCensusCSV> stateCensusComparator = Comparator.comparing(stateCensus -> stateCensus.densityPerSqKm);
        this.sortPopulation(indianCensusCSVList, stateCensusComparator);
        String sortedStateCensusJson = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusJson;
    }

    private <E> void sortPopulation(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E census1 = csvList.get(j);
                E census2 = csvList.get(j + 1);
                if (comparator.compare(census1, census2) < 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }

    private <E> void sort(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E census1 = csvList.get(j);
                E census2 = csvList.get(j + 1);
                if (comparator.compare(census1, census2) > 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }
}
