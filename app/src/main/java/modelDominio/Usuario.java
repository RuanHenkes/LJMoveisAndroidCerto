package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable{
    private static final long serialVersionUID = 123L;
    private long cpf;
    private String nome;
    private String sobrenome;
    private String celular;
    private String email;
    private String senha;
    private String estado;
    private String cidade;
    private String bairro;
    private int cep;
    private int numeroEndereco;

    public Usuario(long cpf, String nome, String sobrenome, String celular, String email, String senha, String estado, String cidade, String bairro, int cep, int numeroEndereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
    }

    public Usuario(String nome, String sobrenome, String celular, String email, String senha, String estado, String cidade, String bairro, int cep, int numeroEndereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }


    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

}