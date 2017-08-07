package com.demonstration.hibernate.dao.extend;

import java.util.List; 
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils; 
import org.hibernate.Criteria; 
import org.hibernate.criterion.Criterion; 
import org.hibernate.criterion.Restrictions; 
import org.springframework.util.Assert; 
import org.springframework.util.ReflectionUtils;
import com.demonstration.hibernate.dao.HibernateEntityDao;

/** 
 * ��ǿ���entity dao. 
 * <p>�Զ�����Undeletable Entity.<br> 
 * Undeletable Entity ��ɾ��ʱֻ��״̬��Ϊ��Ч,��������ִ��ɾ��.<br> 
 * Undeletable Entity ����ͨ��annotation��ӿ�������ʽ������.<br> 
 * ����annotationģʽ������״̬�е�����������Ϊ"status",������ע����ȷ����������Ϊ״̬����.<br> 
 * </p> 
 * 
 * @author springside 
 * 
 * @see HibernateEntityDao 
 * @see EntityInfo 
 * @see IUndeleteableEntityOperation 
 * @see IUndeletable 
 * @see IUndeletableEntity 
 */ 
@SuppressWarnings("unchecked") 
public class HibernateEntityExtendDao<T> extends HibernateEntityDao<T> implements IUndeleteableEntityOperation<T> {
    /** 
     * �����������Entity����Ϣ. 
     */ 
    protected EntityInfo entityInfo;
    /** 
     * ���캯������ʼ��entity��Ϣ. 
     */ 
    public HibernateEntityExtendDao() { 
        entityInfo = new EntityInfo(entityClass); 
    }
    /** 
     * ȡ������״̬Ϊ��Ч�Ķ���. 
     * 
     * @see IUndeleteableEntityOperation#getAllValid() 
     */ 
    public List<T> getAllValid() { 
        Criteria criteria = createCriteria(); 
        if (entityInfo.isUndeletable) 
            criteria.add(getUnDeletableCriterion()); 
        return criteria.list(); 
    }
    /** 
     * ��ȡ������ɾ�������hql�������. 
     * 
     * @see IUndeleteableEntityOperation#getUnDeletableHQL() 
     */ 
    public String getUnDeletableHQL() { 
        return entityInfo.statusProperty + "<>" + UNVALID_VALUE; 
    }
    /** 
     * ��ȡ������ɾ�������Criterion�������. 
     * 
     * @see UndeleteableEntityOperation# 
     */ 
    public Criterion getUnDeletableCriterion() { 
        return Restrictions.not(Restrictions.eq(entityInfo.statusProperty, UNVALID_VALUE));
    }
    /** 
     * ���ر��溯��,�ڱ���ǰ�ȵ���onValid(T),�����������ظ������ݿ���ص�У��. 
     * 
     * @see #onValid(Object) 
     * @see HibernateEntityDao#save(Object) 
     */ 
    @Override 
    public void save(Object entity) { 
        Assert.isInstanceOf(getEntityClass(), entity); 
        onValid((T) entity); 
        super.save(entity); 
    }
    /** 
     * ɾ�����������Undeleteable��entity,���ö����״̬������ֱ��ɾ��. 
     * 
     * @see HibernateEntityDao#remove(Object) 
     */ 
    @Override 
    public void remove(Object entity) { 
        if (entityInfo.isUndeletable) { 
            try { 
                PropertyUtils.setProperty(entity, entityInfo.statusProperty, UNVALID_VALUE);
                save(entity); 
            } catch (Exception e) { 
                ReflectionUtils.handleReflectionException(e); 
            } 
        } else 
            super.remove(entity); 
    }
    /** 
     * �����ݿ���ص�У��,�����ж����������ݿ�����û���ظ�, �ڱ���ʱ������,����������. 
     * 
     * @see #save(Object) 
     */ 
    public void onValid(T entity) { 
    }
    /** 
     * ����Map�е�������Criteria��ѯ. 
     * 
     * @param map Map�н�����������������ֵ��Ĭ��ȫ����ͬ,�����ء� 
     */ 
    public List<T> find(Map map) { 
        Criteria criteria = createCriteria(); 
        return find(criteria, map); 
    }
    /** 
     * ����Map�е�������Criteria��ѯ. 
     * 
     * @param map Map�н�����������������ֵ,Ĭ��ȫ����ͬ,������. 
     */ 
    public List<T> find(Criteria criteria, Map map) { 
        Assert.notNull(criteria); 
        criteria.add(Restrictions.allEq(map)); 
        return criteria.list(); 
    } 
}
