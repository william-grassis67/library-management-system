package data;

import model.Emprestimo;
import model.Livro;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {

    private List<Livro> livro = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    public List<Emprestimo> getEmprestimo(){
        return emprestimos;
    }
}