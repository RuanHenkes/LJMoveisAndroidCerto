package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljmoveisandroidcerto.databinding.FragmentCatalogoBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;


public class CatalogoFragment extends Fragment {

    FragmentCatalogoBinding binding;
    InformacoesViewModel informacoesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_catalogo, container, false);
        binding = FragmentCatalogoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}