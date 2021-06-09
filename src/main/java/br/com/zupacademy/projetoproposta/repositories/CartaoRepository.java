package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.Cartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao, Long> {
}
