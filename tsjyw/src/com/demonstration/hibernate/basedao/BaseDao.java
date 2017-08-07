package com.demonstration.hibernate.basedao;

import java.io.Serializable; 
import java.lang.reflect.InvocationTargetException; 
import java.util.List; 
import java.util.Map;
import org.hibernate.Criteria; 
import org.hibernate.Query; 
import org.hibernate.criterion.Criterion;
import com.demonstration.hibernate.dao.HibernateEntityDao; 
import com.demonstration.hibernate.dao.HibernateGenericDao; 
import com.demonstration.hibernate.dao.extend.HibernateEntityExtendDao; 
import com.demonstration.hibernate.dao.extend.IUndeleteableEntityOperation; 
import com.demonstration.hibernate.dao.support.Page;
 
/** 
 * @author  
 * 
 * IBaseDao��ʵ����ͨ��springע��HibernateEntityDao��HibernateEntityExtendDao��ʵ�� 
 */ 
public class BaseDao<T> implements IBaseDao<T> {
    protected Class<T> entityClass;// DAO�������Entity����. 
    private HibernateEntityDao<T> hedao; 
    private HibernateEntityExtendDao<T> hexdao; 
     
     
    public void setHedao(HibernateEntityDao<T> hedao) { 
        hedao.setEntityClass(entityClass); 
        this.hedao=hedao; 
    }
    public void setHexdao(HibernateEntityExtendDao<T> hexdao) { 
        hexdao.setEntityClass(entityClass); 
        this.hexdao=hexdao; 
    } 
     
    /** 
     *��spring�ṩ���캯��ע�� 
     */ 
    public BaseDao(Class<T> type) { 
        this.entityClass = type; 
    } 
     
    public BaseDao(){} 
    /** 
     * ����ID��ȡ����. 
     *  
     * @see HibernateGenericDao#getId(Class,Object) 
     */ 
    public T get(Serializable id) { 
        return hedao.get(id); 
    }
    /** 
     * ��ȡȫ������ 
     *  
     * @see HibernateGenericDao#getAll(Class) 
     */ 
    public List<T> getAll() { 
        return hedao.getAll(); 
    }

