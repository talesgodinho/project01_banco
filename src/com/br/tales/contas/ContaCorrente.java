package com.br.tales.contas;

public class ContaCorrente extends Conta{
    private static double chequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        super(nome, cpf, rendaMensal, conta, agencia);
        setChequeEspecial(rendaMensal * 0.3); // Defino o valor de cheque especial para 30% da renda mensal do cliente
        setSaldo(getSaldo()+getChequeEspecial());
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
    public int getConta() {
        return super.getConta();
    }

    @Override
    public String getCpf() {
        return super.getCpf();
    }
}
