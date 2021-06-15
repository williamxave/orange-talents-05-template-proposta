package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.enums.TipoDeCarteira;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Carteira;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    private String email;

    @NotNull
    private String carteira;

    public CarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getCarteira() {
        return carteira;
    }
    public String getEmail() {
        return email;
    }


    public Carteira toModel(Cartao cartao){
        return new Carteira(
                this.email,
                cartao,
                TipoDeCarteira.valueOf(this.carteira));
    }

}
