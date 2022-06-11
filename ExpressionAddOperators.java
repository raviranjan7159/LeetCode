import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/expression-add-operators/
 */

public class ExpressionAddOperators {

	public static void main(String[] args) {
		ExpressionAddOperators sample = new ExpressionAddOperators();
		List<String> result = sample.addOperators("123", 6);
		result.forEach(System.out::println);
	}

	public List<String> addOperators(String num, int target) {

		List<String> result = new ArrayList<>();

		char numArray[] = num.toCharArray();
		dfs(numArray, 0, 0l, 0l, new StringBuilder(), result, target);
		return result;
	}

	private void dfs(char[] numArray, int index, long prev, long res, StringBuilder path, List<String> result,
			int target) {

		int n = numArray.length;
		if (index == n) {
			if (target == res) {
				result.add(path.toString());
			}
			return;
		}

		long num1 = 0;
		int size = path.length();
		if (index > 0) {
			path.append("+");
		}
		for (int i = index; i < n; i++) {
			if (i > index && numArray[index] == '0') {
				break;
			}

			num1 = num1 * 10 + (numArray[i] - '0');
			path.append(numArray[i]);
			if (index == 0) {
				dfs(numArray, i + 1, num1, num1, path, result, target);
			} else {
				path.setCharAt(size, '+');
				dfs(numArray, i + 1, num1, res + num1, path, result, target);

				path.setCharAt(size, '-');
				dfs(numArray, i + 1, -num1, res - num1, path, result, target);

				path.setCharAt(size, '*');
				dfs(numArray, i + 1, num1 * prev, res - prev + prev * num1, path, result, target);

			}

		}
		path.delete(size, path.length());

	}

}
