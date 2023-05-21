package com.hbazai.industreport.pages.search_page

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.report_page.ShowDetailReportActivity
import com.hbazai.industreport.pages.search_page.adapter.SearchReportAdapter
import com.hbazai.industreport.pages.search_page.viewModel.SearchReportsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private lateinit var txtSearch: TextInputEditText
    private lateinit var rvSearchReport: RecyclerView

    private val searchReportViewModel: SearchReportsViewModel by viewModel()
    private val searchReportAdapter: SearchReportAdapter by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtSearch = view.findViewById(R.id.txt_search)
        rvSearchReport = view.findViewById(R.id.rv_search_reports)

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                searchReportViewModel.searchReports(p0.toString(), "2000-01-01", "3000-01-01")
                searchReportViewModel.searchReportsLiveDta.observe(viewLifecycleOwner) { listOfSearchItems ->
                    if (listOfSearchItems != null) {
                        searchReportAdapter.differ.submitList(listOfSearchItems)
                        rvSearchReport.apply {
                            layoutManager =
                                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                            adapter = searchReportAdapter
                        }

                        searchReportAdapter.setOnItemClickListener {
                            val intent =
                                Intent(requireActivity(), ShowDetailReportActivity::class.java)
                            intent.apply {
                                putExtra("id",it.id.toString())
                                putExtra("date", it.date.toString())
                                putExtra("image", it.image.toString())
                                putExtra("time", it.time.toString())
                                putExtra("description", it.description.toString())
                                putExtra("time", it.time.toString())
                                putExtra("instrumentTag", it.instrumentTag.toString())
                                putExtra("title", it.title.toString())
                                putExtra("unit", it.unit.toString())
                                putExtra("userId", it.userId.toString())
                                putExtra("type", it.type.toString())
                                putExtra("reportToken", it.reportToken.toString())
                                putExtra("reportType", it.reportType.toString())
                            }
                            startActivity(intent)
                        }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "اشکال در ارتباط با سرور",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    })

}

}