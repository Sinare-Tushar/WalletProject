package com.example.spring.datajpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "currency")
public class Currency {
	
	@Id
	@GenericGenerator(name = "CURRENCY_SEQUENCE", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "CURRENCY_SEQUENCE")
	
    private Long id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;

    
    public Currency(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}


	public Currency() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}


	@Override
   	public String toString() {
   		return "Currency [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + "]";
   	}

}
