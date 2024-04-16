package com.example.employeeproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private TextView emplIdText_View;
    private TextView emplNameText_View;
    private TextView emplSalaryText_View;
    private TextView Taxt_View;
    private Button NextButton;
    private Button PrevButton;
    private Button CalculateTax;

    public static int getCurrentIndex() {
        return currentIndex;
    }

    private static int currentIndex=0;
    public static String TAG="Employee Project";
    public static String KEY_INDEX = "index";
    public static ArrayList<Employee> all_employee = new ArrayList<>();
    Employee employee1 = new Employee(1,"Rafael",24);
    Employee employee2 = new Employee(2,"Eduardo",14);
    Employee employee3 = new Employee(3,"James",10);


    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        all_employee.add(employee1);
        all_employee.add(employee2);
        all_employee.add(employee3);

        if(savedInstanceState!=null)
        {
            currentIndex=savedInstanceState.getInt(KEY_INDEX);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        //Get view of empl_id
        emplIdText_View = (TextView) v.findViewById(R.id.emp_id_text_view);
        emplIdText_View.setText(Integer.toString(all_employee.get(currentIndex).getEmp_id()));

        //Get view of empl_name
        emplNameText_View = (TextView) v.findViewById(R.id.emp_name_text_view);
        emplNameText_View.setText(all_employee.get(currentIndex).getEmp_name());

        //Get view of empl_salary
        emplSalaryText_View = (TextView) v.findViewById(R.id.emp_salary_text_view);
        emplSalaryText_View.setText(Double.toString(all_employee.get(currentIndex).getEmp_salary()));

        //Get view of tax
        Taxt_View= (TextView) v.findViewById(R.id.tax_text_view);

        //Get view Next Button
        NextButton = (Button) v.findViewById(R.id.nextEmployeeButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex= (currentIndex+1)%all_employee.size();
                emplIdText_View.setText(Integer.toString(all_employee.get(currentIndex).getEmp_id()));
                emplNameText_View.setText(all_employee.get(currentIndex).getEmp_name());
                emplSalaryText_View.setText(Double.toString(all_employee.get(currentIndex).getEmp_salary()));
            }
        });

        //Get the view of prev button
        PrevButton=(Button) v.findViewById(R.id.prevEmployeeButton);
        PrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex - 1 + all_employee.size()) % all_employee.size();
                emplIdText_View.setText(Integer.toString(all_employee.get(currentIndex).getEmp_id()));
                emplNameText_View.setText(all_employee.get(currentIndex).getEmp_name());
                emplSalaryText_View.setText(Double.toString(all_employee.get(currentIndex).getEmp_salary()));
            }
        });

        //Get the view of calculate button
        CalculateTax=(Button) v.findViewById(R.id.employeeTaxButton);
        CalculateTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Taxt_View.setText("Total tax: "+Double.toString(all_employee.get(currentIndex).calculateTotalTax()));
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState: called");
        savedInstanceState.putInt(KEY_INDEX,currentIndex);
    }
}