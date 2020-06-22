public class MatrixInt {
    private int n;
    public BaseNumber[][] matrix;
    public BaseRow[] allRows;
    public BaseCol[] allCols;

    public MatrixInt(int n) {
        this.n = n;
        matrix = new BaseNumber[n][n];
        allRows = new BaseRow[n];
        allCols = new BaseCol[n];
    }

    public int getN() {
        return this.n;
    }

    public void createBaseRows() {
        for (int row = 0; row < n; row++) {
            BaseRow newBaseRow = new BaseRow();
            newBaseRow.row = this.matrix[row];
            this.allRows[row] = newBaseRow;
        }
    }

    public void printBaseRows() {
        System.out.println("------BASE ROWS-----");
        for (int row = 0; row < n; row++) {
            System.out.println(allRows[row] + "TICK: " + allRows[row].getTick() + " LINE: " + allRows[row].getLine() + "\n");
        }
    }

    public void printBaseCols() {
        System.out.println("------BASE COLS-----");
        for (int index = 0; index < allRows.length; index++) {
            System.out.println(allCols[index] + "TICK: " + allCols[index].getTick() + " LINE: " + allCols[index].getLine() + "\n");
        }
    }

    public void createBaseCols() {
        for (int index = 0; index < allRows.length; index++) {
            BaseCol newBaseCol = new BaseCol();

            if (allCols[index] != null) {
                allCols[index].col = null;
                BaseNumber[] columnValues = new BaseNumber[n];

                for (int col = 0; col < n; col++) {
                    columnValues[col] = matrix[col][index];
                }

                newBaseCol.col = columnValues;
                newBaseCol.setTick(allCols[index].getTick());
                newBaseCol.setLine(allCols[index].getLine());
                allCols[index] = newBaseCol;
                
            } else {
                BaseNumber[] columnValues = new BaseNumber[n];
                for (int col = 0; col < n; col++) {
                    columnValues[col] = matrix[col][index];
                }
                newBaseCol.col = columnValues;
                allCols[index] = newBaseCol;
            }
        }
    }

    public String printMatrix() {
        String str = "";
        for (int i = 0; i < n; i++) {
            str += "\n";
            for (int j = 0; j < n; j++) {
                str += matrix[i][j].getVal() + " ";
            }
        }
        return str;
    }

    public String toString() {
        String str = "";
        // Row, col, val
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                str += "ROW: " + i + " COL: " + j + matrix[i][j] + "\n";
            }
        }

        str += "\n------BASE ROWS-----\n";

        for (int row = 0; row < n; row++) {
            str += this.allRows[row] + "\n";
        }

        str += "\n------BASE COLS-----\n";

        for (int col = 0; col < n; col++) {
            str += this.allCols[col] + "\n";
        }

        str += "\n------MATRIX-----";

        // Actual matrix
        for (int i = 0; i < n; i++) {
            str += "\n";
            for (int j = 0; j < n; j++) {
                str += matrix[i][j].getVal() + " ";
            }
        }

        return str;
    }
}

