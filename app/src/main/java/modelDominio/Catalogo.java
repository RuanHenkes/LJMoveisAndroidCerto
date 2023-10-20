
package modelDominio;

import java.io.Serializable;

/**
 *
 * @author ruan
 */
public class Catalogo implements Serializable{
    private static final long serialVersionUID = 123L;
    private int idCatalago;
    private byte[] imagem;
    private String descricao;
    private String ambiente;

    public Catalogo(int idCatalago, byte[] imagem, String descricao, String ambiente) {
        this.idCatalago = idCatalago;
        this.imagem = imagem;
        this.descricao = descricao;
        this.ambiente = ambiente;
    }

    public Catalogo(byte[] imagem, String descricao, String ambiente) {
        this.imagem = imagem;
        this.descricao = descricao;
        this.ambiente = ambiente;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public int getIdCatalago() {
        return idCatalago;
    }

    public void setIdCatalago(int idCatalago) {
        this.idCatalago = idCatalago;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}