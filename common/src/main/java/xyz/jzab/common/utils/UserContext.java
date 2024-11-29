package xyz.jzab.common.utils;

/**
 * @author JZAB
 */
public class UserContext {
    public static final ThreadLocal<Long> tl = new ThreadLocal<>();

    public static void setUser(long id){
        tl.set(id);
    }

    public static long getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
