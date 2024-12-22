package src;

import java.util.LinkedList;
import java.util.List;

import arbre.ArbreShannon;
import robdd.Noeud_ROBDD;
import robdd.ROBDD;
import expression.*;


/**
 * Classe principale pour ex&eacute;cuter diff&eacute;rents exercices li&eacute;s aux expressions logiques,
 * a la construction de ROBDD et au probl&egrave;me des N reines.
 * 
 * @author Sammari Eya
 * @author Cherif Mey
 */

public class Main {

	/**
     * <b>Exercice 1 : </b>: manipulation et &eacute;valuation de deux expressions logiques.
     * 
     * <br>
     * Remarque : Pour l'expression e2 = (x &and; y), l'arbre de Shannon est construit
     * en analysant toutes les combinaisons possibles de valeurs des atomes {@code x} et
     * {@code y}. L'ordre des atomes utilis&eacute; pour l'arbre est {@code [x, y]}.
     * 
     * @param e1 la premi&egrave;re expression logique &agrave; manipuler et &eacute;valuer
     * @param e2 la deuxi&egrave;me expression logique &agrave; analyser
     */
	private static void exercice1(Expression e1, Expression e2) {

		System.out.println("***********************************");
		System.out.println("*          Exercice 1             *");
		System.out.println("***********************************");

		System.out.println("Expression initiale ( Expression 1 ): " + e1.toString());

		System.out.println("Atomes :" + e1.atomes()); // affiche la liste des atomes (=variables booléennes) présents
														// dans exp
		System.out.println("Remplacement de 'x' par true et 'y' par false ... ");

		e1 = e1.remplace("x", true); 
		// System.out.println(exp.evalue()); // <- erreur car (true ^ y) ne peut pas
		// être évalué
		e1 = e1.remplace("y", false); // exp vaut maintenant (true ^ false)

		System.out.println("Résultat final : " + e1.evalue());

		// Affichage de l'arbre associé a l'expression exp pour l'ordre x > y
		List<String> ordre_atomes = new LinkedList<String>();
		ordre_atomes.add("x");
		ordre_atomes.add("y");

		System.out.println("Arbre de l'expression 1 (ordre : x, y)");

		System.out.println(e1.arbre(ordre_atomes)); // <- que se passe-t-il ?

		System.out.println("Expression initiale ( Expression 2 ): " + e2.toString());

		System.out.println("Arbre de l'expression 2 (ordre : x, y) ");
		System.out.println(e2.arbre(ordre_atomes));

	}

    /**
	 * <b>Exercice 2 : </b>affichage de l'arbre de Shannon pour une
	 * expression logique.
	 * <br> 
	 * <br>
	 * <b> Interpr&eacute;tation des r&eacute;sultats :</b>
	 * <br>
	 * Expression initiale : ([x1] &lt;=&gt; [y1]) &and; ([x2] &lt;=&gt; [y2]) <br>
	 *  <br>
	 * - L'expression repr&eacute;sente une conjonction de deux &eacute;quivalences
	 * logiques :
	 * <ul>
	 * <li>[x1] &lt;=&gt; [y1] : x1 est &eacute;quivalent &agrave; y1.</li>
	 * <li>[x2] &lt;=&gt; [y2] : x2 est &eacute;quivalent &agrave; y2.</li>
	 * </ul>
	 * <br>
	  * <b>Arbre de Shannon (ordre 1 : [x1, y1, x2, y2])</b>
	  * <br>
	  * 
	 * - Explore les atomes dans l'ordre : x1, y1, x2, y2.<br>
	 * - Si x1 et y1 sont vrais, le r&eacute;sultat d&eacute;pend de x2 et y2.<br>
	 * - Sinon, le r&eacute;sultat est faux.
	 * 
	 * <br>
	 * <br>
	 * <b>Arbre de Shannon (ordre 2 : [x1, x2, y1, y2])</b> 
	 * <br>
	 * - Explore les atomes dans l'ordre : x1, x2, y1, y2.<br>
	 * - Si x1 et x2 sont vrais, le r&eacute;sultat d&eacute;pend de y1 et y2.<br>
	 * - Si x1 ou x2 est faux, le r&eacute;sultat est calcul&eacute; en fonction des autres valeurs.<br>
	 * - L'ordre d'&eacute;valuation influence la structure de l'arbre.
	 * 
	 * @param e  l'expression logique &agrave; analyser
	 * @param o1 le premier ordre des atomes pour l'arbre de Shannon
	 * @param o2 le deuxi&egrave;me ordre des atomes pour l'arbre de Shannon
	 */
	private static void exercice2(Expression e, List<String> o1, List<String> o2) {

		System.out.println("\n***********************************");
		System.out.println("*          Exercice 2             *");
		System.out.println("***********************************");

		System.out.println("Expression initiale ( Expression 3 ): " + e.toString());
		System.out.println("Atomes :" + e.atomes()); // atome de exp3

		System.out.println("Arbre de Shannon (ordre 1 : " + o1 + ")");
		ArbreShannon arbre = e.arbre(o1); // ordre1
		System.out.println(arbre);

		System.out.println("Arbre de Shannon (ordre 2 : " + o2 + ")");
		ArbreShannon arbre2 = e.arbre(o2); // ordre2
		System.out.println(arbre2);

	}
	
