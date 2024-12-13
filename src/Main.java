package src;
import java.util.LinkedList;
import java.util.List;

import arbre.ArbreShannon;
import robdd.Noeud_ROBDD;
import robdd.ROBDD;
import expression.*;

public class Main {

	public static void main(String[] args) {
		//EXEMPLE 
		Expression exp = new Et(new Atome("x"),new Atome("y")); // représente (x ^ y)
		System.out.println(exp.atomes()); // affiche la liste des atomes (=variables booléennes) présents dans exp
		exp = exp.remplace("x",true); // exp vaut maintenant (true ^ y)
		//System.out.println(exp.evalue()); // <- erreur car (true ^ y) ne peut pas être évalué
		exp = exp.remplace("y",false); // exp vaut maintenant (true ^ false)
		System.out.println(exp.evalue());
		
		//Affichage de l'arbre associé à l'expression exp pour l'ordre x > y 
		List<String> ordre_atomes = new LinkedList<String>();
		ordre_atomes.add("x");
		ordre_atomes.add("y");
		System.out.println("\n Arbre de exp : \n" + exp.arbre(ordre_atomes)); // <- que se passe-t-il ? 
		Expression exp2 = new Et(new Atome("x"),new Atome("y")); // représente (x ^ y)
		System.out.println("\n Arbre de exp2 : \n" + exp2.arbre(ordre_atomes));		
		
		//exercice 2
        System.out.println("exercice 2 : "); 

        Expression exp3 = new Et(
            new Equiv(new Atome("x1"), new Atome("y1")), 
            new Equiv(new Atome("x2"), new Atome("y2"))
        );

        System.out.println("Atomes de exp3 : " + exp3.atomes());

     

        List<String> o1 = new LinkedList<>();
        o1.add("x1");
        o1.add("y1");
        o1.add("x2");
        o1.add("y2");

        ArbreShannon arbre = exp3.arbre(o1);

        System.out.println("Arbre de Shannon pour exp3 :");
        System.out.println(arbre);
    

        List<String> o2 = new LinkedList<>();
        o2.add("x1");
        o2.add("x2");
        o2.add("y1");
        o2.add("y2");

        ArbreShannon arbre2 = exp3.arbre(o2);

        System.out.println("Arbre de Shannon pour exp3 :");
        System.out.println(arbre2);
        
        //exercice 3 
        System.out.println("exercice 3"); 

        Expression exp4 = new Constante(true);
        System.out.println(exp4.estVrai());
        System.out.println(exp4.estFaux()); 


        exp4 = new Non(new Constante(false));
        System.out.println(exp4.estVrai()); 
        System.out.println(exp4.estFaux()); 

        //exercice4
        System.out.println("exercice 4"); 

        ROBDD robdd = new ROBDD();
        
        //Création de nœuds
        Noeud_ROBDD n1 = new Noeud_ROBDD("z", 0, 1);
        Noeud_ROBDD n2 = new Noeud_ROBDD("y", 1, 2);
        Noeud_ROBDD n3 = new Noeud_ROBDD("y", 0, 1);
        Noeud_ROBDD n4 = new Noeud_ROBDD("x", 3, 4);

        //ajout des nœuds au ROBDD
        robdd.ajouter(n1);
        robdd.ajouter(n2);
        robdd.ajouter(n3);
        robdd.ajouter(n4);

        //Recherche des indices avec obtenirROBDDIndex
        System.out.println(robdd.obtenirROBDDIndex("z", 0, 1)); 
        System.out.println(robdd.obtenirROBDDIndex("y", 1, 2)); 
        System.out.println(robdd.obtenirROBDDIndex("z", 0,1)); 
        System.out.println(robdd.obtenirROBDDIndex("x",3, 4)); 
        System.out.println(robdd.obtenirROBDDIndex("x", 2,1)); //aucun nœud ne correspond.

		// exercice 5
        System.out.println("exercice 5"); 

		ROBDD robdd1 = new ROBDD();
		exp3.construireROBDD(robdd1, o1);

		System.out.println(robdd1);



    
}
	
	
}
