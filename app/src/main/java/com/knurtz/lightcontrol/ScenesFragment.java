package com.knurtz.lightcontrol;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Fragment for the scenes list
 */
public class ScenesFragment extends Fragment {

    // for interaction with parent activity
    private OnFragmentInteractionListener mListener;

    // UI elements of this fragment
    private RecyclerView scenes_rec_view_;

    // adapter for lights recycler view
    private RecyclerView.Adapter scenes_view_adapter_;

    // access application data through ViewModel
    LightControlModel mViewModel;


    public ScenesFragment() {
        // Empty public constructor is required
    }

    /**
     * Creates new instance of the ScenesFragment.
     *
     * @return A new instance of ScenesFragment.
     */
    public static ScenesFragment newInstance() {
        return new ScenesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(LightControlModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scenes, container, false);    // Inflate the layout for this fragment
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // set up the main recycler view for lights
        scenes_view_adapter_ = new ScenesAdapter(mViewModel.scene_dataset_);        // create adapter for the main recycler view
        scenes_rec_view_ = getActivity().findViewById(R.id.scenes_rec_view);
        scenes_rec_view_.setHasFixedSize(true);                                     // improves performance
        scenes_rec_view_.setLayoutManager(new LinearLayoutManager(getActivity()));
        scenes_rec_view_.setAdapter(scenes_view_adapter_);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) mListener = (OnFragmentInteractionListener) context;
        else throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * If stuff inside this fragments happens, that the main activity should react to,
     * we call mListener.updateFromScenesFragment(), which is implemented inside the activity.
     */
    public interface OnFragmentInteractionListener {
        void updateFromScenesFragment();
    }
}
