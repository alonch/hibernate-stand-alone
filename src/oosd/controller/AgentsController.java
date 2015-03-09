/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import oosd.dao.Agent;
import oosd.model.AgentsModel;
import oosd.model.JpaDAO;
import oosd.model.TableModel;
import oosd.view.AgentsFrame;

/**
 *
 * @author alonch
 */
public class AgentsController {

    AgentsFrame view;
    private final AgentsModel model;

    public AgentsController() {
        view = new AgentsFrame();
        model = AgentsModel.getInstance();
        prepareView();
    }

    private void prepareView() {
        List<Agent> data = model.findAll();
        TableModel tableModel = new TableModel(data, model, Agent.class);
        view.setAgentModel(tableModel);
        view.refreshView();        
    }

    public JFrame getView() {
        return view;
    }

}
