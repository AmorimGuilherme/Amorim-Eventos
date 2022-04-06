package com.gft.gerenciador.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.gft.gerenciador.entities.enums.GeneroMusical;

@Entity
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Nome do evento não pode estar vazio!")
	private String nome;

	@Min(value = 0, message = "Capacidade não ser negativa!")
	private Integer capacidade;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;

	@Min(value = 0, message = "Preço do ingresso não pode ser negativo!")
	@Digits(fraction = 2, integer = 10)
	private double precoIngresso;

	@ManyToOne
	private CasaDeShow casaDeShow;

	private GeneroMusical generoMusical;

	public Evento() {

	}

	public Evento(String nome, Integer capacidade, Date data, double precoIngresso, CasaDeShow casaDeShow,
			GeneroMusical generoMusical) {
		this.nome = nome;
		this.capacidade = capacidade;
		this.data = data;
		this.precoIngresso = precoIngresso;
		this.casaDeShow = casaDeShow;
		this.generoMusical = generoMusical;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getPrecoIngresso() {
		return precoIngresso;
	}

	public void setPrecoIngresso(double precoIngresso) {
		this.precoIngresso = precoIngresso;
	}

	public CasaDeShow getCasaDeShow() {
		return casaDeShow;
	}

	public void setCasaDeShow(CasaDeShow casaDeShow) {
		this.casaDeShow = casaDeShow;
	}

	public GeneroMusical getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(GeneroMusical generoMusical) {
		this.generoMusical = generoMusical;
	}

}
