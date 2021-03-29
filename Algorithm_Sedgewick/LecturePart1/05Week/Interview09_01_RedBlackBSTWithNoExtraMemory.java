// Q1. Red-black BST with no extra memory.
// Describe how to save the memory for storing the color information
// when implementing a red-black BST. 

// sol)
// 1. Use Least Significant Bit.
// The Node pointers should contain even address on most platforms.
// But in Java, this is impossible since Java does not expose the bits of its pointers

// 2. Exchange left child and right child if it is red node.
// If order mismatch occured, It means that this nodes is red.
// But If Both left child and right child are null,
// we don't know whether this node is red or black.
// And the problem with this method is there is an overhead.
// When we go down the Red-Black Tree, we must always check order mismatch, twice compare occur.
// Use this method at your own expense.
// We solve the problem that child node is null case.
// Case 1. Node has one null-child
// - By order mismatch of non-null nodes, we can determine.
// Case 2. Node has two null-children.
// - Consider that we work at left leaning red-black tree.
// If parent node is red, both children nodes must be black nodes.
// If parent node is black, right child node must be black nodes,
// By insert algorithm, left child node is red nodes.

// 3. Particular Rule
// Let Node p(parents) link Node r(left child red node) by red link.
// Change key as p.key + p.key - r.key ( = r')
// Then r' > p.key, and order mismatch occur.
// If we find order mismatch, it is red node and we can go back original key
// as p.key + p.key - r' ( = r.key)
// But this method can only use if key is operable '+', '-'.
public class Interview09_01_RedBlackBSTWithNoExtraMemory {
	
}
