package com.br.tales.menu;

import com.br.tales.contas.Conta;
import com.br.tales.contas.ContaCorrente;

import java.util.Random;
import java.util.Scanner;

public class MenuMain{

    static Scanner input = new Scanner(System.in);

    public static void mostrarMenu(){
        System.out.println("\n--- BEM-VINDO AO BANCO DEVinHOUSE ---");
        System.out.println("O que você deseja fazer hoje?");
        System.out.println("1 - Abrir uma conta\n2 - Acessar minha conta\n3 - Acessar administração do sistema\n4 - SAIR");
        System.out.print("Digite o número da opção desejada: ");
        int op = Integer.parseInt(input.nextLine());
        MenuMain.executaMenu(op);
    }

    public static void executaMenu(int op){
        switch (op){
            case 1 -> aberturaConta();

            case 2 -> {

            }
            default ->
                    System.out.println("Opção inválida, tente novamente");
        }
    }

    public static void aberturaConta(){
        System.out.println("\n--- ABERTURA DE CONTA | BANCO DEVinHouse --- ");
        System.out.println("Qual tipo de conta você deseja abrir?");
        System.out.println("1 - Conta Corrente\n2 - Conta Poupança\n3 - Conta investimento\n4 - VOLTAR");
        System.out.print("Digite o número da opção desejada: ");
        int op = Integer.parseInt(input.nextLine());

        switch (op){
            case 1 -> abreCC();
            case 2 -> abreCP();
            case 3 -> abreCI();
            case 4 -> mostrarMenu();
        }
    }

    public static void abreCC(){
        System.out.println("\nVOCÊ ESTÁ ABRINDO UMA NOVA CONTA CORRENTE NO BANCO DEVinHOuse!");
        System.out.print("Informe seu nome completo: ");
        String nome = input.nextLine();
        System.out.print("Informe seu CPF (SOMENTE NÚMEROS): ");
        String cpf = input.nextLine();
        System.out.print("Informe sua renda mensal: ");
        Double rendaMensal = Double.parseDouble(input.nextLine());

        int conta = 0;

        do {
            Random geraConta = new Random();
            conta = geraConta.nextInt(10000) + 99999;
        } while (!ContaCorrente.validaConta(conta));

        boolean validaAg = false;
        String agencia = null;
        do {
            System.out.println("Em qual agência você deseja abrir a conta?");
            System.out.println("1 - FLORIANÓPOLIS\n2 - SÃO JOSÉ");
            int ag = Integer.parseInt(input.nextLine());
            if (ag == 1){
                agencia = "001 - FLORIANOPOLIS";
                validaAg = true;
            } else if (ag == 2){
                agencia = "002 - SAO JOSE";
                validaAg = true;
            }
        } while (!validaAg);

        Conta.contas.add(new ContaCorrente(nome, cpf, rendaMensal, conta, agencia));

        Double cheque = ContaCorrente.getChequeEspecial();
        System.out.println(nome + " sua conta de número: " + conta + " na agência " + agencia + " foi criada com sucesso." );
        System.out.printf("Você possui cheque especial aprovado no valor de: R$ %.2f", ContaCorrente.getChequeEspecial());
        System.out.println("\nVocê será redirecionado para o login de sua conta ...");
        mostrarMenu();

//        ContaCorrente.exibeArray();

    }

    public static void abreCP(){
    }

    public static void abreCI(){
    }
}
