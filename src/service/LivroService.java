package service;

import data.BancoDados;
import model.Emprestimo;
import model.Livro;
import repository.OpcaoInvalidaException;

import java.util.List;
import java.util.Scanner;

public class LivroService {
    Scanner scanner = new Scanner(System.in);

    private final BancoDados bancoDados;

    public LivroService (BancoDados bancoDados){
        this.bancoDados = bancoDados;
    }

    int contador = 0;

    String situacao;

    //CADASTRAR
    public Livro Register(){
        contador++;
        System.out.println("POR FAVOR DIGITE O TITULO DO LIVRO:");
        String titulo = scanner.nextLine();

        System.out.println("AGORA DIGITE O AUTOR DO LIVRO:");
        String autor = scanner.nextLine();

        System.out.println("DIGITE O ANO DO LIVRO:");
        int ano;

        while (true){
            ano = scanner.nextInt();
            scanner.nextLine();
            if (ano > 4){
                System.out.println("ANO INEXISTENTE");
                continue;
            }
            break;
        }

        System.out.println("QUANTOS LIVROS EXISTEM NO ESTOQUE:");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        if (titulo.trim().isEmpty() && autor.trim().isEmpty()){
            throw new OpcaoInvalidaException("O campo não deve ser vazio!");
        }


        Livro livro = new Livro(titulo, autor, ano, false, contador, estoque);

        //SALVAR NO BANCO DE DADOS

        bancoDados.getLivro().add(livro);
        System.out.println("Quantidade: " + bancoDados.getLivro().size());

        //MOSTRA A MENSAGEM DE SUCESSO
        System.out.println("LIVRO CADASTRADO COM SUCESSO!");

        return livro;
    }

    //LISTAR
    public void List(){
        System.out.println("Quantidade: " + bancoDados.getLivro().size());

        //VALIDA SE TEM ALGO DENTRO DO BANCO DE DADOS
        if (bancoDados.getLivro().isEmpty()){
            System.out.println("NENHUM LIVRO CADASTRADO");
        }


        for (Livro l: bancoDados.getLivro()){
            if (l.isEmprestado() == true){
                situacao = "Emprestado";
            }else {
                situacao = "No estoque";
            }

            System.out.println("Titulo: " + l.getTitulo()+ ", AUTOR: " + l.getAutor() + ", ANO: " + l.getAno() + ", SITUAÇÃO: " + situacao + " ID: " + l.getId() + ", ESTOQUE: " + l.getEstoque());
        }

    }

    //BUSCAR POR TITULO
    public List<Livro> buscarPorTitulo(String titulo) {

        return bancoDados.getLivro()
                .stream()
                .filter(l -> l.getTitulo().equals(titulo))
                .toList();
    }

    // EMPRESTAR
    public void emprestar() {
        System.out.println("QUAL É O SEU NOME!");
        String nome = scanner.nextLine();
        System.out.println("DIGITE O NOME DO LIVRO QUE DESEJA EMPRESTA: ");
        String titulo = scanner.nextLine();
        System.out.println("DIGITE A QUANTIDADE DE LIVROS QUE DESEJA PEGAR!");
        int qtd = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DIGITE O AUTOR DO LIVRO:");
        String autor = scanner.nextLine();
        for (Livro l:bancoDados.getLivro()){
            if (!l.getAutor().equalsIgnoreCase(autor)){
                System.out.println("nenhum autor foi encontrado!");
            }
        }
        Emprestimo emprestimo = new Emprestimo(nome, titulo, autor, qtd);
        if (titulo.trim().isEmpty()){
            throw new OpcaoInvalidaException("O campo titulo não pode ser vazio!");
        }
        Livro livro = bancoDados.getLivro()
                .stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (livro != null) {
            livro.setEmprestado(true);
            if (qtd > livro.getEstoque()){
                System.out.println("NÃO TEMOS TUDO ISSO NO ESTOQUE");
            } else if (qtd < 1) {
                System.out.println("COLOQUE UMA QUANTIDADE REAL");
            }
            int calculo = livro.getEstoque() - qtd;
            livro.setEstoque(calculo);
            System.out.println("Livro: " + livro.getTitulo() + " situação: " + "Emprestado com sucesso");
        }
        bancoDados.getEmprestimo().add(emprestimo);
    }

    //BUSCAR
    public Livro Buscar() {
        System.out.print("DIGITE O NOME DO LIVRO: ");
        String titulo = scanner.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("NENHUM LIVRO INFORMADO");
            return null;
        }

        Livro livro = bancoDados.getLivro()
                .stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (livro != null) {
            if (livro.isEmprestado() == true){
                situacao = "Emprestado";
            }else  {
                situacao = "No estoque";
            }
            System.out.println("LIVRO ENCONTRADO!");
            System.out.println("Título: " + livro.getTitulo() + " id: " + livro.getId() + " situação: " + situacao);
        } else {
            System.out.println("LIVRO NÃO ENCONTRADO");
        }

        return livro;
    }

    //DEVOLVER

    public Livro Devolver(){
        System.out.println("DIGITE O SEU NOME:");
        String nome = scanner.nextLine();
        System.out.println("Digite o nome do livro que deseja devolver!");
        String titulo = scanner.nextLine();

        Emprestimo emprestimo = bancoDados.getEmprestimo()
                .stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);

        if (emprestimo != null){
            bancoDados.getEmprestimo().remove(emprestimo);
            System.out.println("LIVRO DEVOLVIDO COM SUCESSO!");
        }else {
            System.out.println("Nenhum dado encontrado!");
        }

        Livro livro = bancoDados.getLivro()
                .stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

        if (livro != null){
            int calculo = livro.getEstoque() + emprestimo.getQtd();
            livro.setEstoque(calculo);
            System.out.println("Obrigado!");
        }else {
            System.out.println("Livro não encontrado! Não é desta biblioteca");
        }

        return livro;
    }


    //REMOVER
    public BancoDados Remove(){
        System.out.println("DIGITE O NOME FO LIVRO QUE DESEJA EXCLUIR:");
        String titulo = scanner.nextLine();
        Livro livro = bancoDados.getLivro()
                .stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
        if (livro != null){
            System.out.println(livro);
        }
        bancoDados.getLivro().remove(livro);
        return bancoDados;
    }

    //TESTE
    public BancoDados ListarTeste(){
        return bancoDados;
    }
}
