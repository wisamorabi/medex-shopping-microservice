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
	@Column(name="inProgress")
	boolean inProgress = false;

	public Order() {}

	//Non default constructor
	public Order(int id, int apatientID, boolean ainProgress) {
		this.id = id;
		this.patientID = apatientID;
		this.inProgress = ainProgress;
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

	public boolean isInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	public boolean getInProgress() {
		return inProgress;
	}
	
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
	
}
