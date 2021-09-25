package ua.lviv.lgs.project.domain;

import java.io.File;
import java.sql.Date;
import java.util.Map;

public class ApplicantProfile {

	Integer profileId;
	User user;
	Map<String, Byte> marksTable;
	File marksCertificate;
	File profilePhoto;
	Date admissionDate;
	boolean isApprooved;
	boolean isAdmitted;

	public ApplicantProfile() {

	}

	public ApplicantProfile(User user, Map<String, Byte> marksTable, File marksCertificate, Date admissionDate,
			boolean isApprooved, boolean isAdmitted) {
		super();
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.admissionDate = admissionDate;
		this.isApprooved = isApprooved;
		this.isAdmitted = isAdmitted;
	}

	public ApplicantProfile(Integer profileId, User user, Map<String, Byte> marksTable, File marksCertificate,
			Date admissionDate, boolean isApprooved, boolean isAdmitted) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.admissionDate = admissionDate;
		this.isApprooved = isApprooved;
		this.isAdmitted = isAdmitted;
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Byte> getMarksTable() {
		return marksTable;
	}

	public void setMarksTable(Map<String, Byte> marksTable) {
		this.marksTable = marksTable;
	}

	public File getMarksCertificate() {
		return marksCertificate;
	}

	public void setSchoolCertificate(File marksCertificate) {
		this.marksCertificate = marksCertificate;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public boolean isApprooved() {
		return isApprooved;
	}

	public void setApprooved(boolean isApprooved) {
		this.isApprooved = isApprooved;
	}

	public boolean isAdmitted() {
		return isAdmitted;
	}

	public void setAdmitted(boolean isAdmitted) {
		this.isAdmitted = isAdmitted;
	}

	@Override
	public String toString() {
		return "ApplicantProfile [profileId=" + profileId + ", user=" + user + ", marksTable=" + marksTable
				+ ", marksCertificate=" + marksCertificate + ", admissionDate=" + admissionDate + ", isApprooved="
				+ isApprooved + ", isAdmitted=" + isAdmitted + "]";
	}

}
