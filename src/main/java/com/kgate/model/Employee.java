package com.kgate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Entity
@Table(name = "employee_details")
@Component
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Employee implements Serializable {

    private static final long serialVersionUID = -3465813074586302847L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "join_employee_skill", joinColumns = {
        @JoinColumn(name = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "skill_Id")})
    private List<Skill> listSkill = new ArrayList<Skill>();

    @Transient
    private List<String> skills = new ArrayList<>();

    @Column
    private String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public List<Skill> getListSkill() {
        return listSkill;
    }

    public void setListSkill(List<Skill> listSkill) {
        this.listSkill = listSkill;
    }

    @Column
    @NotEmpty(message = "Please enter your name.")
    private String name;

    @Column
    @NotEmpty(message = "Please enter your email.")
    @Email
    private String email;

    @Column
    private String address;

    @Column
    private String telephone;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column
    private String category;

    @Column
    private String status;

    @Column
    private String password;

    @Column

    private int managerId;

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Column
    private String aadhar;
    @Column
    private String pan;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee(String otp, String name, String email,
            String address, String telephone, String category, String status, String password, int managerId,
            String aadhar, String pan) {
        super();

        this.otp = otp;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
        this.category = category;
        this.status = status;
        this.password = password;
        this.managerId = managerId;
        this.aadhar = aadhar;
        this.pan = pan;
    }

    public Employee() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", listSkill=" + listSkill + ", skills=" + skills + ", otp=" + otp + ", name="
                + name + ", email=" + email + ", address=" + address + ", telephone=" + telephone + ", category="
                + category + ", status=" + status + ", password=" + password + ", managerId=" + managerId + ", aadhar="
                + aadhar + ", pan=" + pan + "]";
    }

}
