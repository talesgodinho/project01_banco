package com.br.tales.menu;

import com.br.tales.contas.ContaPoupanca;

import java.util.Objects;
import java.util.Scanner;

public class MenuPoupanca extends MenuMain{

    private static String contaAtual;
    private static ContaPoupanca cliente;

    public static String getcontaAtual() {
        return contaAtual;
    }

    public static void setcontaAtual(String contaAtual) {
        MenuPoupanca.contaAtual = contaAtual;
    }

    public static void logaUsuario(){
        System.out.println("\n ===== Login conta poupança - para retornar ao menu inicial digite VOLTAR =====");
        System.out.print("\nInforme o número da sua conta para prosseguir (SOMENTE NÚMEROS): ");

        Scanner input = new Scanner(System.in);
        setcontaAtual(input.nextLine());

        if (Objects.equals(getcontaAtual(), "VOLTAR")){
            MenuMain.mostrarMenu();
        } else {
            for (int i = 0; i < ContaPoupanca.contas.size(); i++){
                if (ContaPoupanca.contas.get(i).getConta().equals(getcontaAtual())){
                    cliente = (ContaPoupanca) ContaPoupanca.contas.get(i);
                }
            }
            mostraMenuCP();
        }
    }

    public static void mostraMenuCP(){
        System.out.println("\nCONTA: " + cliente.getConta() + " AGÊNCIA: " + cliente.getAgencia());
        System.out.println("*** BEM-VINDO *** " + cliente.getNome() + " você possui as seguintes opções:");

        System.out.println("1 - Saldo");
        System.out.println("2 - Saque");
        System.out.println("3 - Depósito");
        System.out.println("4 - Extrato");
        System.out.println("5 - Transferir");
        System.out.println("6 - Calcular rendimento");
        System.out.println("7 - Alterar dados cadastrais");
        System.out.println("8 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> {
                    System.out.printf("\n" + cliente.getNome() + ", seu saldo disponível é de: R$ %.2f", cliente.getSaldo());
                    System.out.println("\n *** Retornando para o menu anterior ... ***");
                    mostraMenuCP();
                }
                case 2 -> {
                    System.out.printf("\n" + cliente.getNome() + ", seu saldo disponível é de: R$ %.2f", cliente.getSaldo());
                    System.out.print(" | Informe o valor que você deseja sacar: ");
                    double saque = Double.parseDouble(input.nextLine());
                    cliente.saque(saque, 2);
                }
                case 3-> {
                    System.out.print("\nInforme o valor que você deseja depositar: ");
                    double depositar = Double.parseDouble(input.nextLine());
                    cliente.deposito(depositar, 2);
                }

                case 4 -> cliente.extrato(getcontaAtual(), 2);

                case 5 -> {
                    System.out.print("\nInforme o valor que você deseja transferir: ");
                    double transferir = Double.parseDouble(input.nextLine());
                    System.out.print("Informe o número da conta de destino: ");
                    String conta_dest = input.nextLine();
                    cliente.transferir(transferir, conta_dest, 2);
                }

                case 6 ->{
                    System.out.print("\nInforme o valor que você deseja depositar para rendimento: ");
                    double valor = Double.parseDouble(input.nextLine());
                    System.out.print("Informe o tempo (em meses - número inteiro) que você pretende deixar rendendo: ");
                    int tempo = Integer.parseInt(input.nextLine());
                    ContaPoupanca.calculaRendimento(valor, tempo);
                }

                case 7 -> cliente.alterarDados(2);
                case 8 -> MenuMain.optaTipo();

                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6 && op != 7 && op != 8);

    }
}
