package modelDominio;

import java.io.Serializable;

public class Ambiente implements Serializable{
    private static final long serialVersionUID = 123L;
    private int idAmbiente;
    private String nomeAmbiente;

    public Ambiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public Ambiente(int idAmbiente, String nomeAmbiente) {
        this.idAmbiente = idAmbiente;
        this.nomeAmbiente = nomeAmbiente;
    }


    public String getNomeAmbiente() {
        return nomeAmbiente;
    }

    public void setNomeAmbiente(String nomeAmbiente) {
        this.nomeAmbiente = nomeAmbiente;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }
    @Override
    public String toString() {
        return "Ambiente{" + "codAmbiente=" + idAmbiente + ", nomeAmbiente=" + nomeAmbiente + '}';
    }

}

