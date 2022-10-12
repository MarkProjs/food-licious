package dawsoncollege.android.food_ilicious

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import dawsoncollege.android.food_ilicious.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val LOG_TAG = "MAIN_ACTIVITY_DEV_LOG"
        private const val RN_CHOOSER_REQUEST_CODE = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //function to do a random food in a list upon loading and the roll button
        randFood()
        reRollBtn()

        //map nearby for foo text
        val foodImg = binding.foodImg
        foodImg.setOnClickListener {
            mapSearch()
        }

        //more info button
        val moreInfoBtn = binding.moreInfoBtn
        moreInfoBtn.setOnClickListener {
            webSearch()
        }
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
        var imageToPut = ""
        val uri = "@drawable/${foodName.lowercase()}"
        val imageResource = resources.getIdentifier(uri, "drawable", packageName)
        foodImg.setImageResource(imageResource)
        imageToPut = if (foodImg.drawable == null) {
            "@drawable/default_food"
        } else {
            "@drawable/${foodName.lowercase()}"
        }
        val imgResource = resources.getIdentifier(imageToPut, "drawable", packageName)
        foodImg.setImageResource(imgResource)
    }

    private fun mapSearch() {
        val foodTxt = binding.foodTxt.text
        val intentUri = Uri.parse("geo: 0, 0?q=${foodTxt}")
        val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
    private fun webSearch() {
        val foodTxt = binding.foodTxt.text
        val searchPrefix = "https://www.google.com/search?q="
        val queryUrl: Uri = Uri.parse("${searchPrefix}${foodTxt}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        startActivity(intent)
    }
}