package task_service;

import storage_service.RawMaterial;

public final class TaskResLog {
    public final double      totalPrice;
    public final double      totalWage;
    public final RawMaterial product;
    public final RawMaterial material;

    public TaskResLog(double totalPrice,
                      double totalWage,
                      RawMaterial product,
                      RawMaterial material){
        this.totalPrice = totalPrice;
        this.totalWage = totalWage;
        this.product = product;
        this.material = material;
    }

}
