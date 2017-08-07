package edu.iasd.search;

public enum QueryMethod {
	/// <summary>
	/// ����
	/// </summary>
	//[GlobalCode("=", OnlyAttribute = true)]
	Equal,

	/// <summary>
	/// С��
	/// </summary>
	//// [GlobalCode("<", OnlyAttribute = true)]
	LessThan,

	/// <summary>
	/// ����
	/// </summary>
	// [GlobalCode(">", OnlyAttribute = true)]
	GreaterThan,

	/// <summary>
	/// С�ڵ���
	/// </summary>
	// [GlobalCode("<=", OnlyAttribute = true)]
	LessThanOrEqual,

	/// <summary>
	/// ���ڵ���
	/// </summary>
	// [GlobalCode(">=", OnlyAttribute = true)]
	GreaterThanOrEqual,

	/// <summary>
	/// Like
	/// </summary>
	// [GlobalCode("like", OnlyAttribute = true)]
	Like,

	/// <summary>
	/// In
	/// </summary>
	// [GlobalCode("in", OnlyAttribute = true)]
	In,

	/// <summary>
	/// ����һ��ʱ���ȡ��ǰ���ʱ������, ToSqlδʵ�֣���ʵ����IQueryable
	/// </summary>
	// [GlobalCode("between", OnlyAttribute = true)]
	DateBlock,

	// [GlobalCode("<>", OnlyAttribute = true)]
	NotEqual,
	
	// [GlobalCode("like", OnlyAttribute = true)]
	StartsWith,

	// [GlobalCode("like", OnlyAttribute = true)]
	EndsWith,

	/// <summary>
	/// ����Like������
	/// </summary>
	Contains,

	/// <summary>
	/// ����In������
	/// </summary>
	StdIn,
	
	/// <sum
	/// ����DatetimeС��+23h59m59s999f������
	/// </summary>
	DateTimeLessThanOrEqual
}