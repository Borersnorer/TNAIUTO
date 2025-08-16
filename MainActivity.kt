import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.map_container, MapFragment())
            .commit()

        val drawButton = findViewById<Button>(R.id.btn_draw_area)
        drawButton.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.map_container)
            if (fragment is MapFragment) {
                fragment.enableDrawingMode()
            }
        }
    }
}
