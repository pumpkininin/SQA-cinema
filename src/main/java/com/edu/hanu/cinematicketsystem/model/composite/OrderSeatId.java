package com.edu.hanu.cinematicketsystem.model.composite;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderSeatId implements Serializable {
    private static final long serialVersionUID = -3582052521898928329L;

    @Column(name = "order_id", nullable = false, insertable = false, updatable = false)
    private Long orderId;

    @Column(name = "room_seat_id", nullable = false, insertable = false, updatable = false)
    private Long seatId;

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 23;

        hash = hash * prime + orderId.hashCode();
        hash = hash * prime + seatId.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof OrderComboId))
            return false;
        OrderSeatId castedObj = (OrderSeatId) obj;
        return this.orderId.equals(castedObj.orderId) && this.seatId.equals(castedObj.seatId);
    }
}
