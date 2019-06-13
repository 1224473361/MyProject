package com.xhx.mybatisplustest.superm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * mapper父类，不能让这个类被扫描到
 * 
 * @date 2019年6月12日
 * @author lihui
 */
public interface SuperMapper<T> extends BaseMapper<T>{

}
