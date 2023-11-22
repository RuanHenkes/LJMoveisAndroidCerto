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
import modelDominio.Cliente;
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

    public Usuario Logar(Usuario usuario) {
        Usuario usuarioLogado;
        String mensagem;
        System.out.println("Entrei no efetuar login!");

           try {
                this.informacoesViewModel.getOutputStream().writeObject("UsuarioLogar");
                mensagem = (String) this.informacoesViewModel.getInputStream().readObject();
                this.informacoesViewModel.getOutputStream().writeObject(usuario);
                usuarioLogado = (Usuario) this.informacoesViewModel.getInputStream().readObject();
            } catch (IOException ioe) {
                Log.e("BikeShop", "Erro: " + ioe.getMessage());
                usuarioLogado = null;

            } catch (ClassNotFoundException classe) {
                Log.e("BikeShop", "Erro: " + classe.getMessage());
                usuarioLogado = null;

            }
            return usuarioLogado;
        }

    //--------------------------------------Cliente-----------------------------------//

    public boolean clienteInserir(Usuario usuario){
        boolean resultado;
        String mensagem;

        try {
            this.informacoesViewModel.getOutputStream().writeObject("clienteInserir");
            mensagem = (String) this.informacoesViewModel.getInputStream().readObject();
            this.informacoesViewModel.getOutputStream().writeObject(usuario);
            resultado = (Boolean) this.informacoesViewModel.getInputStream().readObject();

        }catch (IOException ioe){
            Log.e("LJMoveisAndroidCerto", "Erro: "+ioe.getMessage());
            resultado = false;
        }catch (ClassNotFoundException classe){
            Log.e("LJMoveisAndroidCerto", "Erro: "+classe.getMessage());
            resultado = false;
        }
        return resultado;
    }

    //------------------AlterarCliente---------------------------

    public boolean clienteAlterar (Usuario usuario){
        boolean resultado;
        String mensagem;
        try {
            this.informacoesViewModel.getOutputStream().writeObject("clienteAlterar");
            System.out.println("Mandei Cliente Alterar");
            mensagem = (String) this.informacoesViewModel.getInputStream().readObject();
            this.informacoesViewModel.getOutputStream().writeObject(usuario);
            resultado = (Boolean) this.informacoesViewModel.getInputStream().readObject();

            out.writeObject(usuario);
            System.out.println("Mandei Usuario");
            resultado = (Boolean) in.readObject();

        }catch (IOException ioe){
            Log.e("LJMoveisAndroidCerto", "Erro: "+ioe.getMessage());
            resultado = false;
        }catch (ClassNotFoundException classe){
            Log.e("LJMoveisAndroidCerto", "Erro: "+classe.getMessage());
            resultado = false;
        }

        return resultado;
    }

    //--------------------------------------Lista-----------------------------------//
    public ArrayList<Usuario> clienteLista(){
        ArrayList<Usuario> listaClientes;

        try {
            this.informacoesViewModel.getOutputStream().writeObject("clienteLista");
            listaClientes = (ArrayList<Usuario>) this.informacoesViewModel.getInputStream().readObject();


        }catch (IOException ioe){
            Log.e("BikeShop", "Erro: "+ioe.getMessage());
            listaClientes = null;

        }catch (ClassNotFoundException classe){
            Log.e("BikeShop", "Erro: "+ classe.getMessage());
            listaClientes = null;
        }
        return listaClientes;
    }


    //-----------------------------------------Catalogo----------------------------------------//

    public boolean catalogoInserir(Catalogo catalogo){
        boolean resultado = false;
        String mensagem;

        try {

            this.informacoesViewModel.getOutputStream().writeObject("catalogoInserir");
            mensagem = (String) this.informacoesViewModel.getInputStream().readObject();
            this.informacoesViewModel.getOutputStream().writeObject(catalogo);
            resultado = (Boolean) this.informacoesViewModel.getInputStream().readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }


    //--------------------------Lista-----------------------//
    public ArrayList<Catalogo> catalogoLista(){
        ArrayList<Catalogo> listaCatalogo;

        try {
            this.informacoesViewModel.getOutputStream().writeObject("catalogoLista");
            listaCatalogo = (ArrayList<Catalogo>) this.informacoesViewModel.getInputStream().readObject();


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

            this.informacoesViewModel.getOutputStream().writeObject("pedidoInserir");
            mensagem = (String) this.informacoesViewModel.getInputStream().readObject();
            this.informacoesViewModel.getOutputStream().writeObject(pedido);
            resultado = (Boolean) this.informacoesViewModel.getInputStream().readObject();

        } catch (IOException ioe) {
            System.out.println("Erro: "+ioe.getMessage());

        }catch(ClassNotFoundException classe){
            System.out.println("Erro"+classe.getMessage());
        }
        return resultado;
    }



    //--------------------------Lista-----------------------//
    public ArrayList<Pedido> pedidoLista(){
        ArrayList<Pedido> listaPedidos;

        try {
            this.informacoesViewModel.getOutputStream().writeObject("pedidoLista");
            listaPedidos = (ArrayList<Pedido>) this.informacoesViewModel.getInputStream().readObject();

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
            Log.e("LJMoveisPlanejadosCliente","Mandei ambienteLista");
            this.informacoesViewModel.getOutputStream().writeObject("ambienteLista");
            Log.e("LJMoveisPlanejadosCliente","Recebi lista Ambeintes");
            listaAmbientes = (ArrayList<Ambiente>) this.informacoesViewModel.getInputStream().readObject();


        } catch (IOException ioe) {
            Log.e("LJMoveisPlanejadosCliente","Erro: "+ioe.getMessage());
            listaAmbientes = null;
        }catch(ClassNotFoundException classe){
            Log.e("LJMoveisPlanejadosCliente","Erro: " + classe.getMessage());
            listaAmbientes = null;
        }
        return listaAmbientes;
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

