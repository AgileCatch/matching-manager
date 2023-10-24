package com.example.matching_manager.ui.team

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matching_manager.databinding.TeamFragmentBinding
import com.example.matching_manager.ui.match.TeamListAdapter
import com.example.matching_manager.ui.team.bottomsheet.TeamAddCategory
import com.example.matching_manager.ui.team.bottomsheet.TeamFilterCategory
import com.example.matching_manager.ui.team.view.TeamViewModel

class TeamFragment : Fragment() {
    private var _binding: TeamFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamViewModel by lazy {
        ViewModelProvider(this)[TeamViewModel::class.java]
    }


    private val listAdapter by lazy {
        TeamListAdapter(onClick = { item ->
            val intent = TeamDetailActivity.newIntent(item, requireContext())
            startActivity(intent)
        }, onIncrementViewCount = { item ->
            viewModel.incrementViewCount(item)
        }
        )

    }

    private val addContentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val teamModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(
                    TeamAddActivity.EXTRA_TEAM_MODEL,
                    TeamItem::class.java
                )
            } else {
                result.data?.getParcelableExtra(
                    TeamAddActivity.EXTRA_TEAM_MODEL,
                )
            }

            binding.apply {
                btnRecruitment.isChecked = false
                btnApplication.isChecked = false
            }

            setAddContent(teamModel)
        }


    companion object {
        fun newInstance() = TeamFragment()
        const val FRAGMENT_REQUEST_KEY = "request_key"
        const val FRAGMENT_RETURN_TYPE = "fragment_return_type"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = TeamFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()

        // 초기 상태로 버튼을 눌러둡니다.
//        binding.btnRecruitment.isChecked = true
//        binding.btnApplication.isChecked = true
    }


    private fun initView() = with(binding) {
        recyclerview.adapter = listAdapter

        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        btnApplication.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                btnRecruitment.isChecked = false
                viewModel.filterApplicationItems() // 용병신청
            } else if (!btnRecruitment.isChecked) {
                viewModel.clearFilter() // 둘 다 체크 안되어 있을 때만 필터링 제거
            }
        }

        btnRecruitment.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                btnApplication.isChecked = false
                viewModel.filterRecruitmentItems() // 용병모집
            } else if (!btnApplication.isChecked) {
                viewModel.clearFilter() // 둘 다 체크 안되어 있을 때만 필터링 제거
            }
        }



        //add btn
        fabAdd.setOnClickListener {
            val teamAddCategory = TeamAddCategory()
            teamAddCategory.show(childFragmentManager, teamAddCategory.tag)
            //프래그먼트의 childFragmentManager를 쓰면 같은 라이프사이클을 사용 해야함
            childFragmentManager.setFragmentResultListener(
                FRAGMENT_REQUEST_KEY,
                viewLifecycleOwner
            ) { key, bundle ->
                val result = bundle.getString(FRAGMENT_RETURN_TYPE)

                when (result) {
                    TeamAddCategory.RETURN_TYPE_RECRUITMENT -> {
                        val intent = TeamAddActivity.newIntentForAddRecruit(
                            requireContext(),
                            TeamAddType.RECRUIT.name
                        )
                        addContentLauncher.launch(intent)
                    }

                    TeamAddCategory.RETURN_TYPE_APPLICATION -> {
                        val intent = TeamAddActivity.newIntentForAddApplication(
                            requireContext(),
                            TeamAddType.APPLICATION.name
                        )
                        addContentLauncher.launch(intent)
                    }
                }

            }
        }
        //filtr btn
        btnFilter.setOnClickListener {
            val teamFilterCategory = TeamFilterCategory()

            val fragmentManager = requireActivity().supportFragmentManager
            teamFilterCategory.show(fragmentManager, teamFilterCategory.tag)

        }

    }

    //viewmodel init
    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }
    }

    //글추가 로직
    private fun setAddContent(item: TeamItem?) {
        if (item != null) {
            Log.d("setAddContent", "item value = $item")
            viewModel.addContentItem(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}