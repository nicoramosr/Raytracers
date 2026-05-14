// Camera generates one Ray per pixel.
// The frustum is defined by near & far clipping planes (from the slides).
public class Camera {
    private Vector3D origin;
    private Vector3D lowerLeftCorner;
    private Vector3D horizontal;
    private Vector3D vertical;
    private double   near;
    private double   far;

    public Camera(Vector3D origin, double fov, int width, int height,
                  double near, double far) {
        this.origin = origin;
        this.near   = near;
        this.far    = far;

        double aspectRatio = (double) width / height;
        double halfHeight  = Math.tan(Math.toRadians(fov / 2.0));
        double halfWidth   = aspectRatio * halfHeight;

        // Camera looks toward -Z
        lowerLeftCorner = new Vector3D(-halfWidth, -halfHeight, -1.0);
        horizontal      = new Vector3D(2 * halfWidth, 0, 0);
        vertical        = new Vector3D(0, 2 * halfHeight, 0);
    }

    // Generate a ray through normalized pixel (u, v) in [0,1]
    public Ray getRay(double u, double v) {
        Vector3D direction = lowerLeftCorner
            .add(horizontal.scale(u))
            .add(vertical.scale(v));
        return new Ray(origin, direction, near, far);
    }
}