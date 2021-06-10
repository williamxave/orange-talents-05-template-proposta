package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.Biometria;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.validatores.IsValid64;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    @Lob
    @IsValid64
    private String fingerPrint;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(@NotBlank String fingerPrint) {
        this.fingerPrint = fingerPrint;

    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(
               this.fingerPrint,
                cartao
        );
    }

    private String decode(byte[] fingerPrint){
        String decodada = Base64.encodeBase64String(fingerPrint);
        return  decodada;
    }

    public String getBiometria() {
        return fingerPrint;
    }

}
