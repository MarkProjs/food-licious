package dawsoncollege.android.food_ilicious

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dawsoncollege.android.food_ilicious.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val LOG_TAG = "MAIN_ACTIVITY_DEV_LOG"
        private const val RN_CHOOSER_REQUEST_CODE = 0
    }
    private lateinit var reRollBtn: Button
    private lateinit var moreInfoBtn: Button
    private lateinit var changeListBtn: Button
    private lateinit var foodImg: ImageView
    private lateinit var foodTxt: TextView
    private lateinit var foodList: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fields needed for the function
        reRollBtn = binding.rerollBtn
        moreInfoBtn = binding.moreInfoBtn
        changeListBtn = binding.editListButton
        foodImg = binding.foodImg
        foodTxt = binding.foodTxt
        foodList = resources.getStringArray(R.array.starting_food_names)

        //function to do a random food in a list upon loading and the roll button
        randFood(foodImg, foodTxt, foodList)

        //roll button
        reRollBtn.setOnClickListener{
            randFood(foodImg, foodTxt, foodList)
        }

        //map nearby for foo text
        foodImg.setOnClickListener {
            mapSearch(foodTxt)
        }

        //more info button
        moreInfoBtn.setOnClickListener {
            webSearch(foodTxt)
        }

        //change list button
        changeListBtn.setOnClickListener {
            switchActivity(foodList)
        }
    }

    private fun randFood(foodImg: ImageView, foodTxt: TextView, foodList: Array<String>) {
        //getting the random values
        val foodName = foodList.random()

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

    private fun mapSearch(foodTxt: TextView) {
        try {
            val intentUri = Uri.parse("geo: 0, 0?q=${foodTxt.text}")
            val mapIntent = Intent(Intent.ACTION_VIEW, intentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }catch (exc: ActivityNotFoundException) {
            Log.e(LOG_TAG,"Could not open google maps", exc)
        }

    }
    private fun webSearch(foodTxt: TextView) {
        try {
            val searchPrefix = "https://www.google.com/search?q="
            val queryUrl: Uri = Uri.parse("${searchPrefix}${foodTxt.text}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }catch (exc: ActivityNotFoundException) {
            Log.e(LOG_TAG, "Could not open the website", exc)
        }

    }

    private fun switchActivity(foodList: Array<String>) {
        val intent = Intent(this, FoodListActivity::class.java)
        intent.putExtra("foodList", foodList)

        try {
            // firing the explicit intent
            startActivityForResult(intent, RN_CHOOSER_REQUEST_CODE)

        } catch (exc: ActivityNotFoundException) {
            Log.e(LOG_TAG, "Could not open RandomNumberChooserActivity", exc)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RN_CHOOSER_REQUEST_CODE -> {
                    Log.d(LOG_TAG, "Returning from FoodListActivity successfully")
                    if (data != null) {
                        foodList = data.getStringArrayExtra("foodList") as Array<String>
                    }
                }
                else -> {
                    Log.w(LOG_TAG, "Returning from an unknown activity")
                }
            }
        } else {
            Log.w(LOG_TAG, "Activity result was not 'Activity.RESULT_OK' (-1), was '$resultCode'")
        }
    }

}