/*
graphs must be entered in the format of a 2D array with 3 columns and each row describes the edge:
smaller vertex, larger vertex, present or not (binary)
in this format you must list all possible connections 
map graphs are undirectional so we only need 1 combination of two numbers not both ways
also loops are not allowed in map graphs so we do not need to list the possible connections of a vertex to itself
the vertices must be numbered 0 to m. here is an example of a cube: 
[0, 1, 1]
[0, 2, 1]
[0, 3, 0]
[1, 2, 0]
[1, 3, 1]
[2, 3, 1]
*/

import java.util.ArrayList;

class Vertex{

    int color;
    ArrayList<Edge> edges = new ArrayList<>();
    String name;

    public Vertex(String _name){
	color = -1;
	name = _name;
    }
    
    public void setColor(int newColor, ArrayList<Edge> _edges){
	this.color = newColor;
	edges = _edges;
    }

}

class Edge{
    
    Vertex start;
    Vertex end;

    public Edge( Vertex _start, Vertex _end){
	start = _start;
	end = _end;
    }
}


class Graph{

    int[] colors = {0, 1, 2, 3};
    ArrayList<Vertex> vertices = new ArrayList<>();

    public Graph( ArrayList<Vertex> _vertices){
	vertices = _vertices;
    }

    public void colorGraph(){

	this.vertices.get(0).color = 0;
	ArrayList<Integer> unAvailableColors = new ArrayList<>();
	int c = -1;

	for(Vertex v : this.vertices){
	    if(v.color == -1){
		for(Edge e : v.edges){
		    //System.out.println(v.name + " is attached to " + e.end.color);
		    for(int i=0; i < colors.length; i++){
			if(e.end.color == colors[i]){
			    unAvailableColors.add(colors[i]);
			}
		    }
		}		
		if(unAvailableColors.size() < 4){
		    for(int i=0; i < colors.length; i++){
			if(!unAvailableColors.contains(colors[i])){
			    c = colors[i];
			}
		    }		    
		    v.color = c;
		}	
		//reset for next vertex
		c = -1;
		for(int i = 0; i < unAvailableColors.size(); i++){
		    unAvailableColors.remove(i);
		}
	    }
	}

	for(Vertex v : this.vertices){
	    if(v.color == -1){
		//KEMP CHAINS OR BACKTRACK
	    }
	}
    }

}


public class FourColors{

    /*    public Graph kemp(Vertex v){
	for(Edge e : v.edges
	}*/

    public static void main(String[] args){

	Vertex A = new Vertex("A");
	Vertex B = new Vertex("B");
	Vertex C = new Vertex("C");
	Vertex D = new Vertex("D");

	Edge ab = new Edge(A, B);
	Edge ad = new Edge(A, D);

	Edge ba = new Edge(B, A);
	Edge bc = new Edge(B, C);

	Edge cb = new Edge(C, B);
	Edge cd = new Edge(C, D);

	Edge dc = new Edge(D, C);
	Edge da = new Edge(D, A);


	A.edges.add(ab);
	A.edges.add(ad);
	B.edges.add(ba);
	B.edges.add(bc);
	C.edges.add(cb);
	C.edges.add(cd);
	D.edges.add(dc);
	D.edges.add(da);

	ArrayList<Vertex> vertices = new ArrayList<>();
	vertices.add(A);
	vertices.add(B);
	vertices.add(C);
	vertices.add(D);

	Graph g = new Graph(vertices);

	//to make sure the graph is connected correctly
	for(Vertex v : g.vertices){
	    System.out.print(v.name + " is attached to: ");
	    for(Edge e : v.edges){
		System.out.print(e.end.name + " and ");
	    }
	    System.out.println();
	}

	g.colorGraph();

	for(Vertex v : g.vertices){
	    System.out.println(v.name + " " + v.color);
	}
    }
}
