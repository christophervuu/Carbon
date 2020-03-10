package com.example.carbon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Activities.ForgotPassword;
import com.example.carbon.Activities.RegisterUser;
import com.example.carbon.Model.Info;
import com.example.carbon.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    private ArrayList<Info> dataList;
    private OnNoteListener mOnNoteListener;

    public DeviceAdapter(OnNoteListener onNoteListener, ArrayList<Info> dataList) {
        this.mOnNoteListener = onNoteListener;
        this.dataList = dataList;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_device, parent, false);
        return new DeviceViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(final DeviceViewHolder holder, int position) {
        holder.mDeviceId.setText(dataList.get(position).getDeviceId());
        holder.mDeviceType.setText(dataList.get(position).getDeviceType());
        holder.mDeviceValue.setText(dataList.get(position).getDeviceValue());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mDeviceId, mDeviceType, mDeviceValue;
        OnNoteListener onNoteListener;

        DeviceViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            mDeviceId = itemView.findViewById(R.id.TextViewDeviceId);
            mDeviceType = itemView.findViewById(R.id.TextViewDeviceType);
            mDeviceValue = itemView.findViewById(R.id.TextViewUpdatedOn);
            //cardView = itemView.findViewById(R.id.CardViewRowDevice);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
