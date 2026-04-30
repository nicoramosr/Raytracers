import java.io.*;
import java.util.*;

public class ObjReader {

    public static void load(String filename, Scene scene) throws IOException {
        List<Vector3D> vertices = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("v ")) {
                String[] parts = line.split("\\s+");
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double z = Double.parseDouble(parts[3]);
                vertices.add(new Vector3D(x, y, z));
            }

            else if (line.startsWith("f ")) {
                String[] parts = line.split("\\s+");

                int i0 = parseFaceIndex(parts[1]);
                int i1 = parseFaceIndex(parts[2]);
                int i2 = parseFaceIndex(parts[3]);

                Vector3D v0 = vertices.get(i0);
                Vector3D v1 = vertices.get(i1);
                Vector3D v2 = vertices.get(i2);

                scene.add(new Triangle(v0, v1, v2));

                if (parts.length == 5) {
                    int i3 = parseFaceIndex(parts[4]);
                    Vector3D v3 = vertices.get(i3);
                    scene.add(new Triangle(v0, v2, v3));
                }
            }
        }

        reader.close();
    }

    private static int parseFaceIndex(String token) {
        String indexStr = token.split("/")[0];
        return Integer.parseInt(indexStr) - 1; 
    }
}