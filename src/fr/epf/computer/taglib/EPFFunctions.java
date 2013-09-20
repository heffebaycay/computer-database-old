package fr.epf.computer.taglib;

import fr.epf.computer.utils.EResult;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class EPFFunctions  {

    /**
     * Generates the end of the List URL with the different GET parameters, for pagination links
     *
     * Sample result: search=Macintosh&p=3&sortBy=name&order=asc
     *
     *
     * @param currentPage
     * @param searchQuery
     * @param sortCriterion
     * @param sortOrder
     * @return
     */
    public static String generateGetParams(int currentPage, String searchQuery, String sortCriterion, String sortOrder) {

        if(searchQuery != null && !searchQuery.trim().isEmpty()) {
            return String.format("search=%s&p=%d&sortBy=%s&order=%s", searchQuery, currentPage, sortCriterion, sortOrder);
        } else {
            return String.format("p=%d&sortBy=%s&order=%s", currentPage, sortCriterion, sortOrder);
        }

    }

    /**
     * Returns a string representation of a Date object in the format defined by the format parameter
     *
     * @param date     The base Date object
     * @param format   Format the date should be returned in. See the SimpleDateFormat JavaDoc for more details.
     * @return         A string representation of the date object given in the format defined by the format parameter
     */
    public static String formatDate(Date date, String format) {
        String strDate = null;

        if(date == null)
            return "";

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // While this is pretty bad in term of design, let's just assume our reference TimeZone is Paris'
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        strDate = sdf.format(date);

        return strDate;
    }

    public static int bwAnd(int i, int j) {
        int res = i & j;
        return  res;
    }

    public static int eResult(String eResultName) {
        try {
            Field field = EResult.class.getField(eResultName);
            return field.getInt(null);
        } catch (NoSuchFieldException e ) {
            return 0;
        } catch ( IllegalAccessException e ) {
            return 0;
        }
    }

}
