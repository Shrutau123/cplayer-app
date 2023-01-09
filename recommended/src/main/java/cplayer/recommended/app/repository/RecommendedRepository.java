package cplayer.recommended.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cplayer.recommended.app.model.Recommended;


public interface RecommendedRepository extends MongoRepository<Recommended, String> {
	
}
