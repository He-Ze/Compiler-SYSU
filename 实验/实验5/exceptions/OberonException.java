/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class OberonException extends Exception {
	/**
	 * �����������
	 */
	protected int line = -1;
	/**
	 * �����������
	 */
	protected int column = -1;

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public OberonException(int line, int column) {
		this.line = line;
		this.column = column;
	}

	/**
	 * �չ�����
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
