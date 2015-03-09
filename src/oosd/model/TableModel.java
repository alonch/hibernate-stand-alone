/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd.model;

import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alonch
 */
public class TableModel extends AbstractTableModel {
    private final List data;
    private final JpaDAO entityManager;
    private final Class entityClass;

    public TableModel(List data, JpaDAO entityManager, Class entityClass) {
        this.data = data;
        this.entityManager = entityManager;
        this.entityClass = entityClass;
        
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return entityClass.getDeclaredFields().length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entityClass.getDeclaredFields()[columnIndex].getName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return entityClass.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            String field = getColumnName(columnIndex);
            field = field.toUpperCase().charAt(0) + field.substring(1);
            Method method = entityClass.getMethod("get" + field);
            return method.invoke(data.get(rowIndex));
        } catch (Exception ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            String field = getColumnName(columnIndex);
            field = field.toUpperCase().charAt(0) + field.substring(1);
            final Class valueType = getColumnClass(columnIndex);

            switch (valueType.getName()) {
                case "int":
                    aValue = Integer.parseInt(aValue.toString());
                    break;
            }
            Method method = entityClass.getMethod("set" + field, valueType);
            method.invoke(data.get(rowIndex), aValue);
            entityManager.persist(data.get(rowIndex));
        } catch (Exception ex) {
            Logger.getLogger(TableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
