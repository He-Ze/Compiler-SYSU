/**
 * Oberon-0�쳣��
 */
package exceptions;

/**
 * @author ����� ���
 * 
 */
public class MismatchedCommentException extends LexicalException{

	/**
	 * ����λ�Ĺ�����
	 * 
	 * @param line
	 *            �����������
	 * @param column
	 *            �����������
	 */
	public MismatchedCommentException(int line, int column) {
		super(line, column);
	}

	/**
	 * �չ�����
	 * 
	 */
	public MismatchedCommentException() {
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
			return "Comment mismatched";
		else
			return "Comment mismatched at " + line + ", " + column;
	}

}
