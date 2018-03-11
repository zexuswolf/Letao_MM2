package com.letao.mm2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVE_MATERIAL_VIEW")
public class MaterialData {

	@Id
	@Column(name="ID")
	private long id;
	@Column(name="MAT_TH_NAME")
	private String thaiName;
	@Column(name="MAT_EN_NAME")
	private String englishName;
	@Column(name="TYPE_TH_NAME")
	private String typeThaiName;
	@Column(name="TYPE_EN_NAME")
	private String typeEnglishName;
	
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
	public String getTypeThaiName() {
		return typeThaiName;
	}
	public void setTypeThaiName(String typeThaiName) {
		this.typeThaiName = typeThaiName;
	}
	public String getTypeEnglishName() {
		return typeEnglishName;
	}
	public void setTypeEnglishName(String typeEnglishName) {
		this.typeEnglishName = typeEnglishName;
	}
	
	@Override
	public String toString() {
		return "MaterialData [id=" + id + ", thaiName=" + thaiName + ", englishName=" + englishName + ", typeThaiName="
				+ typeThaiName + ", typeEnglishName=" + typeEnglishName + "]";
	}
	
	
	
}
