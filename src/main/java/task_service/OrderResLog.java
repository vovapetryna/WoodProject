package task_service;

import storage_service.RawMaterial;

import java.util.LinkedList;

public class OrderResLog {
    public final double                     totalPrice;
    public final double                     totalWage;

    public final LinkedList<RawMaterial> listProducts;
    public final LinkedList<RawMaterial>    listMaterials;


    public OrderResLog(double totalPrice,
                       double totalWage,
                       LinkedList<RawMaterial> listProducts,
                       LinkedList<RawMaterial> listMaterials){
        this.totalPrice = totalPrice;
        this.totalWage = totalWage;
        this.listProducts = listProducts;
        this.listMaterials = listMaterials;
    }
}
