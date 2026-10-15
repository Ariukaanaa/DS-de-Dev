import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Texturer
{
    private BufferedImage imgSource;
    private BufferedImage texture;
    private BufferedImage imgDest;

    public Texturer(String sFic1, String sFic2)
    {
        try
        {
            this.imgSource = ImageIO.read(new File(sFic1));
            this.texture = ImageIO.read(new File(sFic2));
        }
        catch(IOException e)
        {
            System.out.println("Erreur de lecture : " + e.getMessage());
        }
    }

    public void substituer(String chemin, int couleur)
    {
        int clrSource = 0;
        int clrTexture = 0;

        this.imgDest = new BufferedImage(
            imgSource.getWidth(),
            imgSource.getHeight(),
            BufferedImage.TYPE_INT_RGB
        );

        for(int x = 0; x < imgSource.getWidth(); x++)
        {
            for(int y = 0; y < imgSource.getHeight(); y++)
            {
                clrSource = imgSource.getRGB(x, y);
                
                if ((clrSource & 0x00FFFFFF) == couleur)
                {
                    int xt = x % texture.getWidth();
                    int yt = y % texture.getHeight();

                    clrTexture = texture.getRGB(xt, yt);
                    imgDest.setRGB(x, y, clrTexture);
                }
                else
                {
                    imgDest.setRGB(x, y, clrSource);
                }
            }
        }

        try
        {
            ImageIO.write(imgDest, "png", new File(chemin));
        }
        catch(IOException e)
        {
            System.out.println("Erreur d'ecriture : " + e.getMessage());
        }
    }

    public static void main(String[] a)
    {
        String imgSource = a[0];
        String imgSortie = a[1];
        String texture = a[2];
        int couleur = Integer.parseInt(a[3]);

        Texturer texturer = new Texturer(imgSource, texture);
        texturer.substituer(imgSortie, couleur);
    }
}
