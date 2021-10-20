package ua.lviv.lgs.project.domain;

import java.util.Arrays;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * This is SOURCE class (OWNER of the relationship) and CHILD table in relationship with User.class
 * It means this class CONTAINS A REFERENCE to other entity/table
 */
@Entity
@Table(name = "applicant_profiles")
public class ApplicantProfile {

	@Id
	@Column(name = "profile_id")
	private Integer profileId;

	/*
	 * Ensuring that id-numbers of User-entity and ApplicantProfile-entity are
	 * shared and thus the same
	 */
	@OneToOne
	@MapsId
	@JoinColumn(name = "profile_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "apf_faculty_id", referencedColumnName = "faculty_id")
	private Faculty faculty;

	/*
	 * A map represents simple table of subject names 
	 * and corresponding marks on each subject in such a way: 
	 * each Map`s Entry contains <String [subjectName], Byte [subjectMarks]>
	 */
	@ElementCollection
	@CollectionTable(name = "applicant_marks_table")
	private Map<String, Byte> marksTable;
	
	private Short totalMarksAmount;
	
	@Lob
	@Column
	private byte[] marksCertificate;
	
	@Lob
	@Column
	private byte[] profilePhoto;
	
	private boolean isEnrolled;
	private boolean isApprooved;
	private boolean isAdmitted;

	public ApplicantProfile() {

	}

	public ApplicantProfile(User user, Map<String, Byte> marksTable, byte[] marksCertificate, byte[] profilePhoto) {
		super();
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.profilePhoto = profilePhoto;
		this.isApprooved = false;
		this.isAdmitted = false;
	}

	public ApplicantProfile(Integer profileId, User user, Map<String, Byte> marksTable, byte[] marksCertificate,
			byte[] profilePhoto) {
		super();
		this.profileId = profileId;
		this.user = user;
		this.marksTable = marksTable;
		this.marksCertificate = marksCertificate;
		this.profilePhoto = profilePhoto;
		this.isApprooved = false;
		this.isAdmitted = false;
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

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
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

	public boolean isEnrolled() {
		return isEnrolled;
	}

	public void setEnrolled(boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
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

	public Short getTotalMarksAmount() {
		return totalMarksAmount;
	}

	public void setTotalMarksAmount(Short totalMarksAmount) {
		this.totalMarksAmount = totalMarksAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + (isAdmitted ? 1231 : 1237);
		result = prime * result + (isApprooved ? 1231 : 1237);
		result = prime * result + (isEnrolled ? 1231 : 1237);
		result = prime * result + Arrays.hashCode(marksCertificate);
		result = prime * result + ((marksTable == null) ? 0 : marksTable.hashCode());
		result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
		result = prime * result + Arrays.hashCode(profilePhoto);
		result = prime * result + ((totalMarksAmount == null) ? 0 : totalMarksAmount.hashCode());
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
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (isAdmitted != other.isAdmitted)
			return false;
		if (isApprooved != other.isApprooved)
			return false;
		if (isEnrolled != other.isEnrolled)
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
		if (totalMarksAmount == null) {
			if (other.totalMarksAmount != null)
				return false;
		} else if (!totalMarksAmount.equals(other.totalMarksAmount))
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
		return "ApplicantProfile [profileId=" + profileId + ", user=" + user + ", faculty=" + faculty + ", marksTable="
				+ marksTable + ", totalMarksAmount=" + totalMarksAmount + ", marksCertificate="
				+ Arrays.toString(marksCertificate) + ", profilePhoto=" + Arrays.toString(profilePhoto)
				+ ", isEnrolled=" + isEnrolled + ", isApprooved=" + isApprooved + ", isAdmitted=" + isAdmitted + "]";
	}

}