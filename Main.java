import java.io.*;
import java.util.*;

public class Main {

   public static void main(String args[]) throws IOException {  
    // Generate an integer array of each file
    String frame1Filename = "frame1_3.csv";
    ReadFile f_read_1 = new ReadFile();

    f_read_1.createFileArrayList(frame1Filename);
    ArrayList<int[]> frame1Int = f_read_1.getFileArrayListInt();

    String frame2Filename = "frame2_3.csv";
    ReadFile f_read_2 = new ReadFile();

    f_read_2.createFileArrayList(frame2Filename);
    ArrayList<int[]> frame2Int = f_read_2.getFileArrayListInt();

    // Check if it's an nxn matrix
    if (frame1Int.size() != frame2Int.size()) {
        System.out.println("Your frames are not of the same size.");
        return;
    }

    int size = frame1Int.size();

    // Generate 2D array of the cost matrix
    CostMatrix cMatrix = new CostMatrix(frame1Int.size());
    cMatrix.setCostMatrix(frame1Int, frame2Int);
    int[][] costValues = cMatrix.getCostMatrix();

    // Create matrix with the 2D costValues array
    MatrixInt matrix = new MatrixInt(costValues.length);
    MatrixInt originalMatrix = new MatrixInt(costValues.length);

    for (int i = 0; i < costValues.length; i++) {
        for (int j = 0; j < costValues[i].length; j++) {
            BaseNumber baseNum = new BaseNumber(costValues[i][j]);
            BaseNumber baseNumOr = new BaseNumber(costValues[i][j]);
            matrix.matrix[i][j] = baseNum;
            originalMatrix.matrix[i][j] = baseNumOr;
        }
    }
    
    System.out.println("*********** MATRIX ***********" + matrix.printMatrix());

    HungarianAlgorithm alg = new HungarianAlgorithm();
    matrix.createBaseRows();
    matrix.createBaseCols();
    originalMatrix.createBaseRows();
    originalMatrix.createBaseCols();

    for (int i = 0; i < matrix.getN(); i++) {
		alg.step1RowReduction(matrix.matrix[i]);
    }
    
    System.out.print("*********** Step 1 Results ***********");
    System.out.println(matrix.printMatrix());


	for (int row = 0; row < size; row++) {
		BaseNumber[] columnValues = new BaseNumber[size];
		for (int col = 0; col < size; col++) {
            columnValues[col] = matrix.matrix[col][row];
		}
		alg.step2ColReduction(columnValues);
    }
    System.out.print("*********** Step 2 Results ***********");
    System.out.println(matrix.printMatrix());

    System.out.println("*********** Step 3 Results ***********");
    OptimalAssignment step3 = new OptimalAssignment(size);
    step3.step3Optimal(matrix);
    System.out.println("Step 3 Matrix: " + matrix.printMatrix());
    boolean step4 = step3.checkForStep4(matrix);

    if (step4 == true) {
        alg.step4ShiftZeroes(matrix);
        System.out.print("*********** Step 4 Results ***********");
        System.out.println(matrix.printMatrix());
    }

    System.out.println("*********** Step 5 Results ***********");

    int[][] index = alg.step5FinalAssignment(originalMatrix, matrix);
    String str = "The indexes are: ";
    for (int i = 0; i < index.length; i++) {
        str += "[";
        for (int j = 0; j < index[i].length; j++) {
            str += index[i][j] + ",";
        }
        str = str.substring(0, str.length() - 1);
        str += "] ";
    }
    System.out.println(str);

    int calculatedCost = alg.calculateCost(originalMatrix, index);
    System.out.println("The result is: " + calculatedCost);
    System.out.println(originalMatrix.printMatrix());






    // System.out.println("--------------ROWS--------------");
    // matrix.printBaseRows();
    // System.out.println("--------------COLS--------------");
    // matrix.printBaseCols();
    



 }
}

    
