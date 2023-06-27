package com.redis.app;

import com.redis.conexaoRedis.ConexaoRedis;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
       new App().play();
    }

    private void play() {
        boolean continuar = true;
        String opcao;
        Scanner scr = new Scanner(System.in);;

        while (continuar) {
            menu();
            System.out.print("Opção: ");
            opcao = scr.next();

            switch (opcao) {
                //Inserir lembrete
                case "1":
                    inserirBilhete(scr, 1);
                    break;

                //Inserir objeto lembrete
                case "2":
                    inserirBilhete(scr, 0);
                    break;

                //Buscar lembrete
                case "3":
                    buscarBilhete(scr);
                    break;

                //Alterar nome da chave
                case "4":
                    alterarChave(scr);
                    break;

                //Excluir chave
                case "5":
                    excluirChave(scr);
                    break;

                //Mostrar chaves
                case "6":
                    mostrarChaves();
                    break;

                //Encerrar
                case "7":
                    continuar = false;
                    break;

                default:
                System.out.println("Opção inválida");
            }


            try {
                Thread.sleep(3000);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        scr.close();
    }

    private void menu() {
        String retorno =
                        "\n== MENU DE OPÇÕES ==\n\n" +
                                "1 - Inserir lembrete\n" +
                                "2 - Inserir objeto lembrete\n" +
                                "3 - Buscar lembrete\n" +
                                "4 - Alterar nome da chave\n" +
                                "5 - Excluir chave\n" +
                                "6 - Mostrar chaves\n"+
                                "7 - Finalizar\n";

        System.out.println(retorno);
    }

    private void inserirBilhete(Scanner scn, int opcao) {
        String titulo, msg;

        System.out.print("\nTitulo: ");
        titulo = scn.next();
        System.out.print("");
        System.out.print("Lembrete: ");
        msg = scn.next();

        if(opcao == 1) {
            new Lembrete(titulo, msg).gravarLembrete();
        } else {
            new Lembrete(titulo, msg).gravarObjLembrete();
        }
    }

    private void buscarBilhete(Scanner scn) {
        Jedis aux = new ConexaoRedis().getConexao();

        System.out.print("\nTitulo lembrete:");
        System.out.println(aux.get(scn.next().toUpperCase()));
    }

    private void alterarChave(Scanner scn) {
        String old, nov;

        System.out.print("\nChave atual:");
        old = scn.next();
        System.out.print("");
        System.out.print("Nova chave:");
        nov = scn.next();

        Jedis aux = new ConexaoRedis().getConexao();
        aux.rename(old.toUpperCase(), nov.toUpperCase());

        System.out.println("Chave alterada com sucesso");

    }

    private void excluirChave(Scanner scn) {
        Jedis aux = new ConexaoRedis().getConexao();
        String ret;
        Object obj;
        System.out.println("Chave a ser excluida:");
        obj = aux.del(scn.next().toUpperCase());

        if(obj != null) {
            ret = "Chave excluida com sucesso";
        } else {
            ret = "Chave não encontrada";
        }

        System.out.println(ret);
    }

    private void mostrarChaves() {
        Jedis aux = new ConexaoRedis().getConexao();
        String retorno = "";

        for(String a : aux.keys("*")) {
            retorno += a +"\n";
        }

        if (retorno.isEmpty()) {
            retorno = "Nenhuma chave cadastrada";
        }
        System.out.println("== Chaves ==\n" + retorno);

    }
}
