
public class WeaponCSV extends CSVUser {
	private final String weaponFile  = "src\\weapons.csv";
	public WeaponCSV(){
		
	}
	public void readCSV(){
		super.readCSV(weaponFile);
	}
	public void writeCSV(String[] lines){
		super.writeCSV(lines, weaponFile);
	}
	public static void main (String args[]){
			WeaponCSV w = new WeaponCSV();
			w.readCSV();
			
			TitanCSV t = new TitanCSV();
			t.readCSV();
	}
}
