/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cashforward.ui.action;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.cashforward.model.Payment;
import org.cashforward.ui.UIContext;
import org.cashforward.ui.adapter.PaymentServiceAdapter;

/**
 * 
 * @author Bill 
 */
public class LoadScheduledPaymentsAction extends AbstractAction {
    
    PaymentServiceAdapter serviceAdapter;
    
    public LoadScheduledPaymentsAction(){
        super("Scheduled");
        putValue(Action.SHORT_DESCRIPTION, "Scheduled");
    }
    
    //TODO threading
    public void actionPerformed(ActionEvent e) {
        if (serviceAdapter == null)
            serviceAdapter = new PaymentServiceAdapter();
        
        List<Payment> paymentList = 
                UIContext.getDefault().getScheduledPayments();
        
        List allPayments = serviceAdapter.getScheduledPayments();
        paymentList.clear();
        paymentList.addAll(allPayments);
        
        UIContext.getDefault().setPaymentOccurence(Payment.Occurence.ONCE);
    }
    
}