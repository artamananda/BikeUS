package unsri.bikeus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBicycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBicycleFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button myButton;
    SqliteHelper sqliteHelper;
    EditText editText;

    public AddBicycleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBicycleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBicycleFragment newInstance(String param1, String param2) {
        AddBicycleFragment fragment = new AddBicycleFragment();
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
        View myView = inflater.inflate(R.layout.fragment_add_bicycle, container, false);
        editText = (EditText) getActivity().findViewById(R.id.editTextNumber);
        myButton = (Button) myView.findViewById(R.id.buttonRentBike);
        myButton.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View view) {
        editText = (EditText) getActivity().findViewById(R.id.editTextNumber);
        String number = editText.getText().toString();
        if(Bike.authenticate(number) == true){
            User.setRentStatus(true);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String time = formatter.format(date);
            sqliteHelper = new SqliteHelper(getContext());
            sqliteHelper.addBike(number, time);
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else{
            Toast.makeText(getContext(), "Bicycle not found or has been rent", Toast.LENGTH_SHORT).show();
        }
    }
}