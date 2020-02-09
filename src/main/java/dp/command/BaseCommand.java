package dp.command;

/**
 * @author trink
 */
public abstract class BaseCommand {

    /**
     * 执行
     *
     * @return boolean
     */
    abstract boolean execute();

    /**
     * 撤销
     *
     * @return boolean
     */
    abstract boolean undo();
}
