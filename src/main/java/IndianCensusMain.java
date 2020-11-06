public class IndianCensusMain {

    public static void main(String[] args) throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int totalIndianCensusRecords = censusAnalyser.loadIndiaCensusData("C:\\Users\\Shravya\\Desktop\\StateCensusData.csv");
        int totalStateCodeRecords = censusAnalyser.loadStateCodeData("C:\\Users\\Shravya\\Desktop\\StateCode.csv");
        System.out.println("Total Indian Census Records = " + totalIndianCensusRecords);
        System.out.println("Total State Code Records = " + totalStateCodeRecords);
    }

}
