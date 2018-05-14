package pl.aparzych.films.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Film {
	@Id
	private Long id;
	@Basic
	private String name;
	@Basic
	private String description;

	public Film() {
	}

	public Film(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() { return id;	}
	public void setId(Long id) { this.id = id;	}
}
