import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:\\Users\\Shravya\\Downloads\\StateCensusData.csv";
    private static final String WRONG_CSV_FILE_TYPE = "C:\\Users\\Shravya\\Desktop\\StateCensusData.pdf";
    private static final String WRONG_DELIMITER = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";
    private static final String NO_HEADER = "C:\\Users\\Shravya\\Desktop\\StateCensusData.csv";

    private static final String STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Shravya\\Desktop\\StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\Shravya\\Downloads\\StateCode.csv";
    private static final String WRONG_STATE_CODE_CSV_FILE_TYPE = "C:\\Users\\Shravya\\Desktop\\StateCode.pdf";

    @Test
    public void givenIndianCensusCSV_WhenFileReturnsCorrectNumberOfRecords_ShouldReturnNumberOfRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch(CensusAnalyserException ignored) { }
    }

    @Test
    public void givenIndianCensusData_WhenWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch(CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_TYPE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenIncorrectDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenIndianCensusData_WhenNoHeader_ShouldThrowException() {

        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(NO_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FIELD, censusAnalyserException.exceptionType);
        }

    }

    @Test
    public void givenStateCodeData_WhenFileReturnsCorrectNumberOfRecords_ShouldReturnNumberOfRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numberOfRecords = censusAnalyser.loadIndiaCensusData(STATE_CODE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch(CensusAnalyserException ignored) { }
    }

    @Test
    public void givenStateCodeData_WhenWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_STATE_CODE_CSV_FILE_PATH);
        } catch(CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_FILE, censusAnalyserException.exceptionType);
        }
    }

    @Test
    public void givenStateCodeData_WhenWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException expectedException = ExpectedException.none();
            expectedException.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_STATE_CODE_CSV_FILE_TYPE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, censusAnalyserException.exceptionType);
        }
    }

}
