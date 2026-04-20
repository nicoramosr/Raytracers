import java.awt.Color;

public class Sphere extends Object3D {
    public Vector3D center;
    public double radius;

    public Sphere(Vector3D center, double radius, Color color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Intersection intersect(Ray ray) {
        Vector3D L = center.subtract(ray.origin);
        double tca = L.dot(ray.direction);
        if (tca < 0) return new Intersection(false, -1, null);

        double d2 = L.dot(L) - tca * tca;
        if (d2 > radius * radius) return new Intersection(false, -1, null);

        double thc = Math.sqrt(radius * radius - d2);
        double t = tca - thc;
        if (t < 0) t = tca + thc;
        if (t < 0) return new Intersection(false, -1, null);

        return new Intersection(true, t, this);
    }
}