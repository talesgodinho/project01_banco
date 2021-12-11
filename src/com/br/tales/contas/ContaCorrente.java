package com.br.tales.contas;

public class ContaCorrente extends Conta{
    private static double chequeEspecial;

    public ContaCorrente(String nome, String cpf, double rendaMensal, int conta, String agencia) {
        super(nome, cpf, rendaMensal, conta, agencia);
        chequeEspecial = rendaMensal * 0.3;
    }

    public static double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public int getConta() {
        return super.getConta();
    }

        

    public static void exibeArray(){
        for (int i = 0; i<contas.size(); i++)
            System.out.println(contas.get(i).toString());
    }

}
