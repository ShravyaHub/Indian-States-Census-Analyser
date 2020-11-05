import com.opencsv.bean.CsvBindByName;

public class IndianCensusCSV {

    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "Population")
    public long population;
    @CsvBindByName(column = "AreaInSqKm" )
    public long areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public long densityPerSqKm;

}
