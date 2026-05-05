import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RayTracer {

    static final int WIDTH  = 400;
    static final int HEIGHT = 300;
    static final double NEAR = 0.1;
    static final double FAR = 100.0;

   static final String OBJ_FILE = "C:\\Users\\nicor\\Downloads\\teapot.obj";

    public static void main(String[] args) throws IOException {

        Camera camera = new Camera(
            new Vector3D(0, 2, 6),
            60, WIDTH, HEIGHT,
            NEAR, FAR
        );

        Scene scene = new Scene();

        if (OBJ_FILE != null) {
            ObjReader.load(OBJ_FILE, scene);
        } else {
            scene.add(new Triangle(
                new Vector3D( 0.0,  0.8, -2.0), 
                new Vector3D(-0.8, -0.5, -2.0),  
                new Vector3D( 0.8, -0.5, -2.0),  
                new Vector3D(0.2, 0.6, 1.0)       
            ));
        }

        Light light = new Light(
            new Vector3D(1.0, 1.0, 1.0),   
            new Vector3D(1.0, 1.0, 1.0),   
            1.0                            
        );

        double ambient = 0.15;

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

                    Vector3D OC = hit.objectColor;

                    double NdotL = Math.max(0, hit.normal.dot(light.direction));

                    double diffR = light.color.x * OC.x * light.intensity * NdotL;
                    double diffG = light.color.y * OC.y * light.intensity * NdotL;
                    double diffB = light.color.z * OC.z * light.intensity * NdotL;

                    r = (int) Math.min(255, (ambient * OC.x + diffR) * 255);
                    g = (int) Math.min(255, (ambient * OC.y + diffG) * 255);
                    b = (int) Math.min(255, (ambient * OC.z + diffB) * 255);

                } else {
                    r = 15;
                    g = (int) (20 + v * 30);
                    b = (int) (30 + v * 40);
                }

                image.setRGB(i, HEIGHT - 1 - j, (r << 16) | (g << 8) | b);
            }
        }

        String outFile = OBJ_FILE != null
            ? OBJ_FILE.replace(".obj", ".png")
            : "triangle.png";

        System.out.println("Finished rendering");
        ImageIO.write(image, "png", new File(outFile));
    }
}