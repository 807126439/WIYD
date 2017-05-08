package com.spr.core.log4j.layout;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

import com.spr.core.log4j.parser.DBPatternParser;

/**
 * 自定义log4j格式化输出层
 * @author wb_java_zjr
 *
 */
public class DBPatternLayout extends PatternLayout{
    @Override
    protected PatternParser createPatternParser(String pattern)
    {
        return new DBPatternParser(pattern);
    }

}
