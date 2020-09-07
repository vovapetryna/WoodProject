package storage_service;

import client_worker_service.ClientProvider;
import department_service.Department;

import java.time.LocalDateTime;

public final class RawMaterial{
    private StorageChangeType changeType;
    final private String      type;
    private ClientProvider    provider;
    private double            purchasePrice;
    private double            value;
    private Department        department;
    private LocalDateTime     dateTime;

    public RawMaterial(String type,
                       ClientProvider provider,
                       double purchasePrice,
                       double value){
        this.type = type;
        this.provider = provider;
        this.purchasePrice = purchasePrice;
        this.value = value;
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

    public void setDateTime(LocalDateTime newTime){ this.dateTime = newTime; }

    public void setDepartment(Department department){ this.department = department; }

    //standard clone method will be implemented soon
    public RawMaterial clone(){
        return new RawMaterial(type,
                provider,
                purchasePrice,
                value);
    }

    @Override
    public String toString() {
        return "RawMaterial{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
