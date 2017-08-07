package com.demonstration.hibernate.dao;

import java.io.Serializable; 
import java.lang.reflect.InvocationTargetException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils; 
import org.hibernate.Criteria; 
import org.hibernate.Query; 
import org.hibernate.criterion.CriteriaSpecification; 
import org.hibernate.criterion.Criterion; 
import org.hibernate.criterion.DetachedCriteria; 
import org.hibernate.criterion.Order; 
import org.hibernate.criterion.Projection; 
import org.hibernate.criterion.Projections; 
import org.hibernate.criterion.Restrictions; 
import org.hibernate.internal.CriteriaImpl;
//import org.hibernate.impl.CriteriaImpl; 
import org.hibernate.metadata.ClassMetadata; 
import org.springframework.orm.hibernate4.support.HibernateDaoSupport; 
import org.springframework.util.Assert; 
import org.springframework.util.ReflectionUtils;

import com.demonstration.hibernate.dao.support.BeanUtils; 
import com.demonstration.hibernate.dao.support.Page;

/** 
 * Hibernate Dao�ķ��ͻ���. 
 * <p/> 
 * �̳���Spring��<code>HibernateDaoSupport</code>,�ṩ��ҳ���������ɱ�ݲ�ѯ���������Է���ֵ���˷�������ת��. 
 * 
 * @author springside 
 *  
 * @see HibernateDaoSupport 
 * @see HibernateEntityDao 
 */ 
