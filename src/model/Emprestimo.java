package model;

public class Emprestimo {
    String nome;
    String livro;
    String autor;
    int qtd;

    public Emprestimo(String nome, String livro, String autor, int qtd){
        this.nome = nome;
        this.livro = livro;
        this.autor = autor;
        this.qtd = qtd;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLivro(){
        return this.livro;
    }

    public void setAutor(){
        this.autor = autor;
    }

    public int getQtd(){
        return this.qtd;
    }

    public void setQtd(int qtd){
        this.qtd = qtd;
    }
}
