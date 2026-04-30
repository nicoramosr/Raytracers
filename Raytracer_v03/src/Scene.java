import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<Object3D> objects = new ArrayList<>();

    public int count() { return objects.size(); }

    public void add(Object3D obj) { objects.add(obj); }

    public Intersection closestHit(Ray ray) {
        Intersection closest = new Intersection(false, Double.MAX_VALUE, null, 0, 0);
        for (Object3D obj : objects) {
            Intersection hit = obj.intersect(ray);
            if (hit.hit && hit.t < closest.t) closest = hit;
        }
        return closest;
    }
}