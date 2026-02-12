package fr.eni.helpme.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Cours {
    @Id
    @EqualsAndHashCode.Exclude
    private String id;
    @NonNull private String nom;
    @NonNull private String formateur;

    private List<Ticket> tickets;
}
