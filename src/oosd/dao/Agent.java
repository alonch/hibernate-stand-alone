/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alonch
 */
@Entity(name="Agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int AgentId;
    private String AgtFirstName;
    private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
   
    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int AgentId) {
        this.AgentId = AgentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String AgtFirstName) {
        this.AgtFirstName = AgtFirstName;
    }

    public String getAgtMiddleInitial() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String AgtMiddleInitial) {
        this.AgtMiddleInitial = AgtMiddleInitial;
    }

    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String AgtLastName) {
        this.AgtLastName = AgtLastName;
    }

    public String getAgtBusPhone() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String AgtBusPhone) {
        this.AgtBusPhone = AgtBusPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String AgtEmail) {
        this.AgtEmail = AgtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String AgtPosition) {
        this.AgtPosition = AgtPosition;
    }


    @Override
    public String toString() {
        return AgentId + " " + AgtLastName +", "+ AgtFirstName;
    }
    
    
}
