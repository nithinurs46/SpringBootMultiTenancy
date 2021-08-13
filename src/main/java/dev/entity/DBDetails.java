package dev.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TENANT_DB_LIST")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class DBDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6364902041896237890L;
	@Id
	@Column(name = "DATABASE_ID")
	private String dbId;
	@Column(name = "DB_NAME")
	private String dbName;
	@Column(name = "DB_URL")
	private String url;
	@Column(name = "DB_PASSWORD")
	private String dbPwd;
}
