// Stores the result of a ray-object intersection.
public class Intersection {
    public boolean  hit;
    public double   t;        // distance from ray origin to hit point
    public Vector3D point;    // P = O + tD
    public Vector3D normal;   // surface normal at hit point
    public double   u, v;     // barycentric coordinates
    public Material material; // material at hit point, used in Phong shading

    // Miss
    public Intersection(boolean hit, double t, Vector3D point, double u, double v) {
        this.hit   = hit;
        this.t     = t;
        this.point = point;
        this.u     = u;
        this.v     = v;
    }

    // Hit
    public Intersection(boolean hit, double t, Vector3D point, Vector3D normal,
                        double u, double v, Material material) {
        this.hit      = hit;
        this.t        = t;
        this.point    = point;
        this.normal   = normal;
        this.u        = u;
        this.v        = v;
        this.material = material;
    }
}