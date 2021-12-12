package com.br.tales.menu;

import com.br.tales.contas.ContaCorrente;
import com.br.tales.contas.ContaInvestimento;
import com.br.tales.contas.ContaPoupanca;

import java.util.Random;
import java.util.Scanner;

public class MenuMain{

    static Scanner input = new Scanner(System.in);

    public static void mostrarMenu(){
        System.out.println("\n--- BEM-VINDO AO BANCO DEVinHOUSE ---");
        System.out.println("O que você deseja fazer hoje?");
        System.out.println("1 - Abrir uma conta\n2 - Acessar minha conta\n3 - Acessar administração do sistema\n4 - SAIR");
        MenuMain.executaMenu();
    }

    public static void executaMenu(){

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> aberturaConta();
                case 2 -> optaTipo();
                case 3 -> MenuAdminBanco.mostraMenuAdmin();
                case 4 -> System.exit(0);
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4);
    }

    public static void optaTipo(){
        System.out.println("\nQual tipo de conta você deseja acessar?\n1 - Conta Corrente\n2 - Conta poupança\n3 - Conta investimento\n4 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> MenuCorrentista.logaUsuario();
                case 2 -> MenuPoupanca.logaUsuario();
                case 3 -> MenuInvestimento.logaUsuario();
                case 4 -> mostrarMenu();
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4);

    }

    public static void aberturaConta(){
        System.out.println("\n--- ABERTURA DE CONTA | BANCO DEVinHouse --- ");
        System.out.println("Qual tipo de conta você deseja abrir?");
        System.out.println("1 - Conta Corrente\n2 - Conta Poupança\n3 - Conta investimento\n4 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> abreCC();
                case 2 -> abreCP();
                case 3 -> abreCI();
                case 4 -> mostrarMenu();
                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4);
    }

    public static void abreCC(){
        System.out.println("\nVOCÊ ESTÁ ABRINDO UMA NOVA CONTA CORRENTE NO BANCO DEVinHOuse!");
        System.out.print("Informe seu nome completo: ");
        String nome = input.nextLine();
        System.out.print("Informe seu CPF (SOMENTE NÚMEROS): ");
        String cpf = input.nextLine();
        System.out.print("Informe sua renda mensal: ");
        double rendaMensal = Double.parseDouble(input.nextLine());

       int contaId = 0;
       String contaStr;

        do {
            Random random = new Random();
            contaId = random.nextInt(10000) + 89999;
            contaStr = String.valueOf(contaId);
        } while (ContaCorrente.validaConta(contaStr));

        boolean validaAg = false;
        String agencia = null;
        do {
            System.out.println("Em qual agência você deseja abrir a conta?");
            System.out.println("1 - FLORIANÓPOLIS\n2 - SÃO JOSÉ");
            System.out.print("Digite o número correspondente à sua escolha: ");
            int ag = Integer.parseInt(input.nextLine());
            if (ag == 1){
                agencia = "001 - FLORIANOPOLIS";
                validaAg = true;
            } else if (ag == 2){
                agencia = "002 - SAO JOSE";
                validaAg = true;
            }
        } while (!validaAg);

        ContaCorrente.contas.add(new ContaCorrente(nome, cpf, rendaMensal, contaStr, agencia, "1"));

        System.out.println("\n******* PARABÉNS ******* ");
        System.out.println(nome + " sua conta de número: " + contaId+ " na agência " + agencia + " foi criada com sucesso." );
        System.out.printf("Você possui cheque especial aprovado no valor de: R$ %.2f", ContaCorrente.getChequeEspecial());
        System.out.println("\n** ATENÇÃO ** Guarde o número da sua conta, você precisará para realizar o login");
        System.out.println("\nVocê será redirecionado para o menu inicial ...");
        mostrarMenu();
    }

    public static void abreCP(){
        System.out.println("\nVOCÊ ESTÁ ABRINDO UMA NOVA CONTA POUPANÇA NO BANCO DEVinHOuse!");
        System.out.println("*** Aqui seu dinheiro rende 0.44% ao mês ***");
        System.out.print("Informe seu nome completo: ");
        String nome = input.nextLine();
        System.out.print("Informe seu CPF (SOMENTE NÚMEROS): ");
        String cpf = input.nextLine();
        System.out.print("Informe sua renda mensal: ");
        double rendaMensal = Double.parseDouble(input.nextLine());

        int contaId = 0;
        String contaStr;

        do {
            contaId = 0;
            Random random = new Random();
            contaId = random.nextInt(10000) + 89999;
            contaStr = String.valueOf(contaId);
        } while (ContaPoupanca.validaConta(contaStr));

        boolean validaAg = false;
        String agencia = null;
        do {
            System.out.println("Em qual agência você deseja abrir a conta?");
            System.out.println("1 - FLORIANÓPOLIS\n2 - SÃO JOSÉ");
            System.out.print("Digite o número correspondente à sua escolha: ");
            int ag = Integer.parseInt(input.nextLine());
            if (ag == 1){
                agencia = "001 - FLORIANOPOLIS";
                validaAg = true;
            } else if (ag == 2){
                agencia = "002 - SAO JOSE";
                validaAg = true;
            }
        } while (!validaAg);

        ContaPoupanca.contas.add(new ContaPoupanca(nome, cpf, rendaMensal, contaStr, agencia, "2"));

        System.out.println("\n******* PARABÉNS ******* ");
        System.out.println(nome + " sua conta poupança de número: " + contaId+ " na agência " + agencia + " foi criada com sucesso." );
        System.out.println("\n** ATENÇÃO ** Guarde o número da sua conta, você precisará para realizar o login");
        System.out.println("\nVocê será redirecionado para o menu inicial ...");
        mostrarMenu();
    }

    public static void abreCI(){
        System.out.println("\nVOCÊ ESTÁ ABRINDO UMA NOVA CONTA INVESTIMENTO NO BANCO DEVinHOuse!");
        System.out.println("*** Aqui seu dinheiro rende muito! ***");
        System.out.print("Informe seu nome completo: ");
        String nome = input.nextLine();
        System.out.print("Informe seu CPF (SOMENTE NÚMEROS): ");
        String cpf = input.nextLine();
        System.out.print("Informe sua renda mensal: ");
        double rendaMensal = Double.parseDouble(input.nextLine());

        int contaId = 0;
        String contaStr;

        do {
            contaId = 0;
            Random random = new Random();
            contaId = random.nextInt(10000) + 89999;
            contaStr = String.valueOf(contaId);
        } while (ContaInvestimento.validaConta(contaStr));

        boolean validaAg = false;
        String agencia = null;
        do {
            System.out.println("Em qual agência você deseja abrir a conta?");
            System.out.println("1 - FLORIANÓPOLIS\n2 - SÃO JOSÉ");
            System.out.print("Digite o número correspondente à sua escolha: ");
            int ag = Integer.parseInt(input.nextLine());
            if (ag == 1){
                agencia = "001 - FLORIANOPOLIS";
                validaAg = true;
            } else if (ag == 2){
                agencia = "002 - SAO JOSE";
                validaAg = true;
            }
        } while (!validaAg);

        ContaInvestimento.contas.add(new ContaInvestimento(nome, cpf, rendaMensal, contaStr, agencia, "3"));

        System.out.println("\n******* PARABÉNS ******* ");
        System.out.println(nome + " sua conta investimento de número: " + contaId+ " na agência " + agencia + " foi criada com sucesso." );
        System.out.println("\n** ATENÇÃO ** Guarde o número da sua conta, você precisará para realizar o login");
        System.out.println("\nVocê será redirecionado para o menu inicial ...");
        mostrarMenu();
    }

}
