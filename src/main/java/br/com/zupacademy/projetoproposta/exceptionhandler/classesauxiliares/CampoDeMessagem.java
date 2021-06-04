package br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares;

public class CampoDeMessagem {

    private String mensagem;
    private String nomeDoCampo;

    public CampoDeMessagem(String mensagem, String nomeDoCampo) {
        this.mensagem = mensagem;
        this.nomeDoCampo = nomeDoCampo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeDoCampo() {
        return nomeDoCampo;
    }
}
