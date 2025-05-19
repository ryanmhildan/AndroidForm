//Pengerjaan terakhir : 19/05/2025 23:55
//NIM                 : 10122475
//NAMA                : Ryan Muhammad Hildan
//KELAS               : PA-4

package com.ryanmhildan.androidform

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryanmhildan.androidform.databinding.ActivityMainBinding
import com.ryanmhildan.androidform.model.Participant

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val semester = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")

//    private val versiOs = listOf("10", "11", "12")

    private val versiOsMap = mapOf(
        "Windows" to listOf("10", "11", "11 Pro", "11 Enterprise"),
        "MacOS" to listOf("Catalina", "Big Sur", "Monterey", "Ventura"),
        "Linux" to listOf("Ubuntu", "Debian", "Fedora", "Arch")
    )


    private val deployment = listOf("Device Kabel", "Emulator", "Device Wifi", "Tidak Bisa Deploy")

    private val merkHp = listOf("Samsung", "Xiaomi", "Huawei", "Oppo", "Vivo", "Realme", "Asus", "Lenovo", "LG", "Motorola", "Sony", "Google", "Apple", "Tidak Punya")

    private fun updateVersiOsSpinner(osDevice: String) {
        val versiList = versiOsMap[osDevice] ?: emptyList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, versiList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spVersiOs.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.orange),
            ContextCompat.getColor(this, android.R.color.black)
        )

        val colorStateList = ColorStateList(states, colors)

        binding.radWindows.buttonTintList = colorStateList
        binding.radMacos.buttonTintList = colorStateList
        binding.radLinux.buttonTintList = colorStateList
        binding.radPc.buttonTintList = colorStateList
        binding.radLaptop.buttonTintList = colorStateList
        binding.radTidak.buttonTintList = colorStateList
        binding.radWifi.buttonTintList = colorStateList
        binding.radWifiNebeng.buttonTintList = colorStateList
        binding.radMobileData.buttonTintList = colorStateList
        binding.radSudah.buttonTintList = colorStateList
        binding.radBelum.buttonTintList = colorStateList


        //Adapter Semester
        val adapterSemester = ArrayAdapter(this, android.R.layout.simple_spinner_item, semester)
        adapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spSemester.adapter = adapterSemester

        //Adapter Versi OS
//        val adapterVersiOs = ArrayAdapter(this, android.R.layout.simple_spinner_item, versiOs)
//        adapterVersiOs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spVersiOs.adapter = adapterVersiOs

        //Adapter Deployment
        val adapterDeployment = ArrayAdapter(this, android.R.layout.simple_spinner_item, deployment)
        adapterDeployment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDeployment.adapter = adapterDeployment

        //Adapter Merk HP
        val adapterMerkHp = ArrayAdapter(this, android.R.layout.simple_spinner_item, merkHp)
        adapterMerkHp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spMerkHp.adapter = adapterMerkHp

        // Set default versi OS untuk Windows
        updateVersiOsSpinner("Windows")
        binding.radWindows.isChecked = true

        // Listener untuk update spinner versi OS sesuai radio button
        binding.radWindows.setOnClickListener { updateVersiOsSpinner("Windows") }
        binding.radMacos.setOnClickListener { updateVersiOsSpinner("MacOS") }
        binding.radLinux.setOnClickListener { updateVersiOsSpinner("Linux") }


        binding.btnSubmit.setOnClickListener {
            val nim = binding.edtNim.text.toString()
            val nama = binding.edtNama.text.toString()
            val semester = binding.spSemester.selectedItem.toString()

            val device = when{
                binding.radPc.isChecked -> "PC Desktop"
                binding.radLaptop.isChecked -> "Laptop"
                binding.radTidak.isChecked -> "Tidak Punya"
                else -> ""
            }

            val osDevice = when{
                binding.radWindows.isChecked -> "Windows"
                binding.radMacos.isChecked -> "MacOS"
                binding.radLinux.isChecked -> "Linux"
                else -> ""
            }

            val versiOs = binding.spVersiOs.selectedItem?.toString() ?: ""

            val ram = binding.edtRam.text.toString().toInt()

            val cpu = binding.edtCpu.text.toString()

            val deployment = binding.spDeployment.selectedItem.toString()

            val merkHp = binding.spMerkHp.selectedItem.toString()

            val osHp = binding.edtOsHp.text.toString()

            val ukuranHp = binding.edtUkuranHp.text.toString().toDouble()

            val internet = when{
                binding.radWifi.isChecked -> "Wifi"
                binding.radWifiNebeng.isChecked -> "Wifi Nebeng"
                binding.radMobileData.isChecked -> "Mobile Data"
                else -> ""
            }

            val install = when{
                binding.radSudah.isChecked -> "Sudah"
                binding.radBelum.isChecked -> "Belum"
                else -> ""
            }

            val versi = binding.edtVersi.text.toString()

            if (nim.isEmpty() || nama.isEmpty() || semester.isEmpty() || device.isEmpty() || osDevice.isEmpty() || versiOs.isEmpty() || ram.toString().isEmpty() || cpu.isEmpty() || deployment.isEmpty() || merkHp.isEmpty() || osHp.isEmpty() || ukuranHp.toString().isEmpty() || internet.isEmpty() || install.isEmpty() || versi.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val participant = Participant(nim, nama, semester, device, osDevice, versiOs, ram, cpu, deployment, merkHp, osHp, ukuranHp, internet, install, versi)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("participant", participant)
            startActivity(intent)

        }


    }
}