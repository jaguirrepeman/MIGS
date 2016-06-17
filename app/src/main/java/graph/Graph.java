package graph;
import java.util.ArrayList;
import java.util.List;


public class Graph {
	private ItemList V;
	private double[][] E;
	private Item vs;
	private Item vt;
	private List<Pair<Label,Double>> userweights;
	Graph(ItemList V, double[][] ady, Item vs, Item vt, List<Pair<Label,Double>> uw){
		this.V = V;
		this.E = ady; 
		this.vs = vs;
		this.vt = vt;
		this.userweights = uw;
	}
	public int getnumitems(){
		return this.V.size();
	}
	public Item getvs(){
		return vs;
	}
	public Item getvt(){
		return vt;
	}
	public List<Pair<Label,Double>> getuserweights(){
		return this.userweights;
	}
	public double[][] getady(){
		return E;
	}
	public double getadyelement(int i, int j){
		return E[i][j];
	}
	public Item getitem(int i){
		return V.get(i);
	}
	public Item getitemUsingId(int id){
		return V.get(id-1);
	}
	
	public ItemList getallItems(){
		return this.V;
	}
	public String toString(){
		String str = "";
		for (int i = 0; i < V.size(); i++){
			str += "[";
			for (int j = 0; j < V.size() -1 ; j++){
				str += " " + E[i][j] + " ";
			}
			str += E[i][V.size() -1] + "]\n";
		}
		return str;
	}
	public ItemList greedy(double Delta, List<Pair<Label, Double>> userprefs) {
		ItemList V = getallItems();
		double[][] E = getady();
		int numitems = V.totalitems();
		ItemList U = (ItemList) V.clone();
		ItemList G = new ItemList(E, numitems, userprefs);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime();
				if (ci != 0) {
					if ((U.get(i).cov(userprefs) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(userprefs) / ci);
					}
				}
			}
			if (G.bs() + U.get(maxindex).gettime() <= Delta) {
				G.add(U.get(maxindex));
			}
			U.delete(maxindex);
		}
		return G;
	}
	
	public ItemList greedy(double Delta, List<Pair<Label, Double>> userprefs, ItemList route) {
		double[][] E = getady();
		int numitems = route.totalitems();
		ItemList U = (ItemList) route.clone();
		ItemList greedyRoute = new ItemList(E, numitems, userprefs);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime();
				if (ci != 0) {
					if ((U.get(i).cov(userprefs) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(userprefs) / ci);
					}
				}
			}
			if (greedyRoute.bs() + U.get(maxindex).gettime() <= Delta) {
				greedyRoute.add(U.get(maxindex));
			}
			U.delete(maxindex);
		}
		return greedyRoute;
	}
	
	public double max_kc_sum(){
		return V.kc_sum();
	}
	public void setuserweights(List<Pair<Label, Double>> uw) {
		this.userweights = uw;
		this.V.setuserprefs(uw);
		
	}

}
