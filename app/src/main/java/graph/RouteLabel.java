package graph;

import java.util.ArrayList;
import java.util.List;

public class RouteLabel implements Cloneable{
		private ItemList route;
		private double budget;
		private double Delta;
		Graph G;
		RouteLabel(ItemList rjl, double d, Graph gr, double Delta) {
			this.route = rjl;
			this.budget = d;
			this.G = gr;
			this.Delta = Delta;

		}

		public int geti() {
			return route.get(route.size() - 1).getid();
		}

		ItemList getRoute() {
			return this.route;
		}
		
	    public RouteLabel clone(){
	    	ItemList r = route.clone();
	    	return new RouteLabel(r,budget,G, Delta);
	    }

		public double getf() {
			double max,kcl;	
			max = route.heurmax();
			ItemList Lmg = greedy(G.getallItems(), G.getady(), G.getvs(), G.getvt(), G.getuserweights(), Delta);
			kcl = Lmg.kc();
			
			return route.kc() + max*(kcl/(1-1/Math.sqrt(Math.E)));
		}
		
		public double getf_sum() {
			double max,kcl;	
			max = route.heurmax();
			ItemList Lmg = greedy(G.getallItems(), G.getady(), G.getvs(), G.getvt(), G.getuserweights(), Delta);
			kcl = Lmg.kc_sum();
			
			return route.kc() + max*(kcl/(1-1/Math.sqrt(Math.E)));
		}

		public double getf2() {
			double perc = this.route.size() / this.route.totalitems();
			return this.route.kc()/perc;
		}
		public ItemList greedy(ItemList V, double[][] E, Item vs, Item vt, List<Pair<Label,Double>> userweights,
				 double Delta) {
			int numitems = V.totalitems();
			ItemList U = (ItemList) V.clone();
			ItemList G = new ItemList(E, numitems, userweights);
			double max = 0; double ci = 0;
			int maxindex = 0;
			while(U.size() > 0){
				max = Double.NEGATIVE_INFINITY;
				maxindex = 0;
				for (int i = 0; i < U.size(); i++){
					ci = U.get(i).cost(E, V.size()); 
					if (ci != 0){
						if((U.get(i).cov(userweights)/ci) > max){
							maxindex = i;
							max = U.get(i).cov(userweights)/ci;			
						}
					}
				}
				if (G.bs() + max <= Delta){
					G.add(U.get(maxindex));
				}
				U.delete(maxindex);
			}
			return G;
		}
		public String toString(){
			return this.route.toString();
		}

	}