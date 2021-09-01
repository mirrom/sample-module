package sample.module.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.List;

import sample.module.model.SampleEntity;
import sample.module.service.base.SampleEntityLocalServiceBaseImpl;


public class SampleEntityLocalServiceImpl extends SampleEntityLocalServiceBaseImpl {
    
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public SampleEntity addSampleEntity(String sampleText, ServiceContext serviceContext) throws PortalException {
        
        User user = userLocalService.getUserById(serviceContext.getUserId());
        
        Date now = new Date();
        
        SampleEntity sampleEntity = sampleEntityPersistence.create(counterLocalService.increment());
        
        sampleEntity.setCompanyId(serviceContext.getCompanyId());
        sampleEntity.setGroupId(serviceContext.getScopeGroupId());
        sampleEntity.setUserId(user.getUserId());
        sampleEntity.setUserName(user.getFullName());
        sampleEntity.setCreateDate(now);
        sampleEntity.setModifiedDate(now);
        sampleEntity.setExpandoBridgeAttributes(serviceContext);
        
        sampleEntity.setSampleText(sampleText);
        
        sampleEntity = sampleEntityPersistence.update(sampleEntity);
        
        resourceLocalService.addResources(sampleEntity.getCompanyId(), sampleEntity.getGroupId(),
                sampleEntity.getUserId(), SampleEntity.class.getName(), sampleEntity.getPrimaryKey(), false, true,
                true);
        
        return sampleEntity;
    }
    
    @Override
    public SampleEntity deleteSampleEntity(long sampleEntityId) throws PortalException {
        
        return deleteSampleEntity(getSampleEntity(sampleEntityId));
    }
    
    @Indexable(type = IndexableType.DELETE)
    @Override
    public SampleEntity deleteSampleEntity(SampleEntity sampleEntity) throws PortalException {
        
        resourceLocalService.deleteResource(sampleEntity.getCompanyId(), SampleEntity.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, sampleEntity.getPrimaryKey());
        
        return super.deleteSampleEntity(sampleEntity);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId) {
        
        return sampleEntityPersistence.findByGroupId(groupId);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId, int start, int end) {
        
        return sampleEntityPersistence.findByGroupId(groupId, start, end);
    }
    
    @Override
    public List<SampleEntity> getSampleEntitiesByGroupId(long groupId, int start, int end,
            OrderByComparator<SampleEntity> orderByComparator) {
        
        return sampleEntityPersistence.findByGroupId(groupId, start, end, orderByComparator);
    }
    
    @Override
    public int getSampleEntitiesByGroupIdCount(long groupId) {
        
        return sampleEntityPersistence.countByGroupId(groupId);
    }
    
    @Indexable(type = IndexableType.REINDEX)
    @Override
    public SampleEntity updateSampleEntity(long sampleEntityId, String sampleText, ServiceContext serviceContext)
            throws PortalException {
        
        User user = userLocalService.getUserById(serviceContext.getUserId());
        
        Date now = new Date();
        
        SampleEntity sampleEntity = getSampleEntity(sampleEntityId);
        
        sampleEntity.setUserId(user.getUserId());
        sampleEntity.setUserName(user.getFullName());
        sampleEntity.setModifiedDate(now);
        sampleEntity.setExpandoBridgeAttributes(serviceContext);
        
        sampleEntity.setSampleText(sampleText);
        
        sampleEntity = sampleEntityPersistence.update(sampleEntity);
        
        resourceLocalService.updateResources(sampleEntity.getCompanyId(), sampleEntity.getGroupId(),
                SampleEntity.class.getName(), sampleEntity.getPrimaryKey(), serviceContext.getModelPermissions());
        
        return sampleEntity;
    }
    
}
