package edu.iasd.search;

public class ConditionItem {

	private String field;
	private QueryMethod method;
	private Object value;
	private String prefix;
	private String orGroup;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public QueryMethod getMethod() {
		return method;
	}

	public void setMethod(QueryMethod method) {
		this.method = method;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getOrGroup() {
		return orGroup;
	}

	public void setOrGroup(String orGroup) {
		this.orGroup = orGroup;
	}

	public ConditionItem(){
	}

	public ConditionItem(String field, QueryMethod method, Object val) {
		this.field = field;
		this.method = method;
		this.value = val;
	}

	public String toQueryString() {
		if (method == QueryMethod.Like)
			return field + " like '%" + value + "%'";
		else if (method == QueryMethod.Equal)
			return field + "=" + toValueString();
		else if (method == QueryMethod.GreaterThan)
			return field + ">" + toValueString();
		else if (method == QueryMethod.GreaterThanOrEqual)
			return field + ">=" + toValueString();
		else if (method == QueryMethod.LessThan)
			return field + "<" + toValueString();
		else if (method == QueryMethod.LessThanOrEqual)
			return field + "<=" + toValueString();
		else if (method == QueryMethod.NotEqual)
			return field + "<>" + toValueString();
		else
			return field + "=" + toValueString();
	}

	protected String toValueString() {
		if (prefix.equals("string"))
			return "'" + value + "'";
		else if (prefix.equals("date"))
			return "CAST('" + value + "' AS DATE)";
		else if (prefix.equals("datetime"))
			return "CAST('" + value + "' AS DATETIME)";
		else
			return value.toString();
	}
}
