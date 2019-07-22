package com.kgate.dao;

import com.kgate.model.Skill;
import java.util.List;

public interface SkillDao {

    public void addSkill(Skill skill);

    public List<Skill> getAllSkill();

    public void deleteSkill(Integer skill_Id);

    public Skill updateSkill(Skill skill);

    public Skill getSkill(int skill_Id);

    public Skill getSkillByName(String skillName);

    // retrive employeeSkill list
    public List<String> getEmployeeSkill(int empid);

    public List<String> getEmployeeSkillByEmail(String email);

    //return skill Name
    public String findSkill(String skill);
}
