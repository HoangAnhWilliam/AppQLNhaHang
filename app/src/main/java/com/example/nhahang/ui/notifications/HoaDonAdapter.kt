package com.example.nhahang.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhahang.R

class HoaDonAdapter(private val hoaDonList: List<HoaDon>) :
    RecyclerView.Adapter<HoaDonAdapter.HoaDonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoaDonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hoa_don, parent, false)
        return HoaDonViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoaDonViewHolder, position: Int) {
        val hoaDon = hoaDonList[position]
        holder.tvMaHoaDon.text = "Mã hóa đơn: ${hoaDon.maHoaDon}"
        holder.tvTenKhachHang.text = "Tên khách hàng: ${hoaDon.tenKhachHang}"
        holder.tvTongTien.text = "Tổng tiền: ${hoaDon.tongTien}"
        holder.tvNgayTao.text = "Ngày tạo: ${hoaDon.ngayTao}"
    }

    override fun getItemCount(): Int = hoaDonList.size

    class HoaDonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMaHoaDon: TextView = itemView.findViewById(R.id.tvMaHoaDon)
        val tvTenKhachHang: TextView = itemView.findViewById(R.id.tvTenKhachHang)
        val tvTongTien: TextView = itemView.findViewById(R.id.tvTongTien)
        val tvNgayTao: TextView = itemView.findViewById(R.id.tvNgayTao)
    }
}