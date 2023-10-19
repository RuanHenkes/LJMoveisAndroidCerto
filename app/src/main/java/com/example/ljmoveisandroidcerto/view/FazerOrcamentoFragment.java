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
import com.example.ljmoveisandroidcerto.databinding.FragmentFazerOrcamentoBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;
import com.example.ljmoveisplanejadosnovooo.R;
import com.example.ljmoveisplanejadosnovooo.databinding.FragmentFazerUmOrcamentoBinding;
import com.example.ljmoveisplanejadosnovooo.view.controller.ConexaoController;
import com.example.ljmoveisplanejadosnovooo.view.viewModel.InformacoesViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelDominio.Cliente;
import modelDominio.Pedido;

public class FazerOrcamentoFragment extends Fragment {

    FragmentFazerOrcamentoBinding binding;
    InformacoesViewModel informacoesViewModel;

    ArrayList<Pedido> listaPedido;

    Pedido pedido;

    Boolean resultado;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fazer_um_orcamento, container, false);
        binding = FragmentFazerOrcamentoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // obtendo a instância do viewModel
        informacoesViewModel = new ViewModelProvider(getActivity()).get(InformacoesViewModel.class);
        // criando a thread para obtenção da lista de pedidos
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // obtendo a lista de marcas
                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                listaPedido = conexaoController.pedidoLista();
                // verificando as texturas recebidas
                if (listaPedido != null) {
                    // sincronizando as threads para carregar o spinner
                    getActivity().runOnUiThread(new Runnable() {
                        @Override

                        //perguntar pro sor aqui devo carregat  textura literal?
                        public void run() {

                            carregaSpinnerT(listaMarcas);
                        }
                    });
                }
            }
        });
        thread.start();

        // programando o clique nos botões
        binding.bFazerOrcamentoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // verificando se o usuário informou os dados
                if (!binding.etFazerOrcamentoNomeDoMovel.getText().toString().equals("")) {
                    if (!binding.etFazerOrcamentoQualCor.getText().toString().equals("")) {
                        if (binding.spFazerOrcamentoTextura.getSelectedItemPosition() > 0) {
                            if (!binding.etFazerOrcamentoValor.getText().toString().equals("")) {
                                if (binding.spFazerOrcamentoTextura.getSelectedItemPosition() > 0) {
                                    if (!binding.etFazerOrcamentoConsideracao.getText().toString().equals("")) {
                                        // obtendo as informações

                                        String nomeMovel = binding.etFazerOrcamentoNomeDoMovel.getText().toString();
                                        String qualCor = binding.etFazerOrcamentoQualCor.getText().toString();
                                        int textura = binding.spFazerOrcamentoTextura.getSelectedItemPosition();
                                        float preco = Float.parseFloat(binding.etFazerOrcamentoValor.getText().toString());

                                        //ambiente aqui spinner

                                        String consideracao = binding.etFazerOrcamentoConsideracao.getText().toString();


                                        // instanciando o pedido
                                        pedido = new Pedido(nomeMovel, qualCor, textura, preco, faltaa, consideracao, null);

                                        // criando a thread para fazer um orcamento
                                        Thread thread = new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                // realizando fazer o orcamento
                                                ConexaoController conexaoController = new ConexaoController(informacoesViewModel);
                                                resultado = conexaoController.pedidoInserir(pedido);
                                                // sincronizando as threads para tratar o resultado
                                                getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if (resultado == true) {
                                                            Toast.makeText(getContext(), "Pedido feito com sucesso.", Toast.LENGTH_SHORT).show();
                                                            limpaCampos();
                                                        } else {
                                                            Toast.makeText(getContext(), "Erro: pedido não cadastrado.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                        thread.start();

                                    } else {
                                        binding.etFazerOrcamentoNomeDoMovel.setError("Erro: informe o nome do movel.");
                                        binding.etFazerOrcamentoNomeDoMovel.requestFocus();
                                    }
                                } else {
                                    binding.etFazerOrcamentoQualCor.setError("Erro: informe a cor do movel.");
                                    binding.etFazerOrcamentoQualCor.requestFocus();
                                }
                            } else {
                                Toast.makeText(getContext(), "Erro: informe a textura.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            binding.etFazerOrcamentoValor.setError("Erro: informe o modelo.");
                            binding.etFazerOrcamentoValor.requestFocus();
                        }
                    } else {
                        Toast.makeText(getContext(), "Erro: informe o ambiente.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    binding.etFazerOrcamentoConsideracao.setError("Erro: informe a consideração.");
                    binding.etFazerOrcamentoConsideracao.requestFocus();
                }
            }
        });

        binding.bFazerOrcamentoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }


    public void limpaCampos() {
        binding.etFazerOrcamentoNomeDoMovel.setText("");
        binding.etFazerOrcamentoQualCor.setText("");
        binding.spFazerOrcamentoTextura.setSelection(0);
        binding.etFazerOrcamentoValor.setText("");
        binding.spFazerOrcamentoAmbiente.setSelection(0);
        binding.etFazerOrcamentoConsideracao.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}