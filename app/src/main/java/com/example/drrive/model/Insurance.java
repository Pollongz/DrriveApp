package com.example.drrive.model;

public class Insurance {

    private Integer idInsurance;
    private String policyNumber;
    private String assistanceNumber;

    public Insurance() {
    }

    public Insurance(Integer idInsurance, String policyNumber, String assistanceNumber) {
        this.idInsurance = idInsurance;
        this.policyNumber = policyNumber;
        this.assistanceNumber = assistanceNumber;
    }

    public Integer getIdInsurance() {
        return idInsurance;
    }

    public void setIdInsurance(Integer idInsurance) {
        this.idInsurance = idInsurance;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getAssistanceNumber() {
        return assistanceNumber;
    }

    public void setAssistanceNumber(String assistanceNumber) {
        this.assistanceNumber = assistanceNumber;
    }
}
