# Week4Daily4
Coding
Create a multi pane app (add two fragment to the activity in a sequence)
Fragment one should have a list of celebrity names.
Fragment two should have the detail of the celebrity selected.


-Fragment two should be updated on each list item clicked
-Celebrity detail should have at least a picture of the celebrity (you can save all the images in the drawable folder), short description. But be creative to add more if you like.
-Use different attributes to make the design better.
-Do not use EventBus
1. 

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <FrameLayout
        android:id="@+id/fgCelebrityList"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/fgCelebrityDetails">
    </FrameLayout>

    <FrameLayout
        android:id="@+id/fgCelebrityDetails"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toBottomOf="@id/fgCelebrityList"
        app:layout_constraintBottom_toBottomOf="parent">
    </FrameLayout>


</androidx.constraintlayout.widget.ConstraintLayout>

<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".CelebrityListFragment">

    <!-- TODO: Update blank fragment layout -->
    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#EEA423"

        style="@style/TextviewText" />

</FrameLayout>

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#40A0C5"
    tools:context=".CelebrityDetailsFragment">

    <!-- TODO: Update blank fragment layout -->
    <ImageView
        android:id="@+id/imgCelebPic"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:layout_gravity="center_horizontal"

        />

    <TextView
        android:id="@+id/tvName"
        android:textAlignment="center"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/celebrity_name"
        style="@style/TextviewText"/>
    <TextView
        android:id="@+id/tvProfession"
        android:textAlignment="center"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/celebrity_profession"
        style="@style/TextviewText"/>
    <TextView
        android:id="@+id/tvAward"
        android:textAlignment="center"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/celebrity_accolades"
        style="@style/TextviewText"/>

</LinearLayout>

package com.example.week4daily4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CelebrityListFragment.OnFragmentInteractionListener, CelebrityDetailsFragment.OnFragmentInteractionListener {
    FragmentManager fragmentManager;
    CelebrityListFragment celebrityListFragment;
    CelebrityDetailsFragment celebrityDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        celebrityListFragment = CelebrityListFragment.newInstance("","");
        fragmentManager.beginTransaction().add(R.id.fgCelebrityList, celebrityListFragment).commit();

        celebrityDetailsFragment = CelebrityDetailsFragment.newInstance("", "");
        fragmentManager.beginTransaction().add(R.id.fgCelebrityDetails,celebrityDetailsFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendToActivity(String name, String job, String award, int position) {
        celebrityDetailsFragment.setPassedInput(name, job, award, position);
    }
}


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


package com.example.week4daily4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CelebrityDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CelebrityDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CelebrityDetailsFragment extends Fragment {

    ImageView imgCelebrityPic;
    TextView tvCelebrityName;
    TextView tvCelebrityProfession;
    TextView tvCelebrityAcolades;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CelebrityDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CelebrityDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CelebrityDetailsFragment newInstance(String param1, String param2) {
        CelebrityDetailsFragment fragment = new CelebrityDetailsFragment();
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
        return inflater.inflate(R.layout.fragment_celebrity_details, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgCelebrityPic = view.findViewById(R.id.imgCelebPic);
        tvCelebrityName = view.findViewById(R.id.tvName);
        tvCelebrityProfession = view.findViewById(R.id.tvProfession);
        tvCelebrityAcolades = view.findViewById(R.id.tvAward);
    }
    public void setPassedInput(String passedName, String passedProfession, String passedAwards, int passedPic){
        Glide.with(imgCelebrityPic).load(passedPic).into(imgCelebrityPic);
        tvCelebrityName.setText(passedName);
        tvCelebrityProfession.setText(passedProfession);
        tvCelebrityAcolades.setText(passedAwards);
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
        void onFragmentInteraction(Uri uri);
    }
}

![week4daily4screen1-1](https://user-images.githubusercontent.com/51377336/60611172-abd30f00-9d93-11e9-9c8d-d9b7857b221e.png)


