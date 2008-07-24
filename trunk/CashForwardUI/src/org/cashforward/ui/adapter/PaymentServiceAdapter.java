/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cashforward.ui.adapter;

import java.util.Date;
import java.util.List;
import org.cashforward.model.Payment;
import org.cashforward.model.PaymentSearchCriteria;
import org.cashforward.model.Scenario;
import org.cashforward.service.PaymentService;
import org.openide.util.Exceptions;

/**
 * 
 * @author Bill 
 */
public class PaymentServiceAdapter {
    private PaymentService paymentService;
    
    public PaymentServiceAdapter(){
        paymentService = new PaymentService();
    }
    
    public boolean addOrUpdatePayment(Payment payment) {
        try { 
            return paymentService.addOrUpdatePayment(payment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createScenario(Scenario currentScenario, 
            Scenario newScenario) {
        try {
            return paymentService.createScenario(currentScenario,
                    newScenario);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Payment enterNextPayment(Payment scheduledPayment) {
        try {
            return paymentService.enterNextPayment(scheduledPayment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Scenario getDefaultScenario() {
        try {
            return paymentService.getDefaultScenario();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public List getScenarios() {
        try {
            return paymentService.getScenarios();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex); 
            return null;
        }
    }
    
    public boolean skipNextPayment(Payment scheduledPayment) {
        try {
            return paymentService.skipNextPayment(scheduledPayment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List getPayees() {
       try {
            return paymentService.getPayees();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List getLabels() {
       try {
            return paymentService.getLabels();
        } catch (Exception e) {
        }
        
        return null;
    }

    public List<Payment> getPayments(){
        List<Payment> current = getCurrentPayments();
        List<Payment> scheduled = getScheduledPayments();
        if (current != null){
            current.addAll(scheduled);
            return current;
        }
        else
            return scheduled;
    }
    
    public List<Payment> getPayments(PaymentSearchCriteria criteria){
        try {
            return paymentService.getPayments(criteria);
        } catch (Exception e) {
        }
        
        return null;
    }

    public List<Payment> getAllPayments(){
        try {
            return paymentService.getPayments(null);
        } catch (Exception e) {
        }

        return null;
    }
    
    public List<Payment> getCurrentPayments(){
        try {
            return paymentService.getCurrentPayments();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List<Payment> getScheduledPayments(){
        try {
            return paymentService.getScheduledPayments();
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public List<Payment> getScheduledPayments(Payment payment, Date startDate,
            Date endDate){
        try {
            return paymentService.getCalculatedPayments(payment, 
                    startDate, endDate);
        } catch (Exception e){
            
        }
        return null;
    }

    public boolean addScenario(Scenario base) {
        try {
            paymentService.addOrUpdateScenario(base);
            return true;
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        return false;
    }
}