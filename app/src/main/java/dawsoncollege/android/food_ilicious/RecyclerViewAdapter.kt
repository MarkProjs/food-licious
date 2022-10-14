package dawsoncollege.android.food_ilicious

import android.view.LayoutInflater
import android.view.ViewGroup
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
    }

    override fun getItemCount(): Int = foodList.size
}