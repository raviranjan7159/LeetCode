/*
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 */
public class NumMatrix {

	public static void main(String[] args) {
		int matrix[][] = new int[][] { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 },
				{ 1, 0, 3, 0, 5 } };
		NumMatrix numMatrix = new NumMatrix(matrix);
		System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
	}

	private int[][] matrix;
	private int[][] sum;

	public NumMatrix(int[][] matrix) {
		this.matrix = matrix;
		process();
	}

	private void process() {
		sum = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i == 0) {
					sum[i][j] = matrix[i][j] + (j == 0 ? 0 : sum[i][j - 1]);
				} else {
					sum[i][j] = (j == 0 ? 0 : sum[i][j - 1]) + sum[i - 1][j] - (j == 0 ? 0 : sum[i - 1][j - 1])
							+ matrix[i][j];
				}
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {

		int result = sum[row2][col2];
		if (col1 > 0) {
			result = result - sum[row2][col1 - 1];
		}
		if (row1 > 0) {
			result = result - sum[row1 - 1][col2];
		}

		if (row1 > 0 && col1 > 0) {
			result = result + sum[row1 - 1][col1 - 1];
		}
		return result;

	}
}
