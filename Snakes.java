//Time =  O(n^2), because we need to flatten the board and iterate over all n^2 squares in the worst case
//Space = O(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flatBoard = new int[n*n];
        int idx = 0;
        boolean reverse = false; // flag to keep track of reading direction
        
        // flatten the board to a 1D array
        for (int i = n - 1; i >= 0; i--) {
            if (!reverse) {
                for (int j = 0; j < n; j++) {
                    flatBoard[idx++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flatBoard[idx++] = board[i][j];
                }
            }
            reverse = !reverse;
        }
        
        // BFS to find shortest path
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n*n];
        queue.offer(0);
        visited[0] = true;
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n*n - 1) { // reached the end
                    return moves;
                }
                for (int j = 1; j <= 6 && curr+j < n*n; j++) {
                    int next = flatBoard[curr+j] == -1 ? curr+j : flatBoard[curr+j]-1;
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }
        
        return -1; // no path found
    }
}
