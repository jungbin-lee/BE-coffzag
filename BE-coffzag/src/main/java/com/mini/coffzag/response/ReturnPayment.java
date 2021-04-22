package com.mini.coffzag.response;

import com.mini.coffzag.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReturnPayment {

    private boolean ok;
    private List<Payment> payments;
    private ReturnMsg returnMsg;

    public ReturnPayment(boolean ok, List<Payment> payments, ReturnMsg returnMsg ) {
        this.ok = ok;
        this.payments = payments;
        this.returnMsg = returnMsg;
    }

}
