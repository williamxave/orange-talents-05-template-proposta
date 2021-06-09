package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query("select c from Proposta c where c.statusDeValidacao = 'ELEGIVEL' and c.cartao is null")
    List<Proposta> listaProspostaElegiveis();
}
