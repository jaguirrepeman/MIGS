package graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class RouteFinding {

	public RouteFinding(List<Item> listitemm, int[][] ady) {

	}

	public static ItemList ORSKC(Graph G, double Delta) {
		Item vs = G.getvs();
		Item vt = G.getvt();
		ItemList V = G.getallItems();
		double [][]E = G.getady();
		List<Pair<Label,Double>> userweights = G.getuserweights();
		int i;
		int s = vs.getid()-1, t = vt.getid()-1;
		int numitems = V.size();
		int n = numitems - 1;
		RouteLabel L0s,Lik,Likaux,Ljl;
		ItemList Rik,Rjl;
		Comparator<RouteLabel> comparator = new RouteLabelComparator();
        PriorityQueue<RouteLabel> Q = new PriorityQueue<RouteLabel>(1000, comparator);
		ItemList R = new ItemList(E,numitems,userweights);
		double KCmax = Double.NEGATIVE_INFINITY;
		ItemList firstitem = new ItemList(E,numitems,userweights);
		firstitem.add(vs);
		L0s = new RouteLabel(firstitem, 0,G, Delta);
		Q.add(L0s);
		long time = System.currentTimeMillis();
		while (!(Q.isEmpty())) {
			Lik = Q.remove();

			i = Lik.geti();
			if (Lik.getf() <= KCmax)
				return null;
			Rik = Lik.getRoute();
			for (int j = i+1; j < numitems; j++) {
				if (E[i][j] <= 0) continue;
				Rjl = (ItemList) Rik.clone();
				Rjl.add(V.get(j));						
				if (Rjl.bs() > Delta) continue;
				if (j == t) {
					double auxx = Rjl.kc();
					if (Rjl.kc() > KCmax) {
						R = Rjl;
						KCmax = Rjl.kc();
//						System.out.println(KCmax);
//						System.out.println(R);
//						System.out.print("Tiempo de c�lculo: ");
//						System.out.println(System.currentTimeMillis()-time);
//						System.out.print("Coste: ");
//						System.out.println(R.bs());
//						System.out.print("Valor heur�stico: ");
//						System.out.println(R.kc());
//						System.out.println(R.kc_str());						
//						System.out.print("Suma del valor heur�stico de las etiquetas: ");
//						System.out.println(R.kc_sum());
//						System.out.println(R.kc_sum_str());
						
					}
				} else {
					Likaux = new RouteLabel(Rjl, Rjl.bs(),G, Delta);
					double fj = Likaux.getf();	
					if (fj > KCmax) {
						Ljl = Likaux;
						Q.add(Ljl);
					}
				}
			}

		} // Fin del while
		if (KCmax == Double.NEGATIVE_INFINITY)
			return null;
		else
			return R;
	}

	public static ItemList greedy(Graph gr, double Delta, boolean force, boolean sort) {
		long time = System.currentTimeMillis();
		ItemList V = gr.getallItems();
		double[][] E = gr.getady();
		List<Pair<Label, Double>> userweights = gr.getuserweights();
		int numitems = V.totalitems();
		ItemList U = (ItemList) V.clone();
		ItemList G = new ItemList(E, numitems, userweights);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		if (force){
			G.addGreedy(U.get(0));
			G.addGreedy(U.get(numitems-1));
			U.delete(numitems-1);
			U.delete(0);
			
		}
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime() + U.get(i).cost(E, numitems);
				if (ci != 0) {
					if ((U.get(i).cov(userweights) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(userweights) / ci);
					}
				}
			}
			if (G.clone().sort().bs() + U.get(maxindex).gettime() <= Delta) {
				if (sort)
					G.addGreedy(U.get(maxindex));
				else
					G.add(U.get(maxindex));
			}
			U.delete(maxindex);
		}
		if (sort)
			G.sort();
//		System.out.print("Valor heur�stico: ");
//		System.out.println(G.kc());
//		System.out.print("Ruta: ");
//		System.out.println(G);
//		System.out.print("Tiempo de c�lculo: ");
//		System.out.print(System.currentTimeMillis()-time);
//		System.out.println(" milisegundos");
//		System.out.print("Tiempo de la ruta: ");
//		System.out.println(G.bs());
//		System.out.print("Tiempo andando: ");
//		System.out.println(G.walkingTime());
		
		
		//Prints Comentados
