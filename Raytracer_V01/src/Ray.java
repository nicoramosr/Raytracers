public class Ray {
    public Vector3D origin, direction;

    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    public Vector3D at(double t) {
        return origin.add(direction.scale(t));
    }
}