public class Triangle implements Object3D {
    public Vector3D v0, v1, v2;
    public Vector3D color; 

    private static final double EPSILON = 1e-7;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2, Vector3D color) {
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;
        this.color = color;
    }

    @Override
    public Intersection intersect(Ray ray) {
        Vector3D v1v0 = v1.sub(v0);
        Vector3D v2v0 = v2.sub(v0);

        Vector3D P   = ray.direction.cross(v2v0);
        double det = v1v0.dot(P);

        if (Math.abs(det) < EPSILON) return miss();

        double invDet = 1.0 / det;

        Vector3D T = ray.origin.sub(v0);
        double u = invDet * T.dot(P);
        if (u < 0 || u > 1) return miss();

        Vector3D Q = T.cross(v1v0);
        double v = invDet * ray.direction.dot(Q);
        if (v < 0 || (u + v) > (1.0 + EPSILON)) return miss();

        double t = invDet * Q.dot(v2v0);
        if (!ray.inRange(t)) return miss();

        Vector3D point = ray.at(t);

        Vector3D V = v1.sub(v0);
        Vector3D W = v0.sub(v2);
        Vector3D normal = V.cross(W).normalize();

        return new Intersection(true, t, point, normal, u, v, color);
    }

    private Intersection miss() {
        return new Intersection(false, Double.MAX_VALUE, null, 0, 0);
    }
}