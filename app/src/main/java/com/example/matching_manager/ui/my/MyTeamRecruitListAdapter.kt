package com.example.matching_manager.ui.my

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.matching_manager.databinding.MyRecruitItemBinding
import com.example.matching_manager.ui.team.TeamItem

class MyTeamRecruitListAdapter (private val onItemClick: (TeamItem.RecruitmentItem) -> Unit,
                          private val onMenuClick : (TeamItem.RecruitmentItem) -> Unit) : ListAdapter<TeamItem.RecruitmentItem, MyTeamRecruitListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<TeamItem.RecruitmentItem>() {
        override fun areItemsTheSame(oldItem: TeamItem.RecruitmentItem, newItem: TeamItem.RecruitmentItem): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: TeamItem.RecruitmentItem, newItem: TeamItem.RecruitmentItem): Boolean {
            return oldItem == newItem
        }
    }
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyRecruitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClick, onMenuClick)
    }

    class ViewHolder(private val binding: MyRecruitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : TeamItem.RecruitmentItem, onItemClick: (TeamItem.RecruitmentItem) -> Unit, onMenuClick : (TeamItem.RecruitmentItem) -> Unit) = with(binding) {
            ivProfile.setImageResource(item.userImg)
            tvType.text = item.type
            tvDetail.text = "${item.gender} ${item.playerNum}"
            tvSchedule.text = item.schedule
            tvPlace.text = item.area

            btnMenu.setOnClickListener {
                onMenuClick(item)
            }

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}