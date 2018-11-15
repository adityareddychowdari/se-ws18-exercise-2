
public class Sphere extends Entity {
	float radius;
	Vec3D centerPoint;

	Sphere(Vec3D centerPoint, float radius) {
		this.centerPoint = centerPoint;
		this.radius = radius;
	}

	@Override
	Vec3D intersect(Vec3D ray) {
		if (ray == null || centerPoint == null) {
			return null;
		}
		float vX = 0;
		float vY = 0;
		float vZ = 0;
		Vec3D start = new Vec3D(vX, vY, vZ);
		float distance = ray.len();
		Vec3D vec = new Vec3D(ray.x / distance, ray.y / distance, ray.z / distance);

		float a = (float) ((ray.x - vX) * (ray.x - vX) + (ray.y - vY) * (ray.y - vY) + (ray.z - vZ) * (ray.z - vZ));
		float b = (float) (2 * ((ray.x - vX) * (vX - centerPoint.x) + (ray.y - vY) * (vY - centerPoint.y)
				+ (ray.z - vZ) * (vZ - centerPoint.z)));
		float c = (float) ((centerPoint.x * centerPoint.x) + (centerPoint.y * centerPoint.y)
				+ (centerPoint.z * centerPoint.z) + (vX * vX) + (vY * vY) + (vZ * vZ)
				+ 2 * (centerPoint.x * vX + centerPoint.y * vY + centerPoint.z * vZ) - (radius * radius));

		float delta = (float) ((b * b) - 4 * a * c);
		if (delta < 0) {
			return null;
		} else if (delta > 0) {
			float t1 = (float) ((-b + Math.sqrt(delta)) / (2 * a));
			float t2 = (float) ((-b - Math.sqrt(delta)) / (2 * a));

			Vec3D point1 = start.add(vec.mul(t1));
			Vec3D point2 = start.add(vec.mul(t2));

			if (point1.len() < point2.len()) {
				return point1;
			} else {
				return point2;
			}
		} else if (delta == 0) {
			if (a <= 0) {
				return null;
			}
			float t = -b / (2 * a);
			return start.add(vec.mul(t));
		} else {
			return null;
		}
	}
}
