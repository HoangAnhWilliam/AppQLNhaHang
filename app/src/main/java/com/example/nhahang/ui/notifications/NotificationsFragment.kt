package com.example.nhahang.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhahang.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var btnLayHoaDon: Button
    private lateinit var btnClearHoaDon: Button
    private lateinit var recyclerViewHoaDon: RecyclerView
    private val hoaDonList = mutableListOf<HoaDon>()
    private lateinit var hoaDonAdapter: HoaDonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Ánh xạ
        btnLayHoaDon = binding.btnLayHoaDon
        btnClearHoaDon = binding.btnClearHoaDon
        recyclerViewHoaDon = binding.recyclerViewHoaDon

        // Thiết lập RecyclerView
        hoaDonAdapter = HoaDonAdapter(hoaDonList)
        recyclerViewHoaDon.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewHoaDon.adapter = hoaDonAdapter

        // Xử lý nút bấm
        btnLayHoaDon.setOnClickListener {
            getHoaDonFromAPI()
        }

        // Xử lý nút bấm Clear
        btnClearHoaDon.setOnClickListener {
            clearHoaDonData()
        }

        return root
    }
    private fun getHoaDonFromAPI() {
        val hoaDonApi = RetrofitClient.instance.create(HoaDonApi::class.java)

        hoaDonApi.getHoaDon().enqueue(object : retrofit2.Callback<List<HoaDon>> {
            override fun onResponse(call: retrofit2.Call<List<HoaDon>>, response: retrofit2.Response<List<HoaDon>>) {
                if (response.isSuccessful && response.body() != null) {
                    val hoaDons = response.body()!!
                    hoaDonList.clear()
                    hoaDonList.addAll(hoaDons)
                    hoaDonAdapter.notifyDataSetChanged()
                } else {
                    // Xử lý khi API trả về lỗi
                    showError("Không lấy được dữ liệu")
                }
            }

            override fun onFailure(call: retrofit2.Call<List<HoaDon>>, t: Throwable) {
                // Xử lý khi không kết nối được với API
                showError("Lỗi kết nối API: ${t.message}")
            }
        })
    }

    private fun showError(message: String) {
        // Hiển thị lỗi bằng Toast hoặc Log
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    // Hàm xóa dữ liệu trong RecyclerView
    private fun clearHoaDonData() {
        hoaDonList.clear() // Xóa tất cả các mục trong danh sách
        hoaDonAdapter.notifyDataSetChanged() // Cập nhật RecyclerView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}