public class Intersection {
    public boolean  hit;
    public double   t;       
    public Vector3D point;   
    public Vector3D normal;  
    public double   u, v;   

    public Intersection(boolean hit, double t, Vector3D point, double u, double v) {
        this.hit    = hit;
        this.t      = t;
        this.point  = point;
        this.normal = null;
        this.u      = u;
        this.v      = v;
    }

    public Intersection(boolean hit, double t, Vector3D point, Vector3D normal, double u, double v) {
        this.hit    = hit;
        this.t      = t;
        this.point  = point;
        this.normal = normal;
        this.u      = u;
        this.v      = v;
    }
}