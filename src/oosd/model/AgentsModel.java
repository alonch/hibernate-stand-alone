/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd.model;
import oosd.dao.Agent;

/**
 *
 * @author alonch
 */
public class AgentsModel extends JpaDAO<Integer, Agent>{
    private static AgentsModel INSTANCE;

    public static AgentsModel getInstance() {
        if (INSTANCE == null){
            INSTANCE = new AgentsModel();
        }
        return INSTANCE;
    }
    
}
