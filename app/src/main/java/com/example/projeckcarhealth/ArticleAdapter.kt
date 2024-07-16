package com.example.projeckcarhealth

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize

// Define the Article model class

data class Article(
    val imageResId: Int,
    val title: String,
    val description: String,
    val content: String,
    val longDescription: String
)
// Adapter class for RecyclerView
class ArticleAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    // Create ViewHolder instance for each item in RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener { onItemClick(article) }
    }

    // Return the size of the dataset
    override fun getItemCount(): Int = articles.size

    // ViewHolder class to hold the view elements
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewArticle)
        private val titleView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionView: TextView = itemView.findViewById(R.id.textViewDescription)

        // Bind data to the view elements
        fun bind(article: Article) {
            imageView.setImageResource(article.imageResId)
            titleView.text = article.title
            descriptionView.text = article.description
        }
    }
}
