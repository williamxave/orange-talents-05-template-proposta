package br.com.zupacademy.projetoproposta.dtos;

import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.health.ConfigMetricas;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.repositories.PropostaRepository;
import br.com.zupacademy.projetoproposta.validatores.CpfOuCnpf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

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

    @Enumerated(EnumType.STRING)
    private StatusDeValidacao statusDeValidacao;

    public PropostaRequest(@NotBlank String nome,@NotBlank String documento,@NotBlank String email,@NotNull @Min(value = 0) BigDecimal salario) {
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
    }

    public Proposta verificaDuplicidadeDeDocumento(@Valid PropostaRequest request, PropostaRepository propostaRepository) throws DocumentException {
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(request.getDocumento());
        if(possivelProposta.isPresent()) {
            throw new DocumentException(HttpStatus.UNPROCESSABLE_ENTITY, "Documento já está cadastrado!");
        }
        Proposta novaProposta = request.toModel();
        propostaRepository.save(novaProposta);
        return novaProposta;
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

    public StatusDeValidacao getStatusDeValidacao() {
        return statusDeValidacao;
    }

}
