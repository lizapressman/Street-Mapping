# Street-Mapping

Contact information: 
	Liza Pressman
	lpressma
	class ID: 78
	lpressma@u.rochester.edu

Partner contact information:
	Sydney Dlhopolsky
	sdlhopol
	class ID: 118
	sdlhopol@u.rochester.edu


CSC 172 Project 4 (Street Mapping):

All command line arguments for our map:
	java StreetMap ur.txt --show --directions HOYT MOREY
	java StreetMap ur.txt --show
	java StreetMap ur.txt --directions HOYT MOREY
	
	java StreetMap map.txt --show i1 i50 i1000 ...
	java StreetMap map.txt --show --miles radius i1
	java StreetMap map.txt --show --miles --paths radius i1
	java StreetMap map.txt --show --directions --time i1 i50 mph
	java StreetMap map.txt --directions --time i1 i50 mph

Synopsis: Our code has all the functionality presented in the project guidelines.
	- It draws the map for the three text files, prints directions, prints distance traveled, and draws a red line for the shortest path
	- It takes in commands from the command line as outlined in the project guidelines.

***EXTRA CREDIT***:	It also takes in a different commands formatted as:
	
		StreetMap map.txt --show i1 i50 i1000 ...
	(This command will highlight any number of specific intersections or places on the map.)
	
		StreetMap map.txt --show --miles radius i1
	(This command will find all the intersections within a certain radius (written as a double) of the given point.)
	
		StreetMap map.txt --show --miles --paths radius i1
	(This command will find all the shortest paths to every intersection within a certain radius (written as a double) of the given point.)
	
		StreetMap map.txt --show --directions --time i1 i50 mph
	(This command will show and print directions from the two given points but will also take the mph (written as a double) and tell you how long it will take going at this speed.)
	
		StreetMap map.txt --directions --time i1 i50 mph
	(This command does the same as the above one however it will not show the map.)
	
Structure:
	- Our main structure for our graph is in our Graph class. We have two hashMaps. 
		the first is called graph and this is our adjacency list
		the second is called name and this is name of node connected to the node
	- We have a node class that keeps track of all intersections.
		this class has the name of the intersection, if its visited, the previous node (used for shortest path), longitude/latitude, and distance
	- Our DrawMap class produces the visuals and does just that - draws the map and path
	- Our main method is called StreetMap and this has the main method, the method to find shortest path and all helper methods 

Note: Because of our adjacency matrix, we don't use our edge class
		Also, to terminate the program, close the pop up window that contains the map

Obstacles: We had a few problems implementing dijkstra's as our visit variable wasn't being updated properly. We also had a small typo that was messing with that algorithm.

Workload Distribution: We met up to work on all the coding and didn't really split any of it up. Thus we talked through problems and coded everything together.

Run-time analysis: 
	Drawing map: O(n^2) because the time heaviest method is a nested for loop and all other helper functions have a runtime of n and aren't called in a loop. To draw the shortest path, it takes an additional runtime n.
	Shortest Path: O(E log(V)) because for each vertex taken out of the priority queue (represented by logV) we check every edge connected to the vertex through the priority queue hence we multiply by the number of edges
