package br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares;

public class CampoDeMessagem {

    private String nomeDoCampo;
    private String mensagem;


    public CampoDeMessagem(String nomeDoCampo,String mensagem) {
        this.nomeDoCampo = nomeDoCampo;
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeDoCampo() {
        return nomeDoCampo;
    }
}
