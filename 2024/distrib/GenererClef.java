

record Coord ( int x, int y){};


public class GenererClef
{
	// données extraites du fichier a[0] (.data)
	private int hauteur;
	private int largeur;


	private ArrayList<Coord> lstCoord;



	public GenererClef(String[] a)
	{
		this.chargerParametrage(a[0]);
		this.genererImageClef  (a[1]);
	}

	/** Cette méthode charge l'emsemble des attribits à partir des données contenus
	  * dans fichierXML
	  */
	public void chargerParametrage(String fichierXML)
	{
		/* Code à compléter */>



		// si vous n'arrivez pas à écrire cette méthode voici les lignes permettant
		// de mettre en dur les bonnes valeurs  dans  les  attributs,  après  avoir
		// instancié lstCoord.

		/*
		this.hauteur=110;
		this.largeur=100;

		this.lstCoord.add ( new Coord (8, 2) );
		this.lstCoord.add ( new Coord (7, 0) );
		this.lstCoord.add ( new Coord (7, 4) );
		this.lstCoord.add ( new Coord (7, 5) );
		this.lstCoord.add ( new Coord (3, 2) );
		*/


		// Vérification (laissez ces 4 lignes de code à la fin de cette méthode
		System.out.println( "hauteur   : " + this.hauteur );
		System.out.println( "largeur   : " + this.largeur );

		for ( Coord c: this.lstCoord )
			System.out.println ( c );

	}


	/** Cette méthode génère l' imageClef à partir des attributs préalablement chargés
	  */
	public void genererImageClef(String imageClef)
	{
		/* Code à Compléter */

	}




	public static void main(String[] a)
	{
		if ( a.length == 2 )
			new GenererClef ( a );
	}




}