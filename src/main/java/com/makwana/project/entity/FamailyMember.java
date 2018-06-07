package com.makwana.project.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Embeddable
class FamilyID implements Serializable
{
	int familyId;
	int P_id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + P_id;
		result = prime * result + familyId;
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
		FamilyID other = (FamilyID) obj;
		if (P_id != other.P_id)
			return false;
		if (familyId != other.familyId)
			return false;
		return true;
	}	
	
}

@Entity
@IdClass(FamilyID.class)
public class FamailyMember  
{	@Id
	int familyId;
	@Id
	int P_id;	
	String name;
	int age;
	String gender;

	public int getP_id() {
		return P_id;
	}
	public void setP_id(int p_id) {
		P_id = p_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getFamilyId() {
		return familyId;
	}
	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	public FamailyMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FamailyMember [familyId=" + familyId + ", P_id=" + P_id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}
	public FamailyMember(int familyId, int p_id, String name, int age, String gender) {
		super();
		this.familyId = familyId;
		P_id = p_id;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}


	
	
		
}
