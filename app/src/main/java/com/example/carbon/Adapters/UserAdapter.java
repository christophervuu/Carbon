package com.example.carbon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<String> dataList;

    public UserAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_device, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        //holder.mDeviceId.setText(dataList.get(position).getDeviceId());
        holder.mName.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mName;

        UserViewHolder(View itemView) {
            super(itemView);

            mName = itemView.findViewById(R.id.TextViewDeviceType);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