    /** 
     * ��ȡȫ������,���������. 
     *  
     * @see HibernateGenericDao#getAll(Class,String,boolean) 
     */ 
    public List<T> getAll(String orderBy, boolean isAsc) { 
        return hedao.getAll(orderBy, isAsc); 
    }
    /** 
     * ����ID�Ƴ�����. 
     *  
     * @see HibernateGenericDao#removeById(Class,Serializable) 
     */ 
    public void removeById(Serializable id) { 
        hedao.removeById(id); 
    }
    /** 
     * ȡ��Entity��Criteria. 
     *  
     * @see HibernateGenericDao#createCriteria(Class,Criterion[]) 
     */ 
    public Criteria createCriteria(Criterion... criterions) { 
        return hedao.createCriteria(criterions); 
    }
    /** 
     * ȡ��Entity��Criteria,���������. 
     *  
     * @see HibernateGenericDao#createCriteria(Class,String,boolean,Criterion[]) 
     */ 
    public Criteria createCriteria(String orderBy, boolean isAsc, 
            Criterion... criterions) { 
        return hedao.createCriteria(orderBy, isAsc, criterions); 
    }
    /** 
     * ����������������ֵ��ѯ����. 
     *  
     * @return ���������Ķ����б� 
     * @see HibernateGenericDao#findBy(Class,String,Object) 
     */ 
    public List<T> findBy(String propertyName, Object value) { 
        return hedao.findBy(propertyName, value); 
    }
    /** 
     * ����������������ֵ��ѯ����,���������. 
     *  
     * @return ���������Ķ����б� 
     * @see HibernateGenericDao#findBy(Class,String,Object,String,boolean) 
     */ 
    public List<T> findBy(String propertyName, Object value, String orderBy, 
            boolean isAsc) { 
        return hedao.findBy(propertyName, value, orderBy, isAsc); 
    }
    /** 
     * ����������������ֵ��ѯ��������. 
     *  
     * @return ����������Ψһ���� or null 
     * @see HibernateGenericDao#findUniqueBy(Class,String,Object) 
     */ 
    public T findUniqueBy(String propertyName, Object value) { 
        return hedao.findUniqueBy(propertyName, value); 
    }
    /** 
     * �ж϶���ĳЩ���Ե�ֵ�����ݿ���Ψһ. 
     *  
     * @param uniquePropertyNames 
     *            ��POJO�ﲻ���ظ��������б�,�Զ��ŷָ� ��"name,loginid,password" 
     * @see HibernateGenericDao#isUnique(Class,Object,String) 
     */ 
    public boolean isUnique(Object entity, String uniquePropertyNames) { 
        return hedao.isUnique(entity, uniquePropertyNames); 
    }
    /** 
     * ������ Hibernate Session �Ĺ��� 
     *  
     * @param entity 
     */ 
    public void evit(Object entity) { 
        hedao.evit(entity); 
    }
    /** 
     * ȡ������״̬Ϊ��Ч�Ķ���. 
     * 
     * @see IUndeleteableEntityOperation#getAllValid() 
     */ 
    public List<T> getAllValid() { 
        return hexdao.getAllValid(); 
    }
    /** 
     * ��ȡ������ɾ�������hql�������. 
     * 
     * @see IUndeleteableEntityOperation#getUnDeletableHQL() 
     */ 
    public String getUnDeletableHQL() { 
        return hexdao.getUnDeletableHQL(); 
    }
    /** 
     * ��ȡ������ɾ�������Criterion�������. 
     * 
     * @see UndeleteableEntityOperation# 
     */ 
    public Criterion getUnDeletableCriterion() { 
        return hexdao.getUnDeletableCriterion(); 
    }
    /** 
     * ���ر��溯��,�ڱ���ǰ�ȵ���onValid(T),�����������ظ������ݿ���ص�У��. 
     * 
     * @see #onValid(Object) 
     * @see HibernateEntityDao#save(Object) 
     */ 
    public void saveOnValid(Object entity) { 
         hexdao.save(entity); 
    }
    /** 
     * ɾ�����������Undeleteable��entity,���ö����״̬������ֱ��ɾ��. 
     * 
     * @see HibernateEntityDao#remove(Object) 
     */ 
    public void removeUndeleteable(Object entity) { 
           hexdao.remove(entity); 
    }
    /** 
     * �����ݿ���ص�У��,�����ж����������ݿ�����û���ظ�, �ڱ���ʱ������,�ڴ˿���д. 
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
    @SuppressWarnings("unchecked") 
    public List<T> find(Map map) { 
        return hexdao.find(map); 
    }
    /** 
     * ����Map�е�������Criteria��ѯ. 
     * 
     * @param map Map�н�����������������ֵ,Ĭ��ȫ����ͬ,������. 
     */ 
    @SuppressWarnings("unchecked") 
    public List<T> find(Criteria criteria, Map map) { 
        return hexdao.find(criteria, map); 
    }
    /** 
     * ����ID��ȡ����. ʵ�ʵ���Hibernate��session.load()��������ʵ�����proxy����. ������󲻴��ڣ��׳��쳣. 
     */ 
    public T get(Class<T> entityClass, Serializable id) { 
        return hedao.get(entityClass, id); 
    }
    /** 
     * ��ȡȫ������. 
     */ 
    public List<T> getAll(Class<T> entityClass) { 
        return hedao.getAll(entityClass); 
    }
    /** 
     * ��ȡȫ������,�������ֶ������������. 
     */ 
    public List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {    
        return hedao.getAll(entityClass, orderBy, isAsc); 
    }
    /** 
     * �������. 
     */ 
    public void save(Object o) { 
          hedao.save(o); 
    }
    /** 
     * ɾ������. 
     */ 
    public void remove(Object o) { 
         hedao.remove(o); 
    } 
     
    public void flush(){ 
        hedao.flush(); 
    } 
     
