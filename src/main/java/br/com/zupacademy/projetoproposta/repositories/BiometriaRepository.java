package br.com.zupacademy.projetoproposta.repositories;

import br.com.zupacademy.projetoproposta.models.Biometria;
import br.com.zupacademy.projetoproposta.models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends JpaRepository<Biometria, Long> {
}
