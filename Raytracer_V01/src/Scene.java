public class Scene {
    public Object3D[] objects;
    public int count = 0;

    public Scene(int maxObjects) {
        objects = new Object3D[maxObjects];
    }

    public void add(Object3D obj) { objects[count++] = obj; }

    public Intersection closestHit(Ray ray) {
        Intersection closest = new Intersection(false, Double.MAX_VALUE, null);
        for (int i = 0; i < count; i++) {
            Intersection hit = objects[i].intersect(ray);
            if (hit.hit && hit.t < closest.t) closest = hit;
        }
        return closest;
    }
}