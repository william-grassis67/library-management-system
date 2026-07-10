package model;

public class Livro {
    String titulo;
    String autor;
    int ano;
    boolean emprestado;
    Integer id;
    Integer estoque;

    public Livro(String titulo,
    String autor,
    int ano,
    boolean emprestado,
    Integer id,
                 Integer estoque){
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.emprestado = emprestado;
        this.id = id;
        this.estoque = estoque;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return this.autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public int getAno(){
        return this.ano;
    }

    public void setAno(int ano){
        this.ano = ano;
    }

    public boolean isEmprestado(){
        return this.emprestado;
    }

    public void setEmprestado(boolean emprestado){
        this.emprestado = emprestado;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getEstoque(){
        return this.estoque;
    }

    public void setEstoque(Integer estoque){
        this.estoque = estoque;
    }
}
