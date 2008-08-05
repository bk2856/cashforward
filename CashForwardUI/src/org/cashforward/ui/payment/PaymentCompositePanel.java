package org.cashforward.ui.payment;

import ca.odell.glazedlists.EventList;
import com.jidesoft.swing.JideButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.UIResource;
import org.cashforward.model.Payment;
import org.cashforward.ui.action.SavePaymentAction;
import org.openide.util.actions.SystemAction;

/**
 *
 * Combines both the interface for Payment Details and
 * Schedule. Also exposes the ability to persist changes via
 * allowUpdate(boolean).
 *
 * @author  Bill
 */
public class PaymentCompositePanel extends javax.swing.JPanel {

    private SavePaymentAction saveAction;
    private UpdateButton updateButton;

    /** Creates new form PaymentCompositePanel */
    public PaymentCompositePanel() {
        initComponents();
        saveAction =
                (SavePaymentAction) SystemAction.get(SavePaymentAction.class);
        updateButton = new UpdateButton();
        this.jideTabbedPane1.setTabTrailingComponent(updateButton);
    }

    public PaymentDetailPanel getPaymentDetailComponent() {
        return this.paymentDetailPanel;
    }
    
    public PaymentScheduleForm getPaymentScheduleComponent() {
        return this.paymentScheduleForm;
    }

    public void setPayees(EventList payees) {
        paymentDetailPanel.setPayees(payees);
    }

    public void setPayment(Payment payment) {
        paymentDetailPanel.setPayment(payment);
        paymentScheduleForm.setPayment(payment);
    }

    public void setLabels(EventList labels) {
        paymentDetailPanel.setLabels(labels);
    }

    public Payment getPayment() {
        Payment payment = paymentDetailPanel.getPayment();
        Payment.Occurence occurence = paymentScheduleForm.getOccurence();
        paymentScheduleForm.updatePaymentOccurence();
        payment.setOccurence(occurence.name());
        return payment;
    }

    public void allowUpdate(boolean update) {
        this.updateButton.setVisible(update);
    }

    class UpdateButton extends JideButton implements UIResource {

        public UpdateButton() {
            super("Update");
            setButtonStyle(JideButton.FLAT_STYLE);
            addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    saveAction.setPayment(getPayment());
                    PaymentCompositePanel.this.saveAction.actionPerformed(null);
                }
            });
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jideTabbedPane1 = new com.jidesoft.swing.JideTabbedPane();
        paymentDetailPanel = new org.cashforward.ui.payment.PaymentDetailPanel();
        paymentScheduleForm = new org.cashforward.ui.payment.PaymentScheduleForm();

        jideTabbedPane1.setColorTheme(com.jidesoft.swing.JideTabbedPane.COLOR_THEME_WINXP);
        jideTabbedPane1.setBoldActiveTab(true);
        jideTabbedPane1.setBoxStyleTab(true);
        jideTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PaymentCompositePanel.class, "PaymentCompositePanel.paymentDetailPanel.TabConstraints.tabTitle"), paymentDetailPanel); // NOI18N
        jideTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PaymentCompositePanel.class, "PaymentCompositePanel.paymentScheduleForm.TabConstraints.tabTitle"), paymentScheduleForm); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jideTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jideTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jidesoft.swing.JideTabbedPane jideTabbedPane1;
    private org.cashforward.ui.payment.PaymentDetailPanel paymentDetailPanel;
    private org.cashforward.ui.payment.PaymentScheduleForm paymentScheduleForm;
    // End of variables declaration//GEN-END:variables
}
