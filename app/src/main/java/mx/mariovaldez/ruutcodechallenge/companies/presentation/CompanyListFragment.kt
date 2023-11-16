package mx.mariovaldez.ruutcodechallenge.companies.presentation

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.ruutcodechallenge.R
import mx.mariovaldez.ruutcodechallenge.account.presentation.AccountProfileActivity
import mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters.CompaniesAdapter
import mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters.OptionsAdapter
import mx.mariovaldez.ruutcodechallenge.companies.presentation.adapters.StockCollectionAdapter
import mx.mariovaldez.ruutcodechallenge.companies.presentation.models.StockUI
import mx.mariovaldez.ruutcodechallenge.databinding.FragmentCompanyListBinding
import mx.mariovaldez.ruutcodechallenge.ktx.exhaustive
import mx.mariovaldez.ruutcodechallenge.ktx.findDrawable
import mx.mariovaldez.ruutcodechallenge.ktx.firstNameFormat
import mx.mariovaldez.ruutcodechallenge.ktx.gone
import mx.mariovaldez.ruutcodechallenge.ktx.observe
import mx.mariovaldez.ruutcodechallenge.ktx.showLongLengthToast
import mx.mariovaldez.ruutcodechallenge.ktx.viewBinding
import mx.mariovaldez.ruutcodechallenge.ktx.visible
import mx.mariovaldez.ruutcodechallenge.login.presentation.LoginActivity
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.StockDetailViewModel.Companion.NAME
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.StockDetailViewModel.Companion.PERCENT
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.StockDetailViewModel.Companion.PRICE
import mx.mariovaldez.ruutcodechallenge.stockdetail.presentation.StockDetailViewModel.Companion.SYMBOL

@AndroidEntryPoint
class CompanyListFragment : Fragment(R.layout.fragment_company_list) {

    private val binding: FragmentCompanyListBinding by viewBinding(
        FragmentCompanyListBinding::bind
    )
    private val viewModel: CompanyListViewModel by viewModels()
    private lateinit var companiesAdapter: CompaniesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
        viewModel.userName.observe(viewLifecycleOwner, ::handleUserName)
        viewModel.event.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: CompanyListViewModel.State?) {
        when (state) {
            CompanyListViewModel.State.Error -> showError()
            CompanyListViewModel.State.Loading -> showProgress()
            is CompanyListViewModel.State.Success -> showData(state.collections)
            else -> {}
        }.exhaustive
    }

    private fun handle(event: CompanyListViewModel.Event?) {
        when (event) {
            CompanyListViewModel.Event.NavigateToAccountProfile -> AccountProfileActivity.launch(
                requireContext()
            )

            else -> {}
        }.exhaustive
    }

    private fun showProgress() {
        with(binding.home) {
            progressBar.visible()
            recipesRecyclerView.gone()
        }
    }

    private fun hideProgress() {
        with(binding.home) {
            progressBar.gone()
            recipesRecyclerView.visible()
        }
    }

    private fun setupDrawerLayout() {
        val optionsAdapter = OptionsAdapter {
            viewModel.optionClicked(it)
            closeDrawerLayout()
        }.apply {
            addOptions(viewModel.getOptions())
        }
        with(binding) {
            menu.apply {
                container.apply {
                    layoutParams.width = DRAWER_LAYOUT_WIDTH
                    requestLayout()
                }
                menuOptionsRecyclerView.apply {
                    setHasFixedSize(true)
                    adapter = optionsAdapter
                }
                logoutButton.setOnClickListener {
                    closeDrawerLayout()
                    logout()
                }
            }
        }
    }

    private fun logout() {
        viewModel.logout()
        LoginActivity.launch(requireActivity())
        requireActivity().finish()
    }

    private fun showError() {
        activity?.showLongLengthToast("Error")
    }

    private fun showData(collection: List<StockUI>) {
        companiesAdapter = CompaniesAdapter {
            val bundle = bundleOf(
                SYMBOL to it.symbol,
                PRICE to it.latestPrice,
                PERCENT to it.changePercent,
                NAME to it.companyName,
            )
            findNavController().navigate(
                R.id.navigate_from_company_list_fragment_to_stock_detail_fragment,
                bundle
            )
            viewModel.setDefaultState()
        }.apply { addCollections(collection) }
        with(binding.home) {
            recipesRecyclerView.apply {
                setHasFixedSize(true)
                adapter = companiesAdapter
            }
        }
        hideProgress()
    }

    private fun setupViews() {
        setupToolbar()
        setupSpinner()
        setupDrawerLayout()
    }

    private fun setupSpinner() {
        val stockAdapter = StockCollectionAdapter(requireContext())
        with(binding.home.stockCollectionSpinner) {
            apply {
                adapter = stockAdapter
            }
            setPopupBackgroundDrawable(
                requireContext().findDrawable(
                    R.drawable.spinner_outline_dark_background
                )
            )
            setSelection(0)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.fetchData(parent?.selectedItem.toString())
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun handleUserName(user: String) {
        with(binding.home.toolbar) {
            titleTextView.text = getString(
                R.string.formatted_home_greeting,
                user.firstNameFormat()
            )
        }
    }

    private fun openDrawerLayout(): Unit = binding.drawerLayout.openDrawer(GravityCompat.START)

    private fun closeDrawerLayout(): Unit = binding.drawerLayout.closeDrawer(GravityCompat.START)

    private fun setupToolbar() {
        binding.home.toolbar.menuButton.setOnClickListener { openDrawerLayout() }
    }

    companion object {

        private val DRAWER_LAYOUT_WIDTH: Int =
            (Resources.getSystem().displayMetrics.widthPixels * 0.7).toInt()
    }

}
