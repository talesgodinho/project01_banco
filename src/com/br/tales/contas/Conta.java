package com.br.tales.contas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Conta<valida> {

    private String nome;
    private String cpf;
    private double rendaMensal;
    private static int conta;
    private String agencia;
    private double saldo;

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
            System.out.printf("\nSeu novo saldo é de: R$ %.2f", getSaldo());
        } else {
            System.out.printf("\nSaldo insuficiente para saque. Seu saldo é: %.2f", getSaldo());
        }
    }

    public void deposito(double deposita){
        if (deposita > 0){
            this.saldo += deposita;
            System.out.printf("\nFoi depositado o valor de R$ %.2f", deposita);
            System.out.printf("\nSeu novo saldo é de: R$ %.2f", getSaldo());
        } else {
            System.out.println("\nSeu depósito não pôde ser realizado. Informe um valor maior que ZERO.");
        }
    }

    public void extrato(){

    }

    public void transferir(){

    }

    public static void setNome(String nome) {
        nome = nome;
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

    public void setConta(int conta) {
        this.conta = conta;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void alterarDados(){
        System.out.println("\n--- ATUALIZAÇÃO CADASTRAL ---");
        System.out.print("Informe o novo nome para atualizar: ");
        setNome(input.nextLine());
        System.out.print("Informe sua nova renda: ");
        setRendaMensal(Double.parseDouble(input.nextLine()));
        System.out.printf("\nDados atualizados com sucesso!\nSeu novo nome cadastrado é: %s\nSua nova renda é: R$ %.2f", getNome(), getRendaMensal());
    }


    public static boolean validaConta(int conta){
        boolean valida;
        if (contas.contains(conta)){
            valida = false;
        } else {
            valida = true;
        }
        return valida;
    }

    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }


}

