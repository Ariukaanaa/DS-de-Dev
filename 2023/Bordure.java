import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bordure {
    
    private final int COULEUR_BORDURE =  10020843 ;
    private final int COULEUR_RIVIERE = 255 ; 
    private BufferedImage imgSource ; 
    private BufferedImage imgSortie ; 
    int hauteur ; 
    int largeur ; 

    public Bordure( String imgSource)
    {   
        try
        {
            this.imgSource = ImageIO.read( new File( imgSource )) ; 
        }
        catch( IOException e ) { }

        hauteur = this.imgSource.getHeight() ; 
        largeur = this.imgSource.getWidth() ;
    }

    public void transformer( String sortie )
    {
        

        int couleur = 0 ; 

        this.imgSortie = new BufferedImage( largeur , hauteur , BufferedImage.TYPE_INT_RGB) ; 

        for( int x = 0 ; x < largeur ; x++ )
        {
            for( int y = 0 ; y < hauteur ; y++ )
            {
                couleur = imgSource.getRGB(x ,y) ; 

                if( ( couleur & 0x00FFFFFF) == COULEUR_RIVIERE )
                {
                     if( estBordure( x , y , couleur))
                    {
                        imgSortie.setRGB(x, y , COULEUR_BORDURE); 
                    }
                    else
                    {
                        imgSortie.setRGB(x, y , COULEUR_RIVIERE);
                    }
                }
                else
                {
                    imgSortie.setRGB(x , y , couleur );
                }

            }
        
        }

        try
        {
            ImageIO.write(imgSortie , "png" , new File(sortie)) ; 
        }
        catch( IOException e ){}

        
    }

    private boolean estBordure(int x, int y, int couleur)
    {
        for (int dx = -1; dx <= 1; dx++)
        {
            for (int dy = -1; dy <= 1; dy++)
            {
                if (dx == 0 && dy == 0)
                {
                    continue;
                }

                int voisinX = x + dx;
                int voisinY = y + dy;

                if (voisinX < 0 || voisinX >= largeur ||
                    voisinY < 0 || voisinY >= hauteur)
                {
                    return true;
                }

                int couleurVoisin =
                    imgSource.getRGB(voisinX, voisinY) & 0x00FFFFFF;

                if (couleurVoisin != COULEUR_RIVIERE)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main( String[] args)
    {
        String imgSource = args[0] ; 
        String imgSortie = args[1] ; 

        Bordure bordure = new Bordure(imgSource) ; 
        bordure.transformer(imgSortie) ; 
    }
}
