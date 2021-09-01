package sample.module.constants;

import com.liferay.portal.kernel.security.permission.ActionKeys;


public class SampleModuleActionKeys extends ActionKeys {
    
    public static final String ADD_SAMPLE_ENTITY = "ADD_SAMPLE_ENTITY";
    
    private SampleModuleActionKeys() {
        
        throw new IllegalStateException();
    }
    
}
