package ru.petrgostev.thecompany.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import ru.petrgostev.thecompany.data.Company
import ru.petrgostev.thecompany.data.NetworkModule
import ru.petrgostev.thecompany.databinding.ActivityListBinding
import ru.petrgostev.thecompany.repository.CompanyRepository
import ru.petrgostev.thecompany.repository.ICompanyRepository
import ru.petrgostev.thecompany.ui.details.CompanyActivity
import ru.petrgostev.thecompany.ui.list.adapter.CompanyAdapter

class ListActivity : AppCompatActivity() {

    private val companyRepository: ICompanyRepository by lazy {
        CompanyRepository.getInstance(
            NetworkModule.getInstance()
        )
    }

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(companyRepository)
    }

    private lateinit var viewBinding: ActivityListBinding

    private val adapter: CompanyAdapter by lazy {
        CompanyAdapter(fun(companyViewItem: Company) {
            showDetails(companyViewItem.id)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewModel.companyList.observe(this, {
            showProgress(false)
            adapter.submitList(it)
        })

        viewBinding.companyRecycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCompanyList()
        showProgress(true)
    }

    private fun showProgress(visible: Boolean) {
        viewBinding.progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showDetails(id: String) {
        val intent = Intent(this, CompanyActivity::class.java)
        intent.putExtra(COMPANY_ID, id)
        startActivity(intent)
    }

    companion object {
        private const val COMPANY_ID = "company_id"
    }
}