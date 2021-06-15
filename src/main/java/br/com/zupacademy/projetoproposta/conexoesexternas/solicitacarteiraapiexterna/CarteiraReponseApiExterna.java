package br.com.zupacademy.projetoproposta.conexoesexternas.solicitacarteiraapiexterna;

public class CarteiraReponseApiExterna {

    private String resultado;

    private String id;

    public CarteiraReponseApiExterna(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
