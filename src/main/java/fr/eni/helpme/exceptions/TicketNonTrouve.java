package fr.eni.helpme.exceptions;

import lombok.Getter;

@Getter
public class TicketNonTrouve extends RuntimeException {
  private String idTicket;

  public TicketNonTrouve(String idTicket) {
    this.idTicket = idTicket;
  }
}