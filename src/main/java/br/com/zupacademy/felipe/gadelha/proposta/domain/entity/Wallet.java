package br.com.zupacademy.felipe.gadelha.proposta.domain.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wallets")
public class Wallet {
	
	@Id 
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	@NotNull
	@Column(nullable = false)
	private String numberCard;
	@NotNull @NotBlank @Email
	@Column(nullable = false)
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WalletType walletType;
	
	@Deprecated
	public Wallet() { }
	
	public Wallet(String numberCard, @NotNull @NotBlank @Email String email, @NotNull WalletType walletType) {
		this.numberCard = numberCard;
		this.email = email;
		this.walletType = walletType;
	}
	public UUID getId() {
		return id;
	}
	public String getNumberCard() {
		return numberCard;
	}
	public String getEmail() {
		return email;
	}
	public WalletType getWalletType() {
		return walletType;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", numberCard=" + numberCard + ", email=" + email + ", walletType=" + walletType
				+ "]";
	}
}
