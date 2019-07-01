package com.example.week4daily4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CelebrityListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CelebrityListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CelebrityListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String [] celebs = {"Alicia Augello Cook", "Denzel Hayes Washington Jr.", "Barack Obama",
            "Curtis James Jackson III", "Adam Levine", "Michael Jeffrey Jordan", "Kerry Marisa Washington"};

    String [] profession = {"Singer and SongWriter", "Actor, Director, Producer", "Former US President",
            "Rapper and Actor, Entrepreneur", "Singer", "Former NBA Player, Team Owner", "Actress"};

    String [] accolade = {"Grammy Winner, Pianist, Mother of Two", "Golden Globe, Tony, Academy Award, Best Actor",
            "First African American US President", "G-Unit Record Label, Grammy, BET Award, Father of Two",
            "Maroon5, Grammy, World Music Award, Father of Two", "NBA Hall of Fame, Air Jordan, Father of Three",
            "Grammy and Emmy Nominee, Activist, Mother of Three"};

    int [] celebPic = {R.drawable.aliciakeyspic, R.drawable.denzelwashington, R.drawable.barackobama,
            R.drawable.curtisjackson, R.drawable.adamlevine, R.drawable.michaeljordan, R.drawable.kerrywashington };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CelebrityListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CelebrityListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CelebrityListFragment newInstance(String param1, String param2) {
        CelebrityListFragment fragment = new CelebrityListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_celebrity_list, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.celebrities, android.R.layout.simple_expandable_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mListener.sendToActivity(celebs[i], profession[i], accolade[i], celebPic[i]);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void sendToActivity(String name, String job, String award, int  position );
    }

}
