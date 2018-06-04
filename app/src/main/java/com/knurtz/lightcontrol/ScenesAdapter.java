package com.knurtz.lightcontrol;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScenesAdapter extends RecyclerView.Adapter<ScenesAdapter.SceneViewHolder> {

    private SceneContainer scene_dataset_;

    ScenesAdapter(SceneContainer new_dataset){
        this.scene_dataset_ = new_dataset;
    }

    // class for views used by this adapter
    public static class SceneViewHolder extends RecyclerView.ViewHolder {
        CardView scene_card;

        TextView scene_name;
        TextView scene_contents;

        SceneViewHolder(View itemView) {
            super(itemView);
            scene_card = (CardView)itemView.findViewById(R.id.scene_card);
            scene_name = (TextView)itemView.findViewById(R.id.scene_name);
            scene_contents = (TextView)itemView.findViewById(R.id.scene_contents);
        }
    }

    @Override
    public int getItemCount() {
        return this.scene_dataset_.size();
    }

    @Override
    public SceneViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scene_card, viewGroup, false);
        return new SceneViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SceneViewHolder view_holder, int i) {
        view_holder.scene_name.setText(scene_dataset_.getByIndex(i).getName());
        view_holder.scene_contents.setText(scene_dataset_.getByIndex(i).getDescription());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
