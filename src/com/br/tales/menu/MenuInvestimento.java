package com.br.tales.menu;

import com.br.tales.contas.ContaInvestimento;

import java.util.Objects;
import java.util.Scanner;

public class MenuInvestimento extends MenuMain{

    private static String contaAtual;
    private static ContaInvestimento cliente;

    public static String getcontaAtual() {
        return contaAtual;
    }

    public static void setcontaAtual(String contaAtual) {
        MenuInvestimento.contaAtual = contaAtual;
    }

    public static void logaUsuario(){
        System.out.println("\n ===== Login conta investimento - para retornar ao menu inicial digite VOLTAR =====");
        System.out.print("\nInforme o número da sua conta para prosseguir (SOMENTE NÚMEROS): ");
        Scanner input = new Scanner(System.in);
        setcontaAtual(input.nextLine());

        if (Objects.equals(getcontaAtual(), "VOLTAR")){
            MenuMain.mostrarMenu();
        } else {
            for (int i = 0; i < ContaInvestimento.contas.size(); i++){
                if (ContaInvestimento.contas.get(i).getConta().equals(getcontaAtual())){
                    cliente = (ContaInvestimento) ContaInvestimento.contas.get(i);
                }
            }
            mostraMenuCI();
        }
    }

    public static void mostraMenuCI(){
        System.out.println("\nCONTA: " + cliente.getConta() + " AGÊNCIA: " + cliente.getAgencia());
        System.out.println("*** BEM-VINDO *** " + cliente.getNome() + " você possui as seguintes opções:");

        System.out.println("1 - Ver os tipos de investimentos");
        System.out.println("2 - Simular rendimento de um investimento");
        System.out.println("3 - Saldo");
        System.out.println("4 - Saque");
        System.out.println("5 - Depósito");
        System.out.println("6 - Extrato");
        System.out.println("7 - Transferência");
        System.out.println("8 - Alterar dados cadastrais");
        System.out.println("9 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> tiposInvestimento();
                case 2 -> {
                    System.out.println("\nVocê possui as seguintes opções para investimento: ");

                    System.out.println("1 - Renda Fixa simples | Rendimento 12 meses: 2,538%");
                    System.out.println("2 - Multimercado dinâmico | Rendimento 12 meses: 1,995%");
                    System.out.println("3 - Ações Siderurgia | Rendimento 12 meses: 8,908%");

                    ContaInvestimento.simulaRendimento();
                }
                case 3 -> {
                    System.out.printf("\n" + cliente.getNome() + ", seu saldo disponível é de: R$ %.2f", cliente.getSaldo());
                    System.out.println("\n *** Retornando para o menu anterior ... ***");
                    mostraMenuCI();
                }
                case 4 -> {
                    System.out.printf("\n" + cliente.getNome() + ", seu saldo disponível é de: R$ %.2f", cliente.getSaldo());
                    System.out.print(" | Informe o valor que você deseja sacar: ");
                    double saque = Double.parseDouble(input.nextLine());
                    cliente.saque(saque, 3);
                }
                case 5-> {
                    System.out.print("\nInforme o valor que você deseja depositar: ");
                    double depositar = Double.parseDouble(input.nextLine());
                    cliente.deposito(depositar, 3);
                }

                case 6 -> cliente.extrato(getcontaAtual(), 3);

                case 7 -> {
                    System.out.print("\nInforme o valor que você deseja transferir: ");
                    double transferir = Double.parseDouble(input.nextLine());
                    System.out.print("Informe o número da conta de destino: ");
                    String conta_dest = input.nextLine();
                    cliente.transferir(transferir, conta_dest, 3);
                }

                case 8-> cliente.alterarDados(3);
                case 9 -> MenuMain.optaTipo();

                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6 && op != 7 && op != 8 && op != 9);
    }
    public static void tiposInvestimento(){
        System.out.println("\nVocê possui as seguintes opções para investimento: ");

        System.out.println("(01) - Renda Fixa simples | Rendimento 12 meses: 2,538%");
        System.out.println("(02) - Multimercado dinâmico | Rendimento 12 meses: 1,995%");
        System.out.println("(03) - Ações Siderurgia | Rendimento 12 meses: 8,908%");

        System.out.println("\n1 - Simular rendimento");
        System.out.println("2 - Voltar ao menu anterior");
        int op;

        do{
            System.out.print("\nDigite o número correspondente à sua ação: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> ContaInvestimento.simulaRendimento();
                case 2 -> MenuInvestimento.mostraMenuCI();
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2);
    }


}