@SuppressWarnings("unchecked") 
public class HibernateGenericDao extends HibernateDaoSupport { 
    /** 
     * ����ID��ȡ����. ʵ�ʵ���Hibernate��session.load()��������ʵ�����proxy����. ������󲻴��ڣ��׳��쳣. 
     */ 
    public <T> T get(Class<T> entityClass, Serializable id) { 
        return (T) getHibernateTemplate().load(entityClass, id); 
    }
    /** 
     * ��ȡȫ������. 
     */ 
    public <T> List<T> getAll(Class<T> entityClass) { 
        return getHibernateTemplate().loadAll(entityClass); 
    }
    /** 
     * ��ȡȫ������,�������ֶ������������. 
     */ 
    public <T> List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {
        Assert.hasText(orderBy); 
        if (isAsc) 
            return (List<T>) getHibernateTemplate().findByCriteria( 
                    DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
        else 
            return (List<T>) getHibernateTemplate().findByCriteria( 
                    DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
    }
    /** 
     * �������. 
     */ 
    public void save(Object o) { 
        getHibernateTemplate().saveOrUpdate(o); 
    }
    /** 
     * ɾ������. 
     */ 
    public void remove(Object o) { 
        getHibernateTemplate().delete(o); 
    }
    /** 
     * ����IDɾ������. 
     */ 
    public <T> void removeById(Class<T> entityClass, Serializable id) { 
        remove(get(entityClass, id)); 
    }
    public void flush() { 
        getHibernateTemplate().flush(); 
    }
    public void clear() { 
        getHibernateTemplate().clear(); 
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
        Assert.hasText(hql); 
        Query query = /*currentSession()*/getSessionFactory().getCurrentSession().createQuery(hql); 
        for (int i = 0; i < values.length; i++) { 
            query.setParameter(i, values[i]); 
        } 
        return query; 
    }
    /** 
     * ����Criteria����. 
     * 
     * @param criterions �ɱ��Restrictions�����б�,��{@link #createQuery(String,Object...)}
     */ 
    public <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
        Criteria criteria = /*currentSession()*/getSessionFactory().getCurrentSession().createCriteria(entityClass); 
        for (Criterion c : criterions) { 
            criteria.add(c); 
        } 
        return criteria; 
    }
    /** 
     * ����Criteria���󣬴������ֶ����������ֶ�. 
     * 
     * @see #createCriteria(Class,Criterion[]) 
     */ 
    public <T> Criteria createCriteria(Class<T> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
        Assert.hasText(orderBy);
        Criteria criteria = createCriteria(entityClass, criterions);
        if (isAsc) 
            criteria.addOrder(Order.asc(orderBy)); 
        else 
            criteria.addOrder(Order.desc(orderBy));
        return criteria; 
    }
    /** 
     * ����hql��ѯ,ֱ��ʹ��HibernateTemplate��find����. 
     * 
     * @param values �ɱ����,��{@link #createQuery(String,Object...)}
     */ 
    public List find(String hql, Object... values) { 
        Assert.hasText(hql); 
        return getHibernateTemplate().find(hql, values); 
    }
    /** 
     * ����������������ֵ��ѯ����. 
     * 
     * @return ���������Ķ����б� 
     */ 
    public <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName); 
        return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
    }
    /** 
     * ����������������ֵ��ѯ����,���������. 
     */ 
    public <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value, String orderBy, boolean isAsc) {
        Assert.hasText(propertyName); 
        Assert.hasText(orderBy); 
        return createCriteria(entityClass, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
    }
    /** 
     * ����������������ֵ��ѯΨһ����. 
     * 
     * @return ����������Ψһ���� or null if not found. 
     */ 
    public <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
        Assert.hasText(propertyName); 
        return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
    }
    /** 
     * ��ҳ��ѯ������ʹ��hql. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     */ 
    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql); 
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1"); 
        // Count��ѯ 
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values); 
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1) 
            return new Page(); 
        // ʵ�ʲ�ѯ���ط�ҳ���� 
        int startIndex = Page.getStartOfPage(pageNo, pageSize); 
        Query query = createQuery(hql, values); 
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list); 
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
    public Page dataQuery(String hql, int start, int pageSize, Object... values){
        // Count��ѯ 
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List countlist = getHibernateTemplate().find(countQueryString, values); 
        long totalCount = (Long) countlist.get(0);
        if (totalCount < 1) 
            return new Page(); 
        // ʵ�ʲ�ѯ���ط�ҳ���� 
        int startIndex = start; 
        Query query = createQuery(hql, values); 
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list); 
     } 
    /** 
     * ��ҳ��ѯ������ʹ������ò�ѯ�����������<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) { 
        Assert.notNull(criteria); 
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1"); 
        CriteriaImpl impl = (CriteriaImpl) criteria;
        // �Ȱ�Projection��OrderBy����ȡ����,���������ִ��Count���� 
        Projection projection = impl.getProjection(); 
        List<CriteriaImpl.OrderEntry> orderEntries; 
        try { 
            orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
            BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList()); 
        } catch (Exception e) { 
            throw new InternalError(" Runtime Exception impossibility throw "); 
        }
        // ִ�в�ѯ 
        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        // ��֮ǰ��Projection��OrderBy�����������ȥ 
        criteria.setProjection(projection); 
        if (projection == null) { 
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY); 
        }
        try { 
            BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries); 
        } catch (Exception e) { 
            throw new InternalError(" Runtime Exception impossibility throw "); 
        }
        // ���ط�ҳ���� 
        if (totalCount < 1) 
            return new Page();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);; 
        List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list); 
    }
    /** 
     * ��ҳ��ѯ����������entityClass�Ͳ�ѯ������������Ĭ�ϵ�<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(Class entityClass, int pageNo, int pageSize, Criterion... criterions) {
        Criteria criteria = createCriteria(entityClass, criterions); 
        return pagedQuery(criteria, pageNo, pageSize); 
    }
    /** 
     * ��ҳ��ѯ����������entityClass�Ͳ�ѯ��������,�����������Ĭ�ϵ�<code>Criteria</code>. 
     * 
     * @param pageNo ҳ��,��1��ʼ. 
     * @return ���ܼ�¼���͵�ǰҳ���ݵ�Page����. 
     */ 
    public Page pagedQuery(Class entityClass, int pageNo, int pageSize, String orderBy, boolean isAsc,
                           Criterion... criterions) { 
        Criteria criteria = createCriteria(entityClass, orderBy, isAsc, criterions);
        return pagedQuery(criteria, pageNo, pageSize); 
    }
    /** 
     * �ж϶���ĳЩ���Ե�ֵ�����ݿ����Ƿ�Ψһ. 
     * 
     * @param uniquePropertyNames ��POJO�ﲻ���ظ��������б�,�Զ��ŷָ� ��"name,loginid,password" 
     */ 
    public <T> boolean isUnique(Class<T> entityClass, Object entity, String uniquePropertyNames) {
        Assert.hasText(uniquePropertyNames); 
        Criteria criteria = createCriteria(entityClass).setProjection(Projections.rowCount());
        String[] nameList = uniquePropertyNames.split(","); 
        try { 
            // ѭ������Ψһ�� 
            for (String name : nameList) { 
                criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));
            }
            // ���´���Ϊ�������update�����,�ų�entity����.
            String idName = getIdName(entityClass);
            // ȡ��entity������ֵ 
            Serializable id = getId(entityClass, entity);
            // ���id!=null,˵�������Ѵ���,�ò���Ϊupdate,�����ų�������ж� 
            if (id != null) 
                criteria.add(Restrictions.not(Restrictions.eq(idName, id))); 
        } catch (Exception e) { 
            ReflectionUtils.handleReflectionException(e); 
        } 
        return (Integer) criteria.uniqueResult() == 0; 
    }
    /** 
     * ȡ�ö��������ֵ,��������. 
     */ 
    public Serializable getId(Class entityClass, Object entity) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException { 
        Assert.notNull(entity); 
        Assert.notNull(entityClass); 
        return (Serializable) PropertyUtils.getProperty(entity, getIdName(entityClass));
    }
    /** 
     * ȡ�ö����������,��������. 
     */ 
    public String getIdName(Class clazz) { 
        Assert.notNull(clazz); 
        ClassMetadata meta = getSessionFactory().getClassMetadata(clazz); 
        Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
        String idName = meta.getIdentifierPropertyName(); 
        Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
        return idName; 
    }
    /** 
     * ȥ��hql��select �Ӿ䣬δ����union�����,����pagedQuery. 
     * 
     * @see #pagedQuery(String,int,int,Object[]) 
     */ 
    private static String removeSelect(String hql) { 
        Assert.hasText(hql); 
        int beginPos = hql.toLowerCase().indexOf("from"); 
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos); 
    }
    /** 
     * ȥ��hql��orderby �Ӿ䣬����pagedQuery. 
     * 
     * @see #pagedQuery(String,int,int,Object[]) 
     */ 
    private static String removeOrders(String hql) { 
        Assert.hasText(hql); 
        Pattern p = Pattern.compile("order//s*by[//w|//W|//s|//S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql); 
        StringBuffer sb = new StringBuffer(); 
        while (m.find()) { 
            m.appendReplacement(sb, ""); 
        } 
        m.appendTail(sb); 
        return sb.toString(); 
    }    
} 