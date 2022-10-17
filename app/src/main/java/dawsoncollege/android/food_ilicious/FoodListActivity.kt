package dawsoncollege.android.food_ilicious

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dawsoncollege.android.food_ilicious.databinding.ActivityFoodListBinding

class FoodListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodListBinding

    private lateinit var foodList: MutableList<String>
    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var newFoodName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variable to for the list needed
        val stringReceived = intent.getStringArrayExtra("foodList")
        if (stringReceived != null) {
            foodList = stringReceived.toMutableList()
        }
        adapter = RecyclerViewAdapter(foodList)
        binding.myRecyclerView.adapter = adapter
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)

        binding.addBtn.setOnClickListener {
            newFoodName = binding.newFoodEditTxt.text.toString()

            if (newFoodName.isNotBlank()) {
                foodList.add(newFoodName)
            }
            adapter.notifyDataSetChanged()
        }
        binding.saveBtn.setOnClickListener {
            setResult(Activity.RESULT_OK, intent.putExtra("foodList", foodList.toTypedArray()))
            Toast.makeText(this, "New list is saved", Toast.LENGTH_LONG).show()

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("newFoodName", newFoodName)
        super.onSaveInstanceState(outState)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val newFoodName = savedInstanceState.getString("newFoodName")
        if (newFoodName != null) {
            foodList.add(newFoodName)
        }
        adapter.notifyDataSetChanged()
    }
}