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

    public void colorVertex(Vertex v){

	ArrayList<Integer> unAvailableColors = new ArrayList<>();
	int c = -1;

	if(v.color == -1){
	    for(Edge e : v.edges){
		//		    System.out.println(v.name + " is attached to " + e.end.color);
		for(int i=0; i < colors.length; i++){
		    if(e.end.color == colors[i] && !unAvailableColors.contains(colors[i])){			    
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
	}		
    }

    public void colorGraph(){

	this.vertices.get(0).color = 0;



	for(Vertex v : this.vertices){
	    colorVertex(v);
	}

	for(Vertex v : this.vertices){
	    if(v.color == -1){
		//KEMP CHAINS OR BACKTRACK
	    }
	}
    }

    public void backTrack(Vertex v){
	int min_connected = v.edges.get(0).end.edges.size();
	Vertex min_vertex;

	for(Edge e : v.edges){
	    if(e.end.edges.size() < min_connected){
		min_connected = e.end.edges.size();
		min_vertex = e.end;
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
	Vertex E = new Vertex("E");
	Vertex F = new Vertex("F");

	Edge ab = new Edge(A, B);
	Edge ac = new Edge(A, C);
	Edge ad = new Edge(A, D);
	Edge ae = new Edge(A, E);
	Edge af = new Edge(A, F);

	Edge ba = new Edge(B, A);
	Edge bc = new Edge(B, C);

	Edge ca = new Edge(C, A);
	Edge cb = new Edge(C, B);
	Edge cd = new Edge(C, D);
	Edge cf = new Edge(C, F);

	Edge de = new Edge(D, E);
	Edge dc = new Edge(D, C);
	Edge da = new Edge(D, A);
	Edge df = new Edge(D, F);

	Edge ea = new Edge(E, A);
	Edge ed = new Edge(E, D);
	Edge ef = new Edge(E, F);

	Edge fa = new Edge(F, A);
	Edge fe = new Edge(F, E);
	Edge fc = new Edge(F, C);
	Edge fd = new Edge(F, D);



	A.edges.add(ab);
	A.edges.add(ad);
	A.edges.add(ac);
	A.edges.add(ae);
	A.edges.add(af);	
	

	B.edges.add(ba);
	B.edges.add(bc);

	C.edges.add(cb);
	C.edges.add(cd);
	C.edges.add(ca);
	C.edges.add(cf);

	D.edges.add(de);
	D.edges.add(da);
	D.edges.add(dc);
	D.edges.add(df);

	E.edges.add(ed);
	E.edges.add(ea);
	E.edges.add(ef);

	F.edges.add(fa);
	F.edges.add(fe);
	F.edges.add(fc);
	F.edges.add(fd);

	ArrayList<Vertex> vertices = new ArrayList<>();
	vertices.add(A);
	vertices.add(B);
	vertices.add(C);
	vertices.add(D);
	vertices.add(E);	
	vertices.add(F);

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
