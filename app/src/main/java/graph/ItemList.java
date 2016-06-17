package graph;
import java.util.ArrayList;
import java.util.List;


public class ItemList implements Cloneable{
	private int totalitems;
	private List<Item> items;
	private List<Pair<Label,Double>> userweights; //Son las preferencias del usuario
	private double [][] ady = new double [totalitems][totalitems]; //E

	/**
	 * @param n: n�mero de items del Grafo
	 * @param E: Matriz de adyacencia
	 * @param w: lista de preferencias del usuario
	 */
	public ItemList(double[][] e,int n,List<Pair<Label,Double>> w){
		this.userweights = w;
		this.ady = e;
		this.totalitems = n;
		this.items = new ArrayList<Item>();
	}
	/**
	 * 

	 */
	public ItemList(int n, double[][] E, List<Item> li,List<Pair<Label,Double>> w){
		this.userweights = w;
		this.ady = E;
		this.totalitems = n;
		this.items = li;
	}
	/**
	 * Se utiliza al crear el Grafo
	 * @param n N�mero de Items que tendr� el Grafo
	 */
	public void setTotalItems(){
		this.totalitems = this.items.size();
	}
	public double bs(){
		double bs = 0, adyi = 0;
		for(int i = 0; i < items.size()-1; i++){
			adyi = ady[items.get(i).getid()-1][items.get(i+1).getid()-1];
			if (adyi >= 0){
				bs += adyi;
			}
			bs += items.get(i).gettime();
		}
		if(items.size()>0)
			bs += items.get(items.size()-1).gettime();
		return bs;
	}
	public double walkingTime(){
		double bs = this.bs();
		double nodetime = 0;
		for (Item i: this.items){
			nodetime += i.gettime();
		}
		return bs-nodetime;
	}
	private static double interpolation(double ci) {
		return ((ci-0.75)/6.5) + 1;
	}
	public double kc_interpolated(){
	//	for (Etiquetas kc:Etiquetas.values()){
		double kc = 0;
		for (int i = 0; i < this.items.size(); i++){
			kc += this.items.get(i).cov(userweights) / interpolation(this.get(i).gettime());
		}
		return kc;
		
	}
	
	public double kc_sum(){
	//	for (Etiquetas kc:Etiquetas.values()){
		double kc = 0;
		for (int i = 0; i < this.items.size(); i++){
			kc += this.items.get(i).cov(userweights);
		}
		return kc;
		
	}	
	public double kc_sum_timed(){
	//	for (Etiquetas kc:Etiquetas.values()){
		double kc = 0;
		for (int i = 0; i < this.items.size(); i++){
			kc += this.items.get(i).cov(userweights)*this.items.get(i).gettime();
		}
		return kc;
		
	}	
	public String kc_sum_str(){
	String str = "";
		for (int i = 0; i < this.items.size(); i++){
			if (i == 0)
				str += this.items.get(i).cov_str(userweights) + "\n";
			else
				str += "+" + this.items.get(i).cov_str(userweights) + "\n";
		}
		return str;
		
	}	
	
	public double kc_timed(){
	//	for (Etiquetas kc:Etiquetas.values()){
		double kc = 0;
		Label q;
		for (int i = 0; i < userweights.size(); i++){
			q = userweights.get(i).getFirstElement();			
			kc += userweights.get(i).getSecondElement()*cov(q)*timeInLabel(q);
		}
		return kc;
		
	}	
	
