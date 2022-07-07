package br.com.gft.dto;

public class RegistroFilialDTO {

    private String nome;

    private EnderecoDTO endereco;

    public RegistroFilialDTO() {
    }

    public RegistroFilialDTO(String nome, EnderecoDTO endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
