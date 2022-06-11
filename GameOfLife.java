/*
 * https://leetcode.com/problems/game-of-life/
 */
class GameOfLife {
	private int dir[][] = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
			{ 1, 1 } };

	public void gameOfLife(int[][] board) {

		int m = board.length;
		int n = board[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int numOfLive = noOfNeighborLive(board, i, j);
				if (board[i][j] >= 1) {
					if (numOfLive < 2 || numOfLive > 3) {
						board[i][j] = 2;
					}
				} else if (numOfLive == 3) {
					board[i][j] = -1;
				}

			}
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 2) {
					board[i][j] = 0;
				} else if (board[i][j] == -1) {
					board[i][j] = 1;
				}

			}
		}

	}

	private int noOfNeighborLive(int[][] board, int x, int y) {
		int noOfLive = 0;
		for (int i = 0; i < dir.length; i++) {
			int neighborX = x + dir[i][0];
			int neighborY = y + dir[i][1];

			if (neighborX >= 0 && neighborX < board.length && neighborY >= 0 && neighborY < board[0].length
					&& board[neighborX][neighborY] >= 1) {
				noOfLive++;
			}
		}
		return noOfLive;
	}
}