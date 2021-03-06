package com.br.tales.relatorios;

import java.util.ArrayList;

public class Transacoes {

    private String conta;
    private String cpf;
    private String tipo;
    private Double valor;
    private String dataHora;
    private String mensagem;

    public Transacoes(String conta, String cpf, String tipo, Double valor, String dataHora, String mensagem) {
        this.conta = conta;
        this.cpf = cpf;
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.mensagem = mensagem;
    }

    public static ArrayList<Transacoes> transacoesArray = new ArrayList<>();

    public String getConta() {
        return conta;
    }

    @Override
    public String toString() {
        return "Transacoes{" +
                "conta=" + conta +
                ", cpf='" + cpf + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                ", data='" + dataHora + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }
}
