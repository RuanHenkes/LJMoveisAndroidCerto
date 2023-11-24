package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.controller.Hash;
import com.example.ljmoveisandroidcerto.databinding.FragmentAlterarSenhaBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;

import modelDominio.Cliente;

public class AlterarSenhaFragment extends Fragment {

    FragmentAlterarSenhaBinding binding;
    InformacoesViewModel informacoesViewModel;
    boolean resultado;
    Cliente cliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAlterarSenhaBinding.inflate(inflater,container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);

        binding.bAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Trocar para pegar senha Criptografada
                if (binding.etAlterarSenha.getText().toString().equals(informacoesViewModel.getUsuarioLogado().getSenha())){
                    if (binding.etNovaSenha.getText().toString().equals(binding.etNovaSenhaConfirmacao.toString())){
                        String email = informacoesViewModel.getUsuarioLogado().getEmail();
                        String novaSenha = binding.etNovaSenha.getText().toString();
                        String novaSenhaCriptografada = Hash.hashPassword(novaSenha);
                        cliente = new Cliente(email,novaSenhaCriptografada);

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                                resultado = conexaoController.clienteAlterar(cliente);

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (resultado == true) {
                                            Toast.makeText(getContext(), "Senha alterada com sucesso.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getContext(), "Erro: senha não alterada.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                        thread.start();

                    }else{
                        Toast.makeText(getContext(), "Senhas Diferem", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(getContext(), "Senha Errada", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}