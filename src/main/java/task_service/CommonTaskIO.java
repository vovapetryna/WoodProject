package task_service;

import org.javatuples.Quartet;
import org.javatuples.Triplet;
import storage_service.RawMaterial;

public class CommonTaskIO implements Cloneable{
    private RawMaterial     material;
    private RawMaterial     product;
    private double          pricePerUnit;
    private double          wageFundPerUnit;

    boolean                 done;

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
        material = null;
        product = null;
        pricePerUnit = 0.0;
        wageFundPerUnit = 0.0;
    }

    public void setParameters(double materialVolume,
                              double productVolume){
        this.material.setValue(materialVolume);
        this.product.setValue(productVolume);
    }

    public void changePricing(double pricePerUnit,
                              double wageFund){
        this.pricePerUnit = pricePerUnit;
        this.wageFundPerUnit = wageFund;
    }

    public CommonTaskIO clone() {
        CommonTaskIO temp = new CommonTaskIO();
        temp.pricePerUnit = pricePerUnit;
        temp.wageFundPerUnit = wageFundPerUnit;
        temp.material = material.clone();
        temp.product = product.clone();
        return temp;
    }

    public Quartet<Double, Double, RawMaterial, RawMaterial> finalizeTask(){
        return new Quartet<Double, Double, RawMaterial, RawMaterial>((Double)(product.getValue()*pricePerUnit),
                                                        (Double)(product.getValue()*wageFundPerUnit),
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
