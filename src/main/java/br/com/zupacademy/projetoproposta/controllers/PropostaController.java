package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.conexoesexternas.validadorespropostas.AnalisarSolicitacaoRequest;
import br.com.zupacademy.projetoproposta.conexoesexternas.validadorespropostas.AnalisarSolicitacaoResponse;
import br.com.zupacademy.projetoproposta.dtos.PropostaRequest;
import br.com.zupacademy.projetoproposta.dtos.PropostaResponse;
import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.health.ConfigMetricas;
import br.com.zupacademy.projetoproposta.models.Proposta;
import br.com.zupacademy.projetoproposta.enums.StatusDeValidacao;
import br.com.zupacademy.projetoproposta.repositories.PropostaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/proposta")
public class PropostaController {

    private AnalisarSolicitacaoRequest analisarSolicitacaoRequest;

    private PropostaRepository propostaRepository;

    private ConfigMetricas configMetricas;

    public PropostaController(AnalisarSolicitacaoRequest analisarSolicitacaoRequest,
                              PropostaRepository propostaRepository, ConfigMetricas configMetricas
                              ) {
        this.analisarSolicitacaoRequest = analisarSolicitacaoRequest;
        this.propostaRepository = propostaRepository;
        this.configMetricas = configMetricas;

    }

    @PostMapping
    public ResponseEntity<AnalisarSolicitacaoResponse> cadastraProposta(@RequestBody @Valid PropostaRequest request,
                                                                        UriComponentsBuilder builder) throws DocumentException{

            //Verifica a duplicidade da proposta, transforma em model e salva no banco
            Proposta possivelProsta = request.verificaDuplicidadeDeDocumento(request,propostaRepository);
            //Manda a proposta para a api externa que vai fazer a analise
            AnalisarSolicitacaoResponse analisarSolicitacaoResponse = analisarSolicitacaoRequest.validacaoDeDocumento(possivelProsta);
            //Faz a alteração do status da api externa para o status da aplicação
            analisarSolicitacaoResponse.alteraStatusDaProposta(possivelProsta);
            //Metrica para contar quantas propostas foram cadastradas
            configMetricas.addProposta();
            propostaRepository.save(possivelProsta);
            URI uri = builder.path("/proposta/{id}").buildAndExpand(possivelProsta.getId()).toUri();
            if(possivelProsta.getStatusDeValidacao().equals(StatusDeValidacao.NAO_ELEGIVEL)){
                return ResponseEntity.unprocessableEntity().body(analisarSolicitacaoResponse);
            }
            return ResponseEntity.created(uri).body(analisarSolicitacaoResponse);
        }

        @GetMapping("/{id}")
        public  ResponseEntity<PropostaResponse> verProposta(@PathVariable("id") Long id,UriComponentsBuilder builder){
            Optional<Proposta> possivelProposta = propostaRepository.findById(id);
            if(!possivelProposta.isPresent()){
               new DocumentException(HttpStatus.NOT_FOUND, "Proposta não é válida!");
            }
            URI uri = builder.path("/proposta/{id}").buildAndExpand(possivelProposta.get().getId()).toUri();
            return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }
}
