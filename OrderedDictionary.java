
public class OrderedDictionary implements OrderedDictionaryADT {
	private Node root;
	
	public OrderedDictionary() {
		root = null;
	}
	
	// This is a public method that is made to return the record with key k
	// If the record does not exit then it will return null
	// It calls the function getN which does the same thing but returns the Node where the record would be stored if it does not exit
	// If the record is null from the getN function then it means that key k does not exits and thereore will return null
	public Record get(Pair k) {
		return getN(k,root).getRecord();
	}

	
	public void put(Record r) throws DictionaryException {
		Node store = getN(r.getKey(), root);
		if (store.isLeaf()) {
			store.setRecord(r);
			store.setLeft(createLeaf(store));
			store.setRight(createLeaf(store));
		}
		else {
			throw new DictionaryException("A record with the same key already exsits in the Dictionary");			
		}
	}

	
	public void remove(Pair k) throws DictionaryException {
		Node check = getN(k, root), temp;
		if (check.isLeaf()) {
			throw new DictionaryException("There is no record with the given key");
		}
		else {
			if ((!(check.getLeft().isLeaf())) && (!(check.getRight().isLeaf()))) {
				temp = getSorL(-1, check.getRight());
				check.setRecord(temp.getRecord());
				remove(temp.getRecord().getKey());
			}
			else if ((check.getLeft().isLeaf()) && (check.getRight().isLeaf())) {
				if (check.compareNode(root) == 0) {
					root = null;
				}
				else {
					if (check.compareNode(check.getParent()) == 1) {
						check.getParent().setRight(createLeaf(check.getParent()));
					}
					else {
						check.getParent().setLeft(createLeaf(check.getParent()));
					}
				}
			}
			else if (check.getLeft().isLeaf()){
				if (check.compareNode(root) == 0) {
					root = root.getRight();
				}
				else {
					if (check.compareNode(check.getParent()) == 1) {
						check.getParent().setRight(check.getRight());
						check.getRight().setParent(check.getParent());
					}
					else {
						check.getParent().setLeft(check.getRight());
						check.getRight().setParent(check.getParent());
					}
				}
			}
			else if (check.getRight().isLeaf()) {
				if (check.compareNode(root) == 0) {
					root = root.getLeft();
				}
				else {
					if (check.compareNode(check.getParent()) == 1) {
						check.getParent().setRight(check.getLeft());
						check.getLeft().setParent(check.getParent());
					}
					else {
						check.getParent().setLeft(check.getLeft());
						check.getLeft().setParent(check.getParent());
					}
				}
			}
		}
	}

	
	public Record successor(Pair k) {
		Node node = getN(k, root);
		if (node.isLeaf()) {
			return null;
		}
		else if (node.getRecord().getKey().compareTo(largest().getKey()) == 0) {
			return null;
		}
		else {
			if (!(node.getRight().isLeaf())) {
				return getSorL(-1,node.getRight()).getRecord();
			}
			else {
				while (node.compareNode(node.getParent()) == 1) {
					node = node.getParent();
				}
				return node.getParent().getRecord();
			}
		}		
	}

	
	public Record predecessor(Pair k) {
		Node node = getN(k, root);
		if (node.isLeaf()) {
			return null;
		}
		else if (node.getRecord().getKey().compareTo(smallest().getKey()) == 0) {
			return null;
		}
		else {
			if (!(node.getLeft().isLeaf())) {
				return getSorL(1,node.getLeft()).getRecord();
			}
			else {
				while (node.compareNode(node.getParent()) == -1) {
					node = node.getParent();
				}
				return node.getParent().getRecord();
			}
		}
	}

	
	public Record smallest() {
		return getSorL(-1, root).getRecord();
	}

	
	public Record largest() {
		return getSorL(1, root).getRecord();
	}
	
	private Node createLeaf(Node p) {
		Node node = new Node(null);
		node.setParent(p);
		return node;
	}
	
	// This method is a private method
	// It is made to get the node n with key k if it exists in the tree
	// If there is no node with that key exists then return the leaf node where the key would be placed is returned 
	private Node getN(Pair k, Node startNode) {
		if (startNode.getRecord() == null) {
			return startNode;
		}
		int compare = startNode.getRecord().getKey().compareTo(k);		
		if (compare == 0) {
			return startNode;
		}
		else if (compare < 0) {
			return getN(k, startNode.getLeft());
		}
		else {
			return getN(k, startNode.getRight());
		}
	}
	
	private Node getSorL(int x, Node r)  {
		Node node = r;
		if (x == -1) {
			while (node.getRecord() != null) {
				node = node.getLeft();
			}
		}
		else {
			while (node.getRecord() != null) {
				node = node.getRight();
			}
		}
		return node.getParent();
	}

}
