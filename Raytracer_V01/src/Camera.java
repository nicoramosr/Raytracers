public class Camera {
    public Vector3D position;
    public int width, height;

    public Camera(Vector3D position, int width, int height) {
        this.position = position; this.width = width; this.height = height;
    }

    public Ray getRay(int i, int j) {
        double ndcX = (2.0 * i / width - 1.0) * ((double) width / height);
        double ndcY =  1.0 - 2.0 * j / height;
        return new Ray(position, new Vector3D(ndcX, ndcY, -1));
    }
}