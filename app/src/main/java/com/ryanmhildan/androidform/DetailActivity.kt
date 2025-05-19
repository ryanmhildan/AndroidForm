package com.ryanmhildan.androidform

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ryanmhildan.androidform.databinding.ActivityDetailBinding
import com.ryanmhildan.androidform.model.Participant

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participant = intent.getParcelableExtra<Participant>("participant")

        participant?.let{
            binding.txtNimResult.text = it.nim
            binding.txtNamaResult.text = it.nama
            binding.txtSemesterResult.text = it.semester
            binding.txtDeviceResult.text = it.device
            binding.txtOsDeviceResult.text = it.osDevice
            binding.txtVersiOsResult.text = it.versiOs
            binding.txtRamResult.text = it.ram.toString() + " GB"
            binding.txtCpuResult.text = it.cpu
            binding.txtDeploymentResult.text = it.deployment
            binding.txtMerkHpResult.text = it.merkHp
            binding.txtOsHpResult.text = it.osHp
            binding.txtUkuranHpResult.text = it.ukuranHp.toString() + " inch"
            binding.txtInternetResult.text = it.internet
            binding.txtInstallResult.text = it.install
            binding.txtVersiResult.text = it.versi
        }

//        binding.btnInfoDeveloper.setOnClickListener {
//            val bottomSheet = InfoDeveloper()
//            bottomSheet.show(supportFragmentManager, "InfoDeveloper")
//        }

    }
}