package com.letao.mm2.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="LETAO_MM_MATERIAL_TYPE")
public class MaterialType {
	@Id
	private long id;
	private String thaiName;
	@NotBlank
	private String englishName;
	private boolean active = true;
	private long parent = 0;
	
	//@OneToMany(fetch=FetchType.LAZY)
	//private List<Material> materials;
	
	public MaterialType() {}
	
	public MaterialType(long id) {
		this.id = id;
	}
	
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	
	/*public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}*/
	
	@Override
	public String toString() {
		return "MaterialType [id=" + id + ", thaiName=" + thaiName + ", englishName=" + englishName + ", active="
				+ active + ", parent=" + parent + "]";
	}
	
	
	
	
	
}
