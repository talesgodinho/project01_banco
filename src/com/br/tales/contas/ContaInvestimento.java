package com.br.tales.contas;

import com.br.tales.menu.MenuInvestimento;

import java.util.Scanner;

public class ContaInvestimento extends Conta{

    public ContaInvestimento(String nome, String cpf, double rendaMensal, String conta, String agencia, String tipodeConta) {
        super(nome, cpf, rendaMensal, conta, agencia, tipodeConta);
    }


    public static void simulaRendimento(){
        Scanner input = new Scanner(System.in);

        System.out.print("\nDigite o tempo (em meses - número inteiro) que você deseja simular: ");
        String periodo = input.nextLine();

        System.out.print("Digite o valor que você está disposto a investir: ");
        double valorInvest = Double.parseDouble(input.nextLine());

        int op;

        do {
            System.out.print("Digite a opção de investimento que você deseja: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> calculaRendimento(valorInvest, 2.538, Integer.parseInt(periodo));
                case 2 -> calculaRendimento(valorInvest, 1.995, Integer.parseInt(periodo));
                case 3 -> calculaRendimento(valorInvest, 8.908, Integer.parseInt(periodo));
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3);
        System.out.println("\nVocê será redirecionado ao menu anterior... ");
        MenuInvestimento.mostraMenuCI();
    }

    public static void calculaRendimento(double valorInvest, double valorRend, int tempo){
        valorRend = (valorRend/100)/12;
        double montante = valorInvest * (valorRend*tempo);;
        montante += valorInvest;

        System.out.printf("\nO montante do valor investido após %d meses será de R$ %.2f", tempo, montante);
    }

}
