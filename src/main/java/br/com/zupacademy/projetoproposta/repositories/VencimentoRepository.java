package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.Cartao;
import br.com.zupacademy.projetoproposta.models.Vencimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VencimentoRepository extends CrudRepository<Vencimento, Long> {
}
