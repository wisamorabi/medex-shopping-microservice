package com.medex.model;

import javax.persistence.*;

//The order class
@Entity //A 'serializable' entity
@Table(name = "Ordr") //Where this entity will be placed
//Now, the fields that we will annotate will be stored in the hosts table.
public class Ordr {
	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //"Do this identification for me, generate that specific ID for me
	@Column(name="id") //Which column?
	int id;
	@Column(name="patientID")
	int patientID;
	@Column(name="inProgress")
	boolean inProgress = false;
	@Column(name="done")
	boolean done = false;

	public Ordr() {}

	//Non default constructor
	public Ordr(int id, int apatientID, boolean ainProgress, boolean adone) {
		this.id = id;
		this.patientID = apatientID;
		this.inProgress = ainProgress;
		this.done = adone;
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

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	
}
