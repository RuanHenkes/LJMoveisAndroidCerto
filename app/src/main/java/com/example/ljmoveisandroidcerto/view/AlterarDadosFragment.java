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

import com.example.ljmoveisandroidcerto.R;
import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.databinding.FragmentAlterarDadosBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;

import modelDominio.Cliente;

public class AlterarDadosFragment extends Fragment {

    FragmentAlterarDadosBinding binding;
    InformacoesViewModel informacoesViewModel;
    Cliente cliente;
    Boolean resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentAlterarDadosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);

        binding.bAlterarClienteSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                            if (!binding.etCadastroClienteCelular.getText().toString().equals("")) {
                                if (!binding.etCadastroClienteEmail.getText().toString().equals("")) {
                                    if (!binding.etCadastroClienteSenha.getText().toString().equals("")) {
                                        if (!binding.etCadastroClienteEstado.getText().toString().equals("")) {
                                            if (!binding.etCadastroClienteCidade.getText().toString().equals("")) {
                                                if (!binding.etCadastroClienteBairro.getText().toString().equals("")) {
                                                    if (!binding.etCadastroClienteCep.getText().toString().equals("")) {
                                                        if (!binding.etCadastroClienteNumeroEndereco.getText().toString().equals("")) {
                                                            //obtendo as informacoes
                                                            long cpf = Long.parseLong(String.valueOf(informacoesViewModel.getUsuarioLogado().getCpf()));
                                                            String nome = informacoesViewModel.getUsuarioLogado().getNome();
                                                            String sobrenome = informacoesViewModel.getUsuarioLogado().getSobrenome();
                                                            String celular = binding.etCadastroClienteCelular.getText().toString();
                                                            String email = binding.etCadastroClienteEmail.getText().toString();
                                                            String senha = binding.etCadastroClienteSenha.getText().toString();
                                                            String estado = binding.etCadastroClienteEstado.getText().toString();
                                                            String cidade = binding.etCadastroClienteCidade.getText().toString();
                                                            String bairro = binding.etCadastroClienteBairro.getText().toString();
                                                            int cep = Integer.parseInt(binding.etCadastroClienteCep.getText().toString());
                                                            int numeroendereco = Integer.parseInt(binding.etCadastroClienteNumeroEndereco.getText().toString());

                                                            //instanciando o cliente
                                                            cliente = new Cliente(cpf, nome, sobrenome, celular, email, senha, estado, cidade, bairro, cep, numeroendereco);
                                                            // criando a thread para cadastro do cliente
                                                            Thread thread = new Thread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    // instanciando o controller para cadastro do cliente
                                                                    ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                                                                    resultado = conexaoController.clienteAlterar(cliente);
                                                                    // sincronizando as threads para agir na tela
                                                                    getActivity().runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            if (resultado == true) {
                                                                                Toast.makeText(getContext(), "Dados Alterados com sucesso.", Toast.LENGTH_SHORT).show();
                                                                            } else {
                                                                                Toast.makeText(getContext(), "Erro: Dados não alterados.", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        }
                                                                    });
                                                                }
                                                            });
                                                            thread.start();

                                                            //----perguntar pro sor se devo colocar aqui a chamada de tela pra login

                                                        } else {
                                                            binding.etCadastroClienteNumeroEndereco.setError("Erro: informe o número do endereço.");
                                                            binding.etCadastroClienteNumeroEndereco.requestFocus();
                                                        }
                                                    } else {
                                                        binding.etCadastroClienteCep.setError("Erro: informe o CEP.");
                                                        binding.etCadastroClienteCep.requestFocus();
                                                    }

                                                } else {
                                                    binding.etCadastroClienteBairro.setError("Erro: informe o bairro.");
                                                    binding.etCadastroClienteBairro.requestFocus();
                                                }

                                            } else {
                                                binding.etCadastroClienteCidade.setError("Erro: informe a cidade.");
                                                binding.etCadastroClienteCidade.requestFocus();
                                            }
                                        } else {

                                            binding.etCadastroClienteEstado.setError("Erro: informe o estado.");
                                            binding.etCadastroClienteEstado.requestFocus();
                                        }
                                    } else {
                                        binding.etCadastroClienteSenha.setError("Erro: informe a senha.");
                                        binding.etCadastroClienteSenha.requestFocus();
                                    }
                                } else {
                                    binding.etCadastroClienteEmail.setError("Erro: informe o Email.");
                                    binding.etCadastroClienteEmail.requestFocus();
                                }
                            } else {
                                binding.etCadastroClienteCelular.setError("Erro: informe o celular.");
                                binding.etCadastroClienteCelular.requestFocus();
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
