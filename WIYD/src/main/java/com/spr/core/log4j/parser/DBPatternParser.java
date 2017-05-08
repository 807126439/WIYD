package com.spr.core.log4j.parser;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.spi.LoggingEvent;

public class DBPatternParser extends PatternParser{
	
	
	public DBPatternParser(String pattern){
        super(pattern);
    }
	
    @Override
    protected void finalizeConverter(char c)
    {
        switch (c)
        {
            case 'm':
                // 如果是打印信息，调用DBPatternConverter来替换一个单引号成两个单引号
                addConverter(new DBPatternConverter(formattingInfo, c));
                currentLiteral.setLength(0);
                break;
            default:
                // 其他情况调用父类的实现
                super.finalizeConverter(c);
                break;
        }
    }
    
    private static class DBPatternConverter extends PatternConverter{
        char type;
        DBPatternConverter(FormattingInfo formattingInfo, char type)
        {
            super(formattingInfo);
            this.type = type;
        }
        public String convert(LoggingEvent event)
        {
            switch (type)
            {
                case 'm':
                    return event.getRenderedMessage().replace("'", "''");
                default:
                    return null;
            }
        }
    }

}
