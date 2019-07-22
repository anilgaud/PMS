package com.kgate.model;

public class ProjectDTO {

    private String emp_name;
    private String mngr_name;
    private String tsk_name;
    private String tsk_type;
    private String tst_strt;
    private String tst_endt;
    private String status;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getMngr_name() {
        return mngr_name;
    }

    public void setMngr_name(String mngr_name) {
        this.mngr_name = mngr_name;
    }

    public String getTsk_name() {
        return tsk_name;
    }

    public void setTsk_name(String tsk_name) {
        this.tsk_name = tsk_name;
    }

    public String getTsk_type() {
        return tsk_type;
    }

    public void setTsk_type(String tsk_type) {
        this.tsk_type = tsk_type;
    }

    public String getTst_strt() {
        return tst_strt;
    }

    public void setTst_strt(String tst_strt) {
        this.tst_strt = tst_strt;
    }

    public String getTst_endt() {
        return tst_endt;
    }

    public void setTst_endt(String tst_endt) {
        this.tst_endt = tst_endt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
