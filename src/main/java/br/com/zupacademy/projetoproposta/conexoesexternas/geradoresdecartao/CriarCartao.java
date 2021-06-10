package br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao;

import br.com.zupacademy.projetoproposta.conexoesexternas.geradoresdecartao.SolicitaCartaoResponse;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Vencimento;
import br.com.zupacademy.projetoproposta.repositories.CartaoRepository;
import br.com.zupacademy.projetoproposta.repositories.VencimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriarCartao {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private VencimentoRepository vencimentoRepository;

    //Método responsavel por criar um cartão baseado na response da api externa
    public Cartao criaCartao(SolicitaCartaoResponse solicitaCartaoResponse){
        Cartao possivelCartao = solicitaCartaoResponse.toModel();
        cartaoRepository.save(possivelCartao);
        Vencimento vencimentoDoCartao = solicitaCartaoResponse.geraVencimento(possivelCartao);
        vencimentoRepository.save(vencimentoDoCartao);
       return possivelCartao;
    }
}
