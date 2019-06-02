package save;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import object.Node;
import object.Path;

public class DataModel implements Serializable{
	private String modelName = "";
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Path> paths = new ArrayList<Path>();

	public DataModel() {
		
	}
	
	public DataModel(DataModel dataModel) {
		this.nodes = dataModel.getNodes();
		this.paths = dataModel.getPaths();
		this.modelName = dataModel.getName();
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public void addPath(Path path) {
		paths.add(path);
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}
	
	public void setName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getName() {
		return modelName;
		
	}

	public void deleteNode(Point pos) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).isInside(pos)) {
				ArrayList<Path> pathsTemp = nodes.get(i).getPaths();
				for(Path path:pathsTemp) {
					for(int j = 0;j<paths.size();j++) {
						if(path==paths.get(j)) {
							paths.remove(path);
						}
					}
				}
				nodes.remove(i);
			}
		}
	}

	public void deletePath(Point pos) {
		for (int i = 0; i < paths.size(); i++) {
			if (paths.get(i).isInside(pos)) {
				paths.remove(i);
			}
		}
	}
}
