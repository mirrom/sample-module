package sample.module.internal.security.permission.resource.definition;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import sample.module.constants.SampleModuleConstants;
import sample.module.constants.SampleModulePortletKeys;
import sample.module.model.SampleEntity;
import sample.module.service.SampleEntityLocalService;


@Component(immediate = true,
        service = ModelResourcePermissionDefinition.class)
public class SampleEntityModelResourcePermissionDefinition implements ModelResourcePermissionDefinition<SampleEntity> {
    
    @Reference
    private SampleEntityLocalService sampleEntityLocalService;
    
    @Reference(target = "(resource.name=" + SampleModuleConstants.RESOURCE_NAME + ")")
    private PortletResourcePermission portletResourcePermission;
    
    @Reference
    private StagingPermission stagingPermission;
    
    @Override
    public SampleEntity getModel(long primaryKey) throws PortalException {
        
        return sampleEntityLocalService.getSampleEntity(primaryKey);
    }
    
    @Override
    public Class<SampleEntity> getModelClass() {
        
        return SampleEntity.class;
    }
    
    @Override
    public PortletResourcePermission getPortletResourcePermission() {
        
        return portletResourcePermission;
    }
    
    @Override
    public long getPrimaryKey(SampleEntity sampleEntity) {
        
        return sampleEntity.getPrimaryKey();
    }
    
    @Override
    public void registerModelResourcePermissionLogics(ModelResourcePermission<SampleEntity> modelResourcePermission,
            Consumer<ModelResourcePermissionLogic<SampleEntity>> modelResourcePermissionLogicConsumer) {
        
        modelResourcePermissionLogicConsumer.accept(new StagedModelPermissionLogic<>(stagingPermission,
                SampleModulePortletKeys.SAMPLE_MODULE_PORTLET_FULL_LIFERAY_NAME, SampleEntity::getPrimaryKey));
    }
    
}
