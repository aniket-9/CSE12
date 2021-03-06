/**
 * Name: Ji Woon Chung
 * Class: CSE 12, Winter 15
 * Date: February 18, 2015
 * login: cs12xbo
 *
 * Assignment Seven
 * File Name: Tree.java
 * Description: Creates a tree and TNode that will take in multiple
 * element/object that the user has inputted. Each element/object that the
 * user inputs will go into the TNode that will be connected with other 
 * TNode, creating parennt and children. Height and balance will also be
 * adjusted every time a TNode is created. . 
*/ 

public class Tree<Whatever extends Base> extends Base {

        /* data fields */
        private TNode root;
        private long occupancy; 
        private String treeName;

        /* debug flag */
        private static boolean debug;

        /* debug messages */
        private static final String ALLOCATE = " - Allocating]\n";
        private static final String AND = " and ";
        private static final String COMPARE = " - Comparing ";
        private static final String INSERT = " - Inserting ";
        private static final String TREE = "[Tree ";


	/**
         * A constructor for the tree
	 *
	 * @param	name: A string that will store in the string 
	 *		that the user has inputted into the terminal.
         * @return	name of the tree
         */
        public Tree (String name) {
		// setting how many element that's in the Tree to zero. 
		occupancy = 0;
		// setting the root to null.
		root = null;
		// treeName will take in the name that the user has 
		// typed into the terminal. 
		treeName = name;
                // if the user turns on the debug message 
		if (debug == true) {
			// A debug message will pop out in the terminal 
			// stating that the tree and the tree name that
			// the user inputted has been allocated. 
			System.err.print( TREE + treeName + ALLOCATE);
		}
        }
	
	/**
        * This method turns off the debug by setting it to be false.
        *
        */
        public static void debugOff () {

                // set debug to false
		debug = false;
			
        }

	/**
        * This method turns on the debug by setting it to be true.
        *
        */
        public static void debugOn () {

                // set debug to true
		debug = true;
        }

        /**
         * Returns the tree's name
	 *
         * @return name of the tree
         */
        public String getName() {
                return treeName;
        }

