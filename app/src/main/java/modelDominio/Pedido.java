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
    private float valor;
    private int ambiente;
    private int status;
    private String descricao;
    private Usuario usuario;

    public Pedido(int idPedido, String nomePedido, String cor, int textura, float valor, int ambiente, int status, String descricao, Usuario usuario) {
        this.idPedido = idPedido;
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.valor = valor;
        this.ambiente = ambiente;
        this.status = status;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public Pedido(String nomePedido, String cor, int textura, float valor, int ambiente, int status, String descricao, Usuario usuario) {
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.valor = valor;
        this.ambiente = ambiente;
        this.status = status;
        this.descricao = descricao;
        this.usuario = usuario;
    }
    public Pedido(String nomePedido, String cor, int textura, float valor, int ambiente, String descricao, Usuario usuario) {
        this.nomePedido = nomePedido;
        this.cor = cor;
        this.textura = textura;
        this.valor = valor;
        this.ambiente = ambiente;
        this.descricao = descricao;
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
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }
    public String texturaLiteral(int textura){
        String retorno = "MDF";

        if (textura == 1) {
            retorno = "Amadeirado";

        } else if(textura ==2){
            retorno = "Liso";
        }else if (textura == 3){
            retorno = "Brilhante";
        }
        return retorno;
    }

    public String ambienteLiteral(int ambiente){
        String retorno = "Cozinha";

        if (ambiente == 1){
            retorno = "Cozinha";
        } else if (ambiente == 2){
            retorno = "Sala de jantar";
        }else if (ambiente == 3){
            retorno = "Sala de estar";
        }else if (ambiente == 4){
            retorno = "Banheiro";
        }else if (ambiente == 5){
            retorno = "Escritório";
        }else if (ambiente == 6 ){
            retorno = "Garagem";
        }else if (ambiente == 7){
            retorno = "Closet";
        }else if (ambiente == 8){
            retorno = "Quarto";
        }else if (ambiente == 9){
            retorno = "Lavanderia";
        }else if (ambiente == 10 ){
            retorno = "Despensa";
        }else if (ambiente == 11){
            retorno = "Deposito";
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
