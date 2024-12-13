package expression;

import arbre.*;
import robdd.*;

import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;



public abstract class Expression {

	//renvoie la liste (non ordonnées) des atomes associées à l'objet courant
	public abstract Set<String> atomes();

	//renvoie la valeur (booléenne) de l'objet courant.
	//Si celui-ci ne peut pas être évalué (présence d'atomes), on lève une exception.
	public abstract boolean evalue() throws RuntimeException;

	//remplace l'atome s de l'objet courant par le booléen b
	//renvoie l'expression correspondante
	public abstract Expression remplace(String s, boolean b);

	//renvoie une version simplifiée de l'expression this
	//selon les règles du tableau du TD 5, partie 2.3
	public abstract Expression simplifier();

    // Renvoie vrai si l'expression est une constante vraie
	public boolean estVrai(){
	    return this instanceof Constante && this.evalue();
	}
	
    // Renvoie vrai si l'expression est une constante fausse
	public boolean estFaux(){
	    return this instanceof Constante && !this.evalue();
	}

	//construit l'arbre de shannon correspondant à l'expression courante en prenant comme ordre l'ordre indiqué par l'argument ordre_atomes
	public ArbreShannon arbre(List<String> ordre_atomes) {
		if (this.atomes().isEmpty()) {
			return new FeuilleShannon(evalue());
		} else {
			assert !ordre_atomes.isEmpty() : "Toutes les variables n'apparaissaient pas dans ordre_atomes !";
			List<String> ordre_atomes2 = new LinkedList<String>(ordre_atomes); //copie pour que arbre(ordre) ne modifie pas ordre
			String name = ordre_atomes2.remove(0);
			Expression e1 = remplace(name, false);
			Expression e2 = remplace(name, true);
			return new NoeudShannon(name, e1.arbre(ordre_atomes2), e2.arbre(ordre_atomes2));
		}
	}

	//renvoie le ROBDD correspondant à l'expression courante en prenant comme ordre l'ordre indiqué par l'argument ordre_atomes 
	public ROBDD robdd(List<String> atomes_ordonnes) {
		// On commence par vérifier que la liste atomes_ordonnes 
		// contient bien tous les atomes de l'expression courante.
		if (!this.atomes().equals(new HashSet<String>(atomes_ordonnes))){
			System.err.println("robdd: atomes_ordonnes ne contient pas tous les atomes de l'expression, ou en contient en trop.");
			System.exit(0);
		}
		// On crée un ROBDD vide
		ROBDD R = new ROBDD();
		// On construit le ROBDD
		this.construireROBDD(R,atomes_ordonnes);
		return R;
	}
	
	//fonction récursive qui renvoie le noeud ROBDD associé à l'expression courante (this) et construit le ROBDD G
	//La liste atomes_ordonnes contient la liste des atomes présents dans l'expression courante et indique leur ordre (pour pouvoir faire l'équivalent de la fonction max_variable du TD) 
	public int construireROBDD(ROBDD G, List<String> atomes_ordonnes) {
	    Expression F = this.simplifier();
	    if (F.estFaux()) {
	        return ROBDD.idFalse;
	    }

	    if (F.estVrai()) {
	        return ROBDD.idTrue;
	    }
	    if (atomes_ordonnes.isEmpty()) {
	    	throw new RuntimeException("La liste des atomes est vide impo de construire le ROBDD");
	    }
	    
	    String pivot = atomes_ordonnes.get(0);

	    List<String> sousAtomes = new LinkedList<>(atomes_ordonnes);
	    sousAtomes.remove(0);

	    Expression F0 = this.remplace(pivot, false);
	    Expression F1 = this.remplace(pivot, true);

	    int idFg = F0.construireROBDD(G, sousAtomes); 
	    int idFd = F1.construireROBDD(G, sousAtomes); 

	    if (idFg == idFd) {
	        return idFg; 
	    }

	    int existingId = G.obtenirROBDDIndex(pivot, idFg, idFd);
	    if (existingId != -1) {
	        return existingId; 
	    }

	    Noeud_ROBDD nouveauNoeud = new Noeud_ROBDD(pivot, idFg, idFd);
	    G.ajouter(nouveauNoeud);

	    return nouveauNoeud.getId();
	}

	//renvoie le ROBDD correspondant à l'expression courante avec un ordre aléatoire (donné par this.atomes())
	public ROBDD robdd() {
		return robdd(new LinkedList<String>(this.atomes()));
	}

	
}
