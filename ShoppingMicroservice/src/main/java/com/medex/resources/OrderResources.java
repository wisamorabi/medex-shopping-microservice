package com.medex.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.medex.communicationmodules.OrderInfo;
import com.medex.communicationmodules.Status;
import com.medex.model.Order;
import com.medex.services.OrderService;


//Request resources which acts as a layer before our Order services
@Path("/")
public class OrderResources {
	OrderService orderService = new OrderService();

	public OrderResources() {
	}


	
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderInfo> getOrders(@PathParam("Patientid")int patientid) {
		return orderService.getAllOrders(patientid);
	}

	@GET
	@Path("{Orderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public OrderInfo getOrder(@PathParam("Patientid")int patientid, @PathParam("Orderid") int orderid) {
		return orderService.getOrder(patientid, orderid);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderInfo addOrder(@PathParam("Patientid")int patientid, Order order) {
		return orderService.addOrder(patientid, order);
	}
	
	
	
	@PUT
	@Path("{Orderid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OrderInfo updateOrder(@PathParam("Patientid") int patientid, @PathParam("Orderid") int orderid, Order order) {
		return orderService.updateOrder(patientid, orderid, order);
	}

	@DELETE
	@Path("{Orderid}")
	public Status removeOrder(@PathParam("Patientid") int patientid, @PathParam("Orderid") int orderid) {
		return orderService.removeOrder(patientid, orderid);
	}

	
	@Path("{Orderid}/OrderItems")
	public OrderResources getOrderItems()
	{
		return new OrderResources();
	}
	

	


	
	
	
}
