package Agenda.PresentationTier;

import Agenda.BusinessLogic.User;

import java.util.List;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Check boolean.
     *
     * @param command the command
     * @return the boolean
     */
    boolean check(String[] command);

    /**
     * Exec.
     *
     * @param command the command
     * @param users   the users
     */
    void exec(String[] command, List<User> users);
}