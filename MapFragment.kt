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
