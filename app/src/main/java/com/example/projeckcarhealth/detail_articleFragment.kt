package com.example.projeckcarhealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projeckcarhealth.databinding.FragmentDetailArticleBinding

class DetailArticleFragment : Fragment() {

    private var _binding: FragmentDetailArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil argumen dari bundle
        val title = arguments?.getString(ARG_TITLE) ?: ""
        val shortDescription = arguments?.getString(ARG_SHORT_DESCRIPTION) ?: ""
        val longDescription = arguments?.getString(ARG_LONG_DESCRIPTION) ?: ""
        val imageResId = arguments?.getInt(ARG_IMAGE_RES_ID) ?: 0
        val content = arguments?.getString(ARG_CONTENT) ?: ""

        // Tampilkan data di UI
        binding.textViewTitle.text = title
        binding.textViewDescription.text = shortDescription // Gunakan deskripsi singkat di ArtikelFragment
        binding.imageViewArticle.setImageResource(imageResId)
        binding.textViewLongDescription.text = longDescription // Gunakan deskripsi panjang di DetailArticleFragment

        // Handle Back Button Click
        binding.buttonBack.setOnClickListener {
            requireActivity().onBackPressed() // Kembali ke fragment sebelumnya
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_SHORT_DESCRIPTION = "shortDescription"
        private const val ARG_LONG_DESCRIPTION = "longDescription"
        private const val ARG_IMAGE_RES_ID = "imageResId"
        private const val ARG_CONTENT = "content"

        @JvmStatic
        fun newInstance(article: Article) =
            DetailArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, article.title)
                    putString(ARG_SHORT_DESCRIPTION, article.description) // Gunakan deskripsi singkat dari Article
                    putString(ARG_LONG_DESCRIPTION, article.content) // Gunakan deskripsi panjang dari Article
                    putInt(ARG_IMAGE_RES_ID, article.imageResId)
                    putString(ARG_CONTENT, article.longDescription) // Gunakan deskripsi panjang dari Article
                }
            }
    }
}
