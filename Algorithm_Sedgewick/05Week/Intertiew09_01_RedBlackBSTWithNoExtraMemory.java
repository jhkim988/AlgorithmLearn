// Q1. Red–black BST with no extra memory.
// Describe how to save the memory for storing the color information
// when implementing a red–black BST. 

// sol)
// 1. Use Least Significant Bit.
// The Node pointers should contain even address on most platforms.
// But in Java, this is not possible since Java does not expose the bits of its pointers

// 2. Exchange left child and right child if it is red node.
// If order mismatch occured, It means that this nodes is red.
// But If Both left child and right child are null,
// we don't know whether this node is red or black.

// 3. Particular Rule
// Let Node p(parents) link Node r(left child red node) by red link.
// Change key as p.key + p.key - r.key ( = r')
// Then r' > p.key, and order mismatch occur.
// If we find order mismatch, it is red node and we can go back original key
// as p.key + p.key - r' ( = r.key)
// But this method can only use if key is operable '+', '-'.
public class Intertiew09_01_RedBlackBSTWithNoExtraMemory {
	
}
