Short Summary of All Files
settings.gradle Declares project modules (e.g., :app).

build.gradle (Project-level) Configures Gradle wrapper, plugin repositories, and global settings.

build.gradle (Module: app) Sets Android SDK versions, Kotlin options, ViewBinding, and dependencies (OSMDroid, AndroidX, etc.).

proguard-rules.pro Holds ProGuard/R8 rules for release builds.

AndroidManifest.xml Registers permissions (READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE), OSMDroid metadata, and the launcher activity.

res/layout/activity_main.xml Defines the UI container with a FrameLayout for the map fragment and action buttons.

res/layout/fragment_map.xml Contains the full-screen org.osmdroid.views.MapView.

src/main/java/.../MainActivity.kt Hosts the UI, initializes navigation or fragment transactions, and handles top-level interactions (e.g., “Draw” button).

src/main/java/.../ui/MapFragment.kt Inflates fragment_map.xml, loads OSMDroid configuration, centers on Lisbon, and prepares for offline tile overlays.

src/main/java/.../ui/DrawOverlay.kt Implements custom drawing logic (polygons/lines) on the MapView and captures user-drawn shapes.

src/main/java/.../data/LocationEntity.kt Defines the Room entity representing a geographic point (latitude, longitude, metadata).

src/main/java/.../data/LocationDao.kt Provides DAO methods to insert and query LocationEntity objects.

src/main/java/.../data/LocationDatabase.kt Configures the Room database, ties together entities and DAOs.

src/main/java/.../utils/SearchUtils.kt Contains functions to filter and search database entries within user-drawn map regions.

README.md Documents how to obtain/generate the portugal.mbtiles file, where to place it on the device, and usage steps.

External Resource (not in repo) portugal.mbtiles – the offline tile archive stored at /storage/emulated/0/OfflineMaps/.

Detailed Breakdown
1. Project Configuration
settings.gradle Lists included modules so Gradle knows which subprojects to build.

build.gradle (Project-level) Defines buildscript repositories, Gradle plugin versions, and global dependency settings.

build.gradle (Module: app) Sets up the Android application:

SDK versions (compileSdk 34, minSdk 21, targetSdk 34)

Kotlin JVM target

ViewBinding

Dependencies for OSMDroid (offline maps), AndroidX, Material, and lifecycle.

proguard-rules.pro Custom rules to keep or obfuscate classes when building a release APK.

2. App Manifest & Permissions
AndroidManifest.xml

Declares MainActivity as the launcher.

Requests storage permissions for reading external MBTiles.

Provides OSMDroid cache path metadata (osmdroid.basePath, osmdroid.cachePath).

3. Layout Files
activity_main.xml Hosts a FrameLayout placeholder for swapping in MapFragment and a control button (e.g., “Draw Area”).

fragment_map.xml A simple FrameLayout containing an org.osmdroid.views.MapView that fills the screen.

4. UI and Map Logic
MainActivity.kt

Entry point of the app.

Sets up the action bar, handles button clicks to start drawing mode, and commits MapFragment.

MapFragment.kt

Loads OSMDroid configuration (Configuration.getInstance().load(...)).

Enables zoom controls and multi-touch.

Centers the map on Lisbon coordinates at an initial zoom.

Placeholder where offline tile provider (MBTileArchive + TilesOverlay) will be added.

DrawOverlay.kt

Extends an OSMDroid overlay class.

Captures user touch events to draw polygons or lines.

Exposes the drawn geometry for later querying.

5. Data Persistence
LocationEntity.kt

Defines fields for latitude, longitude, and optional metadata (name, timestamp).

LocationDao.kt

Methods to insert new LocationEntity records and query points by geographic bounds or within polygons.

LocationDatabase.kt

Singleton Room database class.

Provides an instance of LocationDao and ties together entities.

6. Search and Utility Logic
SearchUtils.kt

Contains algorithms to test whether a LocationEntity falls inside a user-drawn shape.

Returns filtered lists of points for display or further processing.

7. Documentation & External Data
README.md

Step-by-step on obtaining .mbtiles from OpenMapTiles or generating via TileMill/MapTiler.

Instructions for copying portugal.mbtiles to /storage/emulated/0/OfflineMaps/.

Notes on granting runtime permissions and launching the map.

portugal.mbtiles (External)

The MBTiles archive containing all pre-rendered map tiles for offline use.

With this structure, every responsibility—from app configuration through map rendering, drawing overlays, data storage, and search—is clearly separated into its own file.# TNAIUTO


              MAPAPPMAPAPPMAPAPPMAPAPPMAP


