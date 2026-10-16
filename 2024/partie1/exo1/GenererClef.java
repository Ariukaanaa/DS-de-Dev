import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.imageio.ImageIO;

record Coord ( int x, int y){};


public class GenererClef
{
	// données extraites du fichier a[0] (.data)
	private int hauteur;
	private int largeur;


	private ArrayList<Coord> lstCoord;



	public GenererClef(String[] a) throws Exception
	{
		this.chargerParametrage(a[0]);
		this.genererImageClef  (a[1]);
	}

	/** Cette méthode charge l'emsemble des attribits à partir des données contenus
	  * dans fichierXML
	  */
	public void chargerParametrage(String fichierXML) throws Exception
	{
		/* Code à compléter */

		this.lstCoord = new ArrayList<>()  ; 

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ; 
		DocumentBuilder builder = factory.newDocumentBuilder() ; 
		Document document = builder.parse(new File(fichierXML)) ; 

		NodeList parametres = document.getElementsByTagName("parametre");
		Element parametre = (Element) parametres.item(0);

		this.hauteur = Integer.parseInt(
			parametre.getElementsByTagName("hauteur").item(0).getTextContent()
		);

		this.largeur = Integer.parseInt(
			parametre.getElementsByTagName("largeur").item(0).getTextContent()
		);

		NodeList cases = document.getElementsByTagName("case") ; 

		for( int i = 0 ; i < cases.getLength(); i++ )
		{
			Element element = (Element)cases.item(i) ; 

			int x = Integer.parseInt( element.getAttribute("x")); 
			int y = Integer.parseInt( element.getAttribute("y")); 

			this.lstCoord.add(new Coord( x, y)); 
		}


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
		
		BufferedImage img = new BufferedImage(
			lstCoord.size() + 1,
        	1,
        	BufferedImage.TYPE_INT_RGB
		);

		

		// Premier  pixel 
		img.setRGB(0 , 0 , new Color( hauteur , largeur , 83).getRGB()) ;

		for( int i = 0 ; i < lstCoord.size() ; i++ )
		{
			Random random = new Random();

			int bleu = random.nextInt(256);
			Coord c = lstCoord.get(i); 

			img.setRGB( i+1 , 0 , new Color( c.x() , c.y() , bleu).getRGB() ); 
		}

		try
		{
			ImageIO.write( img , "png" ,  new File(imageClef)); 
		}
		catch(IOException e){}


	}
 



	public static void main(String[] a) throws Exception
	{
		if ( a.length == 2 )
			new GenererClef ( a );
	}




}