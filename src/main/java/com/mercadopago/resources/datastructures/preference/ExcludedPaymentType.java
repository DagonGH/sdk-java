package com.mercadopago.resources.datastructures.preference;

import com.mercadopago.core.annotations.validation.Size;

/**
 * Mercado Pago SDK
 * Excluded Payment Types class
 */
public class ExcludedPaymentType {

    @Size(max=256) private String id = null;

    public ExcludedPaymentType() { }

    public ExcludedPaymentType(String id) {
        this.id=id;
    }

    public void ExcludedPaymentType(String id){
        this.id=id;
    }

    public ExcludedPaymentType setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

}
