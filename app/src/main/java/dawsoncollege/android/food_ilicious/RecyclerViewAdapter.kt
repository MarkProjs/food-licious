package dawsoncollege.android.food_ilicious

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dawsoncollege.android.food_ilicious.databinding.FoodListViewBinding

class RecyclerViewAdapter(private val foodList: MutableList<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodListViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val food = foodList[position]

        binding.foodNameTxt.text = food
        binding.removeBtn.setOnClickListener {
            if (foodList.size == 1) {
                Toast.makeText(holder.binding.removeBtn.context,
                    "Error! Last food in the list", Toast.LENGTH_LONG).show()
            }
            else {
                foodList.removeAt(position)
                notifyDataSetChanged()
            }

        }
    }
    override fun getItemCount(): Int = foodList.size
}