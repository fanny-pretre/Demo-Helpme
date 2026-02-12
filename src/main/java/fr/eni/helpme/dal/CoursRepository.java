package fr.eni.helpme.dal;

import fr.eni.helpme.bo.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursRepository extends MongoRepository<Cours, String> {

}
