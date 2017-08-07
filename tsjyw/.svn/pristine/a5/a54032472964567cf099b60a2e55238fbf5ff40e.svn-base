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
     * 解析器是否支持当前参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 指定参数如果被应用MyParam注解，则使用该解析器。
        // 如果直接返回true，则代表将此解析器用于所有参数
        return parameter.hasParameterAnnotation(SearchParam.class);
    }
    
    /**
     * 将request中的请求参数解析到当前Controller参数上
     * @param parameter 需要被解析的Controller参数，此参数必须首先传给{@link #supportsParameter}并返回true
     * @param mavContainer 当前request的ModelAndViewContainer
     * @param webRequest 当前request
     * @param binderFactory 生成{@link WebDataBinder}实例的工厂
     * @return 解析后的Controller参数
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
				System.out.println("错误的查询条件方法: " + method);
				return;
			}
			ConditionItem item = new ConditionItem(field, qm, val);
			item.setPrefix(prefix);
			item.setOrGroup(orGroup);
			model.getItems().add(item);
		}
	}
}
