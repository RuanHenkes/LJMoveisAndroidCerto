package com.example.ljmoveisandroidcerto.viewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import modelDominio.Usuario;

public class InformacoesViewModel extends ViewModel {
    //informacoes necessarias para socket
    private MutableLiveData<ObjectInputStream> mIn;
    private MutableLiveData<ObjectOutputStream> mOut;

    //usuario logado no aplicativo

    private MutableLiveData<Usuario> mUsuarioLogado;

    public void inicializaObjetosSocket(ObjectInputStream in, ObjectOutputStream out) {
        // instanciando os mutableliveData
        this.mIn = new MutableLiveData<>();
        this.mOut = new MutableLiveData<>();
        // definindo os objetos recebidos por parâmetro
        this.mIn.postValue(in);
        this.mOut.postValue(out);
    }

    public void inicializaUsuarioLogado(Usuario usuarioLogado) {
        // instanciando o mutableLiveData
        this.mUsuarioLogado = new MutableLiveData<>();
        // definindo o objeto recebido por parâmetro
        this.mUsuarioLogado.postValue(usuarioLogado);
    }

    public ObjectInputStream getInputStream() {
        return this.mIn.getValue();
    }

    public ObjectOutputStream getOutputStream() {
        return this.mOut.getValue();
    }

    public Usuario getUsuarioLogado() {
        return this.mUsuarioLogado.getValue();
    }

}
