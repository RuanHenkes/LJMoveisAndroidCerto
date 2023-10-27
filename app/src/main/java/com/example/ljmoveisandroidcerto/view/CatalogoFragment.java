package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ljmoveisandroidcerto.Adapter.CatalogoAdapter;
import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.databinding.FragmentCatalogoBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;

import java.util.ArrayList;

import modelDominio.Catalogo;


public class CatalogoFragment extends Fragment {

    FragmentCatalogoBinding binding;
    InformacoesViewModel informacoesViewModel;
    ArrayList<Catalogo> listaCatalogos;
    Catalogo catalogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_catalogo, container, false);
        binding = FragmentCatalogoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                listaCatalogos = conexaoController.catalogoLista();

                if (listaCatalogos != null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            atualizaListagem();

                        }
                    });
                }

            }
        });
        thread.start();



    }

    public void atualizaListagem() {
        CatalogoAdapter catalogoAdapter = new CatalogoAdapter(listaCatalogos, trataCliqueItem);
        binding.rvVisualizaCatalogos.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.rvVisualizaCatalogos.setItemAnimator(new DefaultItemAnimator());
        binding.rvVisualizaCatalogos.setAdapter(catalogoAdapter);
    }

    CatalogoAdapter.CatalogoOnClickListener trataCliqueItem = new CatalogoAdapter.CatalogoOnClickListener() {
        @Override
        public void onClickCatalogo(View view, int position, Catalogo catalogo) {

        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}