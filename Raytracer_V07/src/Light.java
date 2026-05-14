public class Light {

    public enum Type { DIRECTIONAL, POINT }

    public Type type;
    public Vector3D position;  //point light
    public Vector3D direction; //directional light
    public Vector3D color;     
    public double intensity; 

    public static Light directional(Vector3D direction, Vector3D color, double intensity) {
        Light l = new Light();
        l.type = Type.DIRECTIONAL;
        l.direction = direction.normalize();
        l.color = color;
        l.intensity = intensity;
        return l;
    }

    public static Light point(Vector3D position, Vector3D color, double intensity) {
        Light l = new Light();
        l.type = Type.POINT;
        l.position = position;
        l.color = color;
        l.intensity = intensity;
        return l;
    }

    public Vector3D directionFrom(Vector3D point) {
        if (type == Type.DIRECTIONAL) {
            return direction;
        } else {
            return position.sub(point).normalize();
        }
    }

    public double intensityAt(Vector3D point) {
        if (type == Type.DIRECTIONAL) {
            return intensity;
        } else {
            double dist = position.sub(point).length();
            return intensity / (dist * dist);
        }
    }

    private Light() {}
}