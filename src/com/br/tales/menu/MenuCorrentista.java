package com.br.tales.menu;

import com.br.tales.contas.ContaCorrente;

import java.util.Scanner;

public class MenuCorrentista extends MenuMain{

    private static String cpf;
    private static ContaCorrente cliente;

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        MenuCorrentista.cpf = cpf;
    }

    public static void logaUsuario(){
        System.out.print("\nInforme seu CPF para prosseguir (SOMENTE NÚMEROS): ");
        Scanner input = new Scanner(System.in);
        setCpf(input.nextLine());
        for (int i = 0; i < ContaCorrente.contas.size(); i++){
            if (ContaCorrente.contas.get(i).getCpf().equals(getCpf())){
                cliente = (ContaCorrente) ContaCorrente.contas.get(i);
            }
        }
        mostraMenuCC();
    }

    public static void mostraMenuCC(){
        System.out.println("\nCONTA: " + cliente.getConta() + " AGÊNCIA: " + cliente.getAgencia());
        System.out.println("*** BEM-VINDO *** " + cliente.getNome() + " você possui as seguintes opções:");
        System.out.println("1 - Saldo\n2 - Saque\n3 - Depósito\n4 - Extrato\n5 - Transferir\n6 - Alterar dados cadastrais\n7 - VOLTAR");

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
                    cliente.saque(saque);
                }
                case 3-> {
                    System.out.print("\nInforme o valor que você deseja depositar: ");
                    double depositar = Double.parseDouble(input.nextLine());
                    cliente.deposito(depositar);
                }

                case 4 -> cliente.extrato(getCpf());

                case 5 -> {
                    System.out.print("\nInforme o valor que você deseja transferir: ");
                    double transferir = Double.parseDouble(input.nextLine());
                    System.out.print("\nInforme o CPF da conta de destino: ");
                    String cpf_dest = input.nextLine();
                    cliente.transferir(transferir, cpf_dest);
                }
                case 6 -> cliente.alterarDados();
                case 7 -> MenuMain.optaTipo();

                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6 && op != 7 );

    }
}
