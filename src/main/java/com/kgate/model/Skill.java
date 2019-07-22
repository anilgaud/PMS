package com.kgate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee_skill")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int skill_Id;
	@Column
	private String skill_name;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "join_employee_skill",
//            joinColumns = {
//                @JoinColumn(name = "skill_Id")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "id")})
//    private List<Employee> listEmployee = new ArrayList<Employee>();

//    public List<Employee> getListEmployee() {
//        return listEmployee;
//    }
//
//    public void setListEmployee(List<Employee> listEmployee) {
//        this.listEmployee = listEmployee;
//    }
 
 

	public int getSkill_Id() {
		return skill_Id;
	}
 

	public void setSkill_Id(int skill_Id) {
		this.skill_Id = skill_Id;
	}

	public String getSkill_name() {
		return skill_name;
	}

 

    @Override
    public String toString() {
        return getSkill_name();
    }
 
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
 
 

}
