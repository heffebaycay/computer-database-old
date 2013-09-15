package fr.epf.computer.domain;


import javax.persistence.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table( name = "computer")
@NamedQuery (name = "findAllComputers", query = "Select c from Computer c")
public class Computer {

    @Id
    @GeneratedValue
    private long id;

    @Column( name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "introduced")
    private Date introduced;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "discontinued")
    private Date discontinued;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;


    public Computer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getIntroduced() {
        return introduced;
    }


    /**
     * Returns the Introduced date attribute formatted in a human readable way (yyyy-mm-dd)
     *
     * Todo: remove the need for this method
     *
     * @return
     */
    public String getIntroducedAsString() {
        // Creating a SDF in order to format the Introduced date the way we want
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        return sdf.format(getIntroduced());
    }

    public void setIntroduced(Date introduced) {
        this.introduced = introduced;
    }

    public Date getDiscontinued() {
        return discontinued;
    }


    /**
     * Returns the Discontinued date attribute formatted in a human readable way (yyyy-mm-dd)
     *
     * Todo: remove the need for this method
     *
     * @return
     */
    public String getDiscontinuedAsString() {
        // Creating a SDF in order to format the Discontinued date the way we want
        SimpleDateFormat sdf = new SimpleDateFormat("y-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        return sdf.format(getDiscontinued());

    }

    public void setDiscontinued(Date discontinued) {
        this.discontinued = discontinued;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    public static class Builder {
		private Computer computer;
		
		public Builder() {
			computer = new Computer();
		}
		
		public Builder id(long id) {
			computer.setId(id);
			return this;
		}
		
		public Builder name(String name) {
			computer.setName(name);
			return this;
		}
		
		public Builder introduced(Date introduced) {
			computer.setIntroduced(introduced);
			return this;
		}
		
		public Builder discontinued(Date discontinued) {
			computer.setDiscontinued(discontinued);
			return this;
		}
		
		public Builder company(Company company) {
			computer.setCompany(company);
			return this;
		}
		
		public Computer build() {
			return computer;
		}
	}
}
