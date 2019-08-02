import java.util.concurrent.ThreadLocalRandom;

public class BinarySearchTree {
	public Node root;

	public BinarySearchTree() { // Constructor
		root = null;
	}

	public static void main(String arg[]) throws InterruptedException {
		BinarySearchTree bst = new BinarySearchTree();
		int n = 1000000;
		for (int i = 0; i < n; i++) {
			int rn = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, (Integer.MAX_VALUE - 1) + 1);
			bst.insert(rn);
		}
		System.out.println(bst.height(bst.root));
	}

	public boolean search(int id) {
		Node current = root;
		while (current != null) {
			if (current.key == id) {
				return true;
			} else if (current.key > id) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	// driver method for inserting
	void insert(int k) {
		root = insert2(root, k);
	}

	public Node insert2(Node root, int k) {
		Node newNode = new Node(k);
		Node temp = root;
		// if root
		if (root == null) {
			root = newNode;
			return root;
		}
		// if smaller go left
		if (k < temp.key) {
			temp.left = insert2(root.left, k);
			// else go right
		} else if (k > temp.key) {
			temp.right = insert2(root.right, k);
		}
		return root;
	}

	// finds the deepest leaf by checking subtrees
	int height(Node root) {
		if (root == null)
			return 0;
		// increments height each time
		return (1 + Math.max(height(root.left), height(root.right)));
	}

	// driver method for inserting
	void remove(int k) {
		if (search(k)) {
			// if only root
			if (this.height(root) == 1)
				root = null;
			else
				remove2(this.root, k);
		}
	}

	// removes the specified node
	public Node remove2(Node root, int k) {
		if (root == null)
			return null;
		// goes to the left subtree
		if (k < root.key)
			root.left = remove2(root.left, k);
		// goes to the right subtree
		else if (k > root.key)
			root.right = remove2(root.right, k);
		if (k == root.key) {
			if (root.right == null)
				return root.left;
			else {
				// swaps nodes
				Node node = root.right;
				Node parent = root;
				while (node.left != null) {
					parent = node;
					node = node.left;
				}
				// XOR operator
				root.key = node.key ^ root.key;
				node.key = node.key ^ root.key;
				root.key = node.key ^ root.key;
				if (parent == root)
					parent.right = node.right;
				else
					parent.left = node.right;
			}
		}
		return root;
	}
}
