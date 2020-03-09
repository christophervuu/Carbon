package com.example.carbon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Model.Info;
import com.example.carbon.R;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private ArrayList<Info> dataList;

    public DeviceAdapter(ArrayList<Info> dataList) {
        this.dataList = dataList;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        holder.mDeviceId.setText(dataList.get(position).getDeviceId());
        holder.mDeviceType.setText(dataList.get(position).getDeviceType());
        holder.mDeviceValue.setText(dataList.get(position).getDeviceValue());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView mDeviceId, mDeviceType, mDeviceValue;

        DeviceViewHolder(View itemView) {
            super(itemView);

            mDeviceId = itemView.findViewById(R.id.TextViewDeviceId);
            mDeviceType = itemView.findViewById(R.id.TextViewDeviceType);
            mDeviceValue = itemView.findViewById(R.id.TextViewUpdatedOn);
        }
    }
}
