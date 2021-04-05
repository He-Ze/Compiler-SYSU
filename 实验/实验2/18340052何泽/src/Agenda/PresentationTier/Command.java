package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * 接口，一个抽象类，根据不同但命令创建不同的对象从而完成不同的功能
 */
public interface Command {
    /**
     * 检查参数是否合法
     *
     * @param command 输入的命令
     * @return 返回命令是否合法
     */
    boolean check(String[] command);

    /**
     * 执行相关程序
     *
     * @param command 输入的命令
     * @param users   用户列表
     */
    void exec(String[] command, List<User> users);
}