package com.kcg.trading_system.model;

import java.util.UUID;

public abstract class BaseEntity {
    protected String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() { return id; }

    public abstract void mostrarDados();
}
