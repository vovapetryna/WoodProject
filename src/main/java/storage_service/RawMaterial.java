package storage_service;

import client_worker_service.ClientProvider;
import department_service.Department;

import java.time.LocalDateTime;

public final class RawMaterial{
    private StorageChangeType   changeType;

    final private String        type;
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

    //standard clone method will be implemented soon
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
//                "changeType='" + changeType.name() + '\'' +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
