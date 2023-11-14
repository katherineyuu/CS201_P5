import java.util.*;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size) {
        VTOP = size * size;
        VBOTTOM = size * size + 1;
        finder.initialize(size * size + 2);
        myFinder = finder;
        myGrid = new boolean[size][size];
        myOpenCount = 0;
        for (boolean[] row : myGrid) {
            Arrays.fill(row, false);
        }
    }

    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length)
            return false;
        if (col < 0 || col >= myGrid[0].length)
            return false;
        return true;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        if (!isOpen(row, col)) {
            myGrid[row][col] = true;
            myOpenCount++;
        }
            int cellNum = row*myGrid.length + col;
            int[] rowDelta = {-1, 1, 0, 0};
            int[] colDelta = {0, 0, -1, 1};
            for (int i = 0; i < rowDelta.length; i++) {
                int newRow = row + rowDelta[i];
                int newCol = col + colDelta[i];
                if (inBounds(newRow, newCol) && isOpen(newRow, newCol)) {
                    int otherCellNum = newRow*myGrid.length+ newCol;
                    myFinder.union(cellNum, otherCellNum);
                }
            }
            if (row == 0) {
                myFinder.union(cellNum, VTOP);
            }
            if (row == myGrid.length - 1) {
                myFinder.union(cellNum, VBOTTOM);
            }
        }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        int num = row*myGrid.length + col;
        return myFinder.connected(VTOP, num);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

}
