package com.medex.services;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;

import com.medex.communicationmodules.OrderInfo;
import com.medex.communicationmodules.PatientInfo;
import com.medex.communicationmodules.Status;
import com.medex.database.PatientDB;
import com.medex.database.PharmaceuticalStockDB;
import com.medex.database.PrescriptionDB;
import com.medex.dependentresources.Prescription;
import com.medex.model.CartItem;
import com.medex.model.Patient;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PatientService {
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	CartItemService cartItemService = new CartItemService();
	OrderService orderService = new OrderService();
	PharmaceuticalStockDB pharmaceuticalstockdb = new PharmaceuticalStockDB();
	PrescriptionService prescriptionservice = new PrescriptionService();
	
	public PatientService() {} 
	
	//All what is below is just calling the functions belonging to the pharmacies' database/table.
	
	public List<PatientInfo>getAllPatients()
	{
		List<Patient> patientList = patientdb.getPatients(); //Get all hosts.
		List<PatientInfo> patientinfoList = new ArrayList<PatientInfo>(); //Make a list that contains HostInfo instances
		if (patientList.isEmpty() == true) return null;
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
	
	
	public Patient getPatientLogin(String username, String password)
	{
		return patientdb.getPatientLogin(username, password); //Get all hosts.

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

	public Status pay(int patientid) {
		if (patientdb.getPatient(patientid) == null) return new Status(false);
		int subtotal = 0;
		List<CartItem> lst = cartItemService.getAllCartItems(patientid);
		for (CartItem c : lst)
		{
			if (pharmaceuticalstockdb.getPharmaceuticalStock(c.getPharmacyID(), c.getMedicineID()) == null) return new Status(false);
			if (pharmaceuticalstockdb.getPharmaceuticalStock(c.getPharmacyID(), c.getMedicineID()).getMedicineStock() < c.getCount()) return new Status(false);
			List<Prescription> lst2 = prescriptionservice.getAllPrescriptions(patientid);
			boolean found = false;
			for (Prescription p : lst2)
			{
				if (p.getPharmaceuticalID() == c.getMedicineID())
				{
					if (p.getCount() <= c.getMedicineID()) found = true;
				}
			}
			if (found == false) return new Status(false);
			subtotal += c.getCount() * pharmaceuticalstockdb.getPharmaceuticalStock(c.getPharmacyID(), c.getMedicineID()).getMedicinePrice();
		}
		if (subtotal > patientdb.getPatient(patientid).getWallet()) return new Status(false);
		
		Patient p = patientdb.getPatient(patientid);
		p.setWallet(p.getWallet()-subtotal);
		patientdb.updatePatient(p);
		return new Status(true);
	}


}


