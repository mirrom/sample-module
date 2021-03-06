package sample.module.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import sample.module.constants.SampleModuleActionKeys;
import sample.module.constants.SampleModuleConstants;
import sample.module.model.SampleEntity;
import sample.module.service.base.SampleEntityServiceBaseImpl;


@Component(property = { "json.web.service.context.name=samplemodule", "json.web.service.context.path=SampleEntity" },
        service = AopService.class)
public class SampleEntityServiceImpl extends SampleEntityServiceBaseImpl {
    
    private static volatile ModelResourcePermission<SampleEntity> sampleEntityModelResourcePermission =
            ModelResourcePermissionFactory.getInstance(SampleEntityServiceImpl.class,
                    "sampleEntityModelResourcePermission", SampleEntity.class);
    
    private static volatile PortletResourcePermission portletResourcePermission =
            PortletResourcePermissionFactory.getInstance(SampleEntityServiceImpl.class, "portletResourcePermission",
                    SampleModuleConstants.RESOURCE_NAME);
    
    @Override
    public SampleEntity addSampleEntity(String sampleText, ServiceContext serviceContext) throws PortalException {
        
        portletResourcePermission.check(getPermissionChecker(), serviceContext.getScopeGroupId(),
                SampleModuleActionKeys.ADD_SAMPLE_ENTITY);
        
        return sampleEntityLocalService.addSampleEntity(sampleText, serviceContext);
    }
    
    @Override
    public SampleEntity deleteSampleEntity(long sampleEntityId) throws PortalException {
        
        sampleEntityModelResourcePermission.check(getPermissionChecker(), sampleEntityId, ActionKeys.DELETE);
        
        return sampleEntityLocalService.deleteSampleEntity(sampleEntityId);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId) {
        
        return sampleEntityPersistence.filterFindByGroupId(groupId);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId, int start, int end) {
        
        return sampleEntityPersistence.filterFindByGroupId(groupId, start, end);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId, int start, int end,
            OrderByComparator<SampleEntity> orderByComparator) {
        
        return sampleEntityPersistence.filterFindByGroupId(groupId, start, end, orderByComparator);
    }
    
    @Override
    public int getSampleEntitiesByGroupIdCount(long groupId) {
        
        return sampleEntityPersistence.filterCountByGroupId(groupId);
    }
    
    @Override
    public SampleEntity updateSampleEntity(long sampleEntityId, String sampleText, ServiceContext serviceContext)
            throws PortalException {
        
        sampleEntityModelResourcePermission.check(getPermissionChecker(), sampleEntityId, ActionKeys.UPDATE);
        
        return sampleEntityLocalService.updateSampleEntity(sampleEntityId, sampleText, serviceContext);
    }
    
}
