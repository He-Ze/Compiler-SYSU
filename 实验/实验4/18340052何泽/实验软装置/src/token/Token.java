/**
 *
 */
package token;

/**
 * 接口
 */
public abstract class Token {
    public String type;
    public int tag;

    /**
     * Instantiates a new Token.
     */
    public Token() {
        type = "";
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 获取种类
     *
     * @return 种类
     */
    public int getTag() {
        return tag;
    }

    /**
     * 获取token的值的字符串形式
     *
     * @return 值的字符串形式
     */
    public abstract String getString();
}
