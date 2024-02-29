import java.io.*;

public abstract class CSVUser {
    protected String splitter = ",";

    public CSVUser(){
    	
    }

    public void readCSV(String fileChosen) {    	
        try (BufferedReader br = new BufferedReader(new FileReader(fileChosen))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitter);
                
                for (String field : data) 
                    System.out.print(field + " ");
                System.out.println(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSV(String[] lines, String fileChosen) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileChosen))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine(); // Add newline after each line
            }
            System.out.println("Data has been written to " + fileChosen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
