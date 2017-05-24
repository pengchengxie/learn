package xin.imba.commons.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Util - 时间工具类
 * Created by xiepengcheng on 2017/2/9.
 */
public final class DateUtil {

    /**
     * 获取当前时间的年月日信息
     *
     * @return
     */
    public static String[] getYMD() {
        String[] ymd = new String[3];
        Calendar now = Calendar.getInstance();
        ymd[0] = now.get(Calendar.YEAR) + "";
        ymd[1] = (now.get(Calendar.MONTH) + 1) + "";
        ymd[2] = now.get(Calendar.DAY_OF_MONTH) + "";
        return ymd;
    }

    /**
     * 获取格式化的时间，如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getFormatDate( String formatter, Date date ) {
        SimpleDateFormat sdf = new SimpleDateFormat( formatter );
        String result = sdf.format( date );
        return result;
    }

    /**
     * 简述：计算与当前的时间间隔
     *
     * @param date 对比的时间
     * @return
     */
    public static long getTimeInterval(Date date, long millisecond) {
        return (new Date().getTime() - date.getTime()) / millisecond;// 这样得到的差值是毫秒级别/MS_ONE_HOUR
    }

    /**
     * 是指定日期是否属于某天
     *
     * @param date
     * @param day
     * @return
     */
    public static boolean isTheDay( final Date date, final Date day ) {
        if ( null == date || null == day ) {
            return false;
        }
        return date.getTime() >= dayBegin( day ).getTime() && date.getTime() <= dayEnd( day ).getTime();
    }


    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin( final Date date ) {
        Calendar c = Calendar.getInstance();
        c.setTime( date );
        c.set( Calendar.HOUR_OF_DAY, 0 );
        c.set( Calendar.MINUTE, 0 );
        c.set( Calendar.SECOND, 0 );
        c.set( Calendar.MILLISECOND, 0 );
        return c.getTime();
    }


    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd( final Date date ) {
        Calendar c = Calendar.getInstance();
        c.setTime( date );
        c.set( Calendar.HOUR_OF_DAY, 23 );
        c.set( Calendar.MINUTE, 59 );
        c.set( Calendar.SECOND, 59 );
        c.set( Calendar.MILLISECOND, 999 );
        return c.getTime();
    }
}
