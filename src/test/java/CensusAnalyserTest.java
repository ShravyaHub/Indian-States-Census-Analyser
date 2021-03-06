import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.nio.file.Paths;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";
    private static final String WRONG_INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Shravya\\Downloads\\StateCensusData.csv";
    private static final String WRONG_INDIA_CENSUS_CSV_FILE_TYPE = "C:\\Users\\Shravya\\Desktop\\StateCensusData.pdf";
    private static final String WRONG_INDIA_CENSUS_FILE_DELIMITER = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";
    private static final String NO_INDIA_CENSUS_FILE_HEADER = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";

    private static final String STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Shravya\\Desktop\\StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Shravya\\Downloads\\StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "C:\\Users\\Shravya\\Desktop\\StateCode.pdf";
    private static final String WRONG_STATE_CODE_FILE_DELIMITER = "C:\\Users\\Shravya\\Desktop\\StateCode.csv";
    private static final String NO_STATE_CODE_FILE_HEADER = "C:\\Users\\Shravya\\Desktop\\StateCode.csv";

    @Test
    public void givenIndianCensusCSV_WhenFileReturnsCorrectNumberOfRecords_ShouldReturnNumberOfRecords()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
            numberOfRecords = censusAnalyser.loadDataWithCommonsCSV(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch(CensusAnalyserException ignored) { }
    }

    @Test
    public void givenIndianCensusData_WhenWrongFile_ShouldThrowException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_INDIA_CENSUS_CSV_FILE_PATH);
        } catch(CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenWrongFileType_ShouldThrowException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_INDIA_CENSUS_CSV_FILE_TYPE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenIncorrectDelimiter_ShouldThrowException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_INDIA_CENSUS_FILE_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenNoHeader_ShouldThrowException() throws RuntimeException {

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(NO_INDIA_CENSUS_FILE_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }

    }

    @Test
    public void givenStateCodeData_WhenFileReturnsCorrectNumberOfRecords_ShouldReturnNumberOfRecords()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
            numberOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch(CensusAnalyserException ignored) { }
    }

    @Test
    public void givenStateCodeData_WhenWrongFile_ShouldThrowException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch(CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenStateCodeData_WhenWrongFileType_ShouldThrowException()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_STATE_CODE_CSV_FILE_TYPE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenStateCodeData_WhenIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(WRONG_STATE_CODE_FILE_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenStateCodeData_WhenNoHeader_ShouldThrowException()  {

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCodeData(NO_STATE_CODE_FILE_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }

    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndianCensusCSV[] indianCensusCSV = new Gson().fromJson(sortedCensusData, IndianCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", indianCensusCSV[0].state);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeData_WhenSortedOnStateCode_ShouldReturnSortedResult()  {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadStateCodeData(STATE_CODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            StateCodesCSV[] stateCodesCSVS = new Gson().fromJson(sortedCensusData, StateCodesCSV[].class);
            Assert.assertEquals("AD", stateCodesCSVS[0].tin);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedByPopulation_ShouldReturnSortedOrder() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser();
            censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateCensusDataSortedByPopulation = censusAnalyzer.getPopulationWiseStateCensusData();
            IndianCensusCSV[] indianCensusCSV = new Gson().fromJson(stateCensusDataSortedByPopulation, IndianCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", indianCensusCSV[0].state);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedByDensity_ShouldReturnSortedOrder() {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser();
            censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateCensusDataSortedByDensity = censusAnalyzer.getDensityWiseStateCensusData();
            IndianCensusCSV[] indianCensusCSVS = new Gson().fromJson(stateCensusDataSortedByDensity, IndianCensusCSV[].class);
            Assert.assertEquals("Bihar", indianCensusCSVS[0].state);
        }catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedByArea_ShouldReturnSortedOrder() throws IOException {
        try {
            CensusAnalyser censusAnalyzer = new CensusAnalyser();
            censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateCensusDataSortedByArea = censusAnalyzer.getAreaWiseStateCensusData();
            IndianCensusCSV[] indianCensusCSVS = new Gson().fromJson(stateCensusDataSortedByArea, IndianCensusCSV[].class);
            Assert.assertEquals("Rajasthan", indianCensusCSVS[0].state);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

}
