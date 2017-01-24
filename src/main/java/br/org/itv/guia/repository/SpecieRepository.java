package br.org.itv.guia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.org.itv.guia.model.Specie;

public interface SpecieRepository extends CrudRepository<Specie, Long>{

	public Specie findByScientificName(String scientificName);
	
	@Query("SELECT max(s.objectVersion) FROM Specie s")
    public Integer findDataBaseLastVersion();
	
	@Query("SELECT s FROM Specie s WHERE s.objectVersion > ?#{[0]}")
    public List<Specie> findNewSpecies(Integer id);
	
}
