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

    ArrayList<Vertex> vertices = new ArrayList<>();

    public Graph( ArrayList<Vertex> _vertices){
	vertices = _vertices;
    }

    public void colorVertex(Vertex v, int[] colors){
		
	ArrayList<Integer> unAvailableColors = new ArrayList<>();
	int c = -1;

	for(Edge e : v.edges){
	    //		    System.out.println(v.name + " is attached to " + e.end.color);
	    for(int i=0; i < colors.length; i++){
		if(e.end.color == colors[i] && !unAvailableColors.contains(colors[i])){			    
		    unAvailableColors.add(colors[i]);
		}
	    }
	}		
	if(unAvailableColors.size() < colors.length){
	    for(int i=0; i < colors.length; i++){
		if(!unAvailableColors.contains(colors[i])){
		    c = colors[i];
		}
	    }		    
	    v.color = c;
	}
    }

    public void colorGraph(){

	int[] colors = {0, 1, 2, 3};
	this.vertices.get(0).color = 0;

	for(Vertex v : this.vertices){
	    colorVertex(v, colors);
	}

	for(Vertex v : this.vertices){
	    if(v.color == -1){
		backTrack(v);
	    }
	}
    }

    public void backTrack(Vertex v){
	int min_connected = v.edges.get(0).end.edges.size();
	Vertex min_vertex = v.edges.get(0).end;

	//find the least connected vertex that is connected to v
	for(Edge e : v.edges){
	    if(e.end.edges.size() < min_connected && e.end.color != -1){
		min_connected = e.end.edges.size();
		min_vertex = e.end;
	    }
	}	
	System.out.println("min_v: " + min_vertex.name);
	int[] original_colors = {0,1,2,3};
	int[] colors = new int[3];
	int count =0;
	//create color array without the current color of v
	for(int i=0; i < original_colors.length; i++){
	    if( i != min_vertex.color){
		colors[count] = i;
		System.out.print("c " + count + ": " +colors[count]);
		count++;
	    }
	}
	System.out.println();
	//recolor this least connected vertex NOT THE SAME COLOR
	colorVertex(min_vertex, colors);

	colorVertex(v, original_colors);
    }

}


public class FourColors{

    public static void main(String[] args){

	/*	BACKTRACKING EXAMPLE
		Vertex A = new Vertex("A");
	Vertex B = new Vertex("B");
	Vertex C = new Vertex("C");
	Vertex D = new Vertex("D");
	Vertex E = new Vertex("E");

	Edge ab = new Edge(A, B);
	Edge ad = new Edge(A, D);
	Edge ae = new Edge(A, E);

	Edge ba = new Edge(B, A);
	Edge bc = new Edge(B, C);
	Edge be = new Edge(B, E);
	Edge bd = new Edge(B, D);

	Edge cb = new Edge(C, B);
	Edge cd = new Edge(C, D);
	Edge ce = new Edge(C, E);

	Edge de = new Edge(D, E);
	Edge dc = new Edge(D, C);
	Edge da = new Edge(D, A);
	Edge db = new Edge(D, B);

	Edge ea = new Edge(E, A);
	Edge eb = new Edge(E, B);
	Edge ec = new Edge(E, C);
	Edge ed = new Edge(E, D);

	A.edges.add(ab);
	A.edges.add(ad);
	A.edges.add(ae);
	
	B.edges.add(ba);
	B.edges.add(bc);
	B.edges.add(be);
	B.edges.add(bd);

	C.edges.add(cb);
	C.edges.add(cd);
	C.edges.add(ce);

	D.edges.add(de);
	D.edges.add(da);
	D.edges.add(dc);
	D.edges.add(db);

	E.edges.add(ed);
	E.edges.add(ea);
	E.edges.add(eb);
	E.edges.add(ec);

	ArrayList<Vertex> vertices = new ArrayList<>();
	vertices.add(A);
	vertices.add(B);
	vertices.add(C);
	vertices.add(D);
	vertices.add(E);	*/


	Vertex Ven = new Vertex("Venezuela");
	Vertex Col = new Vertex("Colombia");
	Vertex Ec = new Vertex("Ecuador");
	Vertex Sur = new Vertex("Suriname");
	Vertex Guy = new Vertex("Guyana");
	Vertex Bra = new Vertex("Brazil");

	Edge vc = new Edge(Ven, Col);
	Edge cv = new Edge(Col, Ven);
	Edge ce = new Edge(Col, Ec);
	Edge ec = new Edge(Ec, Col);
	Edge vg = new Edge(Ven, Guy);
	Edge gv = new Edge(Guy, Ven);
	Edge vb = new Edge(Ven, Bra);
	Edge bv = new Edge(Bra, Ven);
	Edge sg = new Edge(Sur, Guy);
	Edge gs = new Edge(Guy, Sur);
	Edge sb = new Edge(Sur, Bra);
	Edge bs = new Edge(Bra, Sur);
	Edge gb = new Edge(Guy, Bra);
	Edge bg = new Edge(Bra, Guy);
	Edge bc = new Edge(Bra, Col);
	Edge cb = new Edge(Col, Bra);

	Ven.edges.add(vc);
	Ven.edges.add(vg);
	Ven.edges.add(vb);

	Col.edges.add(cv);
	Col.edges.add(cb);
	Col.edges.add(ce);

	Ec.edges.add(ec);

	Sur.edges.add(sg);
	Sur.edges.add(sb);
	
	Guy.edges.add(gv);
	Guy.edges.add(gs);
	Guy.edges.add(gb);
	
	Bra.edges.add(bv);
	Bra.edges.add(bs);
	Bra.edges.add(bg);
	Bra.edges.add(bc);


	ArrayList<Vertex> vertices = new ArrayList<>();
	vertices.add(Ven);
	vertices.add(Col);
	vertices.add(Ec);
	vertices.add(Sur);
	vertices.add(Guy);
	vertices.add(Bra);


	Graph g = new Graph(vertices);

	//to make sure the graph is connected correctly
	/*for(Vertex v : g.vertices){
	    System.out.print(v.name + " is attached to: ");
	    for(Edge e : v.edges){
		System.out.print(e.end.name + " and ");
	    }
	    System.out.println();
	    }*/

	g.colorGraph();

	for(Vertex v : g.vertices){
	    System.out.println("['"+v.name +"'"+ ", " + v.color+"],");
	}
    }
}
