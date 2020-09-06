package storage_service;

import client_worker_service.ClientProvider;
import department_service.Department;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class StorageService {
    private LinkedList<RawMaterial>         storageLog = new LinkedList<RawMaterial>();
    private LinkedList<RawMaterial>         uniqueMaterials = new LinkedList<RawMaterial>();

    private void addIfNotExist(RawMaterial newMaterial){
        for (RawMaterial material : uniqueMaterials){
            if (newMaterial.typeCompare(material))
                return;
        }
        uniqueMaterials.add(newMaterial);
    }

    private void addLog(RawMaterial tempNewMaterial){
        storageLog.add(tempNewMaterial);
        addIfNotExist(tempNewMaterial);
    }

    public void PushMaterials(String type,
                              ClientProvider provider,
                              double purchasePrice,
                              double value,
                              Department department,
                              LocalDateTime dateTime){

        RawMaterial tempMaterial = new RawMaterial(StorageChangeType.income,
                type,
                provider,
                purchasePrice,
                value,
                department,
                dateTime);
        addLog(tempMaterial);
    }

    public void GetMaterials(String type,
                             ClientProvider provider,
                             double purchasePrice,
                             double value,
                             Department department,
                             LocalDateTime dateTime){

        RawMaterial tempMaterial = new RawMaterial(StorageChangeType.outcome,
                type,
                provider,
                purchasePrice,
                value,
                department,
                dateTime);
        addLog(tempMaterial);
    }

    public void PushMaterials(LinkedList<RawMaterial> products){
        for (RawMaterial product: products){
            product.setChangeType(StorageChangeType.income);
        }
    }

    public void GetMaterials(LinkedList<RawMaterial> materials){
        for (RawMaterial material: materials){
            material.setChangeType(StorageChangeType.outcome);
        }
    }
}
