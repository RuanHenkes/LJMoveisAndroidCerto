package com.example.ljmoveisandroidcerto.controller;

import android.util.Log;

import com.example.ljmoveisandroidcerto.viewModel.InformacoesViewModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Ambiente;
import modelDominio.Catalogo;
import modelDominio.Pedido;
import modelDominio.Usuario;

/**
 *
 * @author ruan
 */
public class ConexaoController {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Usuario usuarioLogado;

    InformacoesViewModel informacoesViewModel;

    public ConexaoController(InformacoesViewModel informacoesViewModel) {
        this.informacoesViewModel = informacoesViewModel;
    }
    //-------------servidor--------------
    public boolean criaConexaoServidor(String ip, int porta) {
        boolean resultado;
        try {
            // inicializando a conexão socket
            Socket socket = new Socket(ip, porta);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // salvando as informações no viewModel
            this.informacoesViewModel.inicializaObjetosSocket(in, out);
            resultado = true;
        } catch (IOException ioe) {
            Log.e("BikeShop","Erro: " + ioe.getMessage());
            resultado = false;
        }
        return resultado;
    }

    public Usuario Logar(Usuario usuario){
        Usuario usuarioLogado;
        String mensagem;
        System.out.println("Entrei no efetuar login!");

        try {
            out.writeObject("UsuarioLogar");
            mensagem = (String)in.readObject();
            System.out.println("recebi OK");
            out.writeObject(usuario);
            System.out.println("Enviei o usuario.");
            usuarioLogado = (Usuario) in.readObject();
            System.out.println("Recebido: " + usuarioLogado.toString());

        } catch (IOException ioe) {
            System.out.println("Erro! "+ioe.getMessage());
            usuarioLogado = null;
        } catch (ClassNotFoundException classe){
            System.out.println("Erro" +classe.getMessage());
            usuarioLogado = null;
        }
        return usuarioLogado;
    }

    //--------------------------------------Cliente-----------------------------------//
    public boolean clienteInserir(Usuario usuario){
        boolean resultado = false;
        String mensagem;

        try {
            resultado = true;
            out.writeObject("clienteInserir");
            mensagem = (String)in.readObject();
            out.writeObject(usuario);

            resultado = (Boolean)in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }

    //--------------------------------------Alterar-----------------------------------//
    public boolean clienteAlterar(Usuario usuario){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("clienteAlterar");
            mensagem = (String)in.readObject();
            out.writeObject(usuario);
            System.out.println("Alterei o Usuario");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }

    //--------------------------------------Deletar-----------------------------------//
    public boolean clienteDeletar(Usuario usuario){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("clienteDeletar");
            mensagem = (String)in.readObject();
            out.writeObject(usuario);
            System.out.println("Deletei Usuario");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }
    //--------------------------------------Lista-----------------------------------//
    public ArrayList<Usuario> clienteLista(){
        ArrayList<Usuario> listaClientes;

        try {
            out.writeObject("clienteLista");
            listaClientes = (ArrayList<Usuario>) in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());
            listaClientes = null;
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: " + classe.getMessage());
            listaClientes = null;
        }
        return listaClientes;

    }


    //-----------------------------------------Catalogo----------------------------------------//

    public boolean catalogoInserir(Catalogo catalogo){
        boolean resultado = false;
        String mensagem;

        try {
            System.out.println("Entrou no catalogo Inserir");
            resultado = true;
            out.writeObject("catalogoInserir");
            System.out.println("Mandou Catalogo Inserir");
            mensagem = (String)in.readObject();
            out.writeObject(catalogo);

            resultado = (Boolean)in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }


    //--------------------------Deletar-----------------------//
    public boolean catalogoDeletar(Catalogo catalogo){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("catalogoDeletar");
            mensagem = (String)in.readObject();
            out.writeObject(catalogo);
            System.out.println("Deletei Catalogo");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }
    //--------------------------Lista-----------------------//
    public ArrayList<Catalogo> catalogoLista(){
        ArrayList<Catalogo> listaCatalogo;

        try {
            out.writeObject("catalogoLista");
            listaCatalogo = (ArrayList<Catalogo>) in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());
            listaCatalogo = null;
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: " + classe.getMessage());
            listaCatalogo = null;
        }
        return listaCatalogo;
    }


    //------------------------------------------------Pedido-----------------------------------------------//

    public boolean pedidoInserir(Pedido pedido){
        boolean resultado = false;
        String mensagem;

        try {
            resultado = true;
            out.writeObject("pedidoInserir");
            mensagem = (String)in.readObject();
            out.writeObject(pedido);

            resultado = (Boolean)in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }


    //-----------------------Alterar----------------------------//
    public boolean pedidoAlterar(Pedido pedido){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("pedidoAlterar");
            mensagem = (String)in.readObject();
            out.writeObject(pedido);
            System.out.println("Alterei o Pedido");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }


    //-------------------------Deletar--------------------------//
    public boolean pedidoDeletar(Pedido pedido){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("pedidoDeletar");
            mensagem = (String)in.readObject();
            out.writeObject(pedido);
            System.out.println("Deletei Pedido");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }


    //--------------------------Lista-----------------------//
    public ArrayList<Pedido> pedidoLista(){
        ArrayList<Pedido> listaPedidos;

        try {
            out.writeObject("pedidoLista");
            listaPedidos = (ArrayList<Pedido>) in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());
            listaPedidos = null;
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: " + classe.getMessage());
            listaPedidos = null;
        }
        return listaPedidos;
    }

    //------------------------Ambiente-----------------------//

    public ArrayList<Ambiente> ambienteLista(){
        ArrayList<Ambiente> listaAmbientes;

        try {
            out.writeObject("ambienteLista");
            listaAmbientes = (ArrayList<Ambiente>) in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());
            listaAmbientes = null;
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: " + classe.getMessage());
            listaAmbientes = null;
        }
        return listaAmbientes;
    }
    public boolean ambienteInserir(Ambiente ambiente){
        boolean resultado = false;
        String mensagem;

        try {
            resultado = true;
            out.writeObject("ambienteInserir");
            mensagem = (String)in.readObject();
            out.writeObject(ambiente);

            resultado = (Boolean)in.readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }

    public boolean ambienteDeletar(Ambiente ambiente){
        boolean resultado = false;
        String mensagem;

        try{
            resultado = true;
            out.writeObject("ambienteDeletar");
            mensagem = (String)in.readObject();
            out.writeObject(ambiente);
            System.out.println("Deletei o ambiente");
            resultado = (Boolean)in.readObject();

        }catch(IOException ioe){
            System.out.println("Erro: "+ioe.getMessage());
        }catch(ClassNotFoundException classe){
            System.out.println("Erro: "+classe.getMessage());
        }
        return resultado;
    }

    public void fim(){
        try {
            out.writeObject("fim");
            in.close();
            out.close();
        } catch (IOException ioe) {
            System.out.println("Erro: "+ ioe.getMessage());
        }
    }
}

