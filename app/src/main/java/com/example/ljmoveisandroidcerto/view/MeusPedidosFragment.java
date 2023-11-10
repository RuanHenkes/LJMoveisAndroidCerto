package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ljmoveisandroidcerto.Adapter.PedidosAdapter;
import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.databinding.FragmentMeusPedidosBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;

import java.util.ArrayList;

import modelDominio.Pedido;

public class MeusPedidosFragment extends Fragment {

    FragmentMeusPedidosBinding binding;
    InformacoesViewModel informacoesViewModel;
    PedidosAdapter pedidosAdapter;
    ArrayList<Pedido> listaPedidos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_meus_pedidos, container, false);
        binding = FragmentMeusPedidosBinding.inflate(inflater, container, false);
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
                listaPedidos = conexaoController.pedidoLista();

                if (listaPedidos != null){
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
        pedidosAdapter = new PedidosAdapter(listaPedidos, trataCliqueItem);
        binding.rvVisualizaPedido.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvVisualizaPedido.setItemAnimator(new DefaultItemAnimator());
        binding.rvVisualizaPedido.setAdapter(pedidosAdapter);
    }

    PedidosAdapter.PedidoOnClickListener trataCliqueItem = new PedidosAdapter.PedidoOnClickListener() {

        public void onClickPedido(View view, int position, Pedido pedido) {
            Toast.makeText(getContext(), "Pedido: " + pedido.getNomePedido() + " \nCor: "+pedido.getCor()  + " \nTextura: "+ pedido.getTextura()+ "\nValor: "+pedido.getValor(), Toast.LENGTH_SHORT).show();

        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}