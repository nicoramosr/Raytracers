// A ray has an origin O, direction D, and clipping planes (near & far)
// From the slides: P = O + tD
public class Ray {
    public Vector3D origin;     // O = ray origin
    public Vector3D direction;  // D = direction
    public double near;     // near clipping plane
    public double far;      // far clipping plane

    public Ray(Vector3D origin, Vector3D direction, double near, double far) {
        this.origin    = origin;
        this.direction = direction.normalize();
        this.near      = near;
        this.far       = far;
    }

    // P = O + tD  (point along the ray at distance t)
    public Vector3D at(double t) {
        return origin.add(direction.scale(t));
    }

    // Clipping check: t must be between near and far planes
    public boolean inRange(double t) {
        return t >= near && t <= far;
    }
}