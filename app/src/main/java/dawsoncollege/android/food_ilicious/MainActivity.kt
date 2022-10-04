package dawsoncollege.android.food_ilicious

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dawsoncollege.android.food_ilicious.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}