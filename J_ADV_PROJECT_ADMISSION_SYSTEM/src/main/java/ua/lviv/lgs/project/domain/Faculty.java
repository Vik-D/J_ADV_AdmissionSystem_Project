package ua.lviv.lgs.project.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Faculty {

	Short facultyId;
	String facultyName;
	List<String> subjectsList;
	Set<ApplicantProfile> applicantProfiles;
	Short admittanceQuota;
	Map<String, Integer> admittedApplicantsList;

	public Faculty() {

	}

	public Faculty(String facultyName, List<String> subjectsList, Set<ApplicantProfile> applicantProfiles,
			Short admittanceQuota, Map<String, Integer> admittedApplicantsList) {
		super();
		this.facultyName = facultyName;
		this.subjectsList = subjectsList;
		this.applicantProfiles = applicantProfiles;
		this.admittanceQuota = admittanceQuota;
		this.admittedApplicantsList = admittedApplicantsList;
	}

	public Faculty(Short facultyId, String facultyName, List<String> subjectsList,
			Set<ApplicantProfile> applicantProfiles, Short admittanceQuota,
			Map<String, Integer> admittedApplicantsList) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.subjectsList = subjectsList;
		this.applicantProfiles = applicantProfiles;
		this.admittanceQuota = admittanceQuota;
		this.admittedApplicantsList = admittedApplicantsList;
	}

	public Short getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Short facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public List<String> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<String> subjectsList) {
		this.subjectsList = subjectsList;
	}

	public Set<ApplicantProfile> getApplicantProfiles() {
		return applicantProfiles;
	}

	public void setApplicantProfiles(Set<ApplicantProfile> applicantProfiles) {
		this.applicantProfiles = applicantProfiles;
	}

	public Short getAdmittanceQuota() {
		return admittanceQuota;
	}

	public void setAdmittanceQuota(Short admittanceQuota) {
		this.admittanceQuota = admittanceQuota;
	}

	public Map<String, Integer> getAdmittedApplicantsList() {
		return admittedApplicantsList;
	}

	public void setAdmittedApplicantsList(Map<String, Integer> admittedApplicantsList) {
		this.admittedApplicantsList = admittedApplicantsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admittanceQuota == null) ? 0 : admittanceQuota.hashCode());
		result = prime * result + ((admittedApplicantsList == null) ? 0 : admittedApplicantsList.hashCode());
		result = prime * result + ((applicantProfiles == null) ? 0 : applicantProfiles.hashCode());
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
		result = prime * result + ((subjectsList == null) ? 0 : subjectsList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (admittanceQuota == null) {
			if (other.admittanceQuota != null)
				return false;
		} else if (!admittanceQuota.equals(other.admittanceQuota))
			return false;
		if (admittedApplicantsList == null) {
			if (other.admittedApplicantsList != null)
				return false;
		} else if (!admittedApplicantsList.equals(other.admittedApplicantsList))
			return false;
		if (applicantProfiles == null) {
			if (other.applicantProfiles != null)
				return false;
		} else if (!applicantProfiles.equals(other.applicantProfiles))
			return false;
		if (facultyId == null) {
			if (other.facultyId != null)
				return false;
		} else if (!facultyId.equals(other.facultyId))
			return false;
		if (facultyName == null) {
			if (other.facultyName != null)
				return false;
		} else if (!facultyName.equals(other.facultyName))
			return false;
		if (subjectsList == null) {
			if (other.subjectsList != null)
				return false;
		} else if (!subjectsList.equals(other.subjectsList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", subjectsList=" + subjectsList
				+ ", applicantProfiles=" + applicantProfiles + ", admittanceQuota=" + admittanceQuota
				+ ", admittedApplicantsList=" + admittedApplicantsList + "]";
	}

}
