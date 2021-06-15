package br.com.zupacademy.projetoproposta.enums;

public enum TipoDeCarteira {

    PAYPAL;

    //Valida a carteira
    public static boolean verificaCarteira(String possivelCarteira) {
        for (TipoDeCarteira carteira : TipoDeCarteira.values()) {
            if (carteira.name().equals(possivelCarteira)) {
                return true;
            }
        }
        return false;
    }

}

