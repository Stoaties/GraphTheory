package save;

import java.awt.Point;
import java.util.ArrayList;

import object.Node;
import object.Path;

public class DataModel {
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Path> paths = new ArrayList<Path>();
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void addPath(Path path) {
		paths.add(path);
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public ArrayList<Path> getPaths(){
		return paths;
	}

	public void deleteNode(Point pos) {
		for(int i =0;i<nodes.size();i++) {
			if(nodes.get(i).isInside(pos)) {
				for(int j=0;i<paths.size();i++) {
					if(paths.get(j).getNodes()[0] == nodes.get(i) || paths.get(j).getNodes()[1] == nodes.get(i)) {
						paths.remove(i);
					}
				}
				nodes.remove(i);
			}
		}
	}
	
	public void deletePath(Point pos) {
		for(int i = 0;i<paths.size();i++) {
			if(paths.get(i).isInside(pos)) {
				paths.remove(i);
			}
		}
	}
}