    public void clear(){ 
        hedao.clear(); 
    }
    /** 
     * ����Query����. ������Ҫfirst,max,fetchsize,cache,cacheRegion��������õĺ���,�����ڷ���Query����������.
     * ���������������,���£� 
     * <pre> 
     * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list(); 
     * </pre> 
     * ���÷�ʽ���£� 
     * <pre> 
     *        dao.createQuery(hql) 
     *        dao.createQuery(hql,arg0); 
     *        dao.createQuery(hql,arg0,arg1); 
     *        dao.createQuery(hql,new Object[arg0,arg1,arg2]) 
     * </pre> 
     * 
     * @param values �ɱ����. 
     */ 
    public Query createQuery(String hql, Object... values) { 
         
        return hedao.createQuery(hql, values); 
    }
    /** 
     * ����Criteria����. 
     * 
     * @param criterions �ɱ��Restrictions�����б�,��{@link #createQuery(String,Object...)}
     */ 
    public Criteria createCriteria(Class<T> entityClass, 
            Criterion... criterions) { 
         
        return hedao.createCriteria(entityClass, criterions); 
    }
    /** 
     * ����Criteria���󣬴������ֶ����������ֶ�. 
     * 
     * @see #createCriteria(Class,Criterion[]) 
     */ 
    public Criteria createCriteria(Class<T> entityClass, String orderBy, 
            boolean isAsc, Criterion... criterions) { 
        return hedao.createCriteria(entityClass, orderBy, isAsc, criterions); 
    }
    /** 
     * ����hql��ѯ,ֱ��ʹ��HibernateTemplate��find����. 
     * 
     * @param values �ɱ����,��{@link #createQuery(String,Object...)}
     */ 
    @SuppressWarnings("unchecked") 
    public List find(String hql, Object... values) { 
        return hedao.find(hql, values); 
    }
    /** 
     * ����������������ֵ��ѯ����. 
     * 
     * @return ���������Ķ����б� 
     */ 
    public  List<T> findBy(Class<T> entityClass, String propertyName, 
            Object value) { 
         
        return hedao.findBy(entityClass, propertyName, value); 
    }
    /** 
     * ����������������ֵ��ѯ����,���������. 
     */ 
    public List<T> findBy(Class<T> entityClass, String propertyName, 
            Object value, String orderBy, boolean isAsc) { 
        return hedao.findBy(entityClass, propertyName, value, orderBy, isAsc); 
    }
    /** 
     * ����������������ֵ��ѯΨһ����. 
     * 
     * @return ����������Ψһ���� or null if not found. 
     */ 
    public T findUniqueBy(Class<T> entityClass, String propertyName, 
            Object value) { 
        return hedao.findUniqueBy(propertyName, value); 
    }
    /** 
     * ��ҳ��ѯ������ʹ��hql. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     */ 
    public Page pagedQuery(String hql, int pageNo, int pageSize, 
            Object... values) { 
        return hedao.pagedQuery(hql, pageNo, pageSize, values); 
    }
    /** 
     * @author Scott.wanglei 
     * @since  2008-7-21 
     * @param hql ��ѯsql 
     * @param start ��ҳ����һ�����ݿ�ʼ 
     * @param pageSize ÿһ��ҳ��Ĵ�С 
     * @param values ��ѯ���� 
     * @return page���� 
     */ 
    public Page dataQuery(String hql, int start, int pageSize, Object... values) {
        return hedao.dataQuery(hql, start, pageSize, values); 
    }
    /** 
     * ��ҳ��ѯ������ʹ������ò�ѯ�����������<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) { 
        return hedao.pagedQuery(criteria, pageNo, pageSize); 
    } 
     
    /** 
     * ��ҳ��ѯ����������entityClass�Ͳ�ѯ������������Ĭ�ϵ�<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    @SuppressWarnings("unchecked") 
    public Page pagedQuery(Class entityClass, int pageNo, int pageSize, 
            Criterion... criterions) { 
        return hedao.pagedQuery(entityClass, pageNo, pageSize, criterions); 
    }
    @SuppressWarnings("unchecked") 
    public Page pagedQuery(Class entityClass, int pageNo, int pageSize, 
            String orderBy, boolean isAsc, Criterion... criterions) { 
        return hedao.pagedQuery(entityClass, pageNo, pageSize, orderBy, isAsc, criterions);
    }
    /** 
     * �ж϶���ĳЩ���Ե�ֵ�����ݿ����Ƿ�Ψһ. 
     * 
     * @param uniquePropertyNames ��POJO�ﲻ���ظ��������б�,�Զ��ŷָ� ��"name,loginid,password" 
     */ 
    public  boolean isUnique(Class<T> entityClass, Object entity, 
            String uniquePropertyNames) { 
        return hedao.isUnique(entity, uniquePropertyNames); 
    }
    /** 
     * ȡ�ö��������ֵ,��������. 
     */ 
    @SuppressWarnings("unchecked") 
    public Serializable getId(Class entityClass, Object entity) 
            throws NoSuchMethodException, IllegalAccessException, 
            InvocationTargetException { 
        return hedao.getId(entityClass, entity); 
    }
    /** 
     * ȡ�ö����������,��������. 
     */ 
    @SuppressWarnings("unchecked") 
    public String getIdName(Class clazz) { 
        return hedao.getIdName(clazz); 
    }
}