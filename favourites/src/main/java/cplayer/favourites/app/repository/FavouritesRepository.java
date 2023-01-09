package cplayer.favourites.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cplayer.favourites.app.model.Favourites;

public interface FavouritesRepository extends CrudRepository<Favourites, Integer> {

	@Query("select f from Favourites f where f.username= ?1")
	List<Favourites> findByUsername(String username);

} // Player data stored in repository is accessed.
