package unsri.bikeus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BicycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BicycleFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button myButton;
    SqliteHelper sqliteHelper;

    public BicycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BicycleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BicycleFragment newInstance(String param1, String param2) {
        BicycleFragment fragment = new BicycleFragment();
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
        sqliteHelper = new SqliteHelper(this.getContext());
        View myView = inflater.inflate(R.layout.fragment_bicycle, container, false);
        TextView tv2 = (TextView) myView.findViewById(R.id.textViewTime);
        String txt = sqliteHelper.getKeyTimeStart();
        if(txt.equals("0 jam 0 menit")){
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
            return myView;
        }
        try {
            tv2.setText(diffTime(txt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView tv = (TextView) myView.findViewById(R.id.textViewNumber);
        tv.setText(sqliteHelper.getKeyBikeid());
        myButton = (Button) myView.findViewById(R.id.buttonReturnBike);
        myButton.setOnClickListener(this);
        return myView;
    }

    public static String diffTime(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date1 = formatter.parse(time);
        Date date2 = new Date();
        long diff = date1.getTime() + (2000 * 60 * 60) - date2.getTime();
        double diffInHours = diff / ((double) 1000 * 60 * 60);
        int hours = (int)diffInHours;
        int minutes = (int)((diffInHours - (int)diffInHours) * 60);
        return hours + " jam " + minutes + " menit ";
    }


    @Override
    public void onClick(View view) {
        sqliteHelper.delBike();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}