#Time =  O(mn), m and n are the dimensions
#Space = O(mn)

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m, n = len(board), len(board[0])
        row, col = click
        
        if board[row][col] == 'M': # clicked on mine, mark as X and game over
            board[row][col] = 'X'
            return board
        
        # helper function to count adjacent mines
        def countMines(r: int, c: int) -> int:
            count = 0
            for i in range(r-1, r+2):
                for j in range(c-1, c+2):
                    if i >= 0 and i < m and j >= 0 and j < n and board[i][j] == 'M':
                        count += 1
            return count
        
        # DFS to reveal empty squares and count adjacent mines
        def dfs(r: int, c: int) -> None:
            if r < 0 or r >= m or c < 0 or c >= n or board[r][c] != 'E':
                return
            
            count = countMines(r, c)
            
            if count > 0: # if adjacent mines, mark as number and stop recursion
                board[r][c] = str(count)
            else: # if no adjacent mines, mark as B and continue recursion
                board[r][c] = 'B'
                for i in range(r-1, r+2):
                    for j in range(c-1, c+2):
                        dfs(i, j)
        
        dfs(row, col) # start DFS from the clicked square
        return board
