package storage_service;

import client_worker_service.ClientProvider;
import department_service.Department;
import task_service.CommonTaskIO;

import java.time.LocalDateTime;

public class RawMaterial{
    private StorageChangeType   changeType;

    private String              type;
    private ClientProvider      provider;
    private double              purchasePrice;

    private double              value;

    private Department          department;

    private LocalDateTime       dateTime;

    public RawMaterial(StorageChangeType changeType,
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

    public RawMaterial(String type,
                       double value){
        this.type = type;
        this.value = value;
    }

    public void setValue(double value){
        this.value = value;
    }

    public double getValue(){
        return value;
    }

    public void setChangeType(StorageChangeType changeType){
        this.changeType = changeType;
    }

    public boolean typeCompare(RawMaterial anotherMaterial){
        return type.equals(anotherMaterial.type);
    }

    public RawMaterial clone(){
        return new RawMaterial(changeType,
                                type,
                                provider,
                                purchasePrice,
                                value,
                                department,
                                dateTime);
    }

    @Override
    public String toString() {
        return "RawMaterial{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
