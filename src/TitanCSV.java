
public class TitanCSV extends CSVUser {
	private final String titanFile = "src\\titans.csv";
	public TitanCSV(){
	}
		public void readCSV(){
			super.readCSV(titanFile);
		}
		public void writeCSV(String[] lines){
			super.writeCSV(lines, titanFile);
		}
}
