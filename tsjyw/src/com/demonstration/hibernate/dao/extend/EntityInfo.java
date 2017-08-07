package com.demonstration.hibernate.dao.extend;

/** 
 * װ��Entity��Ϣ���ڲ���. 
 * 
 * @author springside 
 *  
 */ 
class EntityInfo { 
    boolean isUndeletable = false; // entity�Ƿ�undeleteable�ı�־
    String statusProperty; // ��ʶ״̬��������
    @SuppressWarnings("unchecked") 
    public EntityInfo(Class entityClass) { 
        init(entityClass); 
    }
    /** 
     * ��ʼ����,�ж�EntityClass�Ƿ�UndeletableEntity. 
     */ 
    @SuppressWarnings("unchecked") 
    private void init(Class entityClass) { 
        // ͨ��EntityClass��interface�ж�entity�Ƿ�undeletable 
        if (IUndeletableEntity.class.isAssignableFrom(entityClass)) { 
            isUndeletable = true; 
            statusProperty = IUndeleteableEntityOperation.STATUS; 
        }
        // ͨ��EntityClass��annotation�ж�entity�Ƿ�undeletable 
        if (entityClass.isAnnotationPresent(IUndeletable.class)) { 
            isUndeletable = true; 
            IUndeletable anno = (IUndeletable) entityClass.getAnnotation(IUndeletable.class);
            statusProperty = anno.status(); 
        } 
    } 
}
