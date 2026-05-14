import java.io.*;
import java.util.*;

// Reads a .obj file and adds all its triangles to a Scene.
// Each triangle gets a default Material (can be extended later).
public class ObjReader {

    // Default material for OBJ-loaded objects: grey, slightly shiny
    private static final Material DEFAULT_MATERIAL =
        new Material(new Vector3D(0.7, 0.7, 0.7), 32, 0.5);

    public static void load(String filename, Scene scene) throws IOException {
        List<Vector3D> vertices = new ArrayList<>();
        BufferedReader reader   = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("v ")) {
                String[] parts = line.split("\\s+");
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                vertices.add(new Vector3D(x, y, z));

            } else if (line.startsWith("f ")) {
                String[] parts = line.split("\\s+");
                int i0 = parseFaceIndex(parts[1]);
                int i1 = parseFaceIndex(parts[2]);
                int i2 = parseFaceIndex(parts[3]);

                scene.add(new Triangle(vertices.get(i0), vertices.get(i1),
                                       vertices.get(i2), DEFAULT_MATERIAL));

                if (parts.length == 5) {
                    int i3 = parseFaceIndex(parts[4]);
                    scene.add(new Triangle(vertices.get(i0), vertices.get(i2),
                                           vertices.get(i3), DEFAULT_MATERIAL));
                }
            }
        }

        reader.close();
        System.out.println("Loaded: " + filename + " -> " + scene.count() + " triangles");
    }

    // Parses "v", "v/vt", or "v/vt/vn" -> returns 0-based index
    private static int parseFaceIndex(String token) {
        return Integer.parseInt(token.split("/")[0]) - 1;
    }
}