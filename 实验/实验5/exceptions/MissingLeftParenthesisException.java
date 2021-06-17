/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class MissingLeftParenthesisException extends SyntacticException{

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public MissingLeftParenthesisException(int line, int column) {
		super(line, column);
	}

	/**
	 * �չ�����
	 * 
	 */
	public MissingLeftParenthesisException() {
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
			return "Missing left parenthesis";
		else
			return "Missing left parenthesis at " + line + ", " + column;
	}

}
