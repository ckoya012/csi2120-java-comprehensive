public class HungarianAlgorithm {

    public int findMinimum (BaseNumber[] arr) {
        int min = arr[0].getVal();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getVal() < min) {
                min = arr[i].getVal();
            }
        }
        return min;
    }
    
    public void step1RowReduction (BaseNumber[] row) {
        int minValue = findMinimum(row);
        for (int i = 0; i < row.length; i++) {
            row[i].setVal(row[i].getVal() - minValue);
        }
    }

    public void step2ColReduction (BaseNumber[] col) {
        int minValue = findMinimum(col);
        for (int i = 0; i < col.length; i++) {
            col[i].setVal(col[i].getVal() - minValue);
        }
    }

    public void step4ShiftZeroes(MatrixInt m) {
        // Find smallest uncovered value
        int minValue = 0;
        for (int rowIndex = 0; rowIndex < m.getN(); rowIndex++) {
            for (int colIndex = 0; colIndex < m.getN(); colIndex++) {
                if (m.allRows[rowIndex].getLine() == false && m.allCols[colIndex].getLine() == false) {
                    if (minValue == 0 || m.matrix[rowIndex][colIndex].getVal() < minValue) {
                        minValue = m.matrix[rowIndex][colIndex].getVal();
                    }
                }
            }
        }

        // Subtract minValue from all uncovered values
        for (int rowIndex = 0; rowIndex < m.getN(); rowIndex++) {
            for (int colIndex = 0; colIndex < m.getN(); colIndex++) {
                BaseNumber baseNum = m.matrix[rowIndex][colIndex];
                int val = baseNum.getVal();
                if (m.allRows[rowIndex].getLine() == false && m.allCols[colIndex].getLine() == false) {
                    baseNum.setVal(val - minValue);
                } else if (m.allRows[rowIndex].getLine() == true && m.allCols[colIndex].getLine() == true) {
                    baseNum.setVal(val + minValue);
                }
            }
        }

        for (int r = 0; r < m.getN(); r++) {
            m.allRows[r].setLine(false);
            m.allRows[r].setTick(false);
            m.allCols[r].setLine(false);
            m.allCols[r].setTick(false);
        }

        m.createBaseCols();
        OptimalAssignment opt = new OptimalAssignment(m.getN());
        opt.step3Optimal(m);
    }

    public int[][] step5FinalAssignment(MatrixInt original, MatrixInt m) {
        int size = original.getN();
        int[][] values = new int[size][2];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (m.matrix[row][col].getAssigned() == true && m.matrix[row][col].getCrossedOut() == false) {
                 int[] index = new int[]{row, col};
                 values[row] = index;
                }
            }      
        }
        return values;
    }

    public int calculateCost(MatrixInt original, int[][] values) {
        int result = 0;
        for (int i = 0; i < original.getN(); i++) {
            int r = values[i][0];
            int c = values[i][1];
            result += original.matrix[r][c].getVal();
        }
        return result;
    }
}