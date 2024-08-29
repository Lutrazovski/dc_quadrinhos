package br.senac.dto;

import java.awt.*;
import java.util.Date;

public class QuadrinhoDto {

    private int id;
    private String nome;
    private Date dataPublicacao;
    private String sinopse;
    private AutorDto autor;
    private CategoriaDto categoria;
    private EditorDto editor;
    private IlustradorDto ilustrador;
    private int NumeroPaginas;
    private byte[] imagem;

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

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public AutorDto getAutor() {
        return autor;
    }

    public void setAutor(AutorDto autor) {
        this.autor = autor;
    }

    public CategoriaDto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDto categoria) {
        this.categoria = categoria;
    }

    public EditorDto getEditor() {
        return editor;
    }

    public void setEditor(EditorDto editor) {
        this.editor = editor;
    }

    public IlustradorDto getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(IlustradorDto ilustrador) {
        this.ilustrador = ilustrador;
    }

    public int getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        NumeroPaginas = numeroPaginas;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
