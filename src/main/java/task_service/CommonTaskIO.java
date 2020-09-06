package task_service;

public class CommonTaskIO{
    private String material;
    private String product;
    private String description;

    double materialVolume;
    double productVolume;

    public CommonTaskIO(String material,
                         String product,
                         double materialVolume,
                         double productVolume){
        this.materialVolume = materialVolume;
        this.productVolume = productVolume;

        this.material = material;
        this.product = product;
    }

    public void setParameters(double materialVolume,
                              double productVolume){
        this.materialVolume = materialVolume;
        this.productVolume = productVolume;
    }
}
