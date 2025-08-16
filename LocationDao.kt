@Dao
interface LocationDao {
    @Query("SELECT * FROM locations")
    fun getAll(): List<LocationEntity>
}
