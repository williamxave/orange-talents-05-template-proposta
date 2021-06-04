package br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDeErro extends ErroPadrao{

    private List<CampoDeMessagem> list =  new ArrayList<>();

    public ValidadorDeErro(Integer statusCode, String message, Long timeStamp) {
        super(statusCode, message, timeStamp);
    }

    public List<CampoDeMessagem> getList() {
        return list;
    }

    public void addErro(String nomeDoCampo, String message) {
        list.add(new CampoDeMessagem(nomeDoCampo,message));
    }
}
