package com.muzima.utils;

//TODO: This class should be burnt and flushed. Constants in an anti-pattern and and a sure sign that your abstractions are wrong: Zabil
//TODO: Burnt?? Maybe or may not be - Prasanna
public class Constants {
    public static final String SYNC_PREF = "SyncMetaData";
    public static final String COHORT_PREFIX_PREF = "CohortPrefixPref";
    public static final String COHORT_PREFIX_PREF_KEY = "CohortPrefixPrefKey";
    public static final String FORM_TAG_PREF = "FormTagPref";
    public static final String FORM_TAG_PREF_KEY = "FormTagPrefKey";
    public static final String CONCEPT_PREF = "ConceptPref";
    public static final String CONCEPT_PREF_KEY = "ConceptPrefKey";
    public static final String STATUS_INCOMPLETE = "incomplete";
    public static final String STATUS_COMPLETE = "complete";
    public static final String STATUS_UPLOADED = "uploaded";
    public static final String SEARCH_STRING_BUNDLE_KEY = "SearchString";
    public static String LOCAL_PATIENT = "LocalPatient";

    public static final String FORM_XML_DISCRIMINATOR_ENCOUNTER = "xml-encounter";
    public static final String FORM_JSON_DISCRIMINATOR_ENCOUNTER = "json-encounter";
    public static final String FORM_DISCRIMINATOR_REGISTRATION = "registration";

    public static class DataSyncServiceConstants {
        public static final String SYNC_TYPE = "sync_type";
        public static final String CREDENTIALS = "credentials";
        public static final String SYNC_STATUS = "sync_status";
        public static final String DOWNLOAD_COUNT_PRIMARY = "donwload_count_primary";
        public static final String DOWNLOAD_COUNT_SECONDARY = "donwload_count_secondary";
        public static final String FORM_IDS = "formIds";
        public static final String COHORT_IDS = "cohortIds";
        public static final String PATIENT_UUID_FOR_DOWNLOAD = "patientUUIDForDownload";

        public static final int SYNC_FORMS = 0;
        public static final int SYNC_TEMPLATES = 1;
        public static final int SYNC_COHORTS = 2;
        public static final int SYNC_PATIENTS_FULL_DATA = 3;
        public static final int SYNC_OBSERVATIONS = 4;
        public static final int SYNC_ENCOUNTERS = 5;
        public static final int SYNC_PATIENTS_ONLY = 6;
        public static final int SYNC_PATIENTS_DATA_ONLY = 7;
        public static final int SYNC_UPLOAD_FORMS = 8;
        public static final int DOWNLOAD_PATIENT_ONLY = 9;

        public static class SyncStatusConstants {
            public static final int DOWNLOAD_ERROR = 0;
            public static final int SAVE_ERROR = 1;
            public static final int AUTHENTICATION_ERROR = 2;
            public static final int DELETE_ERROR = 3;
            public static final int SUCCESS = 4;
            public static final int CANCELLED = 5;
            public static final int CONNECTION_ERROR = 6;
            public static final int PARSING_ERROR = 7;
            public static final int AUTHENTICATION_SUCCESS = 8;
            public static final int REPLACE_ERROR = 9;
            public static final int LOAD_ERROR = 10;
            public static final int UNKNOWN_ERROR = 11;
            public static final int UPLOAD_ERROR = 12;
            public static final int MALFORMED_URL_ERROR = 13;
            public static final int INVALID_CREDENTIALS_ERROR = 14;
        }
    }
}