Consolidated Project Map
Below is a single reference listing every file in your offline‐map app, its location within the project, its primary role, and how it interacts with other components. Keep this handy to ensure nothing slips through the cracks.

1. Gradle & Project Configuration
settings.gradle • Location: /settings.gradle • Role: Enumerates project modules (:app).

build.gradle (Project-level) • Location: /build.gradle • Role: Declares plugin repositories and Gradle wrapper versions.

build.gradle (Module: app) • Location: /app/build.gradle • Role:

Specifies Android SDK (minSdk, targetSdk, compileSdk)

Enables Kotlin and ViewBinding

Adds dependencies (OSMDroid, AndroidX, Material, lifecycle)

proguard-rules.pro • Location: /app/proguard-rules.pro • Role: Contains R8/ProGuard rules for release builds.

2. Android Manifest & Permissions
AndroidManifest.xml • Location: /app/src/main/AndroidManifest.xml • Role:

Requests READ_EXTERNAL_STORAGE & WRITE_EXTERNAL_STORAGE

Defines OSMDroid cache metadata (osmdroid.basePath, osmdroid.cachePath)

Declares MainActivity as launcher

3. Layout Resources
activity_main.xml • Location: /app/src/main/res/layout/activity_main.xml • Role:

Hosts a FrameLayout (@+id/container) for swapping in fragments

Contains UI controls (e.g., “Draw” button)

fragment_map.xml • Location: /app/src/main/res/layout/fragment_map.xml • Role: Full-screen org.osmdroid.views.MapView used by MapFragment.

4. Application Code
4.1 Entry Point & Navigation
MainActivity.kt • Location: /app/src/main/java/com/yourapp/offlinemap/MainActivity.kt • Role:

Launches the app and inflates activity_main.xml

Hosts MapFragment via FragmentManager or Navigation Component

Handles UI events (e.g., toggling draw mode)

4.2 Map Display
MapFragment.kt • Location: /app/src/main/java/com/yourapp/offlinemap/ui/MapFragment.kt • Role:

Inflates fragment_map.xml using ViewBinding

Loads OSMDroid configuration (Configuration.getInstance().load(...))

Centers the map on Lisbon and sets zoom/multi-touch

Will attach TilesOverlay backed by MBTileArchive

4.3 Drawing Overlays
DrawOverlay.kt • Location: /app/src/main/java/com/yourapp/offlinemap/ui/DrawOverlay.kt • Role:

Extends Overlay to capture touch events

Allows users to draw polygons or lines on the map

Emits drawable geometry for downstream querying

5. Data Layer (Room Database)
LocationEntity.kt • Location: /app/src/main/java/com/yourapp/offlinemap/data/LocationEntity.kt • Role: Room @Entity representing a point (latitude, longitude, optional metadata).

LocationDao.kt • Location: /app/src/main/java/com/yourapp/offlinemap/data/LocationDao.kt • Role: @Dao interface; methods to insert points and query by bounding box or polygon.

LocationDatabase.kt • Location: /app/src/main/java/com/yourapp/offlinemap/data/LocationDatabase.kt • Role: Abstract RoomDatabase; provides a singleton instance and access to LocationDao.

6. Search & Utilities
SearchUtils.kt • Location: /app/src/main/java/com/yourapp/offlinemap/utils/SearchUtils.kt • Role:

Contains algorithms to test if a LocationEntity lies within a user-drawn shape

Returns filtered lists of matching points

7. Documentation & External Data
README.md • Location: /app/README.md (or project root) • Role:

Instructions to download/generate portugal.mbtiles

Specifies device path: /storage/emulated/0/OfflineMaps/portugal.mbtiles

Steps to grant runtime storage permissions and run the map

portugal.mbtiles (external, not checked into GitHub) • Device Location: /storage/emulated/0/OfflineMaps/portugal.mbtiles • Role: Contains pre-rendered map tiles for offline display via OSMDroid.

8. Interaction Flow
App Launch MainActivity inflates activity_main.xml.

Map Initialization MainActivity commits MapFragment into the container.

OSMDroid Setup MapFragment loads preferences and config, then centers map.

Offline Tiles MapFragment will attach MBTileArchive → MapTileProviderBasic → TilesOverlay.

Draw Mode User taps “Draw” → MainActivity enables DrawOverlay on the MapView.

Data Query Once drawing completes, the overlay passes geometry to SearchUtils.

Database Lookup SearchUtils queries LocationDao for points inside the drawn area.

Results Display Filtered LocationEntity objects can be rendered as markers or listed in UI.
