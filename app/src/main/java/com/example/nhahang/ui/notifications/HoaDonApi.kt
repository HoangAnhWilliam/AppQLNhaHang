package com.example.nhahang.ui.notifications

import retrofit2.Call
import retrofit2.http.GET

interface HoaDonApi {
    @GET("NhanVien/NhanVienApi") // Thay "your-api-endpoint" bằng endpoint API của bạn
    fun getHoaDon(): Call<List<HoaDon>>
}