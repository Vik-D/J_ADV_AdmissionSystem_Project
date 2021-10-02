package ua.lviv.lgs.project.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "faculties")
public class Faculty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id")
	private Short facultyId;
	private String facultyName;

	@ElementCollection
	@CollectionTable(name = "subjects_list", joinColumns = @JoinColumn(name = "fclt_id", referencedColumnName = "faculty_id"))
	@Column(name = "subject", nullable = false)
	private List<String> subjectsList;

	/*
	 * All the profiles in a set is sorted descending by 'totalMarksAmount' field of
	 * an ApplicantProfile.class and the number of admitted applicants are defined
	 * by 'admittanceQuota' field value
	 * 
	 */
	@OneToMany(mappedBy = "faculty")
	private Set<ApplicantProfile> applicantProfiles;

	private Short admittanceQuota;

	public Faculty() {

	}

	public Faculty(String facultyName, List<String> subjectsList, Set<ApplicantProfile> applicantProfiles,
			Short admittanceQuota) {
		this.facultyName = facultyName;
		this.subjectsList = subjectsList;
		this.applicantProfiles = applicantProfiles;
		this.admittanceQuota = admittanceQuota;
	}

	public Faculty(Short facultyId, String facultyName, List<String> subjectsList,
			Set<ApplicantProfile> applicantProfiles, Short admittanceQuota) {
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.subjectsList = subjectsList;
		this.applicantProfiles = applicantProfiles;
		this.admittanceQuota = admittanceQuota;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admittanceQuota == null) ? 0 : admittanceQuota.hashCode());
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
				+ ", applicantProfiles=" + applicantProfiles + ", admittanceQuota=" + admittanceQuota + "]";
	}

}