
public class Node {
	private Record dataItem;
	private Node left, right, parent;
	
	public Node(Record dataItem) {
		this.dataItem = dataItem;
		left = null;
		right = null;
		parent = null;
	}
	
	public void setRecord(Record dataItem) {
		this.dataItem = dataItem;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setRight (Node right) {
		this.right = right;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Record getRecord() {
		return dataItem;
	}
	
	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public int compareNode(Node n) {
		return dataItem.getKey().compareTo(n.getRecord().getKey());
	}
	
	public boolean isLeaf() {
		return (dataItem == null);
	}
	
}
