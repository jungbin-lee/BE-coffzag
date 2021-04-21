package com.mini.coffzag.response;

import com.mini.coffzag.entity.Payment;
import com.mini.coffzag.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReturnPayment {

    private boolean ok;
    private Payment payment;
    private List<Payment> payments;
    private User user;
    private ReturnMsg returnMsg;

    public ReturnPayment(boolean ok, User user, List<Payment> payments, ReturnMsg returnMsg ) {
        this.ok = ok;
        this.payments = payments;
        this.user = user;
        this.returnMsg = returnMsg;
    }

}
