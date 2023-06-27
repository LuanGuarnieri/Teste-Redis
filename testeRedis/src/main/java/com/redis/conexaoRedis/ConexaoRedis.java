package com.redis.conexaoRedis;

import redis.clients.jedis.Jedis;

public class ConexaoRedis {

    private String url;
    private int porta;
    private String user;
    private String pass;

    public ConexaoRedis() {
        atribuirDadosConexao();
    }

    public Jedis getConexao() {
        Jedis jed = new Jedis(url, porta);
        jed.auth(user, pass);

        return jed;
    };

    private void atribuirDadosConexao() {
        this.porta  = Integer.parseInt(String.valueOf(DadosConexao.PORTA.getDado()));
        this.user   = String.valueOf(DadosConexao.USER.getDado());
        this.pass   = String.valueOf(DadosConexao.KEY.getDado());
        this.url    = String.valueOf(DadosConexao.URL.getDado());

    }
}
