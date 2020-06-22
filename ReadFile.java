import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadFile {
    private ArrayList<int[]> frameInt = new ArrayList<int[]>();

    public void createFileArrayList(String filename) {
        
        File file = new File (filename);

        try {
            Scanner inputStream = new Scanner(file);
    
            while (inputStream.hasNext()) {
                String data = inputStream.next();
                String[] row = data.split(",");
                int[] rowInt = new int[row.length-1];
    
                for (int i = 1; i < row.length; i++) {
                    int num = Integer.parseInt(row[i]);
                    rowInt[i-1] = num;
                }
                frameInt.add(rowInt);
            }
            inputStream.close();
    
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filename);
        }
    }

    public ArrayList<int[]> getFileArrayListInt() {
        return frameInt;
    }
}