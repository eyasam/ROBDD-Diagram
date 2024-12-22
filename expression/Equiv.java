package expression;

import java.util.HashSet;
import java.util.Set;

/**
 * Repr&eacute;sente une &eacute;quivalence logique entre deux expressions.
 * 
 */
public class Equiv extends Expression {

	private Expression e1, e2;

	public Equiv(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

    /**
     * Simplifie l'expression d'&eacute;quivalence.
     * 
     * @return une version simplifi&eacute;e de l'expression
     */
	public Expression simplifier() {
		e1 = e1.simplifier();
		e2 = e2.simplifier();
		if (e1.estVrai())
			return e2;
		if (e2.estVrai())
			return e1;
		if (e1.estFaux())
			return (new Non(e2)).simplifier();
		if (e2.estFaux())
			return (new Non(e1)).simplifier();
		return this;
	}

	/**
     * &Eacute;value l'&eacute;quivalence logique entre les deux expressions.
	 * 
	 * @return true si les deux expressions ont la meme valeur sinon false
	 * @throws RuntimeException si une des expressions contient encore des atomes
	 */
	public boolean evalue() throws RuntimeException {
		return (e1.evalue() == e2.evalue());
	}

	/**
	 * Retourne un ensemble contenant les atomes des deux sous expressions.
	 * 
	 * @return un ensemble d'atomes
	 */
	public Set<String> atomes() {
		HashSet<String> a = new HashSet<String>();
		a.addAll(e1.atomes());
		a.addAll(e2.atomes());
		return a;
	}

	/**
     * Remplace les atomes dans les deux sous expressions par une constante bool&eacute;enne.
	 * 
	 * @param s le nom de l'atome &agrave; remplacer
     * @param b la valeur bool&eacute;enne de remplacement
     * @return une nouvelle expression avec les atomes remplac&eacute;s
	 */
	public Expression remplace(String s, boolean b) {
		return new Equiv(e1.remplace(s, b), e2.remplace(s, b));
	}

	@Override
	public String toString() {
	    return '('+ e1.toString() + " <=> " + e2.toString() +')';
	}

}