    /**
     *<b>Exercice 3 : </b> v&eacute;rification des expressions constantes.
     * 
     * Cette m&eacute;thode v&eacute;rifie si des expressions constantes sont vraies ou fausses.
     * 
     * @param e_true  une expression constante vraie
     * @param e_false une expression constante fausse
     */
	private static void exercice3(Expression e_true,Expression e_false) {

		System.out.println("\n***********************************");
		System.out.println("*          Exercice 3             *");
		System.out.println("***********************************");
		
		System.out.println("Expression initiale ( Expression 4 ): " + e_true.toString());
	    System.out.println("Expression 4 est vraie ? " + e_true.estVrai()); 
	    System.out.println("Expression 4 est fausse ? " + e_true.estFaux()); 

		System.out.println("Expression initiale ( Expression 5 ): " + e_false.toString());
	    System.out.println("Expression 5 est vraie ? " + e_false.estVrai()); 
	    System.out.println("Expression 5 est fausse ? " + e_false.estFaux()); 
	    
		// Cela l&egrave;vera une exception car x et y n'ont pas de valeur.
//		Expression exp9 = new Et(new Atome("x"), new Atome("y"));
//		System.out.println(exp9.estVrai()); 


	}
	
	
    /**
     * <b>Exercice 4 : </b> : Construction et manipulation d'un ROBDD.
     * - Cr&eacute;e un ROBDD vide et ajout des noeuds manuellement.
     * - Affiche le contenu et v&eacute;rifie l'existence de certains noeuds dans le ROBDD.
     */
	private static void exercice4() {
	    System.out.println("\n***********************************");
	    System.out.println("*          Exercice 4             *");
	    System.out.println("***********************************");
	    Noeud_ROBDD.resetCurrentId();

	    System.out.println("Création d'un ROBDD vide...");
	    ROBDD robdd = new ROBDD();
	    System.out.println("ROBDD initialisé avec succès.\n");


	    System.out.println("Ajout des noeuds au ROBDD :");
	    
	    Noeud_ROBDD z = new Noeud_ROBDD("z", ROBDD.idFalse, ROBDD.idTrue);
	    robdd.ajouter(z);
	    System.out.println(" - Nœud ajouté : z (fg: 0, fd: 1)");

	    Noeud_ROBDD y1 = new Noeud_ROBDD("y", ROBDD.idTrue, z.getId());
	    robdd.ajouter(y1);
	    System.out.println(" - Nœud ajouté : y (fg: 1, fd: " + z.getId() + ")");

	    Noeud_ROBDD y2 = new Noeud_ROBDD("y", ROBDD.idFalse, ROBDD.idTrue);
	    robdd.ajouter(y2);
	    System.out.println(" - Nœud ajouté : y (fg: 0, fd: 1)");

	    Noeud_ROBDD x = new Noeud_ROBDD("x", y1.getId(), y2.getId());
	    robdd.ajouter(x);
	    System.out.println(" - Nœud ajouté : x (fg: " + y1.getId() + ", fd: " + y2.getId() + ")\n");

	    System.out.println("Contenu actuel du ROBDD :");
	    System.out.println(robdd + "\n");

	    
	    System.out.println("Vérification des noeuds existants dans le ROBDD :");
	    System.out.println(" - z (fg: 0, fd: 1) -> Index : " + robdd.obtenirROBDDIndex("z", 0, 1));
	    System.out.println(" - y (fg: 1, fd: 2) -> Index : " + robdd.obtenirROBDDIndex("y", 1, 2));
	    System.out.println(" - y (fg: 0, fd: 1) -> Index : " + robdd.obtenirROBDDIndex("y", 0, 1));
	    System.out.println(" - x (fg: 3, fd: 4) -> Index : " + robdd.obtenirROBDDIndex("x", 3, 4));
	    System.out.println(" - x (fg: 2, fd: 1) -> Index : " + robdd.obtenirROBDDIndex("x", 2, 1) + " (Nœud inexistant)");
	}
	


    /**
     * <b>Exercice 5 : </b> Construction d'un ROBDD a partir d'une expression logique.
     * - G&eacute;n&egrave;re un ROBDD bas&eacute; sur une expression logique et un ordre donn&eacute; des atomes.
     * 
     * @param exp Expression logique.
     * @param o1 Ordre des atomes.
     * @param s Description de l'exercice.
     */
	private static void exercice5(Expression exp, List<String> o1, String s ) {
	    System.out.println("\n***********************************");
	    System.out.println("*          Exercice 5             *");
	    System.out.println("***********************************");
	    System.out.println(s);

	    Noeud_ROBDD.resetCurrentId();

	    ROBDD robdd = new ROBDD();
	    exp.construireROBDD(robdd, o1);
	    System.out.println("ROBDD généré pour l'expression "+exp.toString()+": ");

	    System.out.println(robdd);
	}

    /**
     * <b>Exercice 6 : </b>  Recherche d'une solution satisfaisante dans un ROBDD.
     * - Construit un ROBDD pour une expression donn&eacute;e.
     * - Recherche une affectation satisfaisante des variables si elle existe.
     * 
     * @param exp Expression logique.
     * @param o Ordre des atomes.
     * @param s Description de l'exercice.
     */
	private static void exercice6(Expression exp,List<String> o, String s) {
	    System.out.println("\n***********************************");
	    System.out.println("*          Exercice 6             *");
	    System.out.println("***********************************");
	    System.out.println(s);

	    Noeud_ROBDD.resetCurrentId();
	    ROBDD robdd = new ROBDD();
	    
	    exp.construireROBDD(robdd, o);

	    String solution = robdd.trouve_sat();
	    System.out.println("Solution satisfaisante : " + solution);
	}

	/**
	 * <b>Fonction nreine(N) : </b> 
	 * <br>
	 * 
	 * Cette fonction cr&eacute;e un ensemble d'expressions logiques qui mod&eacute;lisent les contraintes du probl&egrave;me :
	 * <ul>
	 * <li> Une seule reine par ligne.</li>
	 * <li> Une seule reine par colonne.</li>
	 * <li> Une seule reine par diagonale.</li>
	 * </ul>
	 * <br>
	 * Chaque reine est repr&eacute;sent&eacute;e par une position (i, j) dans une grille.
	 *
	 * @param N le nombre de reines 
	 * @return une expression logique combinant toutes les contraintes pour les N reines.
	 */
	private static Expression nreine(int N) {
		Expression contraintes = new Constante(true);
		for (int i = 0; i < N; i++) {
			Expression ReineParLigne = new Constante(false);
			for (int j = 0; j < N; j++) {
				ReineParLigne = new Ou(new Atome(i + " " + j), ReineParLigne);
				for (int k = 0; k<N ; k++) {
					if (k != j) {
						contraintes = new Et(new Implique(new Atome(i + " " + j), new Non(new Atome(i + " " + k))), contraintes);
					}
					if (k != i) {
						contraintes = new Et(new Implique(new Atome(i + " " + j), new Non(new Atome(k + " " + j))), contraintes);
					}
				}

				for (int k = 1; k<N ; k++) {
					if (i - k >= 0 && j - k >= 0) {
						contraintes = new Et(new Implique(new Atome(i + " " + j), new Non(new Atome((i - k) + " " + (j - k)))), contraintes);
					}
					if (i - k >= 0 && j + k < N) {
						contraintes = new Et(new Implique(new Atome(i + " " + j), new Non(new Atome((i - k) + " " + (j + k)))),contraintes);
					}
				}
			}
			contraintes = new Et(ReineParLigne, contraintes);
		}
		return contraintes;
	}

