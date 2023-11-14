import java.util.*;
public class PercolationDFS extends PercolationDefault {

    public PercolationDFS(int n) {
        super(n);
    }

    protected void search(int row, int col) {
        if (!inBounds(row, col)) {
            return;
        }
         if (!isOpen(row, col) || isFull(row, col)) {
            return;
        }
		Stack <int[]> stack = new Stack<>();
        int[] rowDelta = {-1, 1, 0, 0};
        int[] colDelta = {0, 0, -1, 1};
        myGrid[row][col] = FULL;
        stack.push(new int[] {row, col});

        while (stack.size() != 0) {
            int[] coords = stack.pop();
            for (int k = 0; k < rowDelta.length; k++) {
                row = coords[0] + rowDelta[k];
                col = coords[1] + colDelta[k];
                if (inBounds(row,col) && myGrid[row][col] == OPEN) {
                    stack.push(new int[] {row, col});
                    myGrid[row][col] = FULL;

                }
            }
        }
	}
}