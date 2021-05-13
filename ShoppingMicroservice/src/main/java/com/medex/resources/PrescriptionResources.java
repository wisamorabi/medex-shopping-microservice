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

import com.medex.communicationmodules.Status;
import com.medex.dependentresources.Prescription;
import com.medex.services.PrescriptionService;


//Request resources which acts as a layer before our Prescription services
@Path("/")
public class PrescriptionResources {
	PrescriptionService prescriptionService = new PrescriptionService();

	public PrescriptionResources() {
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Prescription> getPrescriptions(@PathParam("Patientid") int patientid) {
		return prescriptionService.getAllPrescriptions(patientid);
	}

	@GET
	@Path("{Prescriptionid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription getPrescription(@PathParam("patientid") int patientid, @PathParam("Prescriptionid") int cartitemid) {
		return prescriptionService.getPrescription(patientid, cartitemid);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription addPrescription(@PathParam("Patientid")int patientid, Prescription cartitem)
	{
		return prescriptionService.addPrescription(patientid, cartitem);
	}
	

	//Requirement 9: Create a REST API the provides the ability to delete a PharmaceuticalStock that is allocated to a host (Note: This place it back into the pool!)
	//hosts/{id}/pharmaceuticalStocks web service Requirement #4: Delete a PharmaceuticalStock from the host.
	//Return a JSON object with status true if delete is successful or false if the delete is not successful.
	@DELETE
	@Path("{Prescriptionid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Status removePrescription(@PathParam("Patientid")int patientid, @PathParam("Prescriptionid") int cartitemid)
	{
		return prescriptionService.removePrescription(patientid, cartitemid);
	}
	
	
	//hosts/{id}/pharmaceuticalStocks web service requirement #5: Update a PharmaceuticalStock allocated in the host
	//return a JSON object with status true if update is successful or false if the update is not successful. The host must contain adequate resources.
	@PUT
	@Path("{Prescriptionid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Prescription updatePrescription(@PathParam("Patientid")int patientid, @PathParam("Prescriptionid") int cartitemid, Prescription cartitem)
	{
		return prescriptionService.updatePrescription(patientid, cartitemid, cartitem);
	}
}
