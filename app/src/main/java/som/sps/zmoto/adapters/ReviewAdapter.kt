package som.sps.zmoto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.databinding.LayoutReviewItemBinding
import som.sps.zmoto.model.Reviews

class ReviewAdapter(private val reviews:List<Reviews>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    class ViewHolder(val binding:LayoutReviewItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.ViewHolder {
      return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_review_item,parent,false))
    }

    override fun getItemCount(): Int {
       return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewAdapter.ViewHolder, position: Int) {
    holder.binding.review=reviews.get(position)
        holder.binding.executePendingBindings()
    }


}