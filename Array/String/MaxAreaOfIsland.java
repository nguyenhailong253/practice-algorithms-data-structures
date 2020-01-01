// Technique: DFS with recursion or loop with stack
/**
 * Iterative solution O(row * col) time complexity, O(1) space
 */

class Solution {

    // int[][] store directions: up down left right
    private final int[][] connectedDirections = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int maxIslandArea = 0;

        // double loop through each cell
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1)
                    maxIslandArea = Math.max(maxIslandArea, getIslandAreaByCountingConnectedCell(grid, row, col));
            }
        }

        return maxIslandArea;
    }

    private int getIslandAreaByCountingConnectedCell(int[][] grid, int curRow, int curCol) {
        // create a stack
        Stack<Integer[]> stack = new Stack<>();

        // create area value = 0
        int islandArea = 0;

        // add current cell to stack
        stack.push(new Integer[] { curRow, curCol });
        grid[curRow][curCol] = -1;

        // while stack is not empty
        while (!stack.isEmpty()) {
            // pop
            Integer[] currentCell = stack.pop();
            int row = currentCell[0];
            int col = currentCell[1];

            islandArea++;
            addConnectedCellToStack(stack, grid, row, col);
        }
        return islandArea;
    }

    private void addConnectedCellToStack(Stack<Integer[]> stack, int[][] grid, int row, int col) {
        // for loop 4 directions
        for (int[] direction : this.connectedDirections) {
            // if cell not visited then add to stack
            int neighborRow = row + direction[0];
            int neighborCol = col + direction[1];
            if (neighborRow < grid.length && neighborRow >= 0 && neighborCol < grid[0].length && neighborCol >= 0
                    && grid[neighborRow][neighborCol] == 1) {
                stack.push(new Integer[] { neighborRow, neighborCol });
                grid[neighborRow][neighborCol] = -1;
            }
        }
    }
}

/**
 * Recursion solution, O(row * col) time complexity, O(1) space
 */

class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int maxIslandArea = 0;

        // double loop through each cell
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                maxIslandArea = Math.max(maxIslandArea, getIslandAreaByCountingConnectedCell(grid, row, col));
            }
        }
        return maxIslandArea;
    }

    private int getIslandAreaByCountingConnectedCell(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == -1
                || grid[row][col] == 0)
            return 0;

        grid[row][col] = -1;

        return (1 + getIslandAreaByCountingConnectedCell(grid, row + 1, col)
                + getIslandAreaByCountingConnectedCell(grid, row - 1, col)
                + getIslandAreaByCountingConnectedCell(grid, row, col + 1)
                + getIslandAreaByCountingConnectedCell(grid, row, col - 1));
    }
}