//		System.out.println(G);
//		System.out.print("Tiempo de c�lculo: ");
//		System.out.println(System.currentTimeMillis()-time);
//		System.out.print("Coste: ");
//		System.out.println(G.bs());
//		System.out.print("Valor heur�stico: ");
//		System.out.println(G.kc());
//		System.out.print("Suma del valor heur�stico de las etiquetas: ");
//		System.out.println(G.kc_sum());
		
		
		return G;
	}

	private static double interpolation(double ci) {
		return ((ci-0.75)/3.25) + 1;
	}

	public static void CBRGreedy(Graph G, ItemList recommendedRoute, double Delta) {
		ItemList V = G.getallItems();
		double[][] E = G.getady();
		List<Pair<Label, Double>> userweights = G.getuserweights();
		int numitems = V.totalitems();
		ItemList U = (ItemList) V.clone();
		U.removeItemsFromItemList(recommendedRoute);
		ItemList route = new ItemList(E, numitems, userweights);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime() + U.get(i).cost(E, numitems);
				if (ci != 0) {
					if ((U.get(i).cov(userweights) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(userweights) / ci);
					}
				}
			}
			if (route.bs() + U.get(maxindex).gettime() <= Delta) {
				route.addGreedy(U.get(maxindex));
			}
			U.delete(maxindex);
		}
		route.sort();
		
	}
	public static ItemList greedyMadrid(Graph gr, double Delta, boolean force, boolean sort) {
		long time = System.currentTimeMillis();
		ItemList V = gr.getallItems();
		double[][] E = gr.getady();
		List<Pair<Label, Double>> userweights = gr.getuserweights();
		int numitems = V.totalitems();
		ItemList U = (ItemList) V.clone();
		ItemList G = new ItemList(E, numitems, userweights);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		if (force){
			G.addGreedy(U.get(0));
			G.addGreedy(U.get(numitems-1));
			U.delete(numitems-1);
			U.delete(0);
			
		}
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime() + U.get(i).cost(E, numitems);
				if (ci != 0) {
					if ((U.get(i).cov(userweights) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(userweights) / ci);
					}
				}
			}
			if (G.clone().sort().bs() + U.get(maxindex).gettime() <= Delta) {
				if (sort)
					G.addGreedy(U.get(maxindex));
				else
					G.add(U.get(maxindex));
			}
			U.delete(maxindex);
		}
		if (sort)
			G.sort();
//		System.out.print("Valor heur�stico: ");
//		System.out.println(G.kc());
//		System.out.print("Ruta: ");
//		System.out.println(G);
//		System.out.print("Tiempo de c�lculo: ");
//		System.out.print(System.currentTimeMillis()-time);
//		System.out.println(" milisegundos");
//		System.out.print("Tiempo de la ruta: ");
//		System.out.println(G.bs());
//		System.out.print("Tiempo andando: ");
//		System.out.println(G.walkingTime());
		
		
		//Prints Comentados
//		System.out.println(G);
//		System.out.print("Tiempo de c�lculo: ");
//		System.out.println(System.currentTimeMillis()-time);
//		System.out.print("Coste: ");
//		System.out.println(G.bs());
//		System.out.print("Valor heur�stico: ");
//		System.out.println(G.kc());
//		System.out.print("Suma del valor heur�stico de las etiquetas: ");
//		System.out.println(G.kc_sum());
		
		
		return G;
	}
	public static ItemList ORSKCMadrid(Graph G, double Delta) {
		Item vs = G.getvs();
		Item vt = G.getvt();
		ItemList V = G.getallItems();
		double [][]E = G.getady();
		List<Pair<Label,Double>> userweights = G.getuserweights();
		int i;
		int s = vs.getid()-1, t = vt.getid()-1;
		int numitems = V.size();
		int n = numitems - 1;
		RouteLabel L0s,Lik,Likaux,Ljl;
		ItemList Rik,Rjl;
		Comparator<RouteLabel> comparator = new RouteLabelComparator();
        PriorityQueue<RouteLabel> Q = new PriorityQueue<RouteLabel>(1000, comparator);
		ItemList R = new ItemList(E,numitems,userweights);
		double KCmax = Double.NEGATIVE_INFINITY;
		ItemList firstitem = new ItemList(E,numitems,userweights);
		firstitem.add(vs);
		L0s = new RouteLabel(firstitem, 0,G, Delta);
		Q.add(L0s);
		long time = System.currentTimeMillis();
		while (!(Q.isEmpty())) {
			Lik = Q.remove();

			i = Lik.geti()-1;
			if (Lik.getf() <= KCmax)
				return null;
			Rik = Lik.getRoute();
			for (int j = i+1; j < numitems; j++) {
				if (E[i][j] <= 0) continue;
				Rjl = (ItemList) Rik.clone();
				Rjl.add(V.get(j));						
				if (Rjl.bs() > Delta) continue;
				if (Rjl.kc() > KCmax) {
					R = Rjl;
					KCmax = Rjl.kc();
					
				}
				{
					Likaux = new RouteLabel(Rjl, Rjl.bs(),G, Delta);
					double fj = Likaux.getf();	
					if (fj > KCmax) {
						Ljl = Likaux;
						Q.add(Ljl);
					}
				}
			}

		} // Fin del while
		if (KCmax == Double.NEGATIVE_INFINITY)
			return null;
		else
			return R;
	}
}