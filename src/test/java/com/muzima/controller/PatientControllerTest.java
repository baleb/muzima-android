package com.muzima.controller;

import com.muzima.api.model.CohortMember;
import com.muzima.api.model.Patient;
import com.muzima.api.service.CohortService;
import com.muzima.api.service.PatientService;

import org.apache.lucene.queryParser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class PatientControllerTest {

    private PatientController patientController;
    private PatientService patientService;
    private CohortService cohortService;

    @Before
    public void setup(){
        patientService = mock(PatientService.class);
        cohortService = mock(CohortService.class);
        patientController = new PatientController(patientService,cohortService );
    }

    @Test
    public void getAllPatients_shouldReturnAllAvailablePatients() throws IOException, ParseException, PatientController.PatientLoadException {
        List<Patient> patients = new ArrayList<Patient>();
        when(patientService.getAllPatients()).thenReturn(patients);

        assertThat(patientController.getAllPatients(), is(patients));
    }

    @Test(expected = PatientController.PatientLoadException.class)
    public void getAllForms_shouldThrowFormFetchExceptionIfExceptionThrownByFormService() throws IOException, ParseException, PatientController.PatientLoadException {
        doThrow(new IOException()).when(patientService).getAllPatients();
        patientController.getAllPatients();
    }

    @Test
    public void getTotalPatientsCount_shouldReturnPatientsCount() throws IOException, ParseException, PatientController.PatientLoadException {
        when(patientService.countAllPatients()).thenReturn(2);

        assertThat(patientController.getTotalPatientsCount(), is(2));
    }

    @Test
    public void replacePatients_shouldReplaceAllExistingPatientsAndAddNewPatients() throws IOException, PatientController.PatientReplaceException {
        List<Patient> patients = buildPatients();

        patientController.replacePatients(patients);

        verify(patientService).updatePatients(patients);
        verifyNoMoreInteractions(patientService);
    }

    @Test(expected = PatientController.PatientReplaceException.class)
    public void replacePatients_shouldThrowPatientReplaceExceptionIfExceptionThrownByService() throws IOException, PatientController.PatientReplaceException {
        List<Patient> patients = buildPatients();

        doThrow(new IOException()).when(patientService).updatePatients(patients);

        patientController.replacePatients(patients);
    }

    @Test
    public void getPatientsInCohort_shouldReturnThePatientsInTheCohort() throws IOException, PatientController.PatientLoadException {
        String cohortId = "cohortId";
        List<CohortMember> members = buildCohortMembers(cohortId);
        when(cohortService.getCohortMembers(cohortId)).thenReturn(members);

        Patient patient = new Patient();
        patient.setUuid(members.get(0).getPatientUuid());
        when(patientService.getPatientByUuid(patient.getUuid())).thenReturn(patient);

        List<Patient> patients = patientController.getPatients(cohortId);

        assertThat(patients.size(), is(1));
    }

    @Test
    public void searchPatient_shouldReturnSearchedPatients() throws IOException, ParseException, PatientController.PatientLoadException {
        String serachString = "searchString";
        List<Patient> patients = new ArrayList<Patient>();

        when(patientService.searchPatients(serachString)).thenReturn(patients);

        assertThat(patientController.searchPatient(serachString), is(patients));
    }

    private List<CohortMember> buildCohortMembers(String cohortId) {
        List<CohortMember> cohortMembers = new ArrayList<CohortMember>();
        CohortMember member1 = new CohortMember();
        member1.setCohortUuid(cohortId);
        member1.setPatientUuid("patientId1");
        cohortMembers.add(member1);
        return cohortMembers;
    }

    @Test(expected = PatientController.PatientLoadException.class)
    public void getPatientsInCohort_shouldThrowLoadPatientExceptionIfExceptionThrownByService() throws IOException, PatientController.PatientLoadException {
        String cohortId = "cohortId";
        List<CohortMember> members = buildCohortMembers(cohortId);
        when(cohortService.getCohortMembers(cohortId)).thenReturn(members);
        doThrow(new IOException()).when(patientService).getPatientByUuid(members.get(0).getPatientUuid());

        patientController.getPatients(cohortId);
    }

    @Test
    public void getPatientByUuid_shouldReturnPatientForId() throws Exception, PatientController.PatientLoadException {
        Patient patient = new Patient();
        String uuid = "uuid";

        when(patientService.getPatientByUuid(uuid)).thenReturn(patient);

        assertThat(patientController.getPatientByUuid(uuid), is(patient));
    }

    private List<Patient> buildPatients() {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        Patient patient1 = new Patient();
        patient1.setUuid("uuid1");
        Patient patient2 = new Patient();
        patient2.setUuid("uuid2");
        Patient patient3 = new Patient();
        patient3.setUuid("uuid3");
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        return patients;
    }
}
