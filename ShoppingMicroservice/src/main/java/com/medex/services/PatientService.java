package com.medex.services;

import java.util.ArrayList;
import java.util.List;

import com.medex.communicationmodules.OrderInfo;
import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.PatientDB;
import com.medex.model.CartItem;
import com.medex.model.Patient;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PatientService {
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	CartItemService cartItemService = new CartItemService();
	OrderService orderService = new OrderService();
	public PatientService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<PatientInfo>getAllPatients()
	{
		List<Patient> patientList = patientdb.getPatients(); //Get all hosts.
		List<PatientInfo> patientinfoList = new ArrayList<PatientInfo>(); //Make a list that contains HostInfo instances
		if (patientList.isEmpty() == false) return null;	
		for (Patient p : patientList)
			patientinfoList.add(PatientToPatientInfo(p));
		return patientinfoList;
	}
	
	public PatientInfo getPatient(int id)
	{
		Patient patient = patientdb.getPatient(id); //Get all hosts.
		if (patient == null) return null;
		return PatientToPatientInfo(patient);
	}
	
	public PatientInfo addPatient(Patient aPatient)
	{
		patientdb.insertPatient(aPatient);
		return PatientToPatientInfo(aPatient);
	}
	
	public PatientInfo updatePatient(Patient aPatient)
	{
		if (patientdb.getPatient(aPatient.getId()) == null) return null;
		patientdb.updatePatient(aPatient);
		return PatientToPatientInfo(aPatient);
	}
	
	public Status removePatient(int id)
	{
		if (patientdb.getPatient(id) == null) return new Status(false);
		patientdb.deletePatient(id);
		return new Status(true);
	}
	
	private PatientInfo PatientToPatientInfo(Patient aPatient)
	{
		PatientInfo patientInfo = new PatientInfo(aPatient);
		List<CartItem> lst = cartItemService.getAllCartItems(patientInfo.getId()); //For every host, get the list of VMs it holds and attach that to the hashmap of VMs that each instance of HostInfo has.
		if (lst.isEmpty() == false) { patientInfo.listToMapCart(lst); 	}
		
		return patientInfo;
	}
}


