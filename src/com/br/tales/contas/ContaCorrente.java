package com.br.tales.contas;

public class ContaCorrente extends Conta{
    private static double chequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, String conta, String agencia, String tipodeConta) {
        super(nome, cpf, rendaMensal, conta, agencia, tipodeConta);
        setChequeEspecial(rendaMensal * 0.3); // Defino o valor de cheque especial para 30% da renda mensal do cliente
        setSaldo(getSaldo()+getChequeEspecial()); //Incrementa o saldo com o limite do cheque especial
    }

    public static void setChequeEspecial(double chequeEspecial) {
        ContaCorrente.chequeEspecial = chequeEspecial;
    }

    public static double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public void setSaldo(double saldo) {
        super.setSaldo(saldo);
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }
}
