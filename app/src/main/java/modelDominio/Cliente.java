
package modelDominio;

import java.io.Serializable;
public class Cliente extends Usuario implements Serializable{
    private static final long serialVersionUID = 123L;

    public Cliente(long cpf, String nome, String sobrenome, String celular, String email, String senha, String estado, String cidade, String bairro, int cep, int numeroEndereco) {
        super(cpf, nome, sobrenome, celular, email, senha, estado, cidade, bairro, cep, numeroEndereco);

    }

    public Cliente(String nome, String sobrenome, String celular, String email, String senha, String estado, String cidade, String bairro, int cep, int numeroEndereco) {
        super(nome, sobrenome, celular, email, senha, estado, cidade, bairro, cep, numeroEndereco);
    }

    public Cliente(String email, String senha) {
        super(email, senha);
    }

    @Override
    public String toString() {
        return "Cliente{" + '}' + super.toString();
    }

}
