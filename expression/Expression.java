package expression;

import arbre.*;
import robdd.*;

import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


/**
 * Classe abstraite repr&eacute;sentant une expression logique.
 * 
 */
public abstract class Expression {

	// renvoie la liste (non ordonn&eacutees) des atomes associ&eacutees à l'objet courant
	public abstract Set<String> atomes();

	// renvoie la valeur (bool&eacuteenne) de l'objet courant.
	// Si celui-ci ne peut pas être &eacutevalu&eacute (pr&eacutesence d'atomes), on lève une
	// exception.
	public abstract boolean evalue() throws RuntimeException;

	// remplace l'atome s de l'objet courant par le bool&eacuteen b
	// renvoie l'expression correspondante
	public abstract Expression remplace(String s, boolean b);

	// renvoie une version simplifi&eacutee de l'expression this
	// selon les règles du tableau du TD 5, partie 2.3
	public abstract Expression simplifier();

	/**
	 * V&eacute;rifie si l'expression est une constante fausse.
	 *
	 * <p>Cette m&eacute;thode simplifie l'expression courante et v&eacute;rifie si elle peut &ecirc;tre
	 * r&eacute;duite &agrave; une constante &eacute;valu&eacute;e &agrave; <code>false</code>.
	 * Si l'expression contient encore des atomes non assign&eacute;s, une exception est lev&eacute;e.</p>
	 *
	 * @return <code>true</code> si l'expression est une constante fausse apr&egrave;s simplification.
	 * @throws RuntimeException si des atomes restent non assign&eacute;s dans l'expression.
	 */
	public boolean estVrai() {
	    Expression F = this.simplifier();
	    if (!(F instanceof Constante)) {
	        throw new RuntimeException("Impossible d'&eacutevaluer : des atomes restent non assign&eacutes.");
	    }
	    return F.evalue();
	}

	/**
	 * V&eacute;rifie si l'expression est une constante fausse.
	 *
	 * <p>Cette m&eacute;thode simplifie l'expression courante et v&eacute;rifie si elle peut &ecirc;tre
	 * r&eacute;duite &agrave; une constante &eacute;valu&eacute;e &agrave; <code>false</code>.
	 * Si l'expression contient encore des atomes non assign&eacute;s, une exception est lev&eacute;e.</p>
	 *
	 * @return <code>true</code> si l'expression est une constante fausse apr&egrave;s simplification.
	 * @throws RuntimeException si des atomes restent non assign&eacute;s dans l'expression.
	 */
	public boolean estFaux() {
	    Expression F = this.simplifier();
	    if (!(F instanceof Constante)) {
	        throw new RuntimeException("Impossible d'&eacutevaluer : des atomes restent non assign&eacutes.");
	    }
	    return !F.evalue();
	}

	// construit l'arbre de shannon correspondant à l'expression courante en prenant
	// comme ordre l'ordre indiqu&eacute par l'argument ordre_atomes
	public ArbreShannon arbre(List<String> ordre_atomes) {
		if (this.atomes().isEmpty()) {
			return new FeuilleShannon(evalue());
		} else {
			assert !ordre_atomes.isEmpty() : "Toutes les variables n'apparaissaient pas dans ordre_atomes !";
			List<String> ordre_atomes2 = new LinkedList<String>(ordre_atomes); // copie pour que arbre(ordre) ne modifie
																				// pas ordre
			String name = ordre_atomes2.remove(0);
			Expression e1 = remplace(name, false);
			Expression e2 = remplace(name, true);
			return new NoeudShannon(name, e1.arbre(ordre_atomes2), e2.arbre(ordre_atomes2));
		}
	}

	// renvoie le ROBDD correspondant à l'expression courante en prenant comme ordre
	// l'ordre indiqu&eacute par l'argument ordre_atomes
	public ROBDD robdd(List<String> atomes_ordonnes) {
		// On commence par v&eacuterifier que la liste atomes_ordonnes
		// contient bien tous les atomes de l'expression courante.
		if (!this.atomes().equals(new HashSet<String>(atomes_ordonnes))) {
			System.err.println(
					"robdd: atomes_ordonnes ne contient pas tous les atomes de l'expression, ou en contient en trop.");
			System.exit(0);
		}

		ROBDD R = new ROBDD();
		// On construit le ROBDD
		this.construireROBDD(R, atomes_ordonnes);
		return R;
	}

    /**
     * Construit r&eacute;cursivement un n&oelig;ud ROBDD associ&eacute; &agrave;
     * l'expression courante tout en g&eacute;n&eacute;rant le ROBDD G.
     * La liste atomes_ordonnes d&eacute;finit l'ordre des atomes &agrave; traiter.
     *
     * <b>Description g&eacute;n&eacute;rale :</b>
     * <ul>
     * <li>Divise l'expression en sous expressions en fixant les valeurs des atomes (true ou false).</li>
     * <li>Traite les atomes dans l'ordre donn&eacute; par <code>atomes_ordonnes</code>.</li>
     * <li>Optimise en v&eacute;rifiant si un n&oelig;ud &eacute;quivalent existe d&eacute;j&agrave; dans le ROBDD avant de cr&eacute;er un nouveau.</li>
     * </ul>
     *
     * <b>Fonctionnement :</b>
     * <ol>
     * <li>V&eacute;rifie si l'expression est une constante (<code>true</code> ou <code>false</code>) et retourne l'identifiant correspondant.</li>
     * <li>Construit r&eacute;cursivement les sous-branches : gauche (atome = <code>false</code>) et droite (atome = <code>true</code>).</li>
     * <li>Retourne un identifiant existant si un n&oelig;ud &eacute;quivalent est trouv&eacute;, ou ajoute un nouveau n&oelig;ud au ROBDD.</li>
     * </ol>
     *
     * @param G               le ROBDD en cours de construction
     * @param atomes_ordonnes la liste ordonn&eacute;e des atomes &agrave; traiter
     * @return L'identifiant du n&oelig;ud associ&eacute; &agrave; l'expression courante
     */
	public int construireROBDD(ROBDD G, List<String> atomes_ordonnes) {

	    if (this.atomes().isEmpty()) {
	        if (this.estFaux()) {
	            return ROBDD.idFalse;
	        }
	        if (this.estVrai()) {
	            return ROBDD.idTrue;
	        }
	    }

		String atome = atomes_ordonnes.get(0);
		List<String> atomes_restants = new LinkedList<>(atomes_ordonnes);
		atomes_restants.remove(0);

		int fg = this.remplace(atome, false).construireROBDD(G, atomes_restants);
		int fd = this.remplace(atome, true).construireROBDD(G, atomes_restants);

		if (fg == fd) {
			return fg;
		}

		if ((G.obtenirROBDDIndex(atome, fg, fd)) != -1) {
			return G.obtenirROBDDIndex(atome, fg, fd);
		}

		Noeud_ROBDD n = new Noeud_ROBDD(atome, fg, fd);
		G.ajouter(n);

		return n.getId();
	}

	// renvoie le ROBDD correspondant à l'expression courante avec un ordre
	// al&eacuteatoire (donn&eacute par this.atomes())
	public ROBDD robdd() {
		return robdd(new LinkedList<String>(this.atomes()));
	}

	@Override
	public String toString() {
		return atomes().toString();
	}

}
