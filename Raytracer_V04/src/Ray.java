public class Ray {
    public Vector3D origin;    
    public Vector3D direction;  
    public double near;   
    public double far;    

    public Ray(Vector3D origin, Vector3D direction, double near, double far) {
        this.origin = origin;
        this.direction = direction.normalize();
        this.near = near;
        this.far = far;
    }

    public Vector3D at(double t) {
        return origin.add(direction.scale(t));
    }

    public boolean inRange(double t) {
        return t >= near && t <= far;
    }
}