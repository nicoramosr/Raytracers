import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// RayTracer v0.2
// - Clipping: near & far planes (frustum)
// - Triangle intersection: Möller-Trumbore with barycentric coordinates
// - Scene: holds all Object3D objects, finds the closest hit
// Output: triangle.png
public class RayTracer {

    static final int    WIDTH  = 400;
    static final int    HEIGHT = 300;
    static final double NEAR   = 0.1;
    static final double FAR    = 100.0;

    public static void main(String[] args) throws IOException {

        Camera camera = new Camera(
            new Vector3D(0, 0, 0),
            60, WIDTH, HEIGHT,
            NEAR, FAR
        );

        Scene scene = new Scene(10);
        scene.add(new Triangle(
            new Vector3D( 0.0,  0.8, -2.0),  
            new Vector3D(-0.8, -0.5, -2.0),  
            new Vector3D( 0.8, -0.5, -2.0)    
        ));

        Vector3D lightDir = new Vector3D(1, 1, 1).normalize();

        int hits = 0;

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int j = HEIGHT - 1; j >= 0; j--) {
            for (int i = 0; i < WIDTH; i++) {

                double u = (double) i / (WIDTH  - 1);
                double v = (double) j / (HEIGHT - 1);

                Ray ray = camera.getRay(u, v);

                Intersection hit = scene.closestHit(ray);

                int r, g, b;

                if (hit.hit) {
                    hits++;

                    double diff    = Math.max(0, hit.normal.dot(lightDir));
                    double w       = 1.0 - hit.u - hit.v;

                    double red   = w * 0.9 + hit.u * 0.1 + hit.v * 0.1;
                    double green = w * 0.1 + hit.u * 0.9 + hit.v * 0.1;
                    double blue  = w * 0.1 + hit.u * 0.1 + hit.v * 0.9;

                    double ambient = 0.2;
                    r = (int) Math.min(255, (ambient + (1 - ambient) * diff * red)   * 255);
                    g = (int) Math.min(255, (ambient + (1 - ambient) * diff * green) * 255);
                    b = (int) Math.min(255, (ambient + (1 - ambient) * diff * blue)  * 255);

                } else {
                    r = 15;
                    g = (int) (20 + v * 30);
                    b = (int) (30 + v * 40);
                }

                image.setRGB(i, HEIGHT - 1 - j, (r << 16) | (g << 8) | b);
            }
        }

        ImageIO.write(image, "png", new File("triangle.png"));
    }
}   