	/**
        * A method that allows the user to insert TNodes to the tree. This 
	* method also sets the height and balance of the TNodes in the 
	* tree. 
	*
        * @param	Whatever element: Any object that the user has
	*		typed in to the terminal. 
	* @return	Returns true if the method has ran correctly. 
        */
        public boolean insert (Whatever element) {

                // creating a TNode working that will be set to root at the
		// beginning
		TNode working = root;
		// creating two long temporary heights that will take in the
		// values of left and right height. 
		long leftHeight;
		long rightHeight;
		// if there is no root at the beginning
		if (root == null) {
			// setting root to a new created TNode with the element
			// that the user has typed to the terminal. 
			root = new TNode(element);
			// if the debug is on
			if (debug == true) {
				// Prints out the debug message that states
				// that the name that the user has inputted 
				// has been inserted. 
				System.err.println(TREE + treeName + INSERT 
				+ element.getName() + ']');
			}
			return true;
		}
		// a while loop that works only if the current TNode is
		// present in the tree. 
		while (working != null) {
			// if the user turns on debug 
			if (debug == true) {
				// prints out the debug message that will
				// state the tree is comparing the element
				// that the user has inputted with the
				// element that is stored in the tree.
				System.err.println(TREE + treeName + COMPARE 
				+ element.getName() + AND +
				working.data.getName() + ']');
			}
			// if the element that the user has inputted is same
			// as the one that is already stored in the tree
			if (element.equals(working.data)) {
				// if TNode has already been removed
				if (working.hasBeenDeleted == true) {
					// just turn on the current TNode
					// to be not removed. 
					working.hasBeenDeleted = false;
					// increment occupancy because 
					// occupancy has been decremented by 
					// remove method
					occupancy++;
				}
				// then element will just overrides the data
				// in the current TNode. 
				working.data = element;
				break;
			}
			// however, if the element that the user has
			// inputted has a greater value than the one that is
			// stored, then attaches as a bottom right child. 
			else if (element.isGreaterThan(working.data)){
				// if the current TNode does not have a
				// bottom right child
				if (working.right == null) {
					// if the user turned on the debug
					// message
					if (debug == true) {
						// prints out the debug
						// message about inserting
						// the element that the user
						// has typed. 
						System.err.println(TREE 
						+ treeName + INSERT 
						+ element.getName() + ']');
					}
					// then the bottom right child of 
					// the current TNode will be created
					// with the element that the user
					// has inputted. 
					working.right= new TNode(element);
					// the parent of that child will
					// be the current TNode. 
					working.right.parent = working;
					break;
				}
				// if there is something at the bottom right
				// of the current TNode. 
				else
					// then working will be
					// that bottom right child and
					// goes through the while loop
					// again.
					working = working.right;
			}
			// if the element that is being passed in does not
			// have a greater value than the one that is stored.
			else {
				// if the current TNode does not have a
				// left bottom child
				if (working.left == null) {
					// if the user turns on debug 
					if (debug == true) {
						// prints out the debug
						// message about inserting
						// the element that the user
						// has typed. 
						System.err.println(TREE 
						+ treeName + INSERT 
						+ element.getName() + ']');
					}
					// then the bottom left child of 
					// the current TNode will be created
					// with the element that the user
					// has inputted. 
					working.left = new TNode(element);
					// the parent of that child will
					// be the current TNode. 
					working.left.parent = working;
					break;
				}
				// if there is something at the bottom left
				// of the current TNode.
				else
					// then working will be
					// that bottom left child and
					// goes through the while loop
					// again.
					working = working.left;
			}
		}
		// while the current TNode is there
		while (working != null) {
			// setting the left and right height to -1
			leftHeight = -1;
			rightHeight = -1;
			// if there is a left child of the current TNode
			if (working.left != null) {
				// setting the leftHeight to the left
				// TNode's height.
				leftHeight = working.left.height;
			}
			// if there is a right child of the current TNode.
			if (working.right != null) {
				// setting the rightHeight to the right
				// TNode's height. 
				rightHeight = working.right.height;
			}
			// if the leftHeight is greater or equal to the
			// rightHeight 
			if (leftHeight >= rightHeight) {
				// then that current TNode's height
				// equals to the leftHeight plus one.
				working.height = leftHeight + 1;
			}
			// if the rightHeight is greater than the
			// leftHeight 
			if (rightHeight > leftHeight) {
				// then the current TNode's height will
				// equal to the rightHeight plus one. 
				working.height = rightHeight + 1;
			}
			// the current TNode's balance will be leftHeight
			// minus rightHeight. 
			working.balance = leftHeight - rightHeight;
			// The current TNode's parent will now the current
			// TNode.
			working = working.parent;
		}
                return true;
        }
 
	/**
        * Looks for the element that the user wants to search for.
	*
        * @param	Whatever element: Any object that the user has typed
	*		into the terminal. 
	* @return	Returns null if there is no element in the tree that
	*		mataches the one that the user has typed into the
	*		terminal. If there is a match, then returns the data
	*		that is in the TNode. 
        */
        public Whatever lookup (Whatever element) {
                // creating a TNode called working and setting to the root. 
		TNode working = root;
		// a while loop that runs only if the current TNode has
		// been created. 
		while (working != null) {
			// if the user turns on debug
			if (debug == true) {
				// prints out the debug message that
				// compares the element name that the user
				// has inputted to the store name of the
				// TNode.
				System.err.println(TREE + treeName + COMPARE 
				+ element.getName() + AND +
				working.data.getName() + ']');
			}
			// if the object that the user has inputted has the
			// similar value to the TNode data. 
			if (element.equals(working.data)) {
				// if the TNode's hasBeenDeleted is false
				if (working.hasBeenDeleted == false) {
					// return the data of that TNode.
					return working.data;
				}
				else
					return null;
			}
			// if the element has a greater value than the 
			// data of the current TNode.
			else if (element.isGreaterThan(working.data)) {
				// the right child of that current TNode
				// will now be the current TNode 
				working = working.right;
			}
			else 
				// the left child of that current TNode will
				// now be the current TNode
				working = working.left;
		}
                return null;    // not there
        }
  
