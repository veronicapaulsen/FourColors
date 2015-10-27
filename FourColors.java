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
    String color;

    public Vertex(){
	color = null;
    }
    
    public void setColor(String newColor){
	this.color = newColor;
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


public Graph{

    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Edge> edges = new ArrayList<>();

    public Graph( ArrayList<Vertex> _vertices, ArrayList<Edge> _edges){
	vertices = _vertices;
	edges = _edges;
    }
}


public class FourColors{
	


    public static void main(String[] args){
	
    }
}
