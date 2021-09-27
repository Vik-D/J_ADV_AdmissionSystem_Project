package ua.lviv.lgs.project.domain;

import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

public class ApplicantProfile {

	private Integer profileId;
	private User user;
	private Map<String, Byte> marksTable;
	private byte[] marksCertificate;
	private byte[] profilePhoto;
	private Date admissionDate;
	private boolean isApprooved;
	private boolean isAdmitted;

	public ApplicantProfile() {

	}

	public ApplicantProfile(User user, Map<String, Byte> marksTable, byte[] marksCertificate, byte[] profilePhoto,
			Date admissionDate, boolean isApprooved, boolean isAdmitted) {
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.profilePhoto = profilePhoto;
		this.admissionDate = admissionDate;
		this.isApprooved = isApprooved;
		this.isAdmitted = isAdmitted;
	}

	public ApplicantProfile(Integer profileId, User user, Map<String, Byte> marksTable, byte[] marksCertificate,
			byte[] profilePhoto, Date admissionDate, boolean isApprooved, boolean isAdmitted) {
		this.profileId = profileId;
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.profilePhoto = profilePhoto;
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

	public byte[] getMarksCertificate() {
		return marksCertificate;
	}

	public void setMarksCertificate(byte[] marksCertificate) {
		this.marksCertificate = marksCertificate;
	}

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admissionDate == null) ? 0 : admissionDate.hashCode());
		result = prime * result + (isAdmitted ? 1231 : 1237);
		result = prime * result + (isApprooved ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(marksCertificate);
		result = prime * result + ((marksTable == null) ? 0 : marksTable.hashCode());
		result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + Arrays.hashCode(profilePhoto);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ApplicantProfile other = (ApplicantProfile) obj;
		if (admissionDate == null) {
			if (other.admissionDate != null)
				return false;
		} else if (!admissionDate.equals(other.admissionDate))
			return false;
		if (isAdmitted != other.isAdmitted)
			return false;
		if (isApprooved != other.isApprooved)
			return false;
		if (!Arrays.equals(marksCertificate, other.marksCertificate))
			return false;
		if (marksTable == null) {
			if (other.marksTable != null)
				return false;
		} else if (!marksTable.equals(other.marksTable))
			return false;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (!Arrays.equals(profilePhoto, other.profilePhoto))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApplicantProfile [profileId=" + profileId + ", user=" + user + ", marksTable=" + marksTable
				+ ", marksCertificate=" + Arrays.toString(marksCertificate) + ", profilePhoto="
				+ Arrays.toString(profilePhoto) + ", admissionDate=" + admissionDate + ", isApprooved=" + isApprooved
				+ ", isAdmitted=" + isAdmitted + "]";
	}

}
