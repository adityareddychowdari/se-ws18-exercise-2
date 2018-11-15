public class Plane extends Entity {

	Vec3D planePoint, normalVector;

	Plane(Vec3D planePoint, Vec3D normalVector) {
		this.normalVector = normalVector;
		this.planePoint = planePoint;
	}

	@Override
	Vec3D intersect(Vec3D ray) {
		if (ray == null || planePoint == null || normalVector == null) {
			return null;
		}
		Vec3D l = new Vec3D(ray.x / ray.len(), ray.y / ray.len(), ray.z / ray.len());
		float top = planePoint.sub(ray).mul(normalVector);
		float bottom = l.mul(normalVector);
		if (bottom == 0) {
			return null;
		} else {
			return l.mul(top / bottom).add(ray);
		}
	}
}