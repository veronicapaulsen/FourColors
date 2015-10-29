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

    //possibilities for colors are R G B W
    int color;
    ArrayList<Edge> edges = new ArrayList<>();

    public Vertex(ArrayList<Edge> _edges){
	color = -1;
	edges = _edges;
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
}


public class FourColors{
	
    int[] colors = new int[4];

    public Graph colorGraph(Graph g){

	g.vertices.get(0).color = 0;
	int availableColors = 4;
	int c = -1;

	for(Vertex v : g.vertices){
	    if(v.color == -1){
		for(Edge e : v.edges){
		    for(int i=0; i < colors.length; i++){
			if(e.end.color == colors[i]){
			    availableColors--;
			}else{
			    c = colors[i];
			}
		    }
		    if(availableColors >= 1){
			v.color = c;
		    }
		}
	    }
	}

	for(Vertex v : g.vertices){
	    if(v.color == -1){
		//KEMP CHAINS OR BACKTRACK
	    }
	}
	return g;
    }

    /*    public Graph kemp(Vertex v){
	for(Edge e : v.edges
	}*/

    public static void main(String[] args){

	
	
    }
}
