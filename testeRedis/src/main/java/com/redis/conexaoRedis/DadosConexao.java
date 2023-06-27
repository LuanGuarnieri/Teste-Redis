package com.redis.conexaoRedis;

public enum DadosConexao {

    URL("redis-12200.c296.ap-southeast-2-1.ec2.cloud.redislabs.com"),
    PORTA("12200"),
    KEY("BaseRedisLuanCloud"),
    USER("default");
    private String dado;
    DadosConexao(String dado) {
        this.dado = dado;
    }

    public String getDado() {
        return this.dado;
    }
}
