/**
 * 
 */
package token;

/**
 * punctuation类token
 * @author Aaron-Qiu
 *
 */
public class PunctuationToken extends Token{
	String punctuation;

	/**
	 * 构造函数
	 * @param _punctuation，标点符号
	 * @param _tag，标点符号所属类型
	 */
	public PunctuationToken(String _punctuation, int _tag) {
		tag = _tag;
		punctuation =  _punctuation;
	}

	/**
	 * 返回标点string形式
	 * @return 标点的string形式
	 */
	public String getValueofString() {
		return punctuation;
	}

}
