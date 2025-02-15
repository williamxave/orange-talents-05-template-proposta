package br.com.zupacademy.projetoproposta.controllers;

import br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao.SolicitaCartaoFeign;
import br.com.zupacademy.projetoproposta.conexoesexternas.solicitacarteiraapiexterna.CarteiraRequestApiExterna;
import br.com.zupacademy.projetoproposta.customexceptions.DocumentException;
import br.com.zupacademy.projetoproposta.dtos.CarteiraRequest;
import br.com.zupacademy.projetoproposta.enums.TipoDeCarteira;
import br.com.zupacademy.projetoproposta.exceptionhandler.classesauxiliares.CampoDeMessagem;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Carteira;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;
import br.com.zupacademy.projetoproposta.repositories.CarteiraRepository;
import feign.FeignException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class SolicitaCarteiraController {

    private final CartaoRepository cartaoRepository;
    private final SolicitaCartaoFeign solicitaCartaoFeign;
    private final CarteiraRepository carteiraRepository;

    public SolicitaCarteiraController(CartaoRepository cartaoRepository,
                                      SolicitaCartaoFeign solicitaCartaoFeign,
                                      CarteiraRepository carteiraRepository) {
        this.cartaoRepository = cartaoRepository;
        this.solicitaCartaoFeign = solicitaCartaoFeign;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping("/{uuid}")
    public ResponseEntity<?> adicionaCarteira(@PathVariable(name = "uuid") String uuid,
                                                                      @Valid @RequestBody CarteiraRequest carteiraRequest,
                                                                        UriComponentsBuilder builder) throws DocumentException {
        Optional<Cartao> possivelCartao = Optional.ofNullable(cartaoRepository.findByUuid(uuid).orElseThrow(
                () -> new DocumentException(HttpStatus.NOT_FOUND, "Cartão não encontrado! Tente outrow")));

        //Verifica se a carteira que veio da request existe no sistema
        boolean carteiraPrecisaSerValida = TipoDeCarteira.verificaCarteira(carteiraRequest.getNomeDaCarteira());

        if(carteiraPrecisaSerValida) {
            CarteiraRequestApiExterna carteiraRequestApiExterna = new CarteiraRequestApiExterna(carteiraRequest.getEmail(), carteiraRequest.getNomeDaCarteira());
            associaCarteira(uuid, carteiraRequestApiExterna);
            validaCarteiraIgual(carteiraRequest,possivelCartao.get());

            Carteira carteira = carteiraRequest.toModel(possivelCartao.get());
            cartaoRepository.save(possivelCartao.get());
            carteiraRepository.save(carteira);
            URI uri =  builder.path("/carteira/{uuid}").buildAndExpand(carteira.getUuid()).toUri();
            return  ResponseEntity.created(uri).build();
        }
        return ResponseEntity.badRequest().body(new CampoDeMessagem("Carteira",
                                                    "Carteira está inválida! A carteira [ " + carteiraRequest.getNomeDaCarteira() + " ] não existe"));
    }

    private void associaCarteira(String uuidCartao,CarteiraRequestApiExterna requestApiExterna)throws DocumentException {
        try {
            solicitaCartaoFeign.associaCarteira(uuidCartao, requestApiExterna);
        } catch (FeignException e) {
            new DocumentException(HttpStatus.valueOf(e.status()), "Não foi possivel associadar a carteira");
        }
    }

    //Preciso rever isso
    public  void validaCarteiraIgual(CarteiraRequest carteiraRequest, Cartao cartao){
      Optional<Carteira> carteira = carteiraRepository.findByNomeDaCarteiraAndCartaoId(TipoDeCarteira.valueOf(carteiraRequest.getNomeDaCarteira()),cartao.getId());
       //Optional<Carteira> carteira = carteiraRepository.buscar(cartao.getId());
        if(carteira.isPresent()){
            new DocumentException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já vinculada a esse cartão");
        }
    }
}
