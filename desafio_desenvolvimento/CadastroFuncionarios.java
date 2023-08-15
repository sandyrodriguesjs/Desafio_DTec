package desafio_desenvolvimento;
import java.util.Scanner;
import java.util.InputMismatchException; // Importar a classe InputMismatchException

class Cargo {
    double salario;

    public Cargo(double salario) {
        this.salario = salario;
    }
}

class Funcionario {
    int codigo;
    String nome;
    int codigoCargo;

    public Funcionario(int codigo, String nome, int codigoCargo) {
        this.codigo = codigo;
        this.nome = nome;
        this.codigoCargo = codigoCargo;
    }
}

public class CadastroFuncionarios{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Cargo[] cargos = new Cargo[10];
        cargos[0] = new Cargo(2500); // Cargo 0
        cargos[1] = new Cargo(1500); // Cargo 1
        cargos[2] = new Cargo(10000); // Cargo 2
        cargos[3] = new Cargo(1200); // Cargo 3
        cargos[4] = new Cargo(800); // Cargo 4

        Funcionario[] funcionarios = new Funcionario[50];
        int totalCargos = 5; // Total de cargos predefinidos
        int totalFuncionarios = 0;
        
        // Inicialização dos funcionários com os códigos e nomes especificados
        funcionarios[totalFuncionarios] = new Funcionario(15, "João da Silva", 2); // Cargo 2
        totalFuncionarios++;
        funcionarios[totalFuncionarios] = new Funcionario(1, "Pedro Santos", 0); // Cargo 0
        totalFuncionarios++;
        funcionarios[totalFuncionarios] = new Funcionario(26, "Maria Oliveira", 4); // Cargo 4
        totalFuncionarios++;
        funcionarios[totalFuncionarios] = new Funcionario(12, "Rita Alcantara", 3); // Cargo 3
        totalFuncionarios++;
        funcionarios[totalFuncionarios] = new Funcionario(8, "Ligia", 1); // Cargo 1
        totalFuncionarios++;


        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar cargo");
            System.out.println("2. Cadastrar funcionário");
            System.out.println("3. Mostrar relatório de salários");
            System.out.println("4. Mostrar total de salários por cargo");
            System.out.println("0. Sair");
            
            int escolha = 0;
            try {
                escolha = scanner.nextInt();
            } catch (InputMismatchException e) { // Lidar com a exceção caso o usuário não insira um número inteiro
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            if (escolha == 0) {
                System.out.println("Encerrando o programa...");
                break;
            } else if (escolha == 1) {
                System.out.println("Digite o valor do salário para o novo cargo:");
                double salario = scanner.nextDouble();
                cargos[totalCargos] = new Cargo(salario);
                totalCargos++;
                System.out.println("Cargo cadastrado com sucesso!");
            } else if (escolha == 2) {
                System.out.println("Digite o código do funcionário:");
                
                int codigo = 0;
                // Capturar a entrada do usuário e tratar a exceção
                try {
                    codigo = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    scanner.next(); // Limpar o buffer do scanner
                    continue;
                }
                // Verifica se o código informado peli usuário é negativo
                if (codigo < 0) {
                    System.out.println("Código de funcionário inválido.");
                    continue;
                }
               
                boolean codigoExistente = false;
                for (int i = 0; i < totalFuncionarios; i++) {
                    if (funcionarios[i] != null && funcionarios[i].codigo == codigo) {  // Verifica se o código do funcionário já existe em algum dos já cadastrados
                        codigoExistente = true;
                        break;
                    }
                }

                if (codigoExistente) { // Verifica que existe e mostra mensagem de erro
                    System.out.println("Código de funcionário já existe.");
                } else {
                    System.out.println("Digite o nome do funcionário:");
                    String nome = scanner.next();
                    System.out.println("Escolha o código de um cargo existente (0 a " + (totalCargos - 1) + "):"); // Solicita o código do cargo 
                    
                    int codigoCargo = 0;
                    try {
                        codigoCargo = scanner.nextInt();
                    } catch (InputMismatchException e) { 
                        System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                        scanner.next(); // Limpar o buffer do scanner
                        continue;
                    }

                    if (codigoCargo < 0 || codigoCargo >= totalCargos) { // Verifica se o código do cargo é inválido ou se ele existe na base de dados
                        System.out.println("Código de cargo inválido.");
                        continue;
                    }

                    // Cria um novo objeto Funcionário com os valores fornecidos e adicona no array
                    funcionarios[totalFuncionarios] = new Funcionario(codigo, nome, codigoCargo);
                    totalFuncionarios++;
                    System.out.println("Funcionário cadastrado com sucesso!");
                }
            
            } else if (escolha == 3) {
                System.out.println("Relatório de salários:");
                for (int i = 0; i < totalFuncionarios; i++) { // Loop que percorre todos os funcionários cadastrados
                    Funcionario funcionario = funcionarios[i]; // Obtém o objeto funcinário na posição atual do array funcionário
                    if (funcionario != null) {
                        if (funcionario.codigoCargo >= 0 && funcionario.codigoCargo < totalCargos) { // Verifica se o código do cargo do funcionário é valido dentro do intervalo
                            Cargo cargo = cargos[funcionario.codigoCargo]; // Obtém o objeto cargo associado ao código do cargo do funcionário
                            if (cargo != null) {
                                System.out.println("Código: " + funcionario.codigo + " | Nome: " + funcionario.nome + " | Salário: " + cargo.salario);
                            } else {
                                System.out.println("Cargo não definido para o funcionário de código " + funcionario.codigo);
                            }
                        } else {
                            System.out.println("Código de cargo inválido para o funcionário de código " + funcionario.codigo);
                        }
                    }
                }
                    
            } else if (escolha == 4) {
                System.out.println("Digite o código do cargo para calcular o total de salários:");
                
                int codigoCargo = 0;
                try {
                    codigoCargo = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                    scanner.next(); // Limpar o buffer do scanner
                    continue;
                }

                if (codigoCargo < 0 || codigoCargo >= totalCargos) { // Verifica se o código de cargo é inválido, fora do intervalo de cargos cadastrados
                    System.out.println("Código de cargo inválido.");
                    continue;
                }

                double totalSalarios = 0;
                for (int i = 0; i < totalFuncionarios; i++) { // Loop que percorre todos os funcionários cadastrados
                    if (funcionarios[i].codigoCargo == codigoCargo) { // Verifica se o código do funcionário é igual ao código do cargo fornecido do usuário
                        totalSalarios += cargos[codigoCargo].salario; // Adiciona o salário do cargo a váriavel totalsalarios
                    }
                }

                System.out.println("Total de salários para o cargo " + codigoCargo + ": " + totalSalarios);
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}