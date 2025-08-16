@Dao
interface LocationDao {

    @Query("SELECT * FROM locations")
    fun getAll(): List<LocationEntity>

    // 🔍 Bounding box search — add this below getAll()
    @Query("SELECT * FROM locations WHERE latitude BETWEEN :minLat AND :maxLat AND longitude BETWEEN :minLon AND :maxLon")
    fun getLocationsInBounds(
        minLat: Double,
        maxLat: Double,
        minLon: Double,
        maxLon: Double
    ): List<LocationEntity>
}
