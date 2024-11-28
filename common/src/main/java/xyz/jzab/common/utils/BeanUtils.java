package xyz.jzab.common.utils;

import cn.hutool.core.bean.BeanUtil;

/**
 * @author JZAB
 */
public class BeanUtils extends BeanUtil {
    public static <T,R> R toBean(T source, Class<R> target, Convert<T,R> convert){
        R bean = toBean(source, target);
        if(convert!=null) convert.convert(source,bean);
        return bean;
    }
}
