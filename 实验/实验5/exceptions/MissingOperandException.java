/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class MissingOperandException extends SyntacticException{

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public MissingOperandException(int line, int column) {
		super(line, column);
	}

	/**
	 * �չ�����
	 * 
	 */
	public MissingOperandException() {
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
			return "Missing operand";
		else
			return "Missing operand at " + line + ", " + column;
	}

}
