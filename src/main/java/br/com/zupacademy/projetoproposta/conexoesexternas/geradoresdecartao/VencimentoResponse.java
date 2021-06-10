package br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VencimentoResponse {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoResponse(String id,
                              Integer dia,
                              LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    @Deprecated
    public VencimentoResponse(){

    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

}
