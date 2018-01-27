import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	//graph is essentially and adjacency list
	public HashMap<Node, LinkedList<Node>> graph;
	
	public static HashMap<String, Node> name;
	//public static  HashMap<String, Double> roads;
	
	//constructor that initializes all HasMaps
	public Graph() {
		graph = new HashMap<Node, LinkedList<Node>>();
		name = new HashMap<String, Node>();
		//roads = new HashMap<String, Double>();
		
	}

	//creates a Node, puts it into appropriate Maps
	public void addNode(String n, double la, double lo) {
		Node node = new Node(n, la, lo);
		graph.put(node, new LinkedList<Node>());
		name.put(n, node);
	}
	
	//creates an edge between two given Nodes
	public void addEdge(String n, String i, String j) {
		if (graph.containsKey(name.get(i)) && graph.containsKey(name.get(j))) {
			graph.get(name.get(i)).add(name.get(j));
			graph.get(name.get(j)).add(name.get(i));
			//roads.put(n, -1.0);
		}
	}
}