package graph;
import java.util.List;

public class Item {
private int id; //Identificador del item
private double time; //Tiempo a pasar en el item, se incluye en la matriz de adyacencia
private String description;
private List<Pair<Label,Double>> keywords;
//private List<Etiquetas,int> lista;
private int posx; //Posici�n x en la representaci�n visual del grafo
private int posy;
private double cost = -1;
public int getposx(){
	return posx;
}
public int getposy(){
	return posy;
}
public void setposx(int p){
	this.posx = p;
}
public void setposy(int p){
	this.posy = p;
}
public int getid(){
	return id;
}
public double gettime(){
	return time;
}

public Item(int num, double time2, String descr, List<Pair<Label,Double>> keywords, int posx, int posy){
	this.keywords = keywords;
	this.id = num;
	this.time = time2;
	this.description = descr;
	this.posx = posx;
	this.posy = posy;
}
public double cov(List<Pair<Label,Double>> userweights) {
	Pair<Label, Double> pair;
	double suma = 0;
	Label q;
	for (int i = 0; i < userweights.size(); i++){
		 pair = userweights.get(i);
		 q = pair.getFirstElement();
		 suma += cov(q)*pair.getSecondElement();
	}
	return suma;
}
public double cov(Label q) {
	Pair<Label, Double> pair;
	for (int i = 0; i < keywords.size(); i++){
		 pair = keywords.get(i);
		if (pair.getFirstElement() == q){
			return pair.getSecondElement();
		}
	}
	return 0;
}
public String cov_str(List<Pair<Label,Double>> userweights) {
	Pair<Label, Double> pair;
	double suma = 0;
	String str = "";
	Label q;
	for (int i = 0; i < userweights.size(); i++){
		 pair = userweights.get(i);
		 q = pair.getFirstElement();
		 suma += cov(q)*pair.getSecondElement();
		 if (i == 0)
			 str += cov(q) + "*" + pair.getSecondElement();
		 else
			 str += "+" + cov(q) + "*" + pair.getSecondElement();
	}
	return str;
}
public double cost(double[][] e, int n){
	if (cost == 0) return cost;
	double min1 = Double.POSITIVE_INFINITY, min2 = Double.POSITIVE_INFINITY;
	for (int k = 0; k< n; k++){
		if (e[k][this.id] > 0 && e[k][this.id]  < min1){
			min1 = e[k][this.id];
		}
	}
	for (int j = 0; j< n; j++){
		if (e[this.id][j] > 0 && e[this.id][j] < min2){
			min2 = e[this.id][j];
		}
	}
	return (min1+min2)/2;
}
public String toString(){
	String str ="";
	str += this.id;
	return str;
}
public void setcost(int i) {
	this.cost = i;
}
//Recorta 5 segundos
public void cuttime(double t) {
	this.time = this.time - t/60;
}
public String getdescr() {
	return this.description;
}
public String getformattedtime() {
	int min = (int) this.time;
	double secs = this.time - min;
	int intsecs = (int) (secs*60);
	return min + " minutos " + intsecs + " segundos";
}
public boolean labelInItem(Label q){
	for (int i = 0; i < this.keywords.size(); i++){
		if (keywords.get(i).getFirstElement() == q){
			return true;
		}
	}
	return false;
}

}
