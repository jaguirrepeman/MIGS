package graph;


/**
 * Clase que implementa un objeto par de valores.
 * @author jesus
 *
 * @param <K> Clase del primer elemento del par
 * @param <V> Clase del segundo elemento del par
 */
public class Pair<K, V> {

    private final K element0;
    private final V element1;

    public static <K, V> Pair<K, V> createPair(K element0, V element1) {
        return new Pair<K, V>(element0, element1);
    }

    public Pair(K element0, V element1) {
        this.element0 = element0;
        this.element1 = element1;
    }

    public K getFirstElement() {
        return element0;
    }

    public V getSecondElement() {
        return element1;
    }

    public String toString(){
		return this.element0 + ", " + this.element1;
    	
    }
}