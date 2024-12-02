package edu.programmingclasses2024winter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.recyclerview.widget.RecyclerView
import edu.programmingclasses2024winter.PostsListAdapter.ViewHolder

class PostsListAdapter(
  var data: List<Post>,
  private val onIsReadClicked: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_post, parent, false)

    return ViewHolder(view)
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder) {
    val post = data[position]

    idTextView.text = if (post.id % 2 == 0) {
      post.id.toString()
    } else {
      ""
    }
    titleTextView.text = post.title
    bodyTextView.text = post.body
    isReadToggle.isChecked = post.isRead
    isReadToggle.setOnClickListener {
      onIsReadClicked(position)
    }
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val idTextView: TextView = view.findViewById(R.id.postId)
    val titleTextView: TextView = view.findViewById(R.id.postTitle)
    val bodyTextView: TextView = view.findViewById(R.id.postBody)
    val isReadToggle: AppCompatToggleButton = view.findViewById(R.id.isReadToggle)
  }
}
