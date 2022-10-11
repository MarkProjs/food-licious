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

    private fun randFood() {
        //getting the random values
        val food_names = resources.getStringArray(R.array.starting_food_names)
        val food_name = food_names.random()
        val food_txt = binding.foodTxt
        val food_img = binding.foodImg

        //setting the random value to be set in screen
        //setting the value text
        food_txt.text = food_name
        //setting the value image
        val uri = "@drawable/${food_name.lowercase()}"
        val imageResource = resources.getIdentifier(uri, null, packageName)
        val res = resources.
    }
}