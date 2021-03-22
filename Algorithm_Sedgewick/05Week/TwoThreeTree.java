public class TwoThreeTree {
	// 2-node : has one key, two link(left and right), left < key, right > key
	// 3-node : has two key(key1 < key2), three link(left, middle and right),
	// left < key1, key1 <= middle <= key2, right > key2
	
	// Insert
	// 1. At 2-node -> make 3-node
	// 2. At 3-node with parent 2-node -> make 4-node(3key), median key move up parent
	// 3. At 3-node with parent 3-node -> make 4-node(3key), median key move up parent repeat...
	// If root node become 4-node,  It is only case that height of tree incresing
	
	// Difficult to implements...
}