	/**
	 * <b>Affiche les reines sur l'&eacute;chiquier : </b> 
	 * 
	 * Affiche la solution du probl&egrave;me des N reines.
	 *
	 *	 
	 * @param s la solution.
	 * @param n la taille du plateau.
	 */
	private static void printQueen(String s, int n) {
		boolean[][] echiquier = new boolean[n][n];

		System.out.println(s);

		String[] valeurs = s.split("\\)");
		for (String valeur : valeurs) {
			valeur = valeur.replaceAll("[^0-9= ]", "").trim();
			if (valeur.contains("=")) {
				String[] parties = valeur.split("=");
				try {
					String[] coordonnees = parties[0].trim().split(" ");
					int x = Integer.parseInt(coordonnees[0].trim());
					int y = Integer.parseInt(coordonnees[1].trim());
					int v = Integer.parseInt(parties[1].trim());
					if (v == 1)
						echiquier[x][y] = true;
				} catch (Exception e) {
					System.out.println("Erreur : " + valeur);
				}
			}
		}
		System.out.println("Affichage de l'échiquier :");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(echiquier[i][j] ? "Q " : ". ");
			}
			System.out.println();
		}
	}
	
	/**
	 * <b>Application : le probl&egrave;me des N reines</b> (exercice 7,8,9)
	 *
	 * <p>Cette m&eacute;thode mod&eacute;lise et r&eacute;sout le probl&egrave;me des N reines
	 * en utilisant des contraintes logiques repr&eacute;sent&eacute;es sous forme bool&eacute;enne
	 * et un ROBDD pour simplifier et extraire les solutions.</p>
	 *
	 * <p>Chaque reine est mod&eacute;lis&eacute;e par une variable bool&eacute;enne <code>x_ij</code>, 
	 * o&ugrave; <code>i</code> repr&eacute;sente la ligne et <code>j</code> la colonne de 
	 * l'&eacute;chiquier. Voici les contraintes principales :</p>
	 *
	 * <ul>
	 *     <li><b>Une seule reine par ligne :</b> Chaque ligne doit contenir une seule reine.
	 *         <br>Exemple : <code>(x_i1 OR x_i2 OR ... OR x_iN)</code>.
	 *         <br>2 reines ne peuvent pas partager la m&ecirc;me ligne :
	 *         <code>(NOT x_ij OR NOT x_ik)</code> pour <code>j diff de k</code>.</li>
	 *     <li><b>Une seule reine par colonne :</b> Chaque colonne doit contenir une seule reine.
	 *         <br>Exemple : <code>(x_1j OR x_2j OR ... OR x_Nj)</code>.
	 *         <br>2 reines ne peuvent pas partager la m&ecirc;me colonne :
	 *         <code>(NOT x_ij OR NOT x_kj)</code> pour <code>i diff de k</code>.</li>
	 *     <li><b>Une seule reine par diagonale :</b> Les reines ne peuvent pas partager une m&ecirc;me
	 *         diagonale.
	 *         <br>Exemple pour les diagonales principales :
	 *         <code>(NOT x_ij OR NOT x_kl)</code> si  <code>|i - k| = |j - l|</code>.</li>
	 * </ul>
	 *
	 * <p>Une fois les contraintes d&eacute;finies, la m&eacute;thode utilise un ROBDD pour :</p>
	 * <ol>
	 *     <li>Simplifier la formule bool&eacute;enne mod&eacute;lisant le probl&egrave;me.</li>
	 *     <li>V&eacute;rifier l'existence d'une solution en recherchant une configuration satisfaisante.</li>
	 *     <li>Extraire une solution valide et afficher un &eacute;chiquier repr&eacute;sentant la position des reines.</li>
	 * </ol>
	 *
	 * @param N le nombre de reines.
	 */
	
	private static void exerciceJeuDeQueen(int N) {
		System.out.println("\n********************************************");
		System.out.println("*  Application : le problème des N reines  *");
		System.out.println("********************************************");
		Expression reineExp = nreine(N);
		ROBDD robddReine = reineExp.robdd();

		System.out.println("ROBDD généré pour N = " + N + ":");
		System.out.println(robddReine);

		String solution = robddReine.trouve_sat();
		System.out.println("Solution satisfaisante pour " + N + " reines :");
		printQueen(solution, N);
	}
	
	/**
	 * M&eacute;thode principale pour ex&eacute;cuter les exercices.
	 *
	 * @param args arguments du programme
	 */
	public static void main(String[] args) {

		// 1 Prise en main des expressions booléennes

		// exercice 1
		Expression exp = new Et(new Atome("x"), new Atome("y")); // représente (x ^ y)
		exercice1(exp, exp);

		// exercice 2
		Expression exp3 = new Et(new Equiv(new Atome("x1"), new Atome("y1")),
				new Equiv(new Atome("x2"), new Atome("y2")));

		List<String> ordre1 = new LinkedList<>();
		ordre1.add("x1");
		ordre1.add("y1");
		ordre1.add("x2");
		ordre1.add("y2");

		List<String> ordre2 = new LinkedList<>();
		ordre2.add("x1");
		ordre2.add("x2");
		ordre2.add("y1");
		ordre2.add("y2");

		exercice2(exp3, ordre1, ordre2);

		
		
		
		// 2 Construction du ROBDD

		// exercice 3
		Expression exp4 = new Constante(true);
		Expression exp5 = new Non(new Constante(false));
		exercice3(exp4, exp5);


		// exercice4
		exercice4();

		// exercice 5
		//construction basé sur lexercice 2 de ce TP
		
		exercice5(exp3,ordre1,"      (Exercice 2 du TP)\n");
		
		//construction basé sur lexercice 10 du TD

	    Expression exp6 = new Ou(new Equiv(new Atome("x"), new Atome("y")), new Et(new Atome("z"), new Atome("y")));

		List<String> ordre3 = new LinkedList<>();
		ordre3.add("x");
		ordre3.add("y");
		ordre3.add("z");
		
		exercice5(exp6,ordre3,"      (Exercice 10 du TD)\n");
		
		// exercice 6
		
		exercice6(exp3,ordre1, "Expression (de l'exercice 2) : (([x1] <=> [y1])∧([x2] <=> [y2])) ");
		
		Expression exp9 = new Et(new Atome("x"), new Atome("y")); 
		List<String> ordre9 = new LinkedList<>();
		ordre9.add("x");
		ordre9.add("y");
		exercice6(exp9,ordre9, "Expression : x ∧ y" );
		
		Expression exp10 = new Non(new Atome("y")); 
		List<String> ordre10 = new LinkedList<>();
		ordre10.add("y");
		exercice6(exp10,ordre10, "Expression : ¬ y");

		Expression exp11 = new Et(new Atome("x"), new Non(new Atome("x"))); 
		List<String> ordre11 = new LinkedList<>();
		ordre11.add("x");
		exercice6(exp11,ordre11, "Expression : x ∧ ¬x");

		
		
		
		
		//3 Application : le probléme des N reines
		int nb_queen = 4;
		exerciceJeuDeQueen(nb_queen);


	}
	
}
