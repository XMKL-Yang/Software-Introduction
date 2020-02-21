package assignment4;
/**
 * Implements a B-Tree class.
 * @author ferrie
 *
 */



public class bTree {
	bNode root=null;					// Default constructor
	
/**
 * addNode method - wrapper for rNode
 */	
	
	// the argument is of type gBall
	// modify the addNode method to accommodate the gBall object
	public void addNode(gBall iBall) {		// This is a wrapper to hide
		root = rNode(root,iBall);			// having to deal with root node.
	}									// Calls actual method below.
/**
 * rNode method - recursively adds a new entry into the B-Tree
 */	
	private bNode rNode(bNode root, gBall iBall) {
//
//  Termination condition for recursion.  We have descended to a leaf
//  node of the tree (or the tree may initially be empty).  In either case,
//	create a new node --> copy over data, set successor nodes to null.
//
		if (root==null) {
			bNode node = new bNode();	// Create a new node
			node.iBall = iBall;			// Copy data
			node.left = null;			// Successors to null.
			node.right = null;
			root=node;					// Node created is root
			return root;				// of new (sub) tree.
		}
//
//	Not at a leaf node, so traverse to the left if data being
//  added is strictly less than data at current node.
//
		else if (iBall.bSize< root.iBall.bSize) {
			root.left = rNode(root.left,iBall);
		}
//
//  If greater than or equal, then traverse to the right.  Keep
//  recursing until a node is found with no successors.
		else {
			root.right = rNode(root.right,iBall);
		}
		return root;
	}
	
/**
 * inorder method - inorder traversal via call to recursive method
 */
	
	public void inorder() {			// This is a wrapper for traverse_
		traverse_inorder(root);		// inorder to hide details of the
	}								// root node.
	
/**
 * traverse_inorder method - recursively traverses tree in order and prints each node.
 * Order: Descend following left successor links, returning back to the current
 *        root node (where a specific action takes place, e.g., printing data),
 *        and then repeat the descent along right successor links.
 */
	
	private void traverse_inorder(bNode root) {
		if (root.left != null) traverse_inorder(root.left);
		System.out.println(root.iBall);
		if (root.right != null) traverse_inorder(root.right);
	}
	
/**
 * preorder method - preorder traversal via call to recursive method
 * 
 */
	
	public void preorder() {		// As above, a wrapper for traverse_
		traverse_preorder(root);	// pre-order.
	}
	
/**
 * traverse_preorder method - recursively traverses tree in preorder and prints each node.
 * Order: Similar approach to traverse_inorder, except that the pattern is now
 *        do at root, then traverse left followed by traverse right.
 */

	public void traverse_preorder(bNode root) {
		System.out.println(root.iBall);
		if (root.left != null) traverse_preorder(root.left);
		if (root.right != null) traverse_preorder(root.right);
	}
	
/**
 * postorder method - postorder traversal via call to recursive method
 */
	
	public void postorder() {		// As above, a wrapper for traverse_
		traverse_postorder(root);	// post-order.
	}
	
/**
 * traverse_postorder method - recursively traverses tree in postorder and prints each node.
 * Order: Similar approach to traverse_inorder, except that the pattern is now
 *        traverse left, then traverse right, followed by do at root.
 
 */
	
	public void traverse_postorder(bNode root) {
		if (root.left != null) traverse_postorder(root.left);
		if (root.right != null) traverse_postorder(root.right);
		System.out.println(root.iBall);
	}
	
// Example of a nested class //
	
	public class bNode {
		gBall iBall;
		bNode left;
		bNode right;
	}
	
	//create a new method based on the in-order traversal routine that 
	//scans the B-Tree and checks the status of each gBall
	public boolean isRunning() {           // returns true if simulation still running
		return areRunning(root);
		
	}
		
		public boolean areRunning(bNode root) {   
			// this is a recursive method that check the status of each ball
		
		if(root.left != null) 	if(areRunning(root.left)) return true;
		if(root.iBall.isAlive()) return true;
		if(root.right != null) 	if(areRunning(root.right)) return true;
		return false;
		}

	public void moveSort() {
		move(root,0);
	}
	
	public double move(bNode root, double x) {
		if(root.left != null)  x=move(root.left,x);
		root.iBall.bmove(x);
		x+=root.iBall.getsize()*2;
		if(root.right != null) x=move(root.right,x);
		return x;
	}
	
	
		
	
	
} 	


