package com.br.tales.menu;

import com.br.tales.contas.ContaCorrente;

import java.util.Objects;
import java.util.Scanner;

public class MenuCorrentista extends MenuMain{

    private static String contaAtual;
    private static ContaCorrente cliente;

    public static String getcontaAtual() {
        return contaAtual;
    }

    public static void setcontaAtual(String contaAtual) {
        MenuCorrentista.contaAtual = contaAtual;
    }

    public static void logaUsuario(){
        System.out.println("\n ===== Login conta corrente - para retornar ao menu inicial digite VOLTAR =====");
        System.out.print("\nInforme sua conta para prosseguir (SOMENTE NÚMEROS): ");
        Scanner input = new Scanner(System.in);
        setcontaAtual(input.nextLine());

        if (Objects.equals(getcontaAtual(), "VOLTAR")){
            MenuMain.mostrarMenu();
        } else {
            for (int i = 0; i < ContaCorrente.contas.size(); i++){
                if (ContaCorrente.contas.get(i).getConta().equals(getcontaAtual())){
                    cliente = (ContaCorrente) ContaCorrente.contas.get(i);
                }
            }
            mostraMenuCC();
        }
    }

    public static void mostraMenuCC(){
        System.out.println("\nCONTA: " + cliente.getConta() + " AGÊNCIA: " + cliente.getAgencia());
        System.out.println("*** BEM-VINDO *** " + cliente.getNome() + " você possui as seguintes opções:");

        System.out.println("1 - Saldo");
        System.out.println("2 - Saque");
        System.out.println("3 - Depósito");
        System.out.println("4 - Extrato");
        System.out.println("5 - Transferir");
        System.out.println("6 - Alterar dados cadastrais");
        System.out.println("7 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> {
                    double saldoLimpo = cliente.getSaldo() - (cliente.getRendaMensal() * 0.3);
                    System.out.printf("\n" + cliente.getNome() + ", seu saldo disponível é de: R$ %.2f", saldoLimpo);
                    System.out.printf("\n" + "Seu saldo disponível com o limite do cheque especial é de: R$ %.2f", cliente.getSaldo());
                    System.out.println("\n *** Retornando para o menu anterior ... ***");
                    mostraMenuCC();
                }
                case 2 -> {
                    System.out.printf("\nSaldo da conta + limite do cheque especial: R$ %.2f", cliente.getSaldo());
                    System.out.print(" | Informe o valor que você deseja sacar: ");
                    double saque = Double.parseDouble(input.nextLine());
                    cliente.saque(saque, 1);
                }
                case 3-> {
                    System.out.print("\nInforme o valor que você deseja depositar: ");
                    double depositar = Double.parseDouble(input.nextLine());
                    cliente.deposito(depositar, 1);
                }

                case 4 -> cliente.extrato(getcontaAtual(), 1);

                case 5 -> {
                    System.out.print("\nInforme o valor que você deseja transferir: ");
                    double transferir = Double.parseDouble(input.nextLine());
                    System.out.print("Informe o número da conta de destino: ");
                    String conta_dest = input.nextLine();
                    cliente.transferir(transferir, conta_dest, 1);
                }
                case 6 -> cliente.alterarDados(1);
                case 7 -> MenuMain.optaTipo();

                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6 && op != 7 );

    }
}
