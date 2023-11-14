package modelDominio;

import java.io.Serializable;


/**
 *
 * @author ruan
 */
public class Pedido implements Serializable{
    private static final long serialVersionUID = 123L;
    private int idPedido;
    private String nomePedido;
    private String cor;
    private int textura;
    private float orcamento;
    private String ambiente;
    private int status;
    private Usuario usuario;

    public Pedido(int idPedido, String nomePedido, String cor, int textura, float orcamento, String ambiente, Usuario usuario) {
        this.idPedido = idPedido;
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.orcamento = orcamento;
        this.ambiente = ambiente;
        this.usuario = usuario;
    }

    public Pedido(String nomePedido, String cor, int textura, float orcamento, int status, Usuario usuario) {
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.orcamento = orcamento;
        this.ambiente = ambiente;
        this.status = status;
        this.usuario = usuario;
    }


    public Pedido(int idPedido, String nomePedido, String cor, int textura, float orcamento, String ambiente, int status, Usuario usuario) {
        this.idPedido = idPedido;
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.orcamento = orcamento;
        this.ambiente = ambiente;
        this.status = status;
        this.usuario = usuario;
    }
    public Pedido(String nomePedido, String cor, int textura, float orcamento, String ambiente, int status, Usuario usuario) {
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.orcamento = orcamento;
        this.ambiente = ambiente;
        this.status = status;
        this.usuario = usuario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public String  getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getTextura() {
        return textura;
    }

    public void setTextura(int textura) {
        this.textura = textura;
    }

    public float getValor() {
        return orcamento;
    }

    public void setValor(float orcamento) {
        this.orcamento = orcamento;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }
    public String texturaLiteral(int textura){
        String retorno = "";
//è melhor quando coloca o retorno como uma variavel, dai não tem que fazer outro IF
        if (textura == 1) {
            retorno = "Amadeirado";

        } else if(textura ==2){
            retorno = "Liso";
        }else if (textura == 3){
            retorno = "Brilhante";
        }



        return retorno;
    }



    public String statusLiteral(int status){
        String retorno = "Recebido";

        if (status == 1){
            retorno = "Pedido enviado";
        }else if (status == 2){
            retorno = "Orçamento pronto";
        }else if (status == 3){
            retorno = "Pedido fechado";
        }else if (status == 4){
            retorno = "Pedido iniciado na marcenaria";
        }else if (status == 5){
            retorno = "Pedido pronto";
        }else if (status == 6){
            retorno = "Data de entrega definida";
        }else if (status == 7){
            retorno = "Pedido entregue";
        }


        return retorno;
    }



}
