package com.knurtz.lightcontrol;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Fragment for the lights list
 */
public class LightsFragment extends Fragment {

    // for interaction with parent activity
    private OnFragmentInteractionListener mListener;

    // UI elements of this fragment
    private RecyclerView lights_rec_view_;
    private SwipeRefreshLayout swipe_refresh;

    // adapter for lights recycler view
    private RecyclerView.Adapter lights_view_adapter_;

    // access application data through ViewModel
    LightControlModel mViewModel;


    /**
     * Callback for swipe down to refresh action.
     */
    private SwipeRefreshLayout.OnRefreshListener swipe_refresh_listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // Refresh items
            refreshLights();
        }
    };

    /**
     * Goes through list of lights and request their status
     */
    private void refreshLights() {

        // dummy delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(false);
                Toast.makeText(getActivity(), getString(R.string.dummy_load_finished), Toast.LENGTH_SHORT).show();
            }
        }, 2000);

    }

    public LightsFragment() {
        // Empty public constructor is required
    }

    /**
     * Creates new instance of the LightsFragment.
     *
     * @return A new instance of LightsFragment.
     */
    public static LightsFragment newInstance() {
        return new LightsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(LightControlModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lights, container, false);    // Inflate the layout for this fragment
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // attach listener for pull-down refresh
        swipe_refresh = getActivity().findViewById(R.id.lights_swipe_refresh);
        swipe_refresh.setOnRefreshListener(swipe_refresh_listener);
        swipe_refresh.setDistanceToTriggerSync(700);

        // set up the main recycler view for lights
        lights_view_adapter_ = new LightsAdapter(mViewModel.light_dataset_);        // create adapter for the main recycler view
        lights_rec_view_ = getActivity().findViewById(R.id.lights_rec_view);
        lights_rec_view_.setHasFixedSize(true);                                     // improves performance
        lights_rec_view_.setLayoutManager(new LinearLayoutManager(getActivity()));
        lights_rec_view_.setAdapter(lights_view_adapter_);
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
     * we call mListener.onFragmentInteraction(), which is implemented inside the activity.
     */
    public interface OnFragmentInteractionListener {
        void updateFromLightsFragment();
    }
}
