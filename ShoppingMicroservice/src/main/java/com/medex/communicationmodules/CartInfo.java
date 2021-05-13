package com.medex.communicationmodules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.medex.model.CartItem;

//The Cart class
//Type = 2
//Now, the fields that we will annotate will be stored in the hosts table.
public class CartInfo {
	int id;
	int patientID;
	Map<Integer, CartItem> cartItems = new HashMap<>(); 

	public CartInfo() {}

	//Non default constructor
	public CartInfo(int id, int apatientID) {
		this.id = id;
		this.patientID = apatientID;
	}
	public int getId()
	{
		return id;
	}
	public int getConsumerID()
	{
		return patientID;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setConsumerID(int apatientID)
	{
		this.patientID = apatientID;
	}
	public void listToMap(List<CartItem> lst)
	{
		for (CartItem i : lst) 
		{
			cartItems.put(i.getId(),i);
		}
	}
	public Map<Integer, CartItem> getCartItems()
	{
		return cartItems;
	}
}
