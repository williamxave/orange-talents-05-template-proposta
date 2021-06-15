package br.com.zupacademy.projetoproposta.conexoesexternas.solicitacarteiraapiexterna;

import br.com.zupacademy.projetoproposta.enums.TipoDeCarteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

public class CarteiraRequestApiExterna {
    @NotBlank
    private String email;

    @NotBlank
    private String carteira;

    public CarteiraRequestApiExterna(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
