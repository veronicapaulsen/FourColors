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
	ArrayList<Vertex> recolored_vertices = new ArrayList<>();
	this.vertices.get(0).color = 0;

	for(Vertex v : this.vertices){
	    colorVertex(v, colors);
	}

	boolean finished = false;
	while(!finished){
	    for(Vertex v : this.vertices){
		if(v.color == -1){
		    backTrack(v, recolored_vertices);
		}
	    }
	    for(Vertex v : this.vertices){
		if( v.color != -1 ){
		    finished = true;
		}else{
		    finished = false;
		}
	    }
	}
    }

    public void backTrack(Vertex v, ArrayList<Vertex> recolored_vertices){
	int min_connected = v.edges.get(0).end.edges.size();
	Vertex min_vertex = v.edges.get(0).end;
	boolean finished = true;


	while(!finished){
	    //find the least connected vertex that is connected to v
	    for(Edge e : v.edges){
		if(e.end.edges.size() < min_connected && e.end.color != -1 && !recolored_vertices.contains(min_vertex)){
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
	    recolored_vertices.add(min_vertex);
	    colorVertex(min_vertex, colors);	    
	    
	    colorVertex(v, original_colors);
	    
	    if(v.color != -1){
		finished = false;
	    }else{
		backTrack(v, recolored_vertices);
	    }
	}
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
	Vertex F = new Vertex("F");
	Vertex G = new Vertex("G");

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

	Edge ag = new Edge(A, G);
	Edge ga = new Edge(G, A);
	Edge cf = new Edge(C, F);
	Edge fc = new Edge(F, C);

	A.edges.add(ab);
	A.edges.add(ad);
	A.edges.add(ae);
	A.edges.add(ag);
	
	B.edges.add(ba);
	B.edges.add(bc);
	B.edges.add(be);
	B.edges.add(bd);

	C.edges.add(cb);
	C.edges.add(cd);
	C.edges.add(ce);
	C.edges.add(cf);

	D.edges.add(de);
	D.edges.add(da);
	D.edges.add(dc);
	D.edges.add(db);

	E.edges.add(ed);
	E.edges.add(ea);
	E.edges.add(eb);
	E.edges.add(ec);

	G.edges.add(ga);
	F.edges.add(fc);
	

	ArrayList<Vertex> vertices = new ArrayList<>();
	vertices.add(A);
	vertices.add(B);
	vertices.add(C);
	vertices.add(D);
	vertices.add(E);
	vertices.add(F);
	vertices.add(G);*/


	/*	Vertex Ven = new Vertex("Venezuela");
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
	vertices.add(Bra); */

	Vertex Egy = new Vertex("Egypt");
	Vertex Lib = new Vertex("Libya");
	Vertex Tun = new Vertex("Tunisia");
	Vertex Alg = new Vertex("Algeria");
	Vertex Mor = new Vertex("Morocco");
	Vertex Wsa = new Vertex("Western Sahara");
	Vertex Mau = new Vertex("Mauritania");
	Vertex Mal = new Vertex("Mali");
	Vertex Nir = new Vertex("Niger");
	Vertex Cha = new Vertex("Chad");
	Vertex Sud = new Vertex("Sudan");
	Vertex Ssu = new Vertex("South Sudan");
	Vertex Eri = new Vertex("Eritrea");
	Vertex Eth = new Vertex("Ethiopia");
	Vertex Dji = new Vertex("Djibouti");
	Vertex Som = new Vertex("Somalia");
	Vertex Ken = new Vertex("Kenya");
	Vertex Uga = new Vertex("Uganda");
	Vertex Rwa = new Vertex("Rwanda");
	Vertex Drc = new Vertex("Democratic Republic of the Congo");
	Vertex Car = new Vertex("Central Africa Republic");
	Vertex Roc = new Vertex("Republic of the Congo");
	Vertex Gab = new Vertex("Gabon");
	Vertex Eqg = new Vertex("Equatorial Guinea");
	Vertex Cam = new Vertex("Cameroon");
	Vertex Nia = new Vertex("Nigeria");
	Vertex Ben = new Vertex("Benin");
	Vertex Tog = new Vertex("Togo");
	Vertex Gha = new Vertex("Ghana");
	Vertex Buf = new Vertex("Burkina Faso");
	Vertex Cdi = new Vertex("Cote D'Ivoire");
	Vertex Libe = new Vertex("Liberia");
	Vertex Sil = new Vertex("Sierra Leone");
	Vertex Gui = new Vertex("Guinea");
	Vertex Gub = new Vertex("Guinea Bissau");
	Vertex Gam = new Vertex("Gambia");
	Vertex Sen = new Vertex("Senegal");
	Vertex Bur = new Vertex("Burundi");
	Vertex Tan = new Vertex("Tanzania");
	Vertex Mala = new Vertex("Malawi");
	Vertex Zam = new Vertex("Zambia");
	Vertex Ang = new Vertex("Angola");
	Vertex Nam = new Vertex("Namibia");
	Vertex Bot = new Vertex("Botswana");
	Vertex Zim = new Vertex("Zimbabwe");
	Vertex Moz = new Vertex("Mozambique");
	Vertex Swa = new Vertex("Swaziland");
	Vertex Les = new Vertex("Lesotho");
	Vertex Saf = new Vertex("South Africa");
	Vertex Mad = new Vertex("Madagascar");


	Edge es = new Edge(Egy, Sud);
	Edge el = new Edge(Egy, Lib);
	Egy.edges.add(es);
	Egy.edges.add(el);

	Edge le = new Edge(Lib, Egy);
	Edge lt = new Edge(Lib, Tun);
	Edge lc = new Edge(Lib, Cha);
	Edge ln = new Edge(Lib, Nir);
	Edge la = new Edge(Lib, Alg);
	Edge ls = new Edge(Lib, Sud);
	Lib.edges.add(le);
	Lib.edges.add(lt);
	Lib.edges.add(lc);
	Lib.edges.add(ln);
	Lib.edges.add(la);
	Lib.edges.add(ls);

	Edge tl = new Edge(Tun, Lib);
	Edge ta = new Edge(Tun, Alg);
	Tun.edges.add(tl);
	Tun.edges.add(ta);

	Edge al = new Edge(Alg, Lib);
	Edge at = new Edge(Alg, Tun);
	Edge an = new Edge(Alg, Nir);
	Edge am = new Edge(Alg, Mal);
	Edge amo = new Edge(Alg, Mor);
	Edge ama = new Edge(Alg, Mau);
	Edge aw = new Edge(Alg, Wsa);
	Alg.edges.add(al);
	Alg.edges.add(at);
	Alg.edges.add(an);
	Alg.edges.add(am);
	Alg.edges.add(amo);
	Alg.edges.add(ama);
	Alg.edges.add(aw);

	Edge ma = new Edge(Mor, Alg);
	Edge mw = new Edge(Mor, Wsa);
	Mor.edges.add(ma);
	Mor.edges.add(mw);

	Edge wm = new Edge(Wsa, Mor);
	Edge wa = new Edge(Wsa, Alg);
	Edge wma = new Edge(Wsa, Mau);
	Wsa.edges.add(wm);
	Wsa.edges.add(wa);
	Wsa.edges.add(wma);

	Edge mauma = new Edge(Mau, Mal);
	Edge maus = new Edge(Mau, Sen);
	Edge maua = new Edge(Mau, Alg);
	Edge mauw = new Edge(Mau, Wsa);
	Mau.edges.add(mauma);
	Mau.edges.add(maus);
	Mau.edges.add(maua);
	Mau.edges.add(mauw);

	Edge mala = new Edge(Mal, Alg);
	Edge malnr = new Edge(Mal, Nir);
	Edge malmau = new Edge(Mal, Mau);
	Edge malb = new Edge(Mal, Buf);
	Edge malc = new Edge(Mal, Cdi);
	Edge malg = new Edge(Mal, Gui);
	Edge mals = new Edge(Mal, Sen);
	Mal.edges.add(mala);
	Mal.edges.add(malnr);
	Mal.edges.add(malmau);
	Mal.edges.add(malb);
	Mal.edges.add(malc);
	Mal.edges.add(malg);
	Mal.edges.add(mals);

	Edge smau = new Edge(Sen, Mau);
	Edge sga = new Edge(Sen, Gam);
	Edge sgb = new Edge(Sen, Gub);
	Edge sgu = new Edge(Sen, Gui);
	Edge smal = new Edge(Sen, Mal);
	Sen.edges.add(smau);
	Sen.edges.add(sga);
	Sen.edges.add(sgu);
	Sen.edges.add(sgb);
	Sen.edges.add(smal);

	Edge gamsen = new Edge(Gam, Sen);
	Gam.edges.add(gamsen);

	Edge gubsen = new Edge(Gub, Sen);
	Edge gubgui = new Edge(Gub, Gui);
	Gub.edges.add(gubsen);
	Gub.edges.add(gubgui);

	Edge guisen = new Edge(Gui, Sen);
	Edge guigub = new Edge(Gui, Gub);
	Edge guimal = new Edge(Gui, Mal);
	Edge guisil = new Edge(Gui, Sil);
	Edge guilib = new Edge(Gui, Lib);
	Edge guicdi = new Edge(Gui, Cdi);
	Gui.edges.add(guisen);
	Gui.edges.add(guigub);
	Gui.edges.add(guimal);
	Gui.edges.add(guilib);
	Gui.edges.add(guicdi);
	Gui.edges.add(guisil);

	Edge silgui = new Edge(Sil, Gui);
	Edge sillib = new Edge(Sil, Lib);
	Sil.edges.add(silgui);
	Sil.edges.add(sillib);

	Edge libesil = new Edge(Libe, Sil);
	Edge libegui = new Edge(Libe, Gui);
	Edge libecdi = new Edge(Libe, Cdi);
	Libe.edges.add(libesil);
	Libe.edges.add(libegui);
	Libe.edges.add(libecdi);

	Edge cdimal = new Edge(Cdi, Mal);
	Edge cdigui = new Edge(Cdi, Gui);
	Edge cdilib = new Edge(Cdi, Lib);
	Edge cdibuf = new Edge(Cdi, Buf);
	Edge cdigha = new Edge(Cdi, Gha);
	Cdi.edges.add(cdimal);
	Cdi.edges.add(cdigui);
	Cdi.edges.add(cdilib);
	Cdi.edges.add(cdibuf);
	Cdi.edges.add(cdigha);

	Edge bufmal = new Edge(Buf, Mal);
	Edge bufnir = new Edge(Buf, Nir);
	Edge bufben = new Edge(Buf, Ben);
	Edge buftog = new Edge(Buf, Tog);
	Edge bufgha = new Edge(Buf, Gha);
	Edge bufcdi = new Edge(Buf, Cdi);
	Buf.edges.add(bufmal);
	Buf.edges.add(bufnir);
	Buf.edges.add(bufben);
	Buf.edges.add(buftog);
	Buf.edges.add(bufgha);
	Buf.edges.add(bufcdi);

	Edge ghabuf = new Edge(Gha, Buf);
	Edge ghatog = new Edge(Gha, Tog);
	Edge ghacdi = new Edge(Gha, Cdi);
	Gha.edges.add(ghabuf);
	Gha.edges.add(ghatog);
	Gha.edges.add(ghacdi);

	Edge togbuf = new Edge(Tog, Buf);
	Edge togben = new Edge(Tog, Ben);
	Edge toggha = new Edge(Tog, Gha);
	Tog.edges.add(togbuf);
	Tog.edges.add(togben);
	Tog.edges.add(toggha);

	Edge bennir = new Edge(Ben, Nir);
	Edge bennia = new Edge(Ben, Nia);
	Edge bentog = new Edge(Ben, Tog);
	Edge benbuf = new Edge(Ben, Buf);
	Ben.edges.add(bennir);
	Ben.edges.add(bennia);
	Ben.edges.add(bentog);
	Ben.edges.add(benbuf);

	Edge nirl = new Edge(Nir, Lib);
	Edge nira = new Edge(Nir, Alg);
	Edge nirma = new Edge(Nir, Mal);
	Edge nirbu = new Edge(Nir, Buf);
	Edge nirbe = new Edge(Nir, Ben);
	Edge nirnia = new Edge(Nir, Nia);
	Edge nirc = new Edge(Nir, Cha);
	Nir.edges.add(nirl);
	Nir.edges.add(nira);
	Nir.edges.add(nirma);
	Nir.edges.add(nirbu);
	Nir.edges.add(nirbe);
	Nir.edges.add(nirnia);
	Nir.edges.add(nirc);

	Edge nianir = new Edge(Nia, Nir);	
	Edge niacha = new Edge(Nia, Cha);	
	Edge niacam = new Edge(Nia, Cam);	
	Edge niaben = new Edge(Nia, Ben);	
	Nia.edges.add(nianir);
	Nia.edges.add(niacha);
	Nia.edges.add(niacam);
	Nia.edges.add(niaben);

	Edge camcha = new Edge(Cam, Cha);	
	Edge camnia = new Edge(Cam, Nia);	
	Edge cameqg = new Edge(Cam, Eqg);	
	Edge camgab = new Edge(Cam, Gab);	
	Edge camroc = new Edge(Cam, Roc);	
	Edge camcar = new Edge(Cam, Car);	
	Cam.edges.add(camcha);
	Cam.edges.add(camnia);
	Cam.edges.add(cameqg);
	Cam.edges.add(camgab);
	Cam.edges.add(camroc);
	Cam.edges.add(camcar);

	Edge chalib = new Edge(Cha, Lib);	
	Edge chanir = new Edge(Cha, Nir);	
	Edge chania = new Edge(Cha, Nia);	
	Edge chacam = new Edge(Cha, Cam);	
	Edge chacar = new Edge(Cha, Car);	
	Edge chasud = new Edge(Cha, Sud);	
	Cha.edges.add(chalib);
	Cha.edges.add(chanir);
	Cha.edges.add(chania);
	Cha.edges.add(chacam);
	Cha.edges.add(chacar);
	Cha.edges.add(chasud);

	Edge sudcha = new Edge(Sud, Cha);	
	Edge sudegy = new Edge(Sud, Egy);	
	Edge sudlib = new Edge(Sud, Lib);	
	Edge sudcar = new Edge(Sud, Car);	
	Edge sudssu = new Edge(Sud, Ssu);	
	Edge suderi = new Edge(Sud, Eri);	
	Edge sudeth = new Edge(Sud, Eth);	
	Sud.edges.add(sudcha);
	Sud.edges.add(sudegy);
	Sud.edges.add(sudlib);
	Sud.edges.add(sudcar);
	Sud.edges.add(sudssu);
	Sud.edges.add(suderi);
	Sud.edges.add(sudeth);

	Edge erisud = new Edge(Eri, Sud);	
	Edge erieth = new Edge(Eri, Eth);	
	Edge eridji = new Edge(Eri, Dji);	
	Eri.edges.add(erisud);
	Eri.edges.add(erieth);
	Eri.edges.add(eridji);

	Edge etheri = new Edge(Eth, Eri);	
	Edge ethsud = new Edge(Eth, Sud);	
	Edge ethssu = new Edge(Eth, Ssu);
	Edge ethken = new Edge(Eth, Ken);	
	Edge ethsom = new Edge(Eth, Som);	
	Edge ethdji = new Edge(Eth, Dji);	
	Eth.edges.add(etheri);
	Eth.edges.add(ethsud);
	Eth.edges.add(ethssu);
	Eth.edges.add(ethken);
	Eth.edges.add(ethsom);
	Eth.edges.add(ethdji);

	Edge djieri = new Edge(Dji, Eri);	
	Edge djieth = new Edge(Dji, Eth);	
	Edge djisom = new Edge(Dji, Som);	
	Dji.edges.add(djieri);
	Dji.edges.add(djieth);
	Dji.edges.add(djisom);

	Edge somdji = new Edge(Som, Dji);	
	Edge someth = new Edge(Som, Eth);	
	Edge somken = new Edge(Som, Ken);	
	Som.edges.add(somdji);
	Som.edges.add(someth);
	Som.edges.add(somken);

	Edge kensom = new Edge(Ken, Som);	
	Edge keneth = new Edge(Ken, Eth);	
	Edge kenssu = new Edge(Ken, Ssu);	
	Edge kenuga = new Edge(Ken, Uga);	
	Edge kentan = new Edge(Ken, Tan);	
	Ken.edges.add(kensom);
	Ken.edges.add(keneth);
	Ken.edges.add(kenssu);
	Ken.edges.add(kenuga);
	Ken.edges.add(kentan);

	Edge ssueth = new Edge(Ssu, Eth);	
	Edge ssusud = new Edge(Ssu, Sud);	
	Edge ssucar = new Edge(Ssu, Car);	
	Edge ssudrc = new Edge(Ssu, Drc);	
	Edge ssuuga = new Edge(Ssu, Uga);	
	Edge ssuken = new Edge(Ssu, Ken);	
	Ssu.edges.add(ssueth);
	Ssu.edges.add(ssusud);
	Ssu.edges.add(ssucar);
	Ssu.edges.add(ssudrc);
	Ssu.edges.add(ssuuga);
	Ssu.edges.add(ssuken);

	Edge ugaken = new Edge(Uga, Ken);	
	Edge ugassu = new Edge(Uga, Ssu);	
	Edge ugadrc = new Edge(Uga, Drc);	
	Edge ugarwa = new Edge(Uga, Rwa);	
	Edge ugatan = new Edge(Uga, Tan);	
	Uga.edges.add(ugaken);
	Uga.edges.add(ugassu);
	Uga.edges.add(ugadrc);
	Uga.edges.add(ugarwa);
	Uga.edges.add(ugatan);

	Edge rwauga = new Edge(Rwa, Uga);	
	Edge rwadrc = new Edge(Rwa, Drc);	
	Edge rwabur = new Edge(Rwa, Bur);	
	Edge rwatan = new Edge(Rwa, Tan);	
	Rwa.edges.add(rwauga);
	Rwa.edges.add(rwadrc);
	Rwa.edges.add(rwabur);
	Rwa.edges.add(rwatan);

	Edge burrwa = new Edge(Bur, Rwa);	
	Edge burtan = new Edge(Bur, Tan);	
	Edge burdrc = new Edge(Bur, Drc);	
	Bur.edges.add(burrwa);
	Rwa.edges.add(burtan);
	Rwa.edges.add(burdrc);

	Edge carssu = new Edge(Car, Ssu);	
	Edge carsud = new Edge(Car, Sud);	
	Edge carcha = new Edge(Car, Cha);	
	Edge carcam = new Edge(Car, Cam);	
	Edge carroc = new Edge(Car, Roc);	
	Edge cardrc = new Edge(Car, Drc);      
	Car.edges.add(carssu);
	Car.edges.add(carsud);
	Car.edges.add(carcha);
	Car.edges.add(carcam);
	Car.edges.add(carroc);
	Car.edges.add(cardrc);

	Edge drcbur = new Edge(Drc, Bur);      
	Edge drcrwa = new Edge(Drc, Rwa);      
	Edge drcuga = new Edge(Drc, Uga);      
	Edge drcssu = new Edge(Drc, Ssu);      
	Edge drccar = new Edge(Drc, Car);      
	Edge drcroc = new Edge(Drc, Roc);      
	Edge drcang = new Edge(Drc, Ang);      
	Edge drczam = new Edge(Drc, Zam);      
	Edge drctan = new Edge(Drc, Tan);      
	Drc.edges.add(drcrwa);
	Drc.edges.add(drcuga);
	Drc.edges.add(drcbur);
	Drc.edges.add(drcssu);
	Drc.edges.add(drccar);
	Drc.edges.add(drcroc);
	Drc.edges.add(drcang);
	Drc.edges.add(drczam);
	Drc.edges.add(drctan);

	Edge eqgcam = new Edge(Eqg, Cam);      
	Edge eqggab = new Edge(Eqg, Gab);      
	Eqg.edges.add(eqgcam);
	Eqg.edges.add(eqggab);

	Edge gabcam = new Edge(Gab, Cam);      
	Edge gabeqg = new Edge(Gab, Eqg);      
	Edge gabroc = new Edge(Gab, Roc);      
	Gab.edges.add(gabcam);
	Gab.edges.add(gabeqg);
	Gab.edges.add(gabroc);

	Edge roccar = new Edge(Roc, Car);      
	Edge roccam = new Edge(Roc, Cam);      
	Edge rocgab = new Edge(Roc, Gab);      
	Edge rocdrc = new Edge(Roc, Drc);      
	Roc.edges.add(roccar);
	Roc.edges.add(roccam);
	Roc.edges.add(rocgab);
	Roc.edges.add(rocdrc);

	Edge tanken = new Edge(Tan, Ken);      
	Edge tanuga = new Edge(Tan, Uga);      
	Edge tanrwa = new Edge(Tan, Rwa);      
	Edge tanbur = new Edge(Tan, Bur);      
	Edge tandrc = new Edge(Tan, Drc);      
	Edge tanzam = new Edge(Tan, Zam);      
	Edge tanmala = new Edge(Tan, Mala);      
	Edge tanmoz = new Edge(Tan, Moz);      
	Tan.edges.add(tanken);
	Tan.edges.add(tanuga);
	Tan.edges.add(tanrwa);
	Tan.edges.add(tanbur);
	Tan.edges.add(tandrc);
	Tan.edges.add(tanzam);
	Tan.edges.add(tanmala);
	Tan.edges.add(tanmoz);

	Edge malatan = new Edge(Mala, Tan);      
	Edge malazam = new Edge(Mala, Zam);      
	Edge malamoz = new Edge(Mala, Moz);      
	Mala.edges.add(malatan);
	Mala.edges.add(malazam);
	Mala.edges.add(malamoz);

	Edge zamtan = new Edge(Zam, Tan);      
	Edge zamdrc = new Edge(Zam, Drc);      
	Edge zamang = new Edge(Zam, Ang);      
	Edge zamnam = new Edge(Zam, Nam);      
	Edge zamzim = new Edge(Zam, Zim);      
	Edge zammoz = new Edge(Zam, Moz);      
	Edge zambot = new Edge(Zam, Bot);      	
	Edge zammala = new Edge(Zam, Mala);
	Zam.edges.add(zamtan);
	Zam.edges.add(zamdrc);
	Zam.edges.add(zamang);
	Zam.edges.add(zamnam);
	Zam.edges.add(zamzim);
	Zam.edges.add(zammoz);
	Zam.edges.add(zambot);
	Zam.edges.add(zammala);

	Edge angdrc = new Edge(Ang, Drc);      
	Edge angzam = new Edge(Ang, Zam);      
	Edge angnam = new Edge(Ang, Nam);      
	Ang.edges.add(angdrc);
	Ang.edges.add(angzam);
	Ang.edges.add(angnam);

	Edge namang = new Edge(Nam, Ang);      
	Edge nambot = new Edge(Nam, Bot);      
	Edge namzam = new Edge(Nam, Zam);      
	Edge namsaf = new Edge(Nam, Saf);      
	Edge namzim = new Edge(Nam, Zim);
	Nam.edges.add(namang);
	Nam.edges.add(nambot);
	Nam.edges.add(namzam);
	Nam.edges.add(namsaf);
	Nam.edges.add(namzim);

	Edge botnam = new Edge(Bot, Nam);      
	Edge botsaf = new Edge(Bot, Saf);      
	Edge botzim = new Edge(Bot, Zim);      
	Edge botzam = new Edge(Bot, Zam);      
	Bot.edges.add(botnam);
	Bot.edges.add(botsaf);
	Bot.edges.add(botzim);
	Bot.edges.add(botzam);

	Edge zimmoz = new Edge(Zim, Moz);      
	Edge zimzam = new Edge(Zim, Zam);      
	Edge zimbot = new Edge(Zim, Bot);      
	Edge zimsaf = new Edge(Zim, Saf);      
	Edge zimnam = new Edge(Zim, Nam);
	Zim.edges.add(zimmoz);
	Zim.edges.add(zimzam);
	Zim.edges.add(zimbot);
	Zim.edges.add(zimsaf);
	Zim.edges.add(zimnam);

	Edge moztan = new Edge(Moz, Tan);      
	Edge mozmala = new Edge(Moz, Mala);      
	Edge mozzim = new Edge(Moz, Zim);      
	Edge mozzam = new Edge(Moz, Zam);      
	Edge mozswa = new Edge(Moz, Swa);      
	Edge mozsaf = new Edge(Moz, Saf);      
	Moz.edges.add(moztan);
	Moz.edges.add(mozmala);
	Moz.edges.add(mozzim);
	Moz.edges.add(mozzam);
	Moz.edges.add(mozswa);
	Moz.edges.add(mozsaf);

	Edge swamoz = new Edge(Swa, Moz);      
	Edge swasaf = new Edge(Swa, Saf);      
	Edge lessaf = new Edge(Les, Saf);      
	Swa.edges.add(swamoz);
	Swa.edges.add(swasaf);
	Les.edges.add(lessaf);

	Edge safnam = new Edge(Saf, Nam);      
	Edge safbot = new Edge(Saf, Bot);      
	Edge safzim = new Edge(Saf, Zim);      
	Edge safmoz = new Edge(Saf, Moz);      
	Edge safswa = new Edge(Saf, Swa);      
	Edge safles = new Edge(Saf, Les);      
	Saf.edges.add(safnam);
	Saf.edges.add(safbot);
	Saf.edges.add(safzim);
	Saf.edges.add(safmoz);
	Saf.edges.add(safswa);
	Saf.edges.add(safles);

	ArrayList<Vertex> vertices = new ArrayList<>();

	vertices.add(Egy);
	vertices.add(Lib);
	vertices.add(Tun);
	vertices.add(Alg);
	vertices.add(Mor);
	vertices.add(Wsa);
	vertices.add(Mau);
	vertices.add(Mal);
	vertices.add(Nir);
	vertices.add(Cha);
	vertices.add(Sud);
	vertices.add(Ssu);
	vertices.add(Eri);
	vertices.add(Eth);
	vertices.add(Dji);
	vertices.add(Som);
	vertices.add(Ken);
	vertices.add(Uga);
	vertices.add(Rwa);
	vertices.add(Drc);
	vertices.add(Car);
	vertices.add(Roc);
	vertices.add(Gab);
	vertices.add(Eqg);
	vertices.add(Cam);
	vertices.add(Nia);
	vertices.add(Ben);
	vertices.add(Tog);
	vertices.add(Gha);
	vertices.add(Buf);
	vertices.add(Cdi);
	vertices.add(Libe);
	vertices.add(Sil);
	vertices.add(Gui);
	vertices.add(Gub);
	vertices.add(Gam);
	vertices.add(Sen);
	vertices.add(Bur);
	vertices.add(Tan);
	vertices.add(Mala);
	vertices.add(Zam);
	vertices.add(Ang);
	vertices.add(Nam);
	vertices.add(Bot);
	vertices.add(Zim);
	vertices.add(Moz);
	vertices.add(Swa);
	vertices.add(Les);
	vertices.add(Saf);
	vertices.add(Mad);

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
	    System.out.print("['"+v.name +"'"+ ", " + v.color+"], ");
	}
    }
}
