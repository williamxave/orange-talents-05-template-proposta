package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.dtos.VencimentoResponse;
import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Vencimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VencimentoRepository extends CrudRepository<Vencimento, Long> {

}
