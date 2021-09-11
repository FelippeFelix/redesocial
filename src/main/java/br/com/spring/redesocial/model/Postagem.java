package br.com.spring.redesocial.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
@Entity
public class Postagem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Digite um valor para o título")
	@Column(nullable = false)
	private String titulo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario autor;

	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(nullable = false)
	private LocalDate data;
	
	@Lob
	@NotBlank
	@Column(nullable = false)
	private String texto;
	
}
