package com.senzit.cyberclaz.server.model;

public class Privilege {
	
	private String privilegeId;
	private String privilegeName;
	
	Privilege(){}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

}
