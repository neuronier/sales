package hu.neuron.java.sales.web.controllers.role;

import hu.neuron.java.sales.service.RoleServiceRemote;
import hu.neuron.java.sales.service.vo.RoleVO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ViewScoped
@ManagedBean(name = "roleController")
public class roleController {

	
	private LazyRoleModel lazyRoleModel;
	
	@EJB(name = "RoleService", mappedName = "RoleService")
	private RoleServiceRemote roleService;
	
	private RoleVO selectedRole;
	private RoleVO newRole;
	
	public roleController() {
		super();
	}
	
	@PostConstruct
	public void init() {
		setLazyRoleModel(new LazyRoleModel(roleService));
	}

	public void addNewRoleBtnAction() {
		newRole = new RoleVO();
	}
	
	public void addNewRole(){
		roleService.saveRole(newRole);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",	"Created: " + newRole.getName() ));
	}
	
	public void editRole(){
		roleService.saveRole(selectedRole);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",	"Edited: " + newRole.getName() ));
	}
	
	public void removeRole() {
		roleService.removeRole(selectedRole);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",	"Removed: " + selectedRole.getName() ));
	}


	public LazyRoleModel getLazyRoleModel() {
		return lazyRoleModel;
	}

	public void setLazyRoleModel(LazyRoleModel lazyRoleModel) {
		this.lazyRoleModel = lazyRoleModel;
	}

	public RoleVO getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(RoleVO selectedRole) {
		this.selectedRole = selectedRole;
	}

	public RoleVO getNewRole() {
		return newRole;
	}

	public void setNewRole(RoleVO newRole) {
		this.newRole = newRole;
	}	
	
}
