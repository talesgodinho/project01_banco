package com.br.tales.menu;

import com.br.tales.contas.Conta;
import com.br.tales.contas.ContaCorrente;
import com.br.tales.relatorios.Transacoes;

public class MenuAdminBanco extends MenuMain{

    public static void mostraMenuAdmin(){
        System.out.println("\n*** BEM-VINDO *** | Você possui as seguintes opções:");
        System.out.println("\n1 - Histórico de transações\n2 - Listar todas Contas Correntes\n3 - Listar todas Contas Poupanças" +
                "\n4 - Listar todas Contas Investimento\n5 - Listar Contas com saldo negativo\n6 - Total de valores investidos" +
                "\n7 - Todas as transações por cliente\n8 - VOLTAR");

        int op;

        do {
            System.out.print("Digite o número da opção desejada: ");
            op = Integer.parseInt(input.nextLine());

            switch (op) {
                case 1 -> {
                    System.out.println("\n=============== HISTÓRICO DE TRANSAÇÕES ===============");
                    for (int i = 0; i < Transacoes.transacoesArray.size(); i++){
                        System.out.println(Transacoes.transacoesArray.get(i).toString());
                    }
                    System.out.println("\n*** Redirecionando para o menu anterior ***");
                    mostraMenuAdmin();
                }
                case 2 -> {
                    String tipoConta = "1";
                    System.out.println("\n=============== CONTAS CORRENTES ===============");
                    for (int i = 0; i < Conta.contas.size(); i++){
                        if (Conta.contas.get(i).getTipodeConta().equals(tipoConta)){
                            System.out.println(Conta.contas.get(i).toString());
                        }
                    }
                    System.out.println("\n*** Redirecionando para o menu anterior ***");
                    mostraMenuAdmin();
                }
                case 3-> {
                    String tipoConta = "2";
                    System.out.println("\n=============== CONTAS POUPANÇA ===============");
                    for (int i = 0; i < Conta.contas.size(); i++){
                        if (Conta.contas.get(i).getTipodeConta().equals(tipoConta)){
                            System.out.println(Conta.contas.get(i).toString());
                        }
                    }
                    System.out.println("\n*** Redirecionando para o menu anterior ***");
                    mostraMenuAdmin();
                }
                case 4 ->{
                    String tipoConta = "3";
                    System.out.println("\n=============== CONTAS INVESTIMENTO ===============");
                    for (int i = 0; i < Conta.contas.size(); i++){
                        if (Conta.contas.get(i).getTipodeConta().equals(tipoConta)){
                            System.out.println(Conta.contas.get(i).toString());
                        }
                    }
                    System.out.println("\n*** Redirecionando para o menu anterior ***");
                    mostraMenuAdmin();
                }
                case 5 ->{
                    String tipoConta = "1";
                    System.out.println("\n=============== CONTAS NEGATIVAS ===============");
                    for (int i = 0; i < ContaCorrente.contas.size(); i++){
                        if ((ContaCorrente.contas.get(i).getSaldo() - (ContaCorrente.contas.get(i).getRendaMensal() * 0.3)) < 0
                        && Conta.contas.get(i).getTipodeConta().equals(tipoConta)){
                            System.out.println(ContaCorrente.contas.get(i).toString());
                        }
                    }
                    System.out.println("\n*** Redirecionando para o menu anterior ***");
                    mostraMenuAdmin();
                }
                case 6 -> Conta.totalInvestido();
                case 7->{
                    System.out.println("====== HISTÓRIO DAS TRANSAÇÕES DE UM CLIENTE ======");
                    System.out.print("Informe o número da conta que você deseja consultar: ");
                    String numConta = input.nextLine();

                    for (int i = 0; i < Transacoes.transacoesArray.size(); i++){
                        if (Transacoes.transacoesArray.get(i).getConta().equals(numConta)){
                            System.out.println(Transacoes.transacoesArray.get(i).toString());
                        }
                    }
                    System.out.println("\n*** Redirecionando para o menu de login ***");
                    mostraMenuAdmin();
                }
                case 8 -> MenuMain.mostrarMenu();

                default -> System.out.println("Opção inválida, tente novamente");
            }
        } while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6 && op != 7 && op != 8);

    }

}
