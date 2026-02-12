package fr.eni.helpme.dal;

import fr.eni.helpme.bo.Response;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseRepository extends MongoRepository<Response, String>  {
}
