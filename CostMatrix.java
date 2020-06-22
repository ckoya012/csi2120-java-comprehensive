import java.util.ArrayList;

public class CostMatrix {
    private int[][] costValues;

    public CostMatrix(int size) {
        costValues = new int[size][size];
    }

    public void setCostMatrix(ArrayList<int[]> frame1Int, ArrayList<int[]> frame2Int) {
        for (int f1 = 0; f1 < frame1Int.size(); f1++) {
            int f1_x = frame1Int.get(f1)[0];
            int f1_y = frame1Int.get(f1)[1];
            int f1_width = frame1Int.get(f1)[2];
            int f1_height = frame1Int.get(f1)[3];
    
            Point p1 = new Point(f1_x, f1_y, f1_width, f1_height);
            double p1x = p1.calculateXCoordinate();
            double p2y = p1.calculateYCoordinate();
    
            int[] row = new int[frame1Int.size()];
    
            for (int f2 = 0; f2 < frame2Int.size(); f2++) {
                int f2_x = frame2Int.get(f2)[0];
                int f2_y = frame2Int.get(f2)[1];
                int f2_width = frame2Int.get(f2)[2];
                int f2_height = frame2Int.get(f2)[3];
    
                Point p2 = new Point(f2_x, f2_y, f2_width, f2_height);
                double q1x = p2.calculateXCoordinate();
                double q2y = p2.calculateYCoordinate();
    
                double inside = Math.pow((q1x - p1x), 2) + Math.pow((q2y - p2y), 2);
                int value = (int)Math.sqrt(inside);
    
                row[f2] = value;
            }
            costValues[f1] = row;
        }
    }

    public int[][] getCostMatrix() {
        return costValues;
    }
}