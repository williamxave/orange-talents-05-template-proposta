package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.validators.CpfOuCnpf;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @CpfOuCnpf
    private String documento;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Min(value = 0)
    private BigDecimal salario;

    public PropostaRequest(@NotBlank String nome,@NotBlank String documento,@NotBlank String email,@NotNull @Min(value = 0) BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(this.nome, this.documento,this.email,this.salario);
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
