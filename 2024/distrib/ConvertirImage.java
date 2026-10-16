

record Coord ( int x, int y){};



public class ConvertirImage
{
	// données extraites du fichier a[2] (clef*.png)
	private int hauteur;
	private int largeur;

	private List<Coord> lstCoord;

	public ConvertirImage(String[] a)
	{



		/*-----------------------------------------------------*/
		/* affectation des attributs à partir des informations */
		/* contenues dans l'image clef a[2]                    */
		/*-----------------------------------------------------*/

		/* Code à Compléter */



		// si vous n'arrivez pas à récupérer les information du fichier clef
		// pour charger les attributs voici les instructions à lancer
		// après  avoir instancié lstCoord.

		/*
		this.hauteur=110;
		this.largeur=100;

		this.lstCoord.add ( new Coord (8, 2) );
		this.lstCoord.add ( new Coord (7, 0) );
		this.lstCoord.add ( new Coord (7, 4) );
		this.lstCoord.add ( new Coord (7, 5) );
		this.lstCoord.add ( new Coord (3, 2) );
		*/




		/*-----------------------------------------------------*/
		/* transformtation de l'image d'origine a[0] vers      */
		/* image transformée a[1]                              */
		/* en utilisant les informations issues de la clef     */
		/*-----------------------------------------------------*/

		/* code à compléter */


	}


	public static void main(String[] a)
	{
		if ( a.length == 3 )
			new ConvertirImage ( a );
	}

}