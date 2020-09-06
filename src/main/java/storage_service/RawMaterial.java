package storage_service;

import client_worker_service.ClientProvider;
import department_service.Department;

import java.time.LocalDateTime;

public class RawMaterial {
    private StorageChangeType   changeType;

    private String              type;
    private ClientProvider      provider;
    private double              purchasePrice;

    private double              value;

    private Department          department;

    private LocalDateTime       dateTime;

    RawMaterial(StorageChangeType changeType,
                String type,
                ClientProvider provider,
                double purchasePrice,
                double value,
                Department department,
                LocalDateTime dateTime){
        this.changeType = changeType;
        this.type = type;
        this.provider = provider;
        this.purchasePrice = purchasePrice;
        this.value = value;
        this.department = department;
        this.dateTime = dateTime;
    }

    public boolean typeCompare(RawMaterial anotherMaterial){
        return type.equals(anotherMaterial.type);
    }
}
