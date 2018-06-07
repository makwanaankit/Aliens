package com.makwana.project.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Column;


@Embeddable
class LoginID  implements Serializable
{
	String user_name;
	String password;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		LoginID other = (LoginID) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	
}


@Entity
@IdClass(LoginID.class)
public class Login 
{
	@Id
	String user_name;
	@Id
	String password;
	String type;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Id
    @AttributeOverrides({
    @AttributeOverride(name = "user_name",
    column = @Column(name="user_name")),
    @AttributeOverride(name = "password",
    column = @Column(name="password"))
    })
	
	
	
	
	
	@Override
	public String toString() {
		return "Login [user_name=" + user_name + ", password=" + password + ", type=" + type + "]";
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(String user_name, String password, String type) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.type = type;
	}
	
	
}
