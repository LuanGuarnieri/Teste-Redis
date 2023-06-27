package com.redis.app;

import com.google.gson.Gson;
import com.redis.conexaoRedis.ConexaoRedis;

public class Lembrete {
    private String titulo;
    private String lembrete;

    public Lembrete(String titulo, String lembrete) {
        setTitulo(titulo);
        setLembrete(lembrete);
    }
    public Lembrete() {
        setTitulo(null);
        setLembrete(null);
    }

    private void setTitulo(String titulo) {
        if(titulo != null) {
            this.titulo = titulo.toUpperCase();
        }
        else {
            this.titulo = "Titulo padrão";
        }
    }
    private void setLembrete(String lembrete) {
        if(lembrete != null) {
            this.lembrete = lembrete.toUpperCase();
        }
        else {
            this.lembrete = "Lembrete padrão";
        }
    }

    //gravam o obj
    public void gravarLembrete() {
        String retorno;
        try {
            ConexaoRedis conexao = new ConexaoRedis();
            conexao.getConexao().set(this.titulo, this.lembrete);

            retorno = "Lembrete salvo";
        } catch (Exception e) {
            retorno = "Pau com o redis kkkkk";
        }
        System.out.println(retorno);
    }

    public void gravarObjLembrete() {
        String retorno;

        try {
            Gson g = new Gson();
            ConexaoRedis conexao = new ConexaoRedis();

            conexao.getConexao().set(this.titulo, g.toJson(this));

            retorno = "Objeto gravado";

        } catch (Exception e) {
            retorno = "Deu pau em gravar o objeto no redis kkkkk";
        }

        System.out.println(retorno);
    }
}
