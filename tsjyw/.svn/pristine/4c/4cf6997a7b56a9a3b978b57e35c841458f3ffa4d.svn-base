package edu.iasd.search;

public enum QueryMethod {
	/// <summary>
	/// 等于
	/// </summary>
	//[GlobalCode("=", OnlyAttribute = true)]
	Equal,

	/// <summary>
	/// 小于
	/// </summary>
	//// [GlobalCode("<", OnlyAttribute = true)]
	LessThan,

	/// <summary>
	/// 大于
	/// </summary>
	// [GlobalCode(">", OnlyAttribute = true)]
	GreaterThan,

	/// <summary>
	/// 小于等于
	/// </summary>
	// [GlobalCode("<=", OnlyAttribute = true)]
	LessThanOrEqual,

	/// <summary>
	/// 大于等于
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
	/// 输入一个时间获取当前天的时间块操作, ToSql未实现，仅实现了IQueryable
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
	/// 处理Like的问题
	/// </summary>
	Contains,

	/// <summary>
	/// 处理In的问题
	/// </summary>
	StdIn,
	
	/// <sum
	/// 处理Datetime小于+23h59m59s999f的问题
	/// </summary>
	DateTimeLessThanOrEqual
}