package fr.eni.helpme.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
public class Response {
    @EqualsAndHashCode.Include
    private String auteur;
    @EqualsAndHashCode.Include
    private String message;
    private LocalDateTime creation;

    public Response(){
        this.creation=LocalDateTime.now();
    }

    public  Response(String auteur, String message) {
        this.message=message;
        this.auteur = auteur;
        this.creation = LocalDateTime.now();
    }
}
