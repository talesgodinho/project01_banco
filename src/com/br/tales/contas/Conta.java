package com.br.tales.contas;

import com.br.tales.menu.MenuMain;
import com.br.tales.relatorios.Transacoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Conta{

    private String nome;
    private String cpf;
    private double rendaMensal;
    private static int conta;
    private String agencia;
    private double saldo;

    public String dataHora(){
        LocalDateTime atual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return atual.format(formatter);
    }

    public Conta(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        this.nome = nome;
        this.cpf = cpf;
        this.rendaMensal = rendaMensal;
        this.conta = conta;
        this.agencia = agencia;
    }

    public static ArrayList<Conta> contas = new ArrayList<>();

    Scanner input = new Scanner(System.in);

    public double getSaldo() {
        return saldo;
    }

    public void saque(double sacar){
        if(sacar <= getSaldo()){
            this.saldo -= sacar;
            System.out.printf("\nFoi realizado o saque do valor de R$ %.2f", sacar);
            System.out.printf("\nSeu novo saldo total com cheque especial é de: R$ %.2f", getSaldo());
        } else {
            System.out.printf("\nSaldo insuficiente para saque. Seu saldo é: %.2f", getSaldo());
        }
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "SAQUE", sacar, dataHora(), "saque realizado"));
        System.out.println("\n*** Redirecionando para o menu inicial ***");
        MenuMain.mostrarMenu();
    }

    public void deposito(double deposita){
        if (deposita > 0){
            this.saldo += deposita;
            System.out.printf("\nFoi depositado o valor de R$ %.2f", deposita);
            System.out.printf("\nSeu novo saldo total com cheque especial é de: R$ %.2f", getSaldo());
        } else {
            System.out.println("\nSeu depósito não pôde ser realizado. Informe um valor maior que ZERO.");
        }
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "DEPÓSITO", deposita, dataHora(), "depósito realizado"));
        System.out.println("\n*** Redirecionando para o menu inicial ***");
        MenuMain.mostrarMenu();
    }

    public void extrato(String cpf){
        for (int i = 0; i < Transacoes.transacoesArray.size(); i++){
            if (Transacoes.transacoesArray.get(i).getCpf().equals(cpf)){
                System.out.println(Transacoes.transacoesArray.get(i).toString());
            }
        }
        System.out.println("\n*** Redirecionando para o menu inicial ***");
        MenuMain.mostrarMenu();
    }

    public void transferir(double valor, String destino){
        String cpf_des = null;
        String nome = null;
        int conta;
        String mensagem;
        if(valor <= getSaldo()){
            this.saldo -= valor;
            for (int i = 0; i < ContaCorrente.contas.size(); i++){
                if (ContaCorrente.contas.get(i).getCpf().equals(destino)){
                    ContaCorrente.contas.get(i).setSaldo(ContaCorrente.contas.get(i).getSaldo()+valor);
                    cpf_des = ContaCorrente.contas.get(i).getCpf();
                    nome = ContaCorrente.contas.get(i).getNome();
                    conta = ContaCorrente.contas.get(i).getConta();
                    mensagem = ("recebido transferência de: " + getNome());
                    Transacoes.transacoesArray.add(new Transacoes(conta, cpf_des, "TRANSFERÊNCIA RECEBIDA", valor, dataHora(), mensagem));
                }
            }
            System.out.printf("\nFoi realizado a transferência do valor de R$ %.2f", valor);
            System.out.print(" para " + nome);
            System.out.println(" com o CPF: " + cpf_des);
        } else {
            System.out.printf("\nSaldo insuficiente para transferência. Seu saldo é: %.2f", getSaldo());
        }
        String mensagem2 = ("transferência para: " + nome);
        Transacoes.transacoesArray.add(new Transacoes(getConta(), getCpf(), "TRANSFERÊNCIA REALIZADA", valor, dataHora(), mensagem2));
        System.out.println("\n*** Redirecionando para o menu inicial ***");
        MenuMain.mostrarMenu();
    }

    public String getAgencia() {
        return agencia;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public String getNome() {
        return nome;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public int getConta() {
        return conta;
    }

    public String getCpf() {
        return cpf;
    }

    public void alterarDados(){
        System.out.println("\n--- ATUALIZAÇÃO CADASTRAL ---");
        System.out.print("Informe o novo nome para atualizar: ");
        setNome(input.nextLine());
        System.out.print("Informe sua nova renda: ");
        setRendaMensal(Double.parseDouble(input.nextLine()));
        System.out.printf("\nDados atualizados com sucesso!\nSeu novo nome cadastrado é: %s\nSua nova renda é: R$ %.2f", getNome(), getRendaMensal());
        System.out.println("\n*** Redirecionando para o menu inicial ***");
        MenuMain.mostrarMenu();
    }

    public static boolean validaConta(int contaId){
        boolean valida;
        if (contas.contains(contaId)){
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }


}


