package save;

import java.awt.Point;
import java.util.ArrayList;

import object.Node;

public class DataModel {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void deleteNode(Point pos) {
		for(int i =0;i<nodes.size();i++) {
			if(nodes.get(i).isInside(pos)) {
				nodes.remove(i);
			}
		}		
	}
}
