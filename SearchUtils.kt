package your.package.name.utils // TODO: Replace with your actual package name

import android.content.Context
import org.osmdroid.util.GeoPoint
import your.package.name.database.LocationDatabase // TODO: Update with your actual path
import your.package.name.database.LocationEntity   // TODO: Update with your actual path
import kotlin.math.abs

object SearchUtils {

    /**
     * Checks if a given GeoPoint is inside a polygon using the ray casting algorithm.
     */
    private fun isPointInsidePolygon(point: GeoPoint, polygon: List<GeoPoint>): Boolean {
        var intersectCount = 0
        for (i in polygon.indices) {
            val j = (i + 1) % polygon.size
            val lat1 = polygon[i].latitude
            val lon1 = polygon[i].longitude
            val lat2 = polygon[j].latitude
            val lon2 = polygon[j].longitude
            val lat = point.latitude
            val lon = point.longitude

            if ((lon > minOf(lon1, lon2)) &&
                (lon <= maxOf(lon1, lon2)) &&
                (lat <= maxOf(lat1, lat2)) &&
                (lon1 != lon2)) {

                val xinters = (lon - lon1) * (lat2 - lat1) / (lon2 - lon1) + lat1
                if (lat <= xinters) intersectCount++
            }
        }
        return intersectCount % 2 != 0
    }

    /**
     * Searches the Room database for locations inside the given polygon.
     * First filters by bounding box, then applies point-in-polygon logic.
     */
    fun searchWithinPolygon(context: Context, polygon: List<GeoPoint>): List<LocationEntity> {
        if (polygon.isEmpty()) return emptyList()

        // Step 1: Calculate bounding box
        val minLat = polygon.minOf { it.latitude }
        val maxLat = polygon.maxOf { it.latitude }
        val minLon = polygon.minOf { it.longitude }
        val maxLon = polygon.maxOf { it.longitude }

        // Step 2: Query Room DB for candidates within bounding box
        val db = LocationDatabase.getInstance(context)
        val candidates = db.locationDao().getLocationsInBounds(minLat, maxLat, minLon, maxLon)

        // Step 3: Filter candidates using polygon logic
        return candidates.filter { location ->
            val point = GeoPoint(location.latitude, location.longitude)
            isPointInsidePolygon(point, polygon)
        }
    }
}
