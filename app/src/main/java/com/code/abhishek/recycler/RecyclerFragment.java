package com.code.abhishek.recycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {  //Fragment
    private RecyclerView recycle;
    private StudentAdapter adpt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   // private StudentAdapter adapter;
    //private OnFragmentInteractionListener mListener;

//    public RecyclerFragment() {
        // Required empty public constructor
  //  }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
   /* public static RecyclerFragment newInstance(String param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
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
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recycler, container, false);
        recycle = (RecyclerView) layout.findViewById(R.id.recycler_view);
    //    tool=(Toolbar)layout.findViewById(R.id.toolbar);
        //((ActionBarActivity)getActivity()).setSupportActionBar(tool);
        //((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle("Recycler App");
        //adapter = new StudentAdapter(getActivity(), getData());
        //recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return layout;
    }

   @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {

        //StudentLab sLab = StudentLab.get(getActivity());
        //List<Student> d1 = sLab.getData();
         DatabaseHelper hh=new DatabaseHelper(getActivity());
        List<Student> d1=hh.getDataFromDB();
        if (adpt == null) {
            adpt = new StudentAdapter(d1);
            recycle.setAdapter(adpt);
        } else {
            adpt.notifyDataSetChanged();
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
*/

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
  /*  public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    class Helper extends RecyclerView.ViewHolder implements View.OnClickListener  {
        public TextView tv;
        public ImageView iv;
        private Student mStudent;
        public Helper(View item) {
            super(item);
            tv = (TextView) item.findViewById(R.id.Text);
            iv = (ImageView) item.findViewById(R.id.Icon);
            tv.setOnClickListener(this);
            //tv = (TextView)item;
            //iv=(ImageView)item;
        }
        public void bindStudent(Student stt) {
            mStudent = stt;
            tv.setText(mStudent.getTitle());
            iv.setId(mStudent.getId());
        }
        @Override
        public void onClick(View v) {
            Intent intent = StudentPagerActivity.newIntent(getActivity(), mStudent.getsId());
            startActivity(intent);
        }
    }

    public class StudentAdapter extends RecyclerView.Adapter<Helper> {
        public List<Student> data;

        public StudentAdapter(List<Student> d1) {
            data = d1;
        }


        public Helper onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            View view = layoutInflater.inflate(R.layout.cust_row, parent, false);
            return new Helper(view);
        }

        @Override
        public void onBindViewHolder(Helper holder, int position) {
            Student stt = data.get(position);
            holder.bindStudent(stt);
        }
        @Override
        public int getItemCount() {
            return data.size();
        }


    }

}