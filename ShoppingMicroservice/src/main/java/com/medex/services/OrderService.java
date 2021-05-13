package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.OrderInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.OrderDB;
import com.medex.database.PatientDB;
import com.medex.model.OrderItem;

import com.medex.model.Order;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class OrderService {
	OrderDB orderdb = new OrderDB(); //(Instead of the pseudo-database)
	OrderItemService orderItemService = new OrderItemService();
	PatientDB patientdb = new PatientDB();
	public OrderService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<OrderInfo>getAllOrders(int patientid)
	{
		List<Order> orderList = orderdb.getOrders(patientid); //Get all hosts.
		List<OrderInfo> orderinfoList = new ArrayList<OrderInfo>(); //Make a list that contains HostInfo instances
		if (orderList.isEmpty() == false) return null;	
		for (Order o : orderList) {
			orderinfoList.add(OrderToOrderInfo(patientid, o));
			}		
		return orderinfoList;
	}
	
	public OrderInfo getOrder(int patientid, int orderid)
	{
		Order order = orderdb.getOrder(patientid, orderid); //Get all hosts.
		if (order == null) return null;
		return OrderToOrderInfo(patientid, order);
	}
	
	public OrderInfo addOrder(int patientid, Order order)
	{
		order.setPatientId(patientid);
		if (patientdb.getPatient(patientid) == null) return null;
		orderdb.insertOrder(order);
		return OrderToOrderInfo(patientid, order);
	}
	
	public OrderInfo updateOrder(int patientid, int orderid, Order order)
	{
		if (patientdb.getPatient(patientid) == null) return null;
		if (orderdb.getOrder(patientid, orderid) == null) return null;
		orderdb.updateOrder(order);
		return OrderToOrderInfo(patientid, order);
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	// pharmacys/{id}/pharmaceuticalStocks web service requirement #5: Update a PharmaceuticalStock allocated in the pharmacy
////////////////////////////////////////////////////////////////////////////////////////
	
	public Status removeOrder(int patientid, int orderid)
	{
		if (orderdb.getOrder(patientid, orderid) == null) return new Status(false);
		orderdb.deleteOrder(orderid);
		return new Status(true);
	}
	
	
	private OrderInfo OrderToOrderInfo(int patientid, Order aOrder)
	{
		OrderInfo orderInfo = new OrderInfo(aOrder);
		List<OrderItem> lst = orderItemService.getAllOrderItems(patientid, aOrder.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { orderInfo.listToMap(lst); }
		
		return orderInfo;
	}
}