	/**
        * Lazy removes the element that is in the tree. 
	*
        * @param	Whatever element: Any object that the user has
	*		inputted into the system. 
	* @return	Returns the data of the current TNode. 
        */
        public Whatever remove (Whatever element) {
                // creating a TNode working that is set to root.
		TNode working = root;
		// while the current TNode is there. 
		while (working != null) {
			// if the user turns on debug
			if (debug == true) {
				// print out the debug message that states
				// that the system is comparing the name
				// that the user has inputted to the name
				// that is stored in the tree. 
				System.err.println(TREE + treeName + COMPARE 
				+ element.getName() + AND +
				working.data.getName() + ']');
			}
			// if the object that the user has typed into the
			// terminal is equal to the data of the current
			// TNode and that current TNode's hasBeenDeleted is 
			// at false.
			if (element.equals(working.data) &&
			working.hasBeenDeleted == false) {
				// turn that current TNode's hasBeenDeleted
				// to true
				working.hasBeenDeleted = true;
				// decrement the occupancy of the tree
				occupancy--;
				// return the data of the current TNode
				return working.data;
			}
			// if the element that the user has inputted is
			// greater than the data of the current TNode
			else if (element.isGreaterThan(working.data)) {
				// the right child of the current TNode will
				// now be the current TNode. 
				working = working.right;
			}
			else 
				// the left child of the current TNode will
				// now be the current TNode. 
				working = working.left;
		}
                return null;    // not there
        }

        /**
         * Creates a string representation of this tree. This method first
         * adds the general information of this tree, then calls the
         * recursive TNode function to add all nodes to the return string 
         *
         * @return  String representation of this tree 
         */
        public String toString () {
                String string = "Tree " + treeName + ":\noccupancy is ";
                string += occupancy + " elements.";

                if(root != null)
                        string += root.writeAllTNodes();

                return string;
        }


          private class TNode {

                public Whatever data;
                public TNode left, right, parent;
                public boolean hasBeenDeleted;

                /* left child's height - right child's height */
                public long balance;
                /* 1 + height of tallest child, or 0 for leaf */
                public long height;

		/**
		* A constructor for TNodes
		*
		* @param	Whatever element: Any object that the user
		*		has typed into the terminal.
		* @return	Nothing.  
		*/
                public TNode (Whatever element) {
			balance = 0; // setting the balance of the TNode to 0
			height = 0; // setting the height of the TNode to 0
			left = null; // setting the left of the TNode to null
			right = null; // setting the right of the TNode to null
			parent = null; // setting the parent of the TNode to 
			//null
			data = element; // data will be the element that the
			// user has typed in.
			hasBeenDeleted = false; // setting the hasBeenDeleted
			// to false.
			occupancy++; //increase the occupancy by one every
			//time the user creates a TNode. 
                }

                /**
                 * Creates a string representation of this node. Information
                 * to be printed includes this node's height, its balance,
                 * and the data its storing.
                 *
                 * @return  String representation of this node 
                 */

                public String toString () {
                        return "at height:  " + height + "  with balance: " +
                                balance + "  " + data;
                }

                /**
                 * Writes all TNodes to the String representation field. 
                 * This recursive method performs an in-order
                 * traversal of the entire tree to print all nodes in
                 * sorted order, as determined by the keys stored in each
                 * node. To print itself, the current node will append to
                 * tree's String field.
                 */
                public String writeAllTNodes () {
                        String string = "";
                        if (left != null)
                                string += left.writeAllTNodes ();
                        if (!hasBeenDeleted) 
                                string += "\n" + this;          
                        if (right != null)
                                string += right.writeAllTNodes ();

                        return string;
                }
        }
}
