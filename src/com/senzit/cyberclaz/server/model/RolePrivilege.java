package com.senzit.cyberclaz.server.model;

public class RolePrivilege {
	
	private String rolePrivilegeId;
	private Role role;
	private Privilege privilege;
	
	RolePrivilege(){}

	public String getRolePrivilegeId() {
		return rolePrivilegeId;
	}

	public void setRolePrivilegeId(String rolePrivilegeId) {
		this.rolePrivilegeId = rolePrivilegeId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

}
