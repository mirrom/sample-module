package sample.module.internal.security.permission.resource.definition;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.PortletResourcePermissionDefinition;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sample.module.constants.SampleModuleConstants;
import sample.module.constants.SampleModulePortletKeys;


@Component(immediate = true,
        service = PortletResourcePermissionDefinition.class)
public class SampleModulePortletResourcePermissionDefinition implements PortletResourcePermissionDefinition {
    
    @Reference
    private StagingPermission stagingPermission;
    
    @Override
    public PortletResourcePermissionLogic[] getPortletResourcePermissionLogics() {
        
        return new PortletResourcePermissionLogic[]{ new StagedPortletPermissionLogic(stagingPermission,
                SampleModulePortletKeys.SAMPLE_MODULE_PORTLET_FULL_LIFERAY_NAME) };
    }
    
    @Override
    public String getResourceName() {
        
        return SampleModuleConstants.RESOURCE_NAME;
    }
    
}