	private double timeInLabel(Label q) {
		double time = 0;
		for (int i = 0; i< items.size(); i++){
			if (items.get(i).labelInItem(q))
				time += this.items.get(i).gettime();
		}
		return time;
	}
	public double kc(){
	//	for (Etiquetas kc:Etiquetas.values()){
		double kc = 0;
		Label q;
		for (int i = 0; i < userweights.size(); i++){
			q = userweights.get(i).getFirstElement();
			kc += userweights.get(i).getSecondElement()*cov(q);
			
		}
		return kc;
		
	}	
	public String kc_str(){
	String kcstr = "";
		Label q;
		for (int i = 0; i < userweights.size(); i++){
			q = userweights.get(i).getFirstElement();
			kcstr += " + " + userweights.get(i).getSecondElement() + "*[" + cov_str(q) + "]";
			
		}
		return kcstr;
		
	}	
	public double kc_chosen(String kc){
		if (kc == "kc") return kc();
		else if (kc == "kc_sum") return kc_sum();
		else if (kc == "kc_interpolated") return kc_interpolated();
		else if (kc == "kc_timed") return kc_timed();
		else if (kc == "kc_sum_timed") return kc_sum_timed();
		return kc();
	}
	public double cov(Label q){
		double prod = 1;
		for (int j = 0; j < this.items.size(); j++){
			prod = prod*(1-items.get(j).cov(q));
		}
		return 1 - prod;
	}
	public String cov_str(Label q){
		double prod = 1;
		String str = "";
		for (int j = 0; j < this.items.size(); j++){
			prod = prod*(1-items.get(j).cov(q));
			str += " * " + "(1 - " + items.get(j).cov(q) + ")";
		}
		return "1 - " + str;
	}
	/**
	 * Se utiliza para calcular la funci�n heur�stica
	 * Calcula el m�ximo (entre las etiquetas) de los productos (1-cov(vi))
	 * @return
	 */
	public double heurmax(){
		double prod = Double.NEGATIVE_INFINITY, prodaux = 1;
		Label q;
		for (int i = 0; i < this.userweights.size(); i++){
			q = this.userweights.get(i).getFirstElement();
			prodaux = 1;
			for (int j = 0; j < this.items.size(); j++){
				prodaux = prodaux*(1-items.get(j).cov(q));
			}
			if (prodaux > prod){
				prod = prodaux;
			}
		}
		return prod;
		
	}
	public void add(Item it){
		this.items.add(it);
	}
	public void addGreedy(Item it){
		this.items.add(it);
		this.sort();
	}
	
	public double totalTime(){
		double time = 0;
		for (Item i:this.items)
			time += i.gettime();
		return time;
	}

	public void delete(int i){
		this.items.remove(i);
	}
	
	public boolean deleteIndex(int i){
		for (int j = 0; j< this.size(); j++){
			if (this.items.get(j).getid() == i){
				delete(j);
				return true;
			}
		}
		return false;
	}
	
	public Item get(int i){
		return this.items.get(i);
	}
	public int size(){
		return this.items.size();
	}
	public int totalitems(){
		return this.totalitems;
	}
    public ItemList clone(){
    	List<Item> it = new ArrayList<Item>();
    	for (Item ite:items)
    		it.add(ite);
        ItemList obj=new ItemList(totalitems, ady, it, userweights);
        return obj;
    }
    public String toString(){
    	String str = "";
    	for (int i = 0; i < size() - 1; i++){
    		str += Integer.toString((this.items.get(i).getid())) + ", ";
    	}
    	str += Integer.toString((this.items.get(size()-1).getid()));
    	return str;
    }
    public boolean indexinroute(int id) {
	  	for (Item ite:items)
    		if (ite.getid() == id) return true;
	  	return false;
	}
    public Item getItemfromIndex(int id) {
	  	for (Item ite:items)
    		if (ite.getid() == id) return ite;
	  	return null;
	}
	public double[][] getady(){
		return this.ady;
	}
	public List<Pair<Label, Double>> getuserprefs(){
		return this.userweights;
	}
	public ItemList sort() {
		ItemList sortedroute = new ItemList(ady, totalitems, userweights);
		for (int i = 1; i <= totalitems; i++){
			if (indexinroute(i)) sortedroute.add(getItemfromIndex(i));
		}
		items = sortedroute.items;
		return this;
	}
	public void kcOrder() {
		int numitems = this.totalitems;
		ItemList U = (ItemList) this.clone();
		ItemList greedyRoute = new ItemList(this.ady, numitems, this.userweights);
		double max = 0;
		double ci = 0;
		int maxindex = 0;
		while (U.size() > 0) {
			max = Double.NEGATIVE_INFINITY;
			maxindex = 0;
			for (int i = 0; i < U.size(); i++) {
				ci = U.get(i).gettime();
				if (ci != 0) {
					if ((U.get(i).cov(this.userweights) / ci) > max) {
						maxindex = i;
						max = (U.get(i).cov(this.userweights) / ci);
					}
				}
			}
			greedyRoute.add(U.get(maxindex));
			U.delete(maxindex);
		}
		this.items = greedyRoute.items;
		
	}
	public void removeLast() {
		this.items.remove(this.items.size()-1);	
	}
	public void removeItemsFromItemList(ItemList recommendedRoute) {
		for (int i = 0; i < recommendedRoute.size(); i++){
			this.deleteIndex(recommendedRoute.get(i).getid());	
		}
		
	}
	public void setuserprefs(List<Pair<Label, Double>> userprefs) {
		this.userweights = userprefs;
	}
}
