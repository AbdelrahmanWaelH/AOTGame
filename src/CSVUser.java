import java.io.*;
public class CSVUser {
public static void main(String args[]){
	String file1 = "src\\titans.csv";
	String file2 = "src\\weapons.csv";
	BufferedReader reader = null;
	String line = "";
	
	try{
		reader = new BufferedReader(new FileReader(file1));
		while ((line = reader.readLine())!= null){
			
		}
	}
	catch(Exception e){
		
	}
	finally{
		
	}
	
	
	
}
}
