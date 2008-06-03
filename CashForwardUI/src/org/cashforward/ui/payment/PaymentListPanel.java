/*
 * PaymentListPanel.java
 *
 * Created on May 19, 2008, 9:53 PM
 */
package org.cashforward.ui.payment;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.AdvancedTableFormat;
import ca.odell.glazedlists.gui.WritableTableFormat;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.EventTableModel;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.cashforward.core.Context;
import org.cashforward.model.Payee;
import org.cashforward.model.Payment;
import org.openide.windows.TopComponent;

/**
 *
 * @author  Bill
 */
public class PaymentListPanel extends TopComponent {

    private EventList payments = new BasicEventList();
    private SortedList sortedItems;
    private FilterList filteredList;
    private EventTableModel tableModel;
    private EventSelectionModel selectionModel;

    /** Creates new form PaymentListPanel */
    public PaymentListPanel() {
        initComponents();
    }

    public void setPayments(EventList payments) {
        this.payments = payments;
        //set up model
        sortedItems =
                new SortedList(payments, new PaymentComparator());

        //filteredList = new FilterList(sortedItems,
        //        matcherFactory.createMatcher(songs,this));

        selectionModel = new EventSelectionModel(sortedItems);
        tableModel = new EventTableModel(payments, new PaymentTableFormat());
        paymentTable.setModel(tableModel);
        paymentTable.setSelectionModel(selectionModel);
        paymentTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting()) {
                            return;
                        }
                        Payment payment = (Payment) sortedItems.get(paymentTable.getSelectedRow());
                        //content.set(Collections.singleton (payment), null);
                        Collection all = Context.getDefault().lookupAll(Payment.class);
                        if (all != null) {
                            Iterator ia = all.iterator();
                            while (ia.hasNext()) {
                                Context.getDefault().remove(ia.next());
                            }
                        }

                        Context.getDefault().add(payment);
                    }
                });

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(paymentTable);

        jToggleButton1.setText(org.openide.util.NbBundle.getMessage(PaymentListPanel.class, "PaymentListPanel.jToggleButton1.text")); // NOI18N

        jToggleButton2.setText(org.openide.util.NbBundle.getMessage(PaymentListPanel.class, "PaymentListPanel.jToggleButton2.text")); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(jToggleButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jToggleButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTable paymentTable;
    // End of variables declaration//GEN-END:variables
    class PaymentComparator implements Comparator {

        public int compare(Object a, Object b) {
            Payment itemA = (Payment) a;
            Payment itemB = (Payment) b;

            //initially sort by date, earliest is more important
            Date itemADate = itemA.getStartDate();
            Date itemBDate = itemB.getStartDate();

            return itemADate.compareTo(itemBDate);
        }
    }

    class PaymentTableFormat implements AdvancedTableFormat, WritableTableFormat  {
        
        //table setup
        final String[] colNames = new String[] {
             "Date","Payee", "Amount", "Balance"};
        
        public int getColumnCount() {
            return colNames.length;
        }
        
        public String getColumnName(int column) {
            return colNames[column];
        }
        
        public Object getColumnValue(Object baseObject, int column) {
            Payment payment = (Payment)baseObject;
            Payee payee = payment.getPayee();
            Date paymentDate = payment.getStartDate();
            float amount = payment.getAmount();
            
            if (column == 0) return paymentDate;
            else if (column == 1) return payee.getName();
            else if (column == 2) return Float.valueOf(amount);
            else
                return "";
        }
        
        public Class getColumnClass(int i) {
            if (i == 0)
                return Date.class;
            else if (i == 2)
                return Float.class;
            else
                return String.class;
        }
        
        public Comparator getColumnComparator(int column) {
            if (column == 0) return GlazedLists.booleanComparator();
            else if (column == 1) return GlazedLists.caseInsensitiveComparator();
            else if (column == 2) return GlazedLists.caseInsensitiveComparator();
            //else if (column == 3) return new AuctionItemCostComparator();
            //else if (column == 4) return new AuctionItemCostComparator();
            //else if (column == 5) return GlazedLists.comparableComparator();
            //else if (column == 6) return GlazedLists.comparableComparator();
            else return GlazedLists.caseInsensitiveComparator();
        }
        
        public boolean isEditable(Object o, int i) {
            if (i == 0) return true;
            else return false;
        }
        
        public Object setColumnValue(Object baseObject, Object editedObject, int i ) {
            //if ( i == 0 )
            //    ((AuctionItem)baseObject).setStarred( (Boolean)editedObject );
            return baseObject;
        }
    }
}