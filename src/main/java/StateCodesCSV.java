import com.opencsv.bean.CsvBindByName;

public class StateCodesCSV {

    @CsvBindByName(column = "SrNo")
    public String srNo;
    @CsvBindByName(column = "State")
    public long state;
    @CsvBindByName(column = "Name" )
    public long name;
    @CsvBindByName(column = "TIN")
    public long tin;

}
