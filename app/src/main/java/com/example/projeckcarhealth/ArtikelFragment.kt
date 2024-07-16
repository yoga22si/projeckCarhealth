package com.example.projeckcarhealth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeckcarhealth.databinding.FragmentArtikelBinding

class ArtikelFragment : Fragment() {

    private var _binding: FragmentArtikelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtikelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerViewArticles
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Data artikel berita dengan deskripsi singkat
        val articles = listOf(
            Article(
                R.drawable.olahraga,
                "Mengatur kesehatan dengan olahraga",
                "cara mengatur kesehatan dengan olahraga dengan baik",
                "Olahraga merupakan bagian penting dari budaya hidup sehat yang memberikan dampak besar terhadap kesehatan tubuh.\n" +
                        "\n" +
                        "Namun, agar mendapatkan manfaat sesungguhnya, maka Anda patut mengetahui dengan baik bagaimana cara olahraga yang benar.\n" +
                        "\n" +
                        "Pasalnya, sebagian orang terkadang melewatkan sejumlah persiapan penting dalam berolahraga, seperti tidak memperhatikan kondisi tubuh atau intensitas latihan.\n" +
                        "\n" +
                        "Nah, untuk mengetahui tata cara olahraga yang benar, Anda wajib menyimak penjelasannya pada artikel berikut ini!\n" +
                        "\n" +
                        "Cara Olahraga yang Baik dan Benar\n" +
                        "Secara garis besar, olahraga memegang peranan penting dalam menjaga kesehatan dan kebugaran sehingga Anda pun terhindar dari berbagai risiko penyakit.\n" +
                        "\n" +
                        "Dari segi psikologis pun, olahraga memiliki dampak cukup besar terhadap kesehatan mental Anda.\n" +
                        "\n" +
                        "Untuk mendapatkan manfaat olahraga tersebut, Anda tentu harus melakukannya dengan tepat agar bisa memberikan hasil yang efektif dan optimal.\n" +
                        "\n" +
                        "Bila keliru, Anda justru berpotensi mengalami berbagai masalah, seperti pusing setelah olahraga atau risiko cedera lainnya.\n" +
                        "\n" +
                        "Lantas, bagaimanakah tata cara olahraga yang benar? Berikut beberapa hal yang patut Anda perhatikan.\n" +
                        "\n" +
                        "1. Pastikan Kondisi Tubuh Fit\n" +
                        "Pertama, pastikan tubuh Anda dalam keadaan baik dan cukup fit untuk berolahraga.  Jangan sampai Anda memaksakan olahraga saat sedang sakit.\n" +
                        "\n" +
                        "Karena hal ini bisa memperlambat proses pemulihan dan justru membuat badan semakin lemah. Apalagi jika Anda belum terbiasa melakukan aktivitas fisik berat atau berusia lebih dari 45 tahun.\n" +
                        "\n" +
                        "Selain itu, bila Anda memang memiliki masalah kesehatan tertentu seperti penyakit jantung, asma, atau kondisi kronis lainnya, cobalah konsultasikan terlebih dahulu dengan dokter tentang bagaimana cara olahraga yang benar dan aman, dan jenis olahraga apa yang dilakukan.\n" +
                        "\n" +
                        "2. Pilih Waktu Olahraga yang Tepat\n" +
                        "Cara olahraga yang benar berikutnya adalah memerhatikan waktu berolahraga. Dalam hal ini, Anda bisa sesuaikan dengan kesibukan Anda sehari-hari.\n" +
                        "\n" +
                        "Olahraga pagi merupakan salah satu waktu yang direkomendasikan mengingat udara pada waktu tersebut lebih sejuk dan belum begitu berpolusi.\n" +
                        "\n" +
                        "Bila Anda memang tak memiliki banyak waktu di pagi hari, olahraga sore atau malam pun sebetulnya bukan masalah.\n" +
                        "\n" +
                        "Namun, pastikan tidak terlalu larut atau bahkan mendekati waktu tidur, ya. Sebab, hal tersebut diketahui dapat membuat Anda sulit tidur.",
                "Konten artikel 1 berita panjang..."
            ),
            Article(
                R.drawable.pola_makan,
                "Mengatur pola makan",
                "mengatur pola makan dengan baik",
                "Tips Pola Makan Sehat agar Tubuh Bugar\n" +
                        " \n" +
                        "\n" +
                        "Beberapa tips pola makan sehat agar tubuh tetap bugar yang dapat diterapkan:\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "1. Pilih Jenis Makanan dan Porsi Sehat untuk Disantap\n" +
                        " \n" +
                        "\n" +
                        "Makan bukan berarti asal kenyang begitu saja, namun Anda perlu memperhatikan kandungan gizi dan nutrisi dalam setiap santapan. Piring sajian sebaiknya memenuhi makanan dengan asupan karbohidrat, makanan berprotein tinggi, mengandung vitamin, dan mineral seimbang. Maka itu, pastikan terdapat kombinasi aneka ragam makanan dalam satu piring. Hal ini dikarenakan tidak ada satupun jenis makanan yang mampu mengandung semua jenis zat gizi yang dibutuhkan tubuh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "2. Perbanyak Konsumsi Sayur dan Buah\n" +
                        " \n" +
                        "\n" +
                        "Dalam satu porsi sajian menu harian Anda, pastikan separuh piring terdapat bagian untuk sayur-sayuran dan buah-buahan. Sayur dan buah adalah salah satu makanan yang memiliki serat tinggi, kandungan vitamin, dan mineral yang bermanfaat bagi kesehatan pencernaan dan meningkatkan daya tahan tubuh.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "3. Jadwalkan Aktivitas Fisik atau Olahraga Secara Teratur\n" +
                        " \n" +
                        "\n" +
                        "Jika Anda makan berlebihan terutama kelebihan kalori, Anda disarankan untuk membakar kalori dengan menjadwalkan olahraga seperti jogging, jalan kaki, bersepeda, berenang, dan yoga. Namun, jangan langsung olahraga begitu selesai makan. Beri jarak minimal satu hingga dua jam karena tubuh membutuhkan waktu untuk mencerna makanan.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "4. Perhatikan Jumlah Kalori yang Dimakan\n" +
                        " \n" +
                        "\n" +
                        "Selain kalori, Anda juga didorong untuk lebih memperhatikan kandungan gula, garam, dan lemak dalam sajian menu makanan. Kementerian Kesehatan Republik Indonesia menyarankan batasan konsumsi gula, garam, dan lemak bagi tiap orang per hari, antara lain:\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Gula tidak lebih dari 50 gram (4 sendok makan).\n" +
                        "Garam tidak melebihi 2.000 miligram natrium/sodium atau 5 gram (1 sendok teh).\n" +
                        "Lemak hanya 67 gram (5 sendok makan minyak).\n" +
                        " \n" +
                        "\n" +
                        "Untuk kemudahan mengingat, rumusannya adalah G4 G1 L5. Jangan lupa juga untuk selalu membaca label kemasan makanan agar mendapatkan informasi nilai kandungan gizi sehingga Anda mengetahui dan memantau asupan gizi yang dikonsumsi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "5. Hindari Perut Kosong Sebelum Beraktivitas\n" +
                        " \n" +
                        "\n" +
                        "Banyak dari kita yang gemar melewatkan waktu sarapan. Berbagai hal menjadi alasan, mulai dari tak punya waktu, tidak terbiasa, atau karena sedang menurunkan berat badan. Padahal melewatkan waktu makan di pagi hari ini bisa merugikan kesehatan karena tubuh memerlukan energi untuk beraktivitas. Kebiasaan makan berlebihan akibat perut kosong juga berbahaya bagi kesehatan karena dapat menyebabkan rasa tidak nyaman seperti perut kembung, mual, hingga berisiko terkena penyakit.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Jika Anda atau orang terdekat membutuhkan bantuan profesional, jangan ragu untuk berkonsultasi dengan dokter-dokter Siloam Hospital terdekat untuk memperoleh diagnosis dan penanganan medis secara tepat.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Anda dapat menggunakan fitur Cari Dokter untuk menemukan jadwal dokter, booking, dan buat janji dengan dokter terkait. Atau, gunakan juga aplikasi MySiloam untuk memudahkan Anda berkonsultasi langsung dengan dokter secara online. Mari unduh aplikasi MySiloam dan percayakan kesehatan Anda dan keluarga #BersamaSiloam!\n" +
                        "\n" +
                        "This article was created and published by Siloam Hospitals, read more at: *https://www.siloamhospitals.com/en/informasi-siloam/artikel/jaga-tubuh-tetap-bugar-ikuti-5-tips-makan-sehat-ini*\n" +
                        "\n" +
                        "Get the latest health information and medical services at:\n" +
                        "*Instagram*: https://instagram.com/siloamhospitals/\n" +
                        "*Contact Center*: (021)1-500-181\n" +
                        "*Siloam-At-Home*: https://wa.me/628111950181\n" +
                        "\n" +
                        "Download MySiloam application and enjoy the convenience for your healthcare services:\n" +
                        "*IOS*: https://apple.co/3PYwuZK\n" +
                        "*Android*: https://bit.ly/SiloamPS",
                "Konten artikel 2 berita panjang..."
            ),
            Article(
                R.drawable.pola_tidur,
                "Mengatur pola tidur",
                "mengatur pola tidur dengan baik",
                "Pola tidur yang baik sangat penting bagi kesehatan dan perkembangan anak-anak. Tidur yang cukup dan berkualitas membantu pemulihan tubuh, mendukung fungsi kognitif, serta mempengaruhi kesehatan fisik dan emosional secara keseluruhan. Dalam hal ini, orang tua memiliki peran penting dalam membantu anak-anak mengembangkan kebiasaan tidur yang sehat.\n" +
                        "\n" +
                        "Ada beberapa tips untuk orang tua yang bisa dilakukan dalam membantu menerapkan pola tidur yang baik untuk kesehatan anak, yaitu:\n" +
                        "\n" +
                        "1. Tetapkan jadwal tidur yang konsisten\n" +
                        "\n" +
                        "Mendirikan jadwal tidur yang konsisten membantu anak-anak memiliki ritme tidur yang teratur. Tetapkan waktu tidur yang sama setiap hari, termasuk akhir pekan, untuk menjaga konsistensi. Ini membantu tubuh anak mengatur pola tidur yang lebih baik dan membuat anak merasa lebih terjaga dan bugar di siang hari.\n" +
                        "\n" +
                        "2. Ciptakan rutinitas sebelum tidur\n" +
                        "\n" +
                        "Rutinitas sebelum tidur yang tenang dan terstruktur membantu anak-anak mempersiapkan tubuh dan pikirannya untuk tidur. Aktivitas seperti mandi, membaca buku, atau mendengarkan musik yang menenangkan dapat membantu merelaksasi anak sebelum tidur. Hindari rangsangan seperti permainan video atau televisi yang terlalu dekat dengan waktu tidur.\n" +
                        "\n" +
                        "3. Buat lingkungan tidur yang nyaman\n" +
                        "\n" +
                        "Pastikan lingkungan tidur anak nyaman dan cocok untuk tidur yang berkualitas. Tempat tidur yang nyaman, suhu yang sejuk, kegelapan yang cukup, dan keheningan dapat membantu menciptakan kondisi yang ideal untuk tidur. Orang tua juga dapat mempertimbangkan penggunaan bantal, selimut, atau mainan kesayangan yang memberikan kenyamanan dan keamanan bagi anak.\n" +
                        "\n" +
                        "4. Batasi paparan gadget sebelum tidur\n" +
                        "\n" +
                        "Cahaya biru yang dipancarkan oleh perangkat elektronik seperti ponsel atau tablet dapat mengganggu ritme tidur anak. Batasi penggunaan gadget sebelum tidur dan buatlah kebijakan \"tidak ada gadget di kamar tidur\" untuk memastikan anak mendapatkan waktu tidur yang berkualitas.\n" +
                        "\n" +
                        "5. Fasilitasi aktivitas fisik dan kebugaran\n" +
                        "\n" +
                        "Aktivitas fisik yang cukup dapat membantu anak-anak merasa lebih lelah dan siap untuk tidur di malam hari. Dorong anak untuk berpartisipasi dalam kegiatan fisik seperti olahraga, bermain di luar, atau bermain aktif dalam rumah. Pastikan aktivitas tersebut selesai beberapa jam sebelum tidur agar tubuh anak punya waktu untuk tenang sebelum tidur.\n" +
                        "\n" +
                        "6. Perhatikan asupan makanan dan minuman\n" +
                        "\n" +
                        "Hindari memberikan makanan atau minuman yang mengandung kafein atau gula tinggi menjelang tidur. Kafein dapat mengganggu tidur anak, sedangkan gula tinggi dapat membuat mereka lebih energik. Berikan makanan ringan yang sehat dan hindari makan berlebihan menjelang waktu tidur.\n" +
                        "\n" +
                        "Pola tidur yang baik merupakan komponen penting dalam menjaga kesehatan dan perkembangan anak-anak. Orang tua berperan aktif dalam mendukung kesehatan anak dengan mengikuti tips-tips di atas. Dengan mengatur jadwal tidur yang konsisten, menciptakan rutinitas sebelum tidur yang tenang, menciptakan lingkungan tidur yang nyaman, mengatur penggunaan gadget, memfasilitasi aktivitas fisik, dan memperhatikan asupan makanan dan minuman, dapat membantu anak-anak mendapatkan tidur yang berkualitas dan mengenalkan kesehatan secara menyeluruh.",
                "Konten artikel 3 berita panjang..."
            ),
            Article(
                R.drawable.stress,
                "Mengatur kesehatan agar tidak stres",
                "mengatur kesehatan agar tidak stress berlebihan",
                "Rasa stres yang dialami oleh seseorang merupakan hal yang normal untuk dialami apabila seseorang mengalami tekanan atau dipaksa untuk dapat beradaptasi dalam waktu yang singkat. Namun demikian, bahaya stres dalam jangka waktu yang panjang akan membuat seseorang mengalami berbagai masalah kesehatan yang dapat mengganggu aktivitas hariannya.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Dengan pengertian diatas, maka penting bagi kita untuk bersegera dalam mengatasi stres yang dirasakan. Berikut ini adalah beberapa aktivitas yang dapat dilakukan untuk mengatasi rasa tidak nyaman akibat stres, diantaranya adalah:\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "1. Meningkatkan ibadah kepada Tuhan Yang Maha Esa\n" +
                        "\n" +
                        "2. Menjaga kesehatan dengan berolahraga secara teratur, seperti berjalan dan bersepeda, serta mencukupi kebutuhan tidur dan makan makanan yang bergizi seimbang\n" +
                        "\n" +
                        "3. Melakukan hobi yang sesuai dengan minat dan bakat\n" +
                        "\n" +
                        "4. Berfikit hal-hal yang menyenangkan dalam hidup\n" +
                        "\n" +
                        "5. Membicarakan perasaan dan keluhan yang dialami kepada seseorang yang dapat dipercaya.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Dengan mengetahui berbagai kegiatan positif yang dapat meminimalisir stres diatas, diharapkan dapat membantu masyarakat dalam mengelola stres yang saat ini sedang dihadapi.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Tetap terapkan perilaku hidup sehat dan bersegera dalam melakukan pemeriksaan ke fasilitas kesehatan terdekat apabila mengalami gangguan stress yang tidak kunjung membaik, agar bisa segera mendapatkan penanganan secara cepat dan tepat dari petugas kesehatan.",
                "Konten artikel 4 berita panjang..."
            )
        )

        val adapter = ArticleAdapter(articles) { article ->
            // Navigate to DetailArticleFragment dengan konten artikel panjang
            val detailFragment = DetailArticleFragment.newInstance(article)
            parentFragmentManager.beginTransaction()
                .replace(R.id.flFragment, detailFragment)
                .addToBackStack(null)
                .commit()
        }
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
