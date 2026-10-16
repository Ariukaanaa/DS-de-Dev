
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Merge {

    private BufferedImage imgMerge;
    private int largeur;
    private int hauteur;

    public Merge() {
        largeur = 0;
        hauteur = 0;
    }

    public void lire(String chemin, String sortie) {

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {

            String ligne;
            while ((ligne = br.readLine()) != null) {
                BufferedImage img = ImageIO.read(new File(ligne));

                if (img != null) {
                    largeur = img.getWidth() ;
                    hauteur = img.getHeight();    
                }   
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        imgMerge = new BufferedImage(
            largeur, hauteur, BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g2d = imgMerge.createGraphics();


        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {

            String ligne;

            while ((ligne = br.readLine()) != null) {
                BufferedImage img = ImageIO.read(new File(ligne));

                if (img != null) {
                    merge(img);
                   
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        g2d.dispose();

        try {
            ImageIO.write(imgMerge, "png", new File(sortie));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage merge(BufferedImage img)
    {
        if (imgMerge == null)
        {
            largeur = img.getWidth();
            hauteur = img.getHeight();

            imgMerge = new BufferedImage(
                largeur, hauteur, BufferedImage.TYPE_INT_RGB
            );
        }

        for (int x = 0; x < largeur; x++)
        {
            for (int y = 0; y < hauteur; y++)
            {
                int couleur = img.getRGB(x, y) & 0x00FFFFFF;
                if (couleur != 0xFFFFFF)
                {
                    imgMerge.setRGB(x, y, couleur);
                }
            }
        }

        return imgMerge;
    }

    public static void main(String[] args) {
        String source = args[0];
        String sortie = args[1];

        Merge merge = new Merge();
        merge.lire(source, sortie);
    }
}
