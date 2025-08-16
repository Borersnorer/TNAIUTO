import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapFragment : Fragment() {
    private lateinit var mapView: MapView
    private var drawOverlay: DrawOverlay? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapView = view.findViewById(R.id.map_view)

        // Load offline map file here (to be added later)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setBuiltInZoomControls(true)
        mapView.setMultiTouchControls(true)

        return view
    }

    fun enableDrawingMode() {
        drawOverlay = DrawOverlay(requireContext()) { polygon ->
            val results = SearchUtils.searchWithinPolygon(polygon)
            // Highlight results on map
        }
        mapView.overlays.add(drawOverlay)
    }
}
