package robdd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe repr&eacute;sentant un ROBDD 
 * sous forme d'une liste de noeuds {@link Noeud_ROBDD}.
 */
public class ROBDD {

	//Constantes pour les numéros des "feuilles" VRAI et FAUX
	public static final int idFalse = 0;
	public static final int idTrue = 1;

    /**
     * Liste repr&eacute;sentant le ROBDD.
     */
	private List<Noeud_ROBDD> R;
	
    /**
     * Construit un ROBDD vide.
     */
	public ROBDD(){
		R = new LinkedList<Noeud_ROBDD>();
	}
	
    /**
     * Ajoute un noeud au ROBDD courant
     *
     * @param n le noeud &agrave; ajouter au ROBDD
     */
	public void ajouter(Noeud_ROBDD n) {
		R.add(n);
	}

		
	
	 /**
     * Renvoie le nombre de noeuds du ROBDD
     *
     * @return le nombre total de noeuds dans le ROBDD
     */
	public int nb_noeuds() {
		return R.size()+2; // longueur de la liste R + les 2 noeuds correspondants à VRAI et FAUX
	}

	@Override
	public String toString() {
		return R.toString();
	}
	
    /**
     * Recherche un noeud avec un nom et des fils gauche et droit donn&eacute;s dans le ROBDD.
     * Si le noeud existe, retourne son identifiant, sinon retourne -1.
     *
     * @param nom le nom de la variable associ&eacute;e au noeud
     * @param fg  l'identifiant du fils gauche
     * @param fd  l'identifiant du fils droit
     * @return l'identifiant du noeud correspondant ou -1 si le noeud n'existe pas
     */
	public int obtenirROBDDIndex(String nom, int fg, int fd) {
		for (Noeud_ROBDD n : R) {
	        if (n.getNom().equals(nom)  && n.getIdFilsDroit() == fd && n.getIdFilsGauche() == fg) {
	            return n.getId();
	        }		
	    }
		return -1;
	}
	
    /**
     * Trouve une solution satisfaisante pour le ROBDD courant en explorant ses noeuds.
     *
     * <p>La m&eacute;thode parcourt les noeuds du ROBDD depuis la feuille VRAI 
     * et construit une assignation des variables bool&eacute;ennes 
     * en remontant l'arbre.</p>
     *
     * @return une chaîne d&eacute;crivant une solution ou un message indiquant qu'aucune solution n'existe
     */
	public String trouve_sat() {
	    StringBuilder solution = new StringBuilder();
	    int current = 1; 

	    while (true) {
	        boolean parent_existe=false;
	        for (Noeud_ROBDD node : R) {
	            if (node.getIdFilsGauche() == current) {
	                solution.insert(0,"("+ node.getNom() + " = 0) ");
	                current = node.getId();
	                parent_existe = true;
	                break;
	            } else if (node.getIdFilsDroit() == current) {
	                solution.insert(0, "("+ node.getNom() + " = 1) ");
	                current = node.getId();
	                parent_existe = true;
	                break;
	            }
	        }
	        if (!parent_existe) break; 
	    }
	    
	    return (solution.length() > 0) 
	    		? solution.toString() 
	    		: "Aucune affectation satisfaisante n'existe pour cette expression.";
	}



}