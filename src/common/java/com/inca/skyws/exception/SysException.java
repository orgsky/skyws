package com.inca.skyws.exception;
public class SysException extends Exception {
	private static final long serialVersionUID = -4958396291027076959L;
	private ExceptionEnum expEnum;

	public SysException(ExceptionEnum expEnum) {
		this.expEnum = expEnum;
	}

	public ExceptionEnum getExceptionEnum() {
		return expEnum;
	}

	public void setExceptionEnum(ExceptionEnum expEnum) {
		this.expEnum = expEnum;
	}

	public SysException(String msg, Throwable t) {
		super(msg, t);
	}

	public SysException(Throwable t) {
		super(t);
	}

	public SysException(String msg) {
		super(msg);
	}

	@Override
	public String toString() {
		return "SysException [" + "expEnum=" + expEnum + "] " + super.toString();
	}
}
