
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
                    largeur += img.getWidth();

                    if (img.getHeight() > hauteur) {
                        hauteur = img.getHeight();
                    }
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

        int positionX = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(chemin))) {

            String ligne;

            while ((ligne = br.readLine()) != null) {
                BufferedImage img = ImageIO.read(new File(ligne));

                if (img != null) {
                    g2d.drawImage(img, positionX, 0, null);
                    positionX += img.getWidth();
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

    public static void main(String[] args) {
        String source = args[0];
        String sortie = args[1];

        Merge merge = new Merge();
        merge.lire(source, sortie);
    }
}
