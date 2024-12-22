package robdd;

/**
 * Repr&eacute;sente un n&oelig;ud dans un ROBDD
 */
public class Noeud_ROBDD {
	//index du noeud
	private final int id;
	//étiquette du noeud
	private String nom;
	//index du fils gauche, index du fils droit
	private int fg, fd;

	private static int currentid = 2;
	
    /**
     * R&eacute;initialise le compteur des IDs des noeuds &agrave; sa valeur initiale (2).
     */
	public static void resetCurrentId() {
	    currentid = 2; 
	}

	//constructeur
	public Noeud_ROBDD(String name, int fg, int fd) {
		this.id = currentid++;
		this.nom = new String(name);
		this.fg = fg;
		this.fd = fd;
	}

	/* accesseurs */
	
	public int getIdFilsGauche() {
		return fg;
	}

	public int getIdFilsDroit() {
		return fd;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return new String(nom);
	}

	
	@Override
	public String toString() {
		return "" + id + ":bdd(" + nom + "," + fg + "," + fd + ")";
	}	
}
