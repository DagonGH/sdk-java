package com.mercadopago.resources.datastructures.preference;

public class ShippingMethod {

    private Integer id = null;

    public ShippingMethod(){};
    public ShippingMethod(Integer id) {
        this.id=id;
    }

    public void ShippingMethod(Integer id){
        this.id=id;
    }

    public ShippingMethod setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getId() {
        return this.id;
    }
}
