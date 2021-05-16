package com.example.learnandearnv01.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.learnandearnv01.R;
import com.example.learnandearnv01.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Log.d("ГЕТ ВЬЮ", getView().toString());
        //View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView proName = (TextView) root.findViewById(R.id.tvUserName);
        TextView proMail = (TextView) root.findViewById(R.id.tvUserEmail);
        TextView proType = (TextView) root.findViewById(R.id.tvUserType);
        TextView proPhone = (TextView) root.findViewById(R.id.tvUserPhone);
        if(getActivity().getIntent().getStringExtra("PROFILE_TYPE").equals("0")){
            proType.setText("Нет");
        }
        else{
            proType.setText("Да");
        }
        proPhone.setText(getActivity().getIntent().getStringExtra("PROFILE_PHONE"));
        proName.setText(getActivity().getIntent().getStringExtra("PROFILE_NAME"));
        proMail.setText(getActivity().getIntent().getStringExtra("PROFILE_MAIL"));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}