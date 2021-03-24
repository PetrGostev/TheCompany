package ru.petrgostev.thecompany.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import ru.petrgostev.thecompany.R
import ru.petrgostev.thecompany.data.Company
import ru.petrgostev.thecompany.data.NetworkModule
import ru.petrgostev.thecompany.databinding.ActivityCompanyBinding
import ru.petrgostev.thecompany.repository.CompanyRepository
import ru.petrgostev.thecompany.repository.ICompanyRepository

class CompanyActivity : AppCompatActivity() {

    private val companyRepository: ICompanyRepository by lazy {
        CompanyRepository.getInstance(
            NetworkModule.getInstance()
        )
    }

    private val viewModel: CompanyViewModel by viewModels {
        CompanyViewModelFactory(companyRepository)
    }

    private lateinit var viewBinding: ActivityCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding = ActivityCompanyBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        viewModel.company.observe(this, {
            setupData(it)
        })
        viewModel.error.observe(this, {
            if (it) showError()
        })
    }

    override fun onStart() {
        super.onStart()

        viewBinding.mapView.onStart()
        MapKitFactory.getInstance().onStart()

        intent.getStringExtra(COMPANY_ID)?.let { viewModel.getCompany(it) }
    }

    override fun onStop() {
        super.onStop()
        viewBinding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    private fun setupData(company: Company) {
        viewBinding.www.visibility = if (company.www!!.isNotEmpty())View.VISIBLE else View.GONE
        viewBinding.phone.visibility = if (company.phone!!.isNotEmpty())View.VISIBLE else View.GONE

        viewBinding.name.text = company.name
        viewBinding.www.text = company.www
        viewBinding.phone.text = company.phone
        viewBinding.description.text = company.description

        viewBinding.image.load(company.image) {
            crossfade(false)
            error(R.drawable.vector_image_none)
            transformations(CircleCropTransformation())
        }

        if (company.lat!! != 0.0 && company.lon!! != 0.0) {
            viewBinding.mapView.postDelayed({
                viewBinding.mapView.map.move(
                    CameraPosition(Point(company.lat, company.lon), 12.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 2F),
                    null
                )
                viewBinding.mapView.map.getMapObjects().addPlacemark(Point(company.lat, company.lon))
            }, 1000)
        } else {
            viewBinding.mapView.visibility = View.GONE
        }
    }

    private fun showError() {
        Toast.makeText(this,getString(R.string.error), Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val COMPANY_ID = "company_id"
    }
}