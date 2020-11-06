import com.opencsv.bean.CsvBindByName;

public class StateCodesCSV {

    @CsvBindByName(column = "SrNo")
    public long srNo;
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "Name" )
    public long name;
    @CsvBindByName(column = "TIN")
    public String tin;

}
