public class Intersection {
    public boolean hit;
    public double t;
    public Object3D object;

    public Intersection(boolean hit, double t, Object3D object) {
        this.hit = hit; this.t = t; this.object = object;
    }
}