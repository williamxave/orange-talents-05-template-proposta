package br.com.zupacademy.projetoproposta.repositories;
import br.com.zupacademy.projetoproposta.models.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {

    Optional<Cartao> findByUuid(String uuid);

}
