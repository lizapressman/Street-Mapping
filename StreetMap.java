import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class StreetMap {
	static Graph gph = new Graph();
	static ArrayList<String> points = new ArrayList<String>();
	static ArrayList<String> point = new ArrayList<String>();
	static ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
	static String file = "ur.txt";
	static double radius;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		file = args[0];
		
		ArrayList<Node> l = new ArrayList<Node>();
		Scanner sc = new Scanner(new File(file));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equals("i")) {
				gph.addNode(sc.next(), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()));
			}
			if (s.equals("r")) {
				gph.addEdge(sc.next(), sc.next(), sc.next());
			}
		}

		if(args[1].equals("--show")) {
			if(args.length > 2) {
			if(args[2].equals("--directions")) {
				if (args[3].equals("--time")) {
					l = findShortestPath(convert(args[4]),convert(args[5]));
					double miles = milesTraveled(l);
					double mph = Double.parseDouble(args[6]);
					System.out.println("path: ");
					for(Node n: l) {
						System.out.println(n.name + " ");
					}
					System.out.println("Miles traveled: " + milesTraveled(l));
					System.out.println("Going at " + mph + " miles per hour from " + args[4] + " to " + args[5] + " will take " + (miles/mph) + " hours.");
				}
				else {
					l = findShortestPath(convert(args[3]),convert(args[4]));
					System.out.println("path: ");
					for(Node n: l) {
						System.out.println(n.name + " ");
					}
					System.out.println("Miles traveled: " + milesTraveled(l));
				}
			}
			else if(args[2].equals("--miles")) {
				if(args[3].equals("--paths")) {
					radius = Double.parseDouble(args[4]);
					Node p1 = gph.name.get(args[5]);
					findPaths(radius, p1);
					System.out.println(paths.size() + " paths in " + radius + " miles from " + p1.name);
				}
				else {
				radius = Double.parseDouble(args[3]);
				//System.out.println(radius);
				Node p1 = gph.name.get(args[4]);
				findPoints(radius, p1);
				System.out.println("Points within " + radius + " miles: ");
				for(String p : points) {
					System.out.println(p);
				}
			}
			}
			else {
				for (int i = 2; i < args.length; i++) {
					point.add(args[i]);
				}
			}
			}
			paths.add(l);
			new DrawMap(paths);
		}
		if(args[1].equals("--directions")) {
			if (args[2].equals("--time")) {
				l = findShortestPath(convert(args[3]),convert(args[4]));
				double miles = milesTraveled(l);
				double mph = Double.parseDouble(args[5]);
				System.out.println("path: ");
				for(Node n: l) {
					System.out.println(n.name + " ");
				}
				System.out.println("Miles traveled: " + milesTraveled(l));
				System.out.println("Going at " + mph + " miles per hour from " + args[3] + " to " + args[4] + " will take " + (miles/mph) + " hours.");
			}
			else {
				l = findShortestPath(convert(args[2]),convert(args[3]));
				System.out.println("path: ");
				for(Node n: l) {
					System.out.println(n.name + " ");
				}
				System.out.println("Miles traveled: " + milesTraveled(l));
			}
		}
	}
	
	public static void findPoints(Double r, Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		start.distance = 0;
		pq.add(start);
		start.visit = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visit = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visit) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.previous = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.previous = minDis;
				}
				pq.add(adj);
				adj.visit = true;
			}
			}
		}
		for(Node no: gph.graph.keySet()) {
			//System.out.println(no.distance);
			if (no.distance *69 <= radius && no.distance >=0) {
				points.add(no.name);
			}
	}
	}
	public static void findPaths(Double r, Node start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		//ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visit = true;
		while(!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visit = true;
			for(Node adj : gph.graph.get(minDis)) { 
				if(!adj.visit) {
				double dis2 = distance(minDis,adj) + minDis.distance;
				if(!(minDis.distance==-1) && minDis.distance < dis2) {
					pq.remove(adj);
					adj.distance = dis2;
					adj.previous = minDis;
				}
				else if(minDis.distance == -1) {
					adj.distance = distance(minDis,adj);
					adj.previous = minDis;
				}
				pq.add(adj);
				adj.visit = true;
			}
			}
		}
		Node curr;
		for(Node no: gph.graph.keySet()) {
			if (no.distance *69 <= radius && no.distance >=0) {
				ArrayList<Node> p = new ArrayList<Node>();
				 curr = no;
				 p.add(0,no);
				 while(curr.previous!=null) {
					 curr=curr.previous;
					 p.add(0,curr);
				 }
				 paths.add(p);
			}
	}
	}

	public static Node convert(String name) {
		return Graph.name.get(name);
	}

	public static ArrayList<Node> findShortestPath(Node start, Node end) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		ArrayList<Node> retList = new ArrayList<Node>();
		start.distance = 0;
		pq.add(start);
		start.visit = true;
		while (!pq.isEmpty()) {
			Node minDis = pq.poll();
			minDis.visit = true;
			for (Node adj : gph.graph.get(minDis)) {
				if (!adj.visit) {
					double dis2 = distance(minDis, adj) + minDis.distance;
					if (minDis.distance != -1 && minDis.distance < dis2) {
						pq.remove(adj);
						adj.distance = dis2;
						adj.previous = minDis;
					} 
					else if (minDis.distance == -1) {
						adj.distance = distance(minDis, adj);
						adj.previous = minDis;
					}
					pq.add(adj);
					adj.visit = true;
				}
			}
		}
		Node curr = end;
		if (curr.previous != null) {
			while (curr.previous != null) {
				retList.add(0, curr);
				curr = curr.previous;
			}
			retList.add(0, curr);
		}
		return retList;
	}
	
	public static double milesTraveled(ArrayList<Node> path) {
		double distance = 0.0;
		for (int i = 0; i < path.size() - 1; i++) {
			distance +=  69 * distance(path.get(i), path.get(i + 1));
		}
		return distance;
	}

	public static double distance(Node n1, Node n2) {
		double a = n1.lat - n2.lat;
		double b = n1.lon - n2.lon;
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
}
