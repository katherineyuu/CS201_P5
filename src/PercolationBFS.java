import java.util.*;
public class PercolationBFS extends PercolationDefault {

    public PercolationBFS(int n) {
        super(n);
    }

    public void search(int row, int col) {
        if (!isOpen(row, col)) {
            return;
        }
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};

        Queue<int[]> qp = new LinkedList<>();
        myGrid[row][col] = FULL;
        qp.add(new int[]{row,col});
        
        while (qp.size() != 0) {
            int[] p = qp.remove();
            for (int k = 0; k < rowDelta.length; k++) {
                row = p[0] + rowDelta[k];
                col = p[1] + colDelta[k];
                if (inBounds(row, col) && isOpen(row, col)) {
                    myGrid[row][col] = FULL;
                    qp.add(new int[] {row, col});
                }
            }
        }
    }
    
}
