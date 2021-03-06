package com.knurtz.lightcontrol;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class LightsAdapter extends RecyclerView.Adapter<LightsAdapter.LightViewHolder> {

    private LightContainer light_dataset_;

    LightsAdapter(LightContainer new_dataset){
        this.light_dataset_ = new_dataset;
    }

    // class for views used by this adapter
    public static class LightViewHolder extends RecyclerView.ViewHolder {
        CardView light_card;

        TextView light_name;
        TextView light_address;
        SeekBar light_seekbar;
        Switch light_switch;

        LightViewHolder(View itemView) {
            super(itemView);
            light_card = (CardView)itemView.findViewById(R.id.light_card);
            light_name = (TextView)itemView.findViewById(R.id.light_name);
            light_address = (TextView)itemView.findViewById(R.id.light_address);
            light_seekbar = (SeekBar)itemView.findViewById(R.id.light_level_seekbar);
            light_switch = (Switch)itemView.findViewById(R.id.light_state_switch);
        }
    }

    @Override
    public int getItemCount() {
        return this.light_dataset_.size();
    }

    @Override
    public LightViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.light_card, viewGroup, false);
        return new LightViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LightViewHolder view_holder, int i) {

        // display light name and address
        view_holder.light_name.setText(light_dataset_.getByIndex(i).getName());
        view_holder.light_address.setText(light_dataset_.getByIndex(i).getAddress());

        // display current state
        LightState state = light_dataset_.getByIndex(i).getState();
        view_holder.light_seekbar.setMax(100);
        view_holder.light_seekbar.setProgress(state.getStateLevel());
        view_holder.light_switch.setChecked(state.getStatePower());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
