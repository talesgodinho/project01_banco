package com.br.tales.contas;

import com.br.tales.menu.MenuPoupanca;

public class ContaPoupanca extends Conta{

    public ContaPoupanca(String nome, String cpf, double rendaMensal, String conta, String agencia, String tipodeConta) {
        super(nome, cpf, rendaMensal, conta, agencia, tipodeConta);
    }

    public static void calculaRendimento(double valor, int tempo){
        System.out.println("=======================================================================");
        System.out.println("*** Aqui no banco DEVinHOuse seu dinheiro rende 0.44% ao mês ***");
        Double saldoRend = valor;
        for(int i = 0; i < tempo; i++){
            saldoRend += (saldoRend * 0.0044);
        }

        Double rendimento = saldoRend - valor;

        System.out.printf("\nSeu dinheiro renderá R$ %.2f em %d meses. " +
                "\nGerando o saldo total de: R$ %.2f após o período informado.", rendimento, tempo, saldoRend);

        System.out.println("\n=======================================================================");

        System.out.println("\n\nRedirecionando você para o menu anterior...");
        MenuPoupanca.mostraMenuCP();
    }
}
