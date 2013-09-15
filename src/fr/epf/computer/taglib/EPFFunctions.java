package fr.epf.computer.taglib;

public class EPFFunctions  {

    /**
     * Generates the end of the URL with the different GET parameters, for pagination links
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


}
