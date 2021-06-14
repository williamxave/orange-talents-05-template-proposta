package br.com.zupacademy.projetoproposta.conexoesexternas.bloqueiodocartao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BloqueioCartaoResponse {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
