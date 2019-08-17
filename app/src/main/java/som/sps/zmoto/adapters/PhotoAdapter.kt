package som.sps.zmoto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import som.sps.zmoto.R
import som.sps.zmoto.databinding.ImageItemBinding
import som.sps.zmoto.model.Photos

class PhotoAdapter(private val photos:List<Photos>) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {
    class ViewHolder(val binding:ImageItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.ViewHolder {
      return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.image_item,parent,false))
    }

    override fun getItemCount(): Int {
       return photos.size
    }

    override fun onBindViewHolder(holder: PhotoAdapter.ViewHolder, position: Int) {
    holder.binding.photo=photos.get(position).photo
        holder.binding.executePendingBindings()
    }


}