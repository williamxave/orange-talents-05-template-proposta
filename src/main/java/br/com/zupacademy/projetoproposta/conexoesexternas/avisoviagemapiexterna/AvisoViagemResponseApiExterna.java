package br.com.zupacademy.projetoproposta.conexoesexternas.avisoviagemapiexterna;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AvisoViagemResponseApiExterna {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemResponseApiExterna(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
