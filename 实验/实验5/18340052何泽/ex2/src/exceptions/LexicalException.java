/**
 * Oberon-0异常包
 */
package exceptions;

/**
 * @author 李文韬 贺韬
 * 
 */
public class LexicalException extends OberonException {

	/**
	 * 带定位的构造器
	 * 
	 * @param line
	 *            发生错误的行
	 * @param column
	 *            发生错误的列
	 */
	public LexicalException(int line, int column) {
		super(line, column);
	}

	/**
	 * 空构造器
	 * 
	 */
	public LexicalException() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		if (line == -1 && column == -1)
			return "Oberon lexical error";
		else
			return "Oberon lexical error at " + line + ", " + column;
	}

}
