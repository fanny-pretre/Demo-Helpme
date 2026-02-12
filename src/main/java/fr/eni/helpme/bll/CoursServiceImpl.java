package fr.eni.helpme.bll;

import fr.eni.helpme.bo.Cours;
import fr.eni.helpme.dal.CoursRepository;
import fr.eni.helpme.dto.CoursDTO;
import fr.eni.helpme.exceptions.TicketAlreadyExistException;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursServiceImpl implements CoursService{
    @NonNull
    private CoursRepository coursRepository;

    public CoursServiceImpl(@NonNull CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    @Override
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public Cours ajouterCours(CoursDTO coursDto) {

        Cours cours = new Cours(coursDto.getNom(), coursDto.getFormateur());
        BeanUtils.copyProperties(coursDto, cours);

        Cours newCours = null;
        try {
            newCours = coursRepository.save(cours);
        }catch(DataIntegrityViolationException ex) {
            throw new TicketAlreadyExistException();
        }

        return newCours;
    }
}
