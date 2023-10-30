package com.fhicks.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    private int targetRow;
    private int targetColumn;
    private Color backgroundColor;

    public CustomCellRenderer(int targetRow, int targetColumn, Color backgroundColor) {
        this.targetRow = targetRow;
        this.targetColumn = targetColumn;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row == targetRow && column == targetColumn) {
            component.setBackground(backgroundColor);
        } else {
            if (!(row < targetRow)){
                component.setBackground(table.getBackground());
                // Reset the background color for other cells
            }
        }
        return component;
    }
}