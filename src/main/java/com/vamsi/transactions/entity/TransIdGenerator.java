package com.vamsi.transactions.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransIdGenerator {
	
	@Id
	private String tableName;
	private long nextId;

}
