
@Database(entities = [LocationEntity::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao
}
// Add this to initialize the DB
companion object {
    fun getInstance(context: Context): LocationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LocationDatabase::class.java,
            "location_db"
        ).build()
    }
}
