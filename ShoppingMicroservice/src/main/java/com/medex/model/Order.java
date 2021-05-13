package com.medex.model;

import javax.persistence.*;

//The order class
@Entity //A 'serializable' entity
@Table(name = "Order") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Order {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="patientID")
	int patientID;

	public Order() {}

	//Non default constructor
	public Order(int id, int apatientID) {
		this.id = id;
		this.patientID = apatientID;
	}
	public int getId()
	{
		return id;
	}
	public int getPatientID()
	{
		return patientID;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setPatientId(int apatientID)
	{
		this.patientID = apatientID;
	}
}
