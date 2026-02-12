package fr.eni.helpme.rest;

import ch.qos.logback.core.net.server.Client;
import fr.eni.helpme.bll.CoursService;
import fr.eni.helpme.bo.Cours;
import fr.eni.helpme.bo.Ticket;
import fr.eni.helpme.dto.CoursDTO;
import fr.eni.helpme.dto.TicketDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursRestController {

    private final CoursService coursService;

    public CoursRestController(CoursService coursService) {
        this.coursService = coursService;
    }

    // GET
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Cours> getAllCours() {
        List cours = coursService.getAllCours();
        return cours;
    }

    // POST
    @PostMapping
    public ResponseEntity<Cours> createCours(@Valid @RequestBody CoursDTO coursDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        Cours createdCours = coursService.ajouterCours(coursDTO);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/api/cours/" + createdCours.getId())
                .body( createdCours);
    }
    }
