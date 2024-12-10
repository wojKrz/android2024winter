package edu.programmingclasses2024winter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.switchmaterial.SwitchMaterial
import edu.programmingclasses2024winter.PostListAdapter.PostViewHolder

class PostListAdapter(
  var posts: List<Post>,
  private val onIsReadSwitchClicked: (Int) -> Unit
  ) : Adapter<PostViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_post, parent, false)

    return PostViewHolder(view)
  }

  override fun getItemCount(): Int = posts.size

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
    with(posts[position]) {
      holder.title.text = title
      holder.body.text = body
      holder.isReadSwitch.isChecked = isRead
      holder.isReadSwitch.setOnClickListener {
        onIsReadSwitchClicked(position)
      }
    }
  }

  class PostViewHolder(item: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(item) {
    val title: TextView = item.findViewById(R.id.title)
    val body: TextView = item.findViewById(R.id.body)
    val isReadSwitch: SwitchMaterial = item.findViewById(R.id.isPostReadSwitch)
  }
}
