package com.letao.mm2.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="LETAO_MM_MATERIAL")
@EntityListeners(AuditingEntityListener.class)
public class Material {

	@Id
	@Min(0)
	private long id;
	@NotBlank
	private String thaiName;
	@NotBlank
	private String englishName;
	@Transient
	private long materialTypeId;
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	private MaterialType materialType;
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdAt;
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updatedAt;
	private boolean active = true;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getThaiName() {
		return thaiName;
	}
	public void setThaiName(String thaiName) {
		this.thaiName = thaiName;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public long getMaterialTypeId() {
		return materialTypeId;
	}
	public void setMaterialTypeId(long materialTypeId) {
		this.materialTypeId = materialTypeId;
		this.materialType = new MaterialType(materialTypeId);
	}
	public MaterialType getMaterialType() {
		return materialType;
	}
	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
		this.materialTypeId = materialType.getId();
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (active != other.active)
			return false;
		if (englishName == null) {
			if (other.englishName != null)
				return false;
		} else if (!englishName.equals(other.englishName))
			return false;
		if (id != other.id)
			return false;
		if (materialType == null) {
			if (other.materialType != null)
				return false;
		} else if (!materialType.equals(other.materialType))
			return false;
		if (materialTypeId != other.materialTypeId)
			return false;
		if (thaiName == null) {
			if (other.thaiName != null)
				return false;
		} else if (!thaiName.equals(other.thaiName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Material [id=" + id + ", thaiName=" + thaiName + ", englishName=" + englishName + ", materialTypeId="
				+ materialTypeId + ", materialType=" + materialType + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", active=" + active + "]";
	}

	
}
