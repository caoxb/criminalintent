package com.bignerdranch.android.criminalintent.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.bignerdranch.android.criminalintent.R;
import com.bignerdranch.android.criminalintent.bean.Crime;
import com.bignerdranch.android.criminalintent.data.CrimeLab;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckbox;

    public CrimeFragment() {
    }
    //这个也是留给别人访问的入口，可以传一些参数
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        //有参数的保存，那应该有参数的取出
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mCrime = new Crime();
        //获取设置的参数，在这里罪犯的获取方式发生了变化。
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        //显示保存的时间数据
        mDateButton.setText(mCrime.getDate().toString());
        //留出可以设置的接口
        mDateButton.setEnabled(false);

        mSolvedCheckbox = v.findViewById(R.id.crime_solved);
        //显示现有确认状态
        mSolvedCheckbox.setChecked(mCrime.isSolved());
        //留出对外审核接口
        mSolvedCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mCrime.setSolved(isChecked);
        });
        return v;
    }

}
