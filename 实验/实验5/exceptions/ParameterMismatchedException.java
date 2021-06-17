/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class ParameterMismatchedException extends SemanticException{

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public ParameterMismatchedException(int line, int column) {
		super(line, column);
	}

	/**
	 * �չ�����
	 * 
	 */
	public ParameterMismatchedException() {
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
			return "Parameter mismatched";
		else
			return "Parameter mismatched at " + line + ", " + column;
	}

}
