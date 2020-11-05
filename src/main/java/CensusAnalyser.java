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

    int numberOfRecords;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<IndianCensusCSV> iterator = this.getCSVFileIterator(reader, IndianCensusCSV.class);
            Iterable<IndianCensusCSV> iterable = () -> iterator;
            numberOfRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        } catch (NoSuchFileException noSuchFileException) {
            if (!csvFilePath.contains(".csv"))
                throw new CensusAnalyserException("Please enter proper file type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (RuntimeException runtimeException) {
            throw new CensusAnalyserException("Exception due to incorrect delimiter position", CensusAnalyserException.ExceptionType.NO_SUCH_FIELD);
        }
        return numberOfRecords;
    }

    public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            Iterator<StateCodesCSV> iterator = this.getCSVFileIterator(reader, StateCodesCSV.class);
            Iterable<StateCodesCSV> iterable = () -> iterator;
            numberOfRecords = (int) StreamSupport.stream(iterable.spliterator(), false).count();
        } catch (NoSuchFileException noSuchFileException) {
            if (!csvFilePath.contains(".csv"))
                throw new CensusAnalyserException("Please enter proper file type", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Enter proper file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
        return numberOfRecords;
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (RuntimeException runtimeException) {
            throw new CensusAnalyserException("Exception due to incorrect delimiter position", CensusAnalyserException.ExceptionType.NO_SUCH_FIELD);
        }
    }

}
