package fr.epf.computer.wrapper;


import fr.epf.computer.domain.Computer;

import java.util.List;

public class ComputerSearchWrapper {

    private List<Computer> computers;

    private long totalQueryCount;

    public List<Computer> getComputers() {
        return computers;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    public long getTotalQueryCount() {
        return totalQueryCount;
    }

    public void setTotalQueryCount(long totalQueryCount) {
        this.totalQueryCount = totalQueryCount;
    }

    public static class Builder {
        private ComputerSearchWrapper computerSearchWrapper;

        public Builder() {
            computerSearchWrapper = new ComputerSearchWrapper();
        }

        public Builder computers(List<Computer> computers) {
            computerSearchWrapper.setComputers(computers);
            return this;
        }

        public Builder totalQueryCount(long totalQueryCount) {
            computerSearchWrapper.setTotalQueryCount(totalQueryCount);
            return this;
        }

        public ComputerSearchWrapper build() {
            return computerSearchWrapper;
        }


    }
}
