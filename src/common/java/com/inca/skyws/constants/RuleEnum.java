package com.inca.skyws.constants;

public enum RuleEnum {

	USER("user", "YH", "yyMMdd", 4),
	GROUP("group", "QL", "yyMMdd", 4),
	GOODS("goods", "SP", "yyMMdd", 4),
	ORDER("order","SD", "yyMMdd", 4);

	private String func;
	private String code;
	private String format;
	private Integer length;

	private RuleEnum(String func, String code,String format,Integer length) {
		this.func = func;
		this.code = code;
		this.format = format;
		this.length = length;
	}

	public String getPrefix() {
		return code;
	}

	public String getFunc() {
		return func;
	}
	
	public String getFormat() {
		return format;
	}
	
	public Integer getLength() {
		return length;
	}
	
	public static RuleEnum findByFunc(String func) {
		RuleEnum[] values = RuleEnum.values();
		for (RuleEnum em : values) {
			if (em.getFunc().equalsIgnoreCase(func)) {

				return em;
			}
		}
		return null;
	}
}
