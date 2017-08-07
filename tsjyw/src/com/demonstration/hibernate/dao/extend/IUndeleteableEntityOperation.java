package com.demonstration.hibernate.dao.extend;

import java.util.List;
import org.hibernate.criterion.Criterion;

/** 
 * �������֧��Entity����ֱ��ɾ������֧�ֵ�Operation. 
 * 
 * @author springside 
 *  
 */ 
public interface IUndeleteableEntityOperation<T> { 
    /* 
     * Undelete Entity�õ��ļ�������,��ΪҪͬʱ���Interface��Annotation,���Լ��зŴ�. 
     */ 
    String UNVALID_VALUE = "-1";
    String NORMAL_VALUE = "0";
    String STATUS = "status";
    /** 
     * ȡ������״̬Ϊ��Ч�Ķ���. 
     */ 
    List<T> getAllValid();
    /** 
     * ɾ�����󣬵������Undeleteable��entity,���ö����״̬������ֱ��ɾ��. 
     */ 
    void remove(Object entity);
    /** 
     * ��ȡ������ɾ�������hql�������. 
     */ 
    String getUnDeletableHQL(); 
    /** 
     * ��ȡ������ɾ�������Criterion�������. 
     */ 
    Criterion getUnDeletableCriterion(); 
}