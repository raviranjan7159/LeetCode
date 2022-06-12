import java.util.ArrayDeque;
import java.util.Deque;

/*
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerilizeandDesrializeBinaryTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		TreeNode node = new TreeNode(3);
		root.right = node;
		node.left = new TreeNode(4);
		node.right = new TreeNode(5);

		SerilizeandDesrializeBinaryTree serilizeandDesrializeBinaryTree = new SerilizeandDesrializeBinaryTree();

		String serialize = serilizeandDesrializeBinaryTree.serialize(root);
		System.out.println(serialize);
		root = serilizeandDesrializeBinaryTree.deserialize(serialize);

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			System.out.println(root.val);
			if (root.left != null) {
				queue.offer(root.left);
			}
			if (root.right != null) {
				queue.offer(root.right);
			}
		}
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}

		StringBuilder serializeTree = new StringBuilder();

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		serializeTree.append(root.val).append("R");
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.left != null) {
				serializeTree.append(root.left.val).append("l");
				queue.offer(root.left);
			} else {
				serializeTree.append("#");
			}

			if (root.right != null) {
				serializeTree.append(root.right.val).append("r");
				queue.offer(root.right);
			} else {
				serializeTree.append("#");
			}
		}
		return serializeTree.toString();

	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		TreeNode root = null;
		if (data == null || data.length() == 0) {
			return root;
		}
		int val = 0;
		int sign = 1;
		Deque<TreeNode> queue = new ArrayDeque<>();
		TreeNode node = null;
		TreeNode parent = null;
		int noOfChild = 0;
		for (char c : data.toCharArray()) {
			if (c == '-') {
				sign = -1;
			} else if (Character.isDigit(c)) {
				val = val * 10 + (c - '0');
			} else {
				if (c == 'R') {
					root = new TreeNode(val * sign);
					queue.offer(root);
				} else if (c == 'l') {
					node = new TreeNode(val * sign);
					parent = queue.peek();
					parent.left = node;
					queue.offer(node);
					noOfChild++;
				} else if (c == 'r') {
					node = new TreeNode(val * sign);
					parent = queue.poll();
					parent.right = node;
					noOfChild = 0;
					queue.offer(node);
				} else if (c == '#') {
					noOfChild++;
					if (noOfChild == 2) {
						queue.poll();
						noOfChild = 0;
					}
				}

				val = 0;
				sign = 1;
			}
		}
		return root;
	}
}
