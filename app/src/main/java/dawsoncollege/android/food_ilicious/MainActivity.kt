package dawsoncollege.android.food_ilicious

import android.annotation.SuppressLint
import android.app.Activity
import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import dawsoncollege.android.food_ilicious.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val LOG_TAG = "MAIN_ACTIVITY_DEV_LOG"
        private const val RN_CHOOSER_REQUEST_CODE = 0
        const val MAXIMUM_NUMBER_BUNDLE_KEY = "MAXIMUM_NUMBER_KEY"
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

        //change list button
        val changeListBtn = binding.editListButton
        changeListBtn.setOnClickListener {
            switchActivity()
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

    private fun switchActivity() {
        val intent = Intent(this, FoodListActivity::class.java)

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
                    Log.d(LOG_TAG, "Returning from RandomNumberChooserActivity successfully")
                    val maximumNumberStr: String? =
                        data?.extras?.getString(MAXIMUM_NUMBER_BUNDLE_KEY)

                    Toast.makeText(
                        this,
                        if (maximumNumberStr != null) "Maximum number was : $maximumNumberStr"
                        else "Could not get maximum number...",
                        Toast.LENGTH_LONG
                    ).show()
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