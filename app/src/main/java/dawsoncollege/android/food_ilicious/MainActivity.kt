package dawsoncollege.android.food_ilicious

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import dawsoncollege.android.food_ilicious.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //function to do a random food in a list upon loading and the roll button
        randFood()
        reRollBtn()
    }


    private fun reRollBtn() {
        val reRollBtn = binding.rerollBtn
        reRollBtn.setOnClickListener{
            randFood()
        }
    }
    private fun randFood() {
        //getting the random values
        val foodArr = resources.getStringArray(R.array.starting_food_names)
        val foodName = foodArr.random()
        val foodTxt = binding.foodTxt
        val foodImg = binding.foodImg

        //setting the random value to be set in screen
        //setting the value text
        foodTxt.text = foodName
        //setting the value image
        val uri = "@drawable/${foodName.lowercase()}"
        val imageResource = resources.getIdentifier(uri, "drawable", packageName)
        foodImg.setImageResource(imageResource)
    }


}