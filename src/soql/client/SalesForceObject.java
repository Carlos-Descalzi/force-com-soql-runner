package soql.client;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class SalesForceObject {
	
	private ObjectAttributes attributes;
	private String id;
	private String ownerId; 
	private Boolean isDeleted;
	private String name;
	private String currencyIsoCode;
	private Date createDate;
	private String createdById;
	private Date lastModifiedDate;
	private String lastModifiedBy;
	private String systemModstamp;
	private Date lastActivityDate;
	
	public ObjectAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(ObjectAttributes attributes) {
		this.attributes = attributes;
	}
	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JsonProperty("OwnerId")
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@JsonProperty("IsDeleted")
	public Boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@JsonProperty("Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("CurrencyIsoCode")
	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}
	@JsonProperty("CreatedDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@JsonProperty("CreatedById")
	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	@JsonProperty("LastModifiedDate")
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	@JsonProperty("LastModifiedById")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	@JsonProperty("SystemModstamp")
	public String getSystemModstamp() {
		return systemModstamp;
	}

	public void setSystemModstamp(String systemModstamp) {
		this.systemModstamp = systemModstamp;
	}
	@JsonProperty("LastActivityDate")
	public Date getLastActivityDate() {
		return lastActivityDate;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		this.lastActivityDate = lastActivityDate;
	}

}
