package com.example.matching_manager.ui.match

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matching_manager.R
import com.example.matching_manager.databinding.MatchFragmentBinding
import com.example.matching_manager.ui.match.bottomsheet.MatchFilterCategory
import com.example.matching_manager.ui.match.bottomsheet.MatchSortBottomSheet
import com.example.matching_manager.ui.team.bottomsheet.TeamFilterCategory

class MatchFragment : Fragment() {
    private var _binding: MatchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MatchViewModel by viewModels {
        MatchViewModelFactory()
    }

    private val sharedViewModel: MatchSharedViewModel by activityViewModels()

    private val listadapter by lazy {
        MatchListAdapter { item ->
            val matchList = viewModel.realTimeList.value ?: emptyList()
            if(matchList.any { it.matchId == item.matchId }) {
                startActivity(detailIntent(requireContext(), item))
            }
            else {
                val dialog = MatchDeletedAlertDialog()
                dialog.show(childFragmentManager, "matchDeletedAlertDialog")
            }
        }
    }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    companion object {
        fun newInstance() = MatchFragment()
        const val OBJECT_DATA = "item_object"
        const val ID_DATA = "item_userId"
        const val CATEGORY_REQUEST_KEY = "category_key"

        fun detailIntent(context: Context, item: MatchDataModel): Intent {
            val intent = Intent(context, MatchDetailActivity::class.java)
            intent.putExtra(OBJECT_DATA, item)
            return intent
        }

        //로그인 기능 구현 후 유저 아이디 보내줘야함
        fun writeIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, MatchWritingActivity::class.java)
            intent.putExtra(ID_DATA, userId)
            return intent
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = MatchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {

        progressBar.visibility = View.VISIBLE
        viewModel.fetchData()
        rv.adapter = listadapter


        val manager = LinearLayoutManager(requireContext())
        manager.reverseLayout = true
        manager.stackFromEnd = true
        rv.layoutManager = manager

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    viewModel.fetchData()
                }
            }

        btnSort.setOnClickListener {
            val matchSortBottomSheet = MatchSortBottomSheet()

            val fragmentManager = requireActivity().supportFragmentManager
            matchSortBottomSheet.show(fragmentManager, matchSortBottomSheet.tag)
        }

        fabAdd.setOnClickListener {
            resultLauncher.launch(writeIntent(requireContext(), "testUser"))
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchData()
            swipeRefreshLayout.isRefreshing = false
        }
        swipeRefreshLayout.setColorSchemeResources(R.color.common_point_green)

        //filtr btn
        btnFilter.setOnClickListener {
            val matchFilterCategory = MatchFilterCategory()
            val fragmentManager = requireActivity().supportFragmentManager
            matchFilterCategory.show(fragmentManager, matchFilterCategory.tag)

            setFragmentResultListener(CATEGORY_REQUEST_KEY) { _, bundle ->
                //결과 값을 받는곳입니다.
                val area = bundle.getString(TeamFilterCategory.SELECTED_AREA)
                val game = bundle.getString(TeamFilterCategory.SELECTED_GAME)

                //선택한 게임과 지역에 따라 아이템을 필터링합니다.
                viewModel.filterItems(area = area,game = game)
            }
        }
    }

    private fun initViewModel() = with(binding) {
        with(viewModel) {
            autoFetchData()
            list.observe(viewLifecycleOwner, Observer {
                if (it.isEmpty()) {
                    binding.tvEmpty.visibility = (View.VISIBLE)
                    listadapter.submitList(it.toList())
                } else {
                    binding.tvEmpty.visibility = (View.INVISIBLE)
                    listadapter.submitList(it.toList())
                }

                var smoothList = 0
                if (it.size > 0) smoothList = it.size - 1
                else smoothList = 1
                binding.progressBar.visibility = View.INVISIBLE
                binding.rv.smoothScrollToPosition(smoothList)
            })
        }
        with(sharedViewModel) {
            filter.observe(viewLifecycleOwner, Observer { (area, game) ->
                val filter = kotlin.String.format("%s / %s", area, game)
                val areaFilter = kotlin.String.format("모든 지역/%s", game)
                val gameFilter = kotlin.String.format("%s/모든 경기", area)

                if (area.contains("선택") && game.contains("선택")) {
                    tvFilter.text = "전체 글"
                    tvFilter.visibility = (android.view.View.VISIBLE)
                } else if (area.contains("선택")) {
                    tvFilter.text = areaFilter
                    tvFilter.visibility = (android.view.View.VISIBLE)
                } else if (game.contains("선택")) {
                    tvFilter.text = gameFilter
                    tvFilter.visibility = (android.view.View.VISIBLE)
                } else {
                    tvFilter.text = filter
                    tvFilter.visibility = (android.view.View.VISIBLE)
                }
            })
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}