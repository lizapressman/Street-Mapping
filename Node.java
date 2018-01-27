
public class Node implements Comparable<Node> {
	//all data that each Node contains
	String name;
	boolean visit;
	Node previous;
	double lon;
	double lat;
	double distance;
	
	//Constructor that initializes all variables appropriately
	public Node(String n, double la, double lo) {
		name = n;
		visit = false;
		previous = null;
		lon = lo;
		lat = la;
		distance = -1;
		
	}
	
	//compareTo method needed for priorityQueue used in StreetMap.java to find shortest path
	public int compareTo(Node n) {
		if (n.distance < this.distance) {
			return 1;
		}
		else if (n.distance == this.distance){
			return 0;
		}
		return -1;
	}
}
