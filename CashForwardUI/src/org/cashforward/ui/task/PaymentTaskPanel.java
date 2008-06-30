/*
 * PaymentTaskPanel.java
 *
 * Created on May 17, 2008, 9:18 PM
 */
package org.cashforward.ui.task;

import com.jidesoft.list.AbstractGroupableListModel;
import com.jidesoft.swing.PartialLineBorder;
import com.jidesoft.swing.PartialSide;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.cashforward.ui.action.LoadCurrentPaymentsAction;
import org.cashforward.ui.action.LoadScheduledPaymentsAction;
import org.cashforward.ui.action.LoadSpecificPaymentsAction;

/**
 *
 * @author  Bill
 */
public class PaymentTaskPanel extends javax.swing.JPanel {

    private LoadScheduledPaymentsAction loadScheduledPayments;
    private LoadCurrentPaymentsAction loadCurrentPayments;
    private LoadSpecificPaymentsAction loadSpecificPayments;

    /** Creates new form PaymentTaskPanel */
    public PaymentTaskPanel() {
        initComponents();
        loadCurrentPayments = new LoadCurrentPaymentsAction();
        loadScheduledPayments = new LoadScheduledPaymentsAction();
        loadSpecificPayments = new LoadSpecificPaymentsAction();
        this.groupList1.setModel(new PaymentListFilterModel());
        this.groupList1.setGroupCellRenderer(new GroupCellRenderer());
        this.groupList1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) return;
                
                String filter = //for now a string
                        (String)PaymentTaskPanel.this.groupList1.getSelectedValue();
                //System.out.println(filter);
                if ("Current".equals(filter))
                    PaymentTaskPanel.this.loadCurrentPayments.actionPerformed(null);
                else if ("Scheduled".equals(filter))
                    PaymentTaskPanel.this.loadScheduledPayments.actionPerformed(null);
                else if ("Default".equals(filter))
                    PaymentTaskPanel.this.loadSpecificPayments.actionPerformed(null);
            }
        });
        
        //remember what was last selected? //should be an object
        groupList1.setSelectedIndex(1);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupList1 = new com.jidesoft.list.GroupList();

        jScrollPane1.setViewportView(groupList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private com.jidesoft.list.GroupList groupList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    static class PaymentListFilterModel extends AbstractGroupableListModel {

        private static String[] GROUP_NAMES = {
            "Scenarios","Payments", "Labels"
        };
        private static final String[] scenarios = {
            "Default"
        };
        private static final String[] payments = {
            "Scheduled", "Current"
        };
        private static final String[] labels = {
            "Auto", "Entertainment", "Food"
        };

        public int getSize() {
            //System.out.println("getSize:"+scenarios.length + payments.length + labels.length);
            return scenarios.length + payments.length + labels.length;
        }

        public Object getElementAt(int index) {
            //System.out.println("getElementAt:"+index);
            if (index < scenarios.length) {
                //System.out.println("scenarios:"+(scenarios.length));
                return scenarios[index];
            } else if (index < scenarios.length + payments.length) {
                //System.out.println("scenarios and payments:"+(index - scenarios.length));
                return payments[index - scenarios.length];
            } else {
                //System.out.println("scenarios and payments and labels:"+(index - scenarios.length - payments.length));
                return labels[index - (scenarios.length + payments.length)];
            }
        }

        @Override
        public Object[] getGroups() {
            return GROUP_NAMES;
        }

        @Override
        public Object getGroupAt(int index) {
            //System.out.println("getGroupAt:"+index);
            if (index < scenarios.length) {
                //System.out.println("scenarios:"+(scenarios.length));
                return GROUP_NAMES[0];
            } else if (index < scenarios.length + payments.length) {
                //System.out.println("scenarios and payments:"+(index - scenarios.length));
                return GROUP_NAMES[1];
            } else {
                //System.out.println("scenarios and payments and labels:"+(index - scenarios.length - payments.length));
                return GROUP_NAMES[2];
            }
        }
    }
    
    class GroupCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setBackground(isSelected ? list.getSelectionBackground() : new Color(221, 231, 238));
        label.setForeground(new Color(0, 21, 110));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        label.setBorder(BorderFactory.createCompoundBorder(new PartialLineBorder(Color.LIGHT_GRAY, 1, PartialSide.SOUTH),
                BorderFactory.createEmptyBorder(2, 6, 2, 2)));
        return label;
    }
}

}