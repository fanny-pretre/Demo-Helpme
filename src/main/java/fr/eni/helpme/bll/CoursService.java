package fr.eni.helpme.bll;

import fr.eni.helpme.bo.Cours;
import fr.eni.helpme.dto.CoursDTO;

import java.util.List;

public interface CoursService {

    List<Cours> getAllCours();

    Cours ajouterCours(CoursDTO coursDto);
}
