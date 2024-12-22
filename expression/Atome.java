package expression;

import java.util.HashSet;
import java.util.Set;

/**
 * Repr&eacute;sente un atome dans une expression logique.
 * 
 **/
public class Atome extends Expression {

	private String name;

	public Atome(String s) {
		this.name = new String(s);
	}

	public boolean evalue() throws RuntimeException {
		throw new RuntimeException("L'expression ne peut pas être évaluée car elle contient (au moins) l'atome "+name);
	}

	public Set<String> atomes() {
		Set<String> s = new HashSet<String>();
		s.add(name);
		return s;
	}
	

    /**
     * Remplace l'atome actuel par une constante si son nom correspond au parametre donne.
     * 
     * @param s le nom de l'atome a remplacer
     * @param b la valeur bool&eacute;enne de remplacement
     * @return une nouvelle expression (Constante ou Atome)
     */
	public Expression remplace(String s, boolean b) {
		
		if (name.equals(s)) {
            return new Constante(b);
		}
		return this;
		
	}

	public Expression simplifier(){
		
		return this;
	}

}
