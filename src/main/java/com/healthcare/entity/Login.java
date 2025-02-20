package com.healthcare.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Login {
	@Id
	private String emailid;
	private String password;
	private String typeofuser;
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "emailid")
	private List<Orders> listoforders;

}
