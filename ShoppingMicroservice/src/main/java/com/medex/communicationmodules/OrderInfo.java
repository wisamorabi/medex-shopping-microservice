package com.medex.communicationmodules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.medex.model.Order;
import com.medex.model.OrderItem;


//The order class
@Entity //A 'serializable' entity
//Now, the fields that we will annotate will be stored in the hosts table.
public class OrderInfo {
	int id;
	int patientID;
	boolean inProgress;
	
	Map<Integer, OrderItem> orderItems = new HashMap<>(); 

	public OrderInfo() {}

	//Non default constructor
	public OrderInfo(int id, int apatientID, boolean ainProgress) {
		this.id = id;
		this.patientID = apatientID;
		this.inProgress = ainProgress;
	}
	public OrderInfo(Order o)
	{
		this.id = o.getId();
		this.patientID = o.getPatientID();
		this.inProgress = o.getInProgress();
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
	public void listToMap(List<OrderItem> lst)
	{
		for (OrderItem i : lst) 
		{
			orderItems.put(i.getId(),i);
		}
	}
	public Map<Integer, OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public boolean getInProgress() {
		return inProgress;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	
}
