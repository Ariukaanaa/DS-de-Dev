import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class PlacerVille
{
    private BufferedImage img ; 

    public PlacerVille( String sFic )
    {   try
        {
            this.img = ImageIO.read( new File( sFic )) ; 
        }
        catch( IOException e  ){}
        
    }

    public void placer( String cheminXml ) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder builder = factory.newDocumentBuilder() ; 
        Document document = builder.parse(new File(cheminXml)) ; 


        NodeList villes = document.getElementsByTagName("ville") ; 
        Graphics2D g2 = img.createGraphics() ; 

        g2.setFont(new Font( Font.SANS_SERIF, Font.BOLD , 20 )); 
        g2.setColor( Color.BLACK ); 


        for( int i = 0 ; i < villes.getLength() ; i++ )
        {
            Element ville = (Element)villes.item(i); 

            int x = Integer.parseInt(ville.getAttribute("x")) ; 
            int y = Integer.parseInt(ville.getAttribute("y")) ; 

            String nom = ville.getTextContent().trim(); 

            g2.setColor(Color.WHITE);
            g2.fillOval(x - 10, y - 10, 20, 20);

            g2.setColor(Color.BLACK);
            g2.drawOval(x - 10, y - 10, 20, 20);
            g2.drawString(nom , x+15 , y+5) ; 
        }

        g2.dispose(); 

    }

    public void sauvergarder( String cheminSortie ) throws IOException
    {
        ImageIO.write(img , "png" , new File(cheminSortie) ); 
    }
    public static void main( String[] a ) throws Exception
    {
        String source = a[0] ; 
        String cheminXml = a[1]; 

        PlacerVille ville = new PlacerVille( source ); 
        ville.placer(cheminXml); 
        ville.sauvergarder("./sortie/mappe22.png"); 
    }
}