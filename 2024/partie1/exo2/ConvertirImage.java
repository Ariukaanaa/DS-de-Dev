import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D; // For drawing
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

record Coord(int x, int y) {}

public class ConvertirImage {
    private int hauteur;
    private int largeur;
    private List<Coord> lstCoord;
    private BufferedImage imgClef;
    private BufferedImage imgSource;
    private BufferedImage[][] list;

    public ConvertirImage(String[] a) {
       

        try {
            imgClef = ImageIO.read(new File(a[2]));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        this.lstCoord = new ArrayList<>();

        
        Color pixel = new Color(imgClef.getRGB(0, 0));
        this.hauteur = pixel.getRed();   
        this.largeur = pixel.getGreen(); 

        for (int i = 1; i < imgClef.getWidth(); i++) {
            pixel = new Color(imgClef.getRGB(i, 0));
            int x = pixel.getRed();
            int y = pixel.getGreen();
            this.lstCoord.add(new Coord(x, y));
        }

        System.out.println("hauteur : " + this.hauteur);
        System.out.println("largeur : " + this.largeur);
        for (Coord c : this.lstCoord) {
            System.out.println(c);
        }

        try {
            this.imgSource = ImageIO.read(new File(a[0]));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int largeurOrigine = imgSource.getWidth();
        int hauteurOrigine = imgSource.getHeight();

        int nbColonnes = largeurOrigine / largeur; 
        int nbLignes   = hauteurOrigine / hauteur;   


        list = new BufferedImage[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                int x = j * largeur;
                int y = i * hauteur;
                list[i][j] = imgSource.getSubimage(x, y, largeur, hauteur);
            }
        }

       
        BufferedImage temp = list[lstCoord.get(0).y()][lstCoord.get(0).x()];

        for (int i = 0; i < lstCoord.size() - 1 ; i++) {

            Coord c1 = lstCoord.get(i);
            Coord c2 = lstCoord.get(i + 1);

            list[c1.y()][c1.x()] = list[c2.y()][c2.x()];
        }

        Coord last = lstCoord.get(lstCoord.size() - 1);
        list[last.y()][last.x()] = temp;

        

        BufferedImage imgDest = new BufferedImage(largeurOrigine, hauteurOrigine, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imgDest.createGraphics();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                g2d.drawImage(list[i][j], j * largeur, i * hauteur, null);
            }
        }
        g2d.dispose();

        
        try {
            ImageIO.write(imgDest, "png", new File(a[1]));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a) {
        if (a.length == 3) {
            new ConvertirImage(a);
        } 
    }
}