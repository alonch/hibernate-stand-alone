/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import oosd.controller.AgentsController;
import oosd.model.JpaDAO;

/**
 *
 * @author alonch
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final AgentsController agentsController = new AgentsController();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                agentsController.getView().setVisible(true);
            }
        });
    }
}
