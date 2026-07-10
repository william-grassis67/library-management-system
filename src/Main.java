import data.BancoDados;
import repository.OpcaoInvalidaException;
import service.LivroService;

import java.util.Scanner;

public void Menu(){
 System.out.println("[1] Cadastrar Livro");
 System.out.println("[2] Listar Livro");
 System.out.println("[3] Buscar Livro");
 System.out.println("[4] Emprestar");
 System.out.println("[5] Devolver");
 System.out.println("[6] Remover livro");
}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    BancoDados bancoDados = new BancoDados();

    LivroService livroService = new LivroService(bancoDados);

    Scanner scanner = new Scanner(System.in);
    //Menu();

    int opcao;

    while (true){
        Menu();
        System.out.print(">");
        opcao = scanner.nextInt();
        scanner.nextLine();
            //throw new OpcaoInvalidaException("Esta opção não foi encontrada!");
        switch (opcao){
            case 1:
                livroService.Register();
                break;
            case 2:
                livroService.List();
                break;
            case 3:
                livroService.Buscar();
                System.out.println("WAIT!");
                break;
            case 4:
                livroService.emprestar();
                break;
            case 5:
                livroService.Devolver();
                break;
            case 6:
                livroService.Remove();
                break;
            case 0:
                break;

        }
    }

}
