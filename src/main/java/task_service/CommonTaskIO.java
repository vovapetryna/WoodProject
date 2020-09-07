package task_service;

import storage_service.RawMaterial;

public final class CommonTaskIO implements Cloneable{
    private RawMaterial material;
    private RawMaterial product;
    private double      pricePerUnit;
    private double      wageFundPerUnit;
    boolean             done;

    public CommonTaskIO(RawMaterial material,
                        RawMaterial product,
                        double pricePerUnit,
                        double wageFundPerUnit){
        this.material = material;
        this.product = product;
        this.pricePerUnit = pricePerUnit;
        this.wageFundPerUnit = wageFundPerUnit;
        this.done = false;
    }

    private CommonTaskIO(){
        pricePerUnit = 0.0;
        wageFundPerUnit = 0.0;
    }

    public void setParameters(double materialVolume,
                              double productVolume){
        this.material.setValue(materialVolume);
        this.product.setValue(productVolume);
    }

    //will be used with additional analytics service
    public void changePricing(double pricePerUnit,
                              double wageFund){
        this.pricePerUnit = pricePerUnit;
        this.wageFundPerUnit = wageFund;
    }

    //standard clone method will be implemented soon
    public CommonTaskIO clone() {
        CommonTaskIO temp = new CommonTaskIO();

        temp.pricePerUnit = pricePerUnit;
        temp.wageFundPerUnit = wageFundPerUnit;
        temp.material = material.clone();
        temp.product = product.clone();

        return temp;
    }

    public TaskResLog finalizeTask(){
        double totalPrice = product.getValue()*pricePerUnit;
        double totalWageFount = product.getValue()*wageFundPerUnit;

        return new TaskResLog(totalPrice,
                totalWageFount,
                product,
                material);
    }

    public boolean sameMaterialProduct(CommonTaskIO anotherTask){
        return (material.typeCompare(anotherTask.material) && product.typeCompare(anotherTask.product));
    }

    @Override
    public String toString() {
        return "CommonTaskIO{" +
                "material='" + material.toString() + '\'' +
                ", product='" + product.toString() + '\'' +
                '}';
    }
}
