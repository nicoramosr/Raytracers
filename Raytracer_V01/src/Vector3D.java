public class Vector3D {
    public double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    public Vector3D add(Vector3D v){
        return new Vector3D(x+v.x, y+v.y, z+v.z);
    }
    public Vector3D subtract(Vector3D v){ 
        return new Vector3D(x-v.x, y-v.y, z-v.z); 
    }

    public Vector3D scale(double t){ 
        return new Vector3D(x*t, y*t, z*t); 
    }

    public double dot(Vector3D v){ 
        return x*v.x + y*v.y + z*v.z; 
    }

    public Vector3D normalize(){ 
        double m = Math.sqrt(dot(this)); 
        return scale(1/m); 
    }
    
}