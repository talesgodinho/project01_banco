package com.br.tales;

import com.br.tales.menu.MenuMain;

/*
Projeto do módulo 1 do Tech-Dive
Instituto Senai de Tecnologia - Santa Catarina
Desenvolvido por Tales Felix da Luz Godinho | Programador I - IST
*/

public class Main {

    public static void main(String[] args) {
        //EXECUTA O MENU PRINCIPAL DO PROJETO, DIRECIONANDO PARA OS CAMINHOS CONFORME SOLICITADO PELO USUARIO
        MenuMain.mostrarMenu();
    }
}
/*
Não foi realizado neste sistema o tratamento de exceções, devido a este conteúdo ainda não ter sido desenvolvido no TechDive.
Com isso, as informações a serem inseridas devem ser corretas para evitar de recomeçar os testes. Como por exemplo, os números
das contas em seus devidos tipos de conta.

Um número de Conta Corrente ao tentar acessar uma conta tipo poupança, retornará exception.
*/

