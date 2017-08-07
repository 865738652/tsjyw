package edu.iasd.search;

import java.nio.charset.Charset;
import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SearchMethodArgumentsResolver implements HandlerMethodArgumentResolver  {
	private Charset defaultCharset = Charset.forName("UTF-8"); 
	/**
     * �������Ƿ�֧�ֵ�ǰ����
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // ָ�����������Ӧ��MyParamע�⣬��ʹ�øý�������
        // ���ֱ�ӷ���true��������˽������������в���
        return parameter.hasParameterAnnotation(SearchParam.class);
    }
    
    /**
     * ��request�е����������������ǰController������
     * @param parameter ��Ҫ��������Controller�������˲����������ȴ���{@link #supportsParameter}������true
     * @param mavContainer ��ǰrequest��ModelAndViewContainer
     * @param webRequest ��ǰrequest
     * @param binderFactory ����{@link WebDataBinder}ʵ���Ĺ���
     * @return �������Controller����
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
    	NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		QueryModel queryModel = new QueryModel();
		String[] val;
		for (Iterator<String> itr = webRequest.getParameterNames(); itr.hasNext();) {
			StringBuffer tmp = new StringBuffer(itr.next());
			if (tmp.charAt(0) != '[')
				continue;
			val = webRequest.getParameterValues(tmp.toString());
			if (val.length == 0 || val[0] == null || val[0].isEmpty()) 
				continue;
			byte [] content = val[0].getBytes(defaultCharset); 
            addSearchItem(queryModel, tmp.toString(), new String(content, "UTF-8"));
		}
		return queryModel;
	}
	
	public static void addSearchItem(QueryModel model, String key, String val) {
		String field = "", prefix = "", orGroup = "", method = "";
		String [] keywords = key.split("\\]|\\)|\\}");
		for (String keyword : keywords)
		{
			if (Character.isLetterOrDigit(keyword.charAt(0))) 
				field = keyword;
			else {
				String last = keyword.substring(1);
				if (keyword.charAt(0) == '(') 
					prefix = last;
				else if (keyword.charAt(0) == '[') 
					method = last;
				else if (keyword.charAt(0) == '{') 
					orGroup = last;  
			}
		}

		if (method == null || method.isEmpty()) 
			return;

		if (field != null && !field.isEmpty()) {
			QueryMethod qm = QueryMethod.Equal;
			try{
				qm = Enum.valueOf(QueryMethod.class, method.trim());
			}
			catch(IllegalArgumentException ex){  
				System.out.println("����Ĳ�ѯ��������: " + method);
				return;
			}
			ConditionItem item = new ConditionItem(field, qm, val);
			item.setPrefix(prefix);
			item.setOrGroup(orGroup);
			model.getItems().add(item);
		}
	}
}
