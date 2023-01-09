package cplayer.user.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import cplayer.user.app.model.User;


public interface UserRepository extends MongoRepository<User, String> {
	
}
