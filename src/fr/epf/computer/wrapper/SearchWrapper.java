package fr.epf.computer.wrapper;

import java.util.List;

public class SearchWrapper<T> {

    private List<T> results;

    private long totalQueryCount;

    public List<T> getResults() {
        return results;
    }

    public SearchWrapper<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    public long getTotalQueryCount() {
        return totalQueryCount;
    }

    public SearchWrapper<T> setTotalQueryCount(long totalQueryCount) {
        this.totalQueryCount = totalQueryCount;
        return this;
    }
}
