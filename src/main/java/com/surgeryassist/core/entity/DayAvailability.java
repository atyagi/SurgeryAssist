package com.surgeryassist.core.entity;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(schema = "SurgeryAssist" ,name = "availability")
@Configurable
public class DayAvailability {

    @OneToMany(mappedBy = "availabilityId")
    private Set<TimeAvailabilities> timeAvailabilitieses;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private ApplicationUser userId;

    @Column(name = "date_of_availability")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dateOfAvailability;
    
	@Column(name = "created_by", updatable = false)
    private Integer createdBy;

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar createdDate;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar modifiedDate;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "availability_id")
    private Integer availabilityID;

	@Version
    @Column(name = "version")
    private Integer version;

	public Integer getAvailabilityID() {
        return this.availabilityID;
    }

	public void setAvailabilityID(Integer id) {
        this.availabilityID = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DayAvailability().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDayAvailabilitys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DayAvailability o", Long.class).getSingleResult();
    }

	public static List<DayAvailability> findAllDayAvailabilitys() {
        return entityManager().createQuery("SELECT o FROM DayAvailability o", DayAvailability.class).getResultList();
    }

	public static DayAvailability findDayAvailability(Integer availabilityID) {
        if (availabilityID == null) return null;
        return entityManager().find(DayAvailability.class, availabilityID);
    }

	public static List<DayAvailability> findDayAvailabilityEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DayAvailability o", DayAvailability.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            DayAvailability attached = DayAvailability.findDayAvailability(this.availabilityID);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public DayAvailability merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DayAvailability merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Calendar getDateOfAvailability() {
        return this.dateOfAvailability;
    }

	public void setDateOfAvailability(Calendar dateOfAvailability) {
        this.dateOfAvailability = dateOfAvailability;
    }

	public Integer getCreatedBy() {
        return this.createdBy;
    }

	public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

	public Calendar getCreatedDate() {
        return this.createdDate;
    }

	public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

	public Integer getModifiedBy() {
        return this.modifiedBy;
    }

	public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

	public Calendar getModifiedDate() {
        return this.modifiedDate;
    }

	public void setModifiedDate(Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

	/**
	 * @return the timeAvailabilitieses
	 */
	public Set<TimeAvailabilities> getTimeAvailabilitieses() {
		return timeAvailabilitieses;
	}

	/**
	 * @param timeAvailabilitieses the timeAvailabilitieses to set
	 */
	public void setTimeAvailabilitieses(Set<TimeAvailabilities> timeAvailabilitieses) {
		this.timeAvailabilitieses = timeAvailabilitieses;
	}

	/**
	 * @return the userId
	 */
	public ApplicationUser getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(ApplicationUser userId) {
		this.userId = userId;
	}
}
