package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ReadFile {
	static Item floor3It1, floor3It2, stairsIt1, stairsIt2, floor4It1, floor4It2;
	static double distance_stairs = 2;
	public static Graph readData(List<Pair<Label, Double>> userprefs) throws FileNotFoundException,
			IOException {
		Graph G;
		double[][] ady = null;
		boolean init = false;
		ItemList itemlist = new ItemList(null, 0, userprefs);
		Item it = null, vs = null, vt = null;
		String description, keystring, dstring, pos, posxs, posys, cadena, adystring, lastline;
		int id, posx, posy; // Posici�n x en la representaci�n visual del
									// grafo
		List<Pair<Label, Double>> keywords;
		Label key;
		double d, time;
		List<String> adylist = new ArrayList<String>();

		Pair<Label, Double> pair;
		URL url = new URL("https://raw.githubusercontent.com/jaguirrepeman/TFG/master/ItemsMuseo");

		// Read all the text returned by the server
		BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
		lastline = b.readLine();
		while (lastline != null) {
			id = Integer.parseInt(lastline);
			String strtime = b.readLine();
			time = Double.parseDouble(strtime);
			description = b.readLine();
			String kws = b.readLine();
			StringTokenizer st = new StringTokenizer(kws);
			keywords = new ArrayList<Pair<Label, Double>>();

			// bucle por todas las palabras
			while (st.hasMoreTokens()) {
				keystring = st.nextToken();
				dstring = st.nextToken();
				key = Label.valueOf(keystring);
				d = Double.parseDouble(dstring);
				pair = new Pair<Label, Double>(key, d);
				keywords.add(pair);
			}
			// while((cadena = b.readLine())!=null) {
			// System.out.println(cadena);
			// }
			pos = b.readLine();
			st = new StringTokenizer(pos);
			posxs = st.nextToken();
			posys = st.nextToken();
			posx = Integer.parseInt(posxs);
			posy = Integer.parseInt(posys);
			it = new Item(id, time, description, keywords, posx, posy);
			itemlist.add(it);
			if (!init){
				vs = it;
				init = true;
			}
			b.readLine(); //Leemos el salto de linea
			lastline = b.readLine();
		}
		b.close();

		vt = it;
		itemlist.setTotalItems();
		
		//Planta 3
		floor3It1 = new Item(3001, 0, "", null, 50, 123);
		floor3It2 = new Item(3002, 0, "", null, 50, 213);
		

		//Escaleras
		stairsIt1 = new Item(5001, 0, "", null, 765, 193);
		stairsIt2 = new Item(5002, 0, "", null, 765, 503);
		
		//Planta 4
		floor4It1 = new Item(5001, 0, "", null, 50, 463);
		floor4It2 = new Item(5002, 0, "", null, 50, 553);

		ady = new double[itemlist.totalitems()][itemlist.totalitems()];
		for (int i = 0; i < itemlist.totalitems(); i++) {
			for (int j = 0; j < itemlist.totalitems(); j++) {
				//if (i != j/* && Math.abs(i-j) < 8*/) ady[i][j] = 0.01 + itemlist.get(j).gettime();//distancegroups(itemlist.get(i),itemlist.get(j)) + itemlist.get(j).gettime();
				if (i != j) ady[i][j] = distancegroups(itemlist.get(i),itemlist.get(j));//distancegroups(itemlist.get(i),itemlist.get(j)) + itemlist.get(j).gettime();
				else ady[i][j] = -1;
			}
		}
		itemlist.get(itemlist.size()-1).setcost(0);


		//TODO Acuerdate de sumar los tiempos de cada item
		
		G = new Graph(itemlist, ady, vs, vt, userprefs);
		return G;
	}

	private static int group(int i) {
		//Recordar que los items empiezan en 0, así que hay que restar 1 al id
		if (i<=27 )return 1;
		else if (i>= 28 && i<=44) return 2;
		else if (i>= 45 && i<=49) return 3;
		else if (i>= 50 && i<=52) return 4;
		else return -1;
	}

	private static double distancegroups(Item i, Item j) {
		Item max, min;
		if (group(i.getid()) > group(j.getid())){
			max = i; min = j;
		}
		else{
			max = j; min = i;
		}
		if (group(i.getid()) == group(j.getid()))
			return distance(min, max);
		else if (group(max.getid()) == 2 && group(min.getid()) == 1) return distance(min,floor3It1) + distance(floor3It1,floor3It2)
				+ distance(floor3It1,max);
		else if (group(max.getid()) == 3 && group(min.getid()) == 1)
			return distance(min,stairsIt1) + distance_stairs + distance(stairsIt2,max);
		else if (group(max.getid()) == 4 && group(min.getid()) == 1)
			return distance(min,stairsIt1) + distance_stairs + distance(stairsIt2,max);
		else if (group(max.getid()) == 3 && group(min.getid()) == 2)
			return distance(min,stairsIt1) + distance_stairs + distance(stairsIt2,max);
		else if (group(max.getid()) == 4 && group(min.getid()) == 3)
			return distance(min,floor4It1) + distance(floor4It1,floor4It2)
					+ distance(floor4It1,max);
		else return 0;

	}

	public static double distance(Item x1, Item x2){
		//TODO multiplicar por un factor de distancia
		int x11, x12, x21, x22;
		double aux;
		x11 = x1.getposx();
		x12 = x1.getposy();
		x21 = x2.getposx();
		x22 = x2.getposy();
		aux =  Math.sqrt(Math.pow((x22-x12),2) + Math.pow((x21-x11),2));
//		   return 0.01;
		return aux / 1000;

	}

}