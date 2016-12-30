package io.github.mshirdel.androidorc.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.mshirdel.androidorc.Models.DTO.GroupDTO;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private List<GroupDTO> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleTv;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener){
            super(itemView);
            titleTv = (TextView)itemView.findViewById(android.R.id.text1);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            GroupDTO group = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(group.getId());

            notifyDataSetChanged();
        }
    }

    public GroupsAdapter(Context context, List<GroupDTO> groups, PostItemListener itemListener){
        mItems = groups;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public GroupsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1,parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupsAdapter.ViewHolder holder, int position){
        GroupDTO item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getName());
    }

    @Override
    public int getItemCount(){
        return mItems.size();
    }

    public void updateGroups(List<GroupDTO> items){
        mItems = items;
        notifyDataSetChanged();
    }

    private GroupDTO getItem(int adapterPosition){
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
