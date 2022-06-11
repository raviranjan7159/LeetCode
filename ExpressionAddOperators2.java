import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/expression-add-operators/
 */

public class ExpressionAddOperators2 {

	public static void main(String[] args) {
		ExpressionAddOperators2 sample = new ExpressionAddOperators2();
		List<String> result = sample.addOperators("123", 6);
		result.forEach(System.out::println);
	}

	class Node {
		long currentNumber;
		long result;
		int index;
		String num;

		public Node(long currentNumber, long result, int index, String num) {
			super();
			this.currentNumber = currentNumber;
			this.result = result;
			this.index = index;
			this.num = num;
		}

	}

	public List<String> addOperators(String num, int target) {

		List<String> result = new ArrayList<>();

		char numArray[] = num.toCharArray();
		int n = numArray.length;

		Deque<Node> queue = new ArrayDeque<>();

		queue.offer(new Node(0l, 0l, 0, ""));

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			if (node.index == n) {
				if (target == node.result) {
					result.add(node.num.toString());
				}
				continue;
			}

			long num1 = 0;
			String numStr = node.num;
			for (int i = node.index; i < n; i++) {
				if (i > node.index && numArray[node.index] == '0') {
					break;
				}
				num1 = num1 * 10 + (numArray[i] - '0');
				if (node.index == 0) {
					numStr = String.valueOf(num1);
					Node current = new Node(num1, num1, i + 1, numStr);
					queue.offer(current);
				} else {
					Node current = new Node(num1, node.result + num1, i + 1, node.num + "+" + num1);
					queue.offer(current);

					current = new Node(-num1, node.result - num1, i + 1, node.num + "-" + num1);
					queue.offer(current);

					current = new Node(node.currentNumber * num1,
							node.result - node.currentNumber + node.currentNumber * num1, i + 1, node.num + "*" + num1);
					queue.offer(current);
				}

			}

		}

		return result;
	}

}
