import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class Raytracer {
    public static void main(String[] args) throws Exception {
        int W = 800, H = 600;
        Camera camera = new Camera(new Vector3D(0, 0, 0), W, H);
        Scene scene = new Scene(10);
        scene.add(new Sphere(new Vector3D(-1,   0, -5), 0.7, Color.RED));
        scene.add(new Sphere(new Vector3D( 0.5, 0, -5), 0.4, Color.BLUE));

        BufferedImage img = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < H; j++) {
            for (int i = 0; i < W; i++) {
                Ray ray = camera.getRay(i, j);
                Intersection hit = scene.closestHit(ray);
                img.setRGB(i, j, hit.hit ? hit.object.color.getRGB() : Color.WHITE.getRGB());
            }
        }

        ImageIO.write(img, "png", new File("output.png"));
        System.out.println("Listo: output.png");
    }
}