package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ljmoveisandroidcerto.R;
import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.databinding.FragmentLoginBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;
import modelDominio.Usuario;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;


    InformacoesViewModel informacoesViewModel;
    boolean resultado;

    Usuario usuarioLogado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // obtendo o viewModel
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);
        // criando a thread para conexão com o servidor Socket
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // criando a conexão com o servidor
                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                resultado = conexaoController.criaConexaoServidor("192.168.4.138", 12345);//trocar a porta
                // sincronizando as threads para agir sobre a tela
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (resultado == true) {
                            Toast.makeText(getContext(), "Conexão estabelecida com sucesso.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Erro: conexão com o servidor não efetuada.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }); thread.start();


        // programando o clique nos botões
        binding.bLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // verificando se o usuário informou os dados
                if (!binding.etLoginUsuario.getText().toString().equals("")) {
                    if (!binding.etLoginSenha.getText().toString().equals("")) {
                        // obtendo as informações
                        String usuario = binding.etLoginUsuario.getText().toString();
                        String senha = binding.etLoginSenha.getText().toString();
                        // instanciando o usuário que está se logando
                        usuarioLogado = new Usuario(usuario, senha);
                        // criando a thread para autenticar o usuário
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // efetuando a autenticação do usuário
                                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                                usuarioLogado = conexaoController.Logar(usuarioLogado);
                                // sincronizando as threads para agir sobre a tela
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (usuarioLogado != null) {
                                            // salvando o usuario logado
                                            informacoesViewModel.inicializaUsuarioLogado(usuarioLogado);
                                            // chamando a próxima tela

                                            //mudar a acao do login e menu e cadastro aqui
                                            Navigation.findNavController(view).navigate(R.id.acao_Login_MenuCliente);
                                        } else {
                                            Toast.makeText(getContext(), "Erro: usuário e/ou senha inválidos.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                        thread.start();
                    } else {
                        binding.etLoginSenha.setError("Erro: informe a senha.");
                        binding.etLoginSenha.requestFocus();
                    }
                } else {
                    binding.etLoginUsuario.setError("Erro: informe o usuário.");
                    binding.etLoginUsuario.requestFocus();
                }
            }
        });

        binding.bLoginCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });

        // programando o clique nos botões para esqueci a senha
        binding.bLoginEsqueciASenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_esqueciSenhaFragment);
            }
        });

        // programando o clique nos botões
        binding.bLoginCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.acao_Login_CadastroCliente);
            }
        });
    }

    public void limpaCampos() {
        binding.etLoginUsuario.setText("");
        binding.etLoginSenha.setText("");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}