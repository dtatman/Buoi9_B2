package com.example.buoi9_b2
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var btnGenerateData: Button
    private lateinit var btnDeleteAll: Button
    private lateinit var listViewData: ListView
    private val dataList = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần giao diện
        btnGenerateData = findViewById(R.id.btnGenerateData)
        btnDeleteAll = findViewById(R.id.btnDeleteAll)
        listViewData = findViewById(R.id.listViewData)

        // Cài đặt Adapter cho ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listViewData.adapter = adapter

        // Xử lý khi nhấn nút "Tạo dữ liệu random"
        btnGenerateData.setOnClickListener {
            generateRandomData()  // Gọi hàm tạo dữ liệu random
        }

        // Xử lý khi nhấn nút "Xóa tất cả"
        btnDeleteAll.setOnClickListener {
            showDeleteDialog()  // Gọi hàm hiển thị AlertDialog
        }
    }

    // Hàm tạo dữ liệu random và cập nhật ListView
    private fun generateRandomData() {
        val randomData = Random.nextInt(30)
        for(i in 1..randomData){
            var dataRand="${Random.nextInt(14)*Random.nextInt(32)}"
            dataList.add(dataRand)  // Thêm vào danh sách
            adapter.notifyDataSetChanged()  // Cập nhật lại ListView
        }
    }

    // Hàm hiển thị AlertDialog xác nhận xóa tất cả dữ liệu
    private fun showDeleteDialog() {
        // Tạo AlertDialog
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Xóa tất cả dữ liệu")
        dialogBuilder.setMessage("Bạn có chắc chắn muốn xóa toàn bộ dữ liệu không?")

        // Cấu hình nút "Xác nhận"
        dialogBuilder.setPositiveButton("Xác nhận") { dialog, _ ->
            // Khi nhấn "Xác nhận", xóa tất cả dữ liệu
            dataList.clear()  // Xóa tất cả phần tử trong danh sách
            adapter.notifyDataSetChanged()  // Cập nhật lại ListView
            Toast.makeText(this, "Đã xóa tất cả dữ liệu", Toast.LENGTH_SHORT).show()
            dialog.dismiss()  // Đóng dialog
        }

        // Cấu hình nút "Hủy"
        dialogBuilder.setNegativeButton("Hủy") { dialog, _ ->
            // Khi nhấn "Hủy", chỉ đóng dialog mà không làm gì
            dialog.dismiss()
        }

        // Tạo và hiển thị AlertDialog
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
