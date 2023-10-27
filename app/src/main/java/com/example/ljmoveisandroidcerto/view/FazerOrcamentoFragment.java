package com.example.ljmoveisandroidcerto.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ljmoveisandroidcerto.controller.ConexaoController;
import com.example.ljmoveisandroidcerto.databinding.FragmentFazerOrcamentoBinding;
import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelDominio.Ambiente;
import modelDominio.Cliente;
import modelDominio.Pedido;
import modelDominio.Usuario;

public class FazerOrcamentoFragment extends Fragment {

    FragmentFazerOrcamentoBinding binding;
    InformacoesViewModel informacoesViewModel;

    ArrayList<Pedido> listaPedido;
    ArrayList<Ambiente>  listaAmbientes;
    ConexaoController conexaoController;

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
                listaAmbientes = conexaoController.ambienteLista();

                // verificando as texturas recebidas
                if (listaPedido != null) {

                    // sincronizando as threads para carregar o spinner
                    getActivity().runOnUiThread(new Runnable() {
                        @Override

                        //perguntar pro sor aqui devo carrega textura literal?
                        public void run() {

                            carregaSpinnerAmbientes(listaAmbientes);
                            carregaSpinnerTextura(listaPedido);
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
                        if (binding.spFazerOrcamentoTextura.getSelectedItemPosition() >= 0) {
//                            Mudar o >=



                            if (!binding.etFazerOrcamentoValor.getText().toString().equals("")) {
                                if (binding.spFazerOrcamentoAmbiente.getSelectedItemPosition() > 0) {
                                    if (!binding.etFazerOrcamentoConsideracao.getText().toString().equals("")) {
                                        // obtendo as informações

                                        String nomeMovel = binding.etFazerOrcamentoNomeDoMovel.getText().toString();
                                        String qualCor = binding.etFazerOrcamentoQualCor.getText().toString();
                                        int textura = binding.spFazerOrcamentoTextura.getSelectedItemPosition();
                                        float preco = Float.parseFloat(binding.etFazerOrcamentoValor.getText().toString());

                                        //ambiente aqui spinner
                                        Ambiente ambiente = listaAmbientes.get(binding.spFazerOrcamentoAmbiente.getSelectedItemPosition() - 1);
                                        String consideracao = binding.etFazerOrcamentoConsideracao.getText().toString();

                                        Usuario usuarioLogado = informacoesViewModel.getUsuarioLogado();
                                        // instanciando o pedido
                                        Pedido pedido = new Pedido(nomeMovel, qualCor, textura, preco, ambiente.getidAmbiente(), consideracao, usuarioLogado);

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
                                        binding.etFazerOrcamentoConsideracao.setError("Erro: informe se tens mais alguma consideracao.");
                                        binding.etFazerOrcamentoConsideracao.requestFocus();}
                                    }
                                } else {
                                    Toast.makeText(getContext(), "Erro: informe a textura.", Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                binding.etFazerOrcamentoValor.setError("Erro: informe o valor que deseja gastar.");
                                binding.etFazerOrcamentoValor.requestFocus();}
                        } else {
                            Toast.makeText(getContext(), "Erro: informe o Ambiente.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        binding.etFazerOrcamentoQualCor.setError("Erro: informe a cor do movel.");
                        binding.etFazerOrcamentoQualCor.requestFocus();
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
    public void carregaSpinnerAmbientes(ArrayList<Ambiente> listaAmbientes) {
        System.out.println("Entrei no carregaSpinner");
        // declarando o vetor com os nomes das Marcas
        String[] nomeAmbientes = new String[listaAmbientes.size() + 1];
        System.out.println("NomeAmbientes");
        // carregando o vetor com os nomes das Marcas
        nomeAmbientes[0] = "<< Selecionar >>"; // adicionando o selecionar na primeira posição (lembrar disso quando obter o objeto a partir do spinner)
        for (int x = 0; x < listaAmbientes.size(); x++) {
            Ambiente ambiente = listaAmbientes.get(x);
            nomeAmbientes[x + 1] = ambiente.getNomeAmbiente();
        }
        
        // definindo o adapter (conteúdo) do spinner
        binding.spFazerOrcamentoAmbiente.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, nomeAmbientes));
    }public void carregaSpinnerTextura(ArrayList<Pedido> listaPedido) {
        // declarando o vetor com os nomes das Marcas
        String[] nomeTexturas = new String[listaPedido.size() + 1];
        // carregando o vetor com os nomes das Marcas
        nomeTexturas[0] = "<< Selecionar >>"; // adicionando o selecionar na primeira posição (lembrar disso quando obter o objeto a partir do spinner)
        for (int x = 0; x < listaPedido.size(); x++) {
            Pedido textura = listaPedido.get(x);
            nomeTexturas[x + 1] = textura.texturaLiteral(textura.getTextura());
        }
        // definindo o adapter (conteúdo) do spinner
        binding.spFazerOrcamentoTextura.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, nomeTexturas));
    }
}