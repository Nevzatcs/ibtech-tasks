package com.ibtech.task.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Id;

@MappedSuperclass
public abstract class BaseEntity {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long Id;


	    public Long getId() {
	        return Id;
	    }

	    public void setId(Long id) {
	        Id = id;
	    }

	    @Override
	    public int hashCode() {
	        return 42;
	    }
}
