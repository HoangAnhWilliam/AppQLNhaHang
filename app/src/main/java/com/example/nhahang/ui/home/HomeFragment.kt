package com.example.nhahang.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nhahang.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        //homeViewModel.text.observe(viewLifecycleOwner) {
          //  textView.text = it
        //}
        setupWebView()
        return root
    }
    private fun setupWebView() {
        val webView: WebView = binding.homeWeb // Tham chiếu đến ID mới

        // Thiết lập WebViewClient để xử lý các sự kiện duyệt web trong WebView
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                // Xử lý lỗi khi không thể tải URL
            }
        }

        // Cấu hình cài đặt cho WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true // Cho phép JavaScript
        webSettings.domStorageEnabled = true // Cho phép DOM Storage nếu cần

        // Tải URL (có thể là HTTP hoặc HTTPS)
        webView.loadUrl("http://restaurantmanagementvn.somee.com/NhanVien/Home/Index")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}