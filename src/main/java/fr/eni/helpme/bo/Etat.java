package fr.eni.helpme.bo;

public enum Etat {

    OUVERT(1), CLOS(2);

    private final int value;

    Etat(final int value) {
        this.value = value;
    }
}