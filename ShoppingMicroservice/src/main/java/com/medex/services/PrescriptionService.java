package com.medex.services;

import java.util.List;

import com.medex.communicationmodules.Status;
import com.medex.database.PrescriptionDB;
import com.medex.dependentresources.Prescription;
import com.medex.database.PatientDB;
import com.medex.database.PharmaceuticalDB;

//This is the "backend" of our resources. This is where the logic is executed; the logic is basic since the database handles itself very well.
public class PrescriptionService {
	PrescriptionDB prescriptiondb = new PrescriptionDB(); //(Instead of the pseudo-database)
	PatientDB patientdb = new PatientDB(); //(Instead of the pseudo-database)
	PharmaceuticalDB pharmaceuticalDB = new PharmaceuticalDB(); //(Instead of the pseudo-database)
	
	public PrescriptionService() {} 
	
	//All what is below is just calling the functions belonging to the prescriptions' database/table.
	
	public List<Prescription>getAllPrescriptions(int patientid)
	{
		return prescriptiondb.getPrescriptions(patientid);
	}
	
	
	
	public Prescription getPrescription(int patientid, int cartitemid)
	{
		return prescriptiondb.getPrescription(patientid, cartitemid);
	}
	
	
	public Prescription addPrescription(int patientid, Prescription cartitem) {
		cartitem.setPatientID(patientid);
		if (patientdb.getPatient(patientid) == null) return null;
		if (pharmaceuticalDB.getPharmaceutical(cartitem.getPharmaceuticalID()) == null) return null;
		prescriptiondb.insertPrescription(cartitem);
		return cartitem;
	}
////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	// pharmacys/{id}/pharmaceuticalStocks web service requirement #5: Update a PharmaceuticalStock allocated in the pharmacy
////////////////////////////////////////////////////////////////////////////////////////

	public Prescription updatePrescription(int patientid, int cartitemid, Prescription prescription)
	{
		if (patientdb.getPatient(patientid) == null) return null;
		if (pharmaceuticalDB.getPharmaceutical(prescription.getPharmaceuticalID()) == null) return null;
		if (prescriptiondb.getPrescription(patientid, cartitemid) == null) return null;
		prescriptiondb.updatePrescription(prescription);
		return prescription;
	}
	
	public Status removePrescription(int patientid, int cartitemid)
	{
		if (patientdb.getPatient(patientid) == null) return new Status(false);
		if (prescriptiondb.getPrescription(patientid, cartitemid) == null) return new Status(false);
		prescriptiondb.deletePrescription(cartitemid);
		return new Status(true);
	}
	
	
}


