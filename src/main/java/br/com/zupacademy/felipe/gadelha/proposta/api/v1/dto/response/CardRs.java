package br.com.zupacademy.felipe.gadelha.proposta.api.v1.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class CardRs {

	@JsonProperty("id")
	private String numberCard;

	@JsonCreator(mode = Mode.PROPERTIES)
	public CardRs(String numberCard) {
		this.numberCard = numberCard;
	}
	public String getNumberCard() {
		return numberCard;
	}
	@Override
	public String toString() {
		return "AccountsRs [numberCard=" + numberCard + "]";
	}
	
}
