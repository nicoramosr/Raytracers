public class Light {
    public Vector3D direction; 
    public Vector3D color;     
    public double intensity; 

    public Light(Vector3D direction, Vector3D color, double intensity) {
        this.direction = direction.normalize();
        this.color = color;
        this.intensity = intensity;
    }
}