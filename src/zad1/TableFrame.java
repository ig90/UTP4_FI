package zad1;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class TableFrame extends JFrame implements ActionListener{
    private JTable table;
    private TravelData travelData;

    public TableFrame(TravelData travelData){
        super("Trips");
        this.travelData = travelData;
        this.table = new JTable();
        initGUI();
        updateModel("pl_PL");
    }

    private void initGUI() {
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JRadioButton pl = new JRadioButton("PL", true);
        pl.setActionCommand("pl");
        pl.addActionListener(this);
        JRadioButton en = new JRadioButton("ENG");
        en.setActionCommand("en");
        en.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(pl);
        group.add(en);

        JPanel p = new JPanel();
        p.add(pl);
        p.add(en);
        this.add(p, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("pl")) updateModel("pl_PL");
        else if(e.getActionCommand().equals("en")) updateModel("en_GB");

        this.validate();
    }

    private void updateModel(final String loc) {
        final java.util.List<Trip> l = travelData.getOffers();
        table.setModel(new TableModel() {
            @Override
            public int getRowCount() {
                return l.size();
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public String getColumnName(int columnIndex) {
                ResourceBundle rb = ResourceBundle.getBundle("Messages", new Locale(loc));
                switch (columnIndex){
                    case 0: return rb.getString("column.city");
                    case 1: return rb.getString("column.datefrom");
                    case 2: return rb.getString("column.dateto");
                    case 3: return rb.getString("column.place");
                    case 4: return rb.getString("column.price");
                    case 5: return rb.getString("column.currency");
                }

                return "";
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Trip t = l.get(rowIndex);
                ResourceBundle rb = ResourceBundle.getBundle("Messages", new Locale(loc));
                switch (columnIndex){
                    case 0: return TravelData.getCountryLocale(TravelData.getLocaleFromString(t.getLocale()), t.getCity()).getDisplayCountry(TravelData.getLocaleFromString(loc));
                    case 1: return t.getDateFrom();
                    case 2: return t.getDateTo();
                    case 3: return rb.getString(TravelData.getPlaceKey(t));
                    case 4: return t.getPrice();
                    case 5: return t.getCurrency();
                }

                return "";
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        });
    }
}
