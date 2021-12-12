package com.br.tales.contas;

import com.br.tales.menu.MenuAdminBanco;
import com.br.tales.menu.MenuCorrentista;
import com.br.tales.menu.MenuInvestimento;
import com.br.tales.menu.MenuPoupanca;
import com.br.tales.relatorios.Transacoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Conta{

    private String nome;
    private String cpf;
    private double rendaMensal;
    private String conta;
    private String agencia;
    private double saldo;
    private String tipodeConta; // 1: Corrente, 2: Poupança, 3: Investimento

    public String dataHora(){
        LocalDateTime atual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return atual.format(formatter);
    }

    public Conta(String nome, String cpf, double rendaMensal, String conta, String agencia, String tipodeConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
        this.conta = conta;
        this.agencia = agencia;
        this.tipodeConta = tipodeConta;
    }

    public static ArrayList<Conta> contas = new ArrayList<>();

    Scanner input = new Scanner(System.in);

    public void saque(double sacar, int tipoConta){
        if(sacar <= getSaldo()){ //O saldo que for referente à conta corrente é tratado anteriormente, já com limite de cheque especial.
            this.saldo -= sacar;
            System.out.printf("\nFoi realizado o saque do valor de R$ %.2f", sacar);
            if (tipoConta == 1){
                System.out.printf("\nSeu novo saldo total com cheque especial é de: R$ %.2f", getSaldo());
            }
        } else {
            System.out.printf("\nSaldo insuficiente para saque. Seu saldo é: %.2f", getSaldo());
        }
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "SAQUE", sacar, dataHora(), "saque realizado"));
        System.out.println("\n*** Redirecionando para o menu de login ***");

        switch (tipoConta){
            case 1 -> MenuCorrentista.logaUsuario();
            case 2 -> MenuPoupanca.logaUsuario();
            case 3 -> MenuInvestimento.logaUsuario();
        }
    }

    public void deposito(double deposita, int tipoConta){
        if (deposita > 0){
            this.saldo += deposita;
            System.out.printf("\nFoi depositado o valor de R$ %.2f", deposita);
            System.out.printf("\nSeu novo saldo total com cheque especial é de: R$ %.2f", getSaldo());
        } else {
            System.out.println("\nSeu depósito não pôde ser realizado. Informe um valor maior que ZERO.");
        }
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "DEPÓSITO", deposita, dataHora(), "depósito realizado"));
        System.out.println("\n*** Redirecionando para o menu de login ***");

        switch (tipoConta){
            case 1 -> MenuCorrentista.logaUsuario();
            case 2 -> MenuPoupanca.logaUsuario();
            case 3 -> MenuInvestimento.logaUsuario();
        }
    }

    public void extrato(String contaAtual, int tipoConta){
        System.out.println("\n=============== EXTRATO DE TRANSAÇÕES ===============");
        for (int i = 0; i < Transacoes.transacoesArray.size(); i++){
            if (Transacoes.transacoesArray.get(i).getConta().equals(contaAtual)){
                System.out.println(Transacoes.transacoesArray.get(i).toString());
            }
        }
        System.out.println("\n*** Redirecionando para o menu de login ***");

        switch (tipoConta){
            case 1 -> MenuCorrentista.logaUsuario();
            case 2 -> MenuPoupanca.logaUsuario();
            case 3 -> MenuInvestimento.logaUsuario();
        }
    }

    public void transferir(double valor, String destino, int tipoConta){
        String cpf_des = null;
        String nome = null;
        String conta_des = null;
        String agencia_des = null;
        String mensagem;
        if(valor <= getSaldo()){
            this.saldo -= valor;
            for (int i = 0; i < Conta.contas.size(); i++){
                if (Conta.contas.get(i).getConta().equals(destino)){
                    Conta.contas.get(i).setSaldo(Conta.contas.get(i).getSaldo()+valor);
                    cpf_des = Conta.contas.get(i).getCpf();
                    nome = Conta.contas.get(i).getNome();
                    conta_des = Conta.contas.get(i).getConta();
                    agencia_des = Conta.contas.get(i).getAgencia();
                    mensagem = ("recebido transferência de: " + getNome() + " Conta: " + getConta() + " Ag: " + getAgencia());
                    Transacoes.transacoesArray.add(new Transacoes(conta_des, cpf_des, "TRANSFERÊNCIA RECEBIDA", valor, dataHora(), mensagem));
                }
            }
            System.out.printf("\nFoi realizado a transferência do valor de R$ %.2f", valor);
            System.out.print(" para " + nome);
            System.out.println(" conta: " + conta_des);
        } else {
            System.out.printf("\nSaldo insuficiente para transferência. Seu saldo é: %.2f", getSaldo());
        }
        String mensagem2 = ("transferência para: " + nome + " Conta: " + conta_des + " Ag: " + agencia_des);
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "TRANSFERÊNCIA REALIZADA", valor, dataHora(), mensagem2));
        System.out.println("\n*** Redirecionando para o menu de login ***");

        switch (tipoConta){
            case 1 -> MenuCorrentista.logaUsuario();
            case 2 -> MenuPoupanca.logaUsuario();
            case 3 -> MenuInvestimento.logaUsuario();
        }
    }

    public String getTipodeConta() {
        return tipodeConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public String getConta() {
        return conta;
    }

    public String getCpf() {
        return cpf;
    }

    public void alterarDados(int tipoConta){
        System.out.println("\n--- ATUALIZAÇÃO CADASTRAL ---");
        System.out.println("Os dados referente ao banco (conta e agência) e o seu CPF não podem ser modificados");
        System.out.println("Seu nome cadastrado é: " + getNome());
        System.out.print("Informe o novo nome para atualizar: ");
        setNome(input.nextLine());
        System.out.println("Sua renda cadastrada é: " + getRendaMensal());
        System.out.print("Informe sua nova renda: ");
        setRendaMensal(Double.parseDouble(input.nextLine()));
        System.out.printf("\nDados atualizados com sucesso!\nSeu novo nome cadastrado é: %s\nSua nova renda é: R$ %.2f", getNome(), getRendaMensal());
        System.out.println("\n*** Redirecionando para o menu de login ***");

        switch (tipoConta){
            case 1 -> MenuCorrentista.logaUsuario();
            case 2 -> MenuPoupanca.logaUsuario();
            case 3 -> MenuInvestimento.logaUsuario();
        }
    }

    public static boolean validaConta(String contaId){
        boolean valida = false;

        for (int i = 0; i < Conta.contas.size(); i++){
            valida = Conta.contas.get(i).getConta().contains(contaId);
        }
        return valida;
    }

    public static void totalInvestido() {
        String cp = "2";
        String ci = "3";
        double totalInvestido = 0;

        for (int i = 0; i < Conta.contas.size(); i++) {
            if (Conta.contas.get(i).getTipodeConta().equals(cp) || Conta.contas.get(i).getTipodeConta().equals(ci)) {
                totalInvestido += Conta.contas.get(i).saldo;
            }
        }
        System.out.printf("\nO valor total investido no banco é de R$ %.2f", totalInvestido);
        System.out.println("\nObservação: Considera-se valor investidos aqueles pertences às contas Poupança e Investimento");

        System.out.println("\n*** Redirecionando para o menu anterior ***");
        MenuAdminBanco.mostraMenuAdmin();
    }

    @Override
    public String toString() {
        return "Conta{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", conta='" + conta + '\'' +
                ", agencia='" + agencia + '\'' +
                '}';
    }
}


