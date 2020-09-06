package task_service;

import junit.framework.TestCase;
import org.junit.Test;
import storage_service.RawMaterial;
import storage_service.StorageChangeType;

public class CommonTaskIOTest extends TestCase {

    @Test
    public void testCloningOperation(){
        RawMaterial material = new RawMaterial("Bar", 0.0);
        RawMaterial product = new RawMaterial("Board", 0.0);

        CommonTaskIO rootObject = new CommonTaskIO(material,
                                                    product,
                                                    0.0,
                                                    0.0);

        CommonTaskIO cloneObject = rootObject.clone();
//        CommonTaskIO cloneObject = rootObject;

        cloneObject.setParameters(0.14,
                2.5);
        System.out.println(rootObject);
        System.out.println(cloneObject);

        assertFalse(rootObject.toString().equals(cloneObject.toString()));



    }
}