/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class IllegalIdentifierLengthException extends LexicalException{

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public IllegalIdentifierLengthException(int line, int column) {
		super(line, column);
	}

	/**
	 * �չ�����
	 * 
	 */
	public IllegalIdentifierLengthException() {
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
			return "Identifier too long";
		else
			return "Identifier too long at " + line + ", " + column;
	}

}
