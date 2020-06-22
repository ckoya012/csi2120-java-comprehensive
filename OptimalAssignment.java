public class OptimalAssignment {

    private int matrixSize;

    public OptimalAssignment(int n) {
        matrixSize = n;
    }

    public void step3Optimal(MatrixInt m) {
        
        // Clear current state of BaseNumbers
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++)  {
                m.matrix[row][col].setCrossedOut(false);
                m.matrix[row][col].setAssigned(false);
            }
        }

        // Set new state for BaseNumbers
        for (int row = 0; row < matrixSize; row++) {
            boolean first = true;
            for (int col = 0; col < matrixSize; col++)  {
                if (m.matrix[row][col].getCrossedOut()) {
                    continue;
                }

                if (m.matrix[row][col].getVal() == 0) {
                    if (first) {
                        first = false;
                        m.matrix[row][col].setAssigned(true);
                        
                        for (int rr = 0; rr < matrixSize; rr++) {
                            if (rr == row) {
                                continue;
                            }

                            if (m.matrix[rr][col].getVal() == 0) {
                                m.matrix[rr][col].setCrossedOut(true);
                            }
                        }
                    } else {
                        m.matrix[row][col].setCrossedOut(true);
                    }
                }
            }
        }
        m.createBaseCols();
        tickRows(m);
    }

    
    public void tickRows(MatrixInt m) {
        for (int row = 0; row < matrixSize; row++) {
            m.allRows[row].setTick(true);
            for (int col = 0; col < matrixSize; col++)  {
                if (m.matrix[row][col].getVal() == 0) {
                    if (m.matrix[row][col].getAssigned() && !m.matrix[row][col].getCrossedOut()) {
                        m.allRows[row].setTick(false);
                    }
                }
            }
        }

        m.createBaseCols();
        tickCols(m);
    }

    public void tickCols(MatrixInt m) {
        // Ticking columns
        for (int row = 0; row < matrixSize; row++) {
            if (!m.allRows[row].getTick()) {
                continue;
            }

            for (int col = 0; col < matrixSize; col++)  {
                if (m.matrix[row][col].getCrossedOut()) {
                    m.allCols[col].setTick(true);
                }
            }
        }

        m.createBaseCols();

        // Ticking rows again
        for (int col = 0; col < matrixSize; col++) {
            if (m.allCols[col].getTick() != true) {
                continue;
            }

            for (int row = 0; row < matrixSize; row++)  {
                if (m.matrix[row][col].getAssigned()) {
                    m.allRows[row].setTick(true);
                }
            }
        }

        drawLines(m);
    }

    public void drawLines(MatrixInt m) {
        System.out.println("----Step 3 in progress----");

        System.out.println("The following rows are covered by lines:");
        for (int row = 0; row < matrixSize; row++) {
            if (m.allRows[row].getTick() != true) {
                m.allRows[row].setLine(true);
                System.out.println(row);
            }
        }

        System.out.println("The following columns are covered by lines:");
        for (int col = 0; col < matrixSize; col++) {
            if (m.allCols[col].getTick() == true) {
                m.allCols[col].setLine(true);
                System.out.println(col);
            }
        }

        m.createBaseCols();
    }

    public boolean checkForStep4(MatrixInt m) {
        int counter = 0;

        for (int row = 0; row < matrixSize; row++) {
            if (m.allRows[row].getLine() == true) {
                counter++;
            }
            if (m.allCols[row].getLine() == true) {
                counter++;
            }
        }

        if (counter < matrixSize) {
            return true;
        }

        return false;
    }
}