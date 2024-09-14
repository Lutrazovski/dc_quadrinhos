package br.senac.dto;

public class QuadrinhoDto {

    private int id;
    private String nome;
    private String imagem;
    private HeroiDto heroi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public HeroiDto getHeroi() {
        return heroi;
    }

    public void setHeroi(HeroiDto heroi) {
        this.heroi = heroi;
    }
}
