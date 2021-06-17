/**
 * Oberon-0异常包
 */
package exceptions;

/**
 * @author 李文韬 贺韬
 * 
 */
public class OberonException extends Exception {
	/**
	 * 发生错误的行
	 */
	protected int line = -1;
	/**
	 * 发生错误的列
	 */
	protected int column = -1;

	/**
	 * 带定位的构造器
	 * 
	 * @param line
	 *            发生错误的行
	 * @param column
	 *            发生错误的列
	 */
	public OberonException(int line, int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * 空构造器
	 * 
	 */
	public OberonException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		if (line == -1 && column == -1)
			return "Oberon language error";
		else
			return "Oberon language error at " + line + ", " + column;
	}

}
