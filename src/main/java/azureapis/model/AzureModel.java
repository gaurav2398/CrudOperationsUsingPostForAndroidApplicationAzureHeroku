package azureapis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "AzureModel")
public class AzureModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ids;
	String name;
	String url;
//	@Transient
//	StatusMessageClass statusMessageClass;

	public AzureModel() {
	}

	public AzureModel(Long ids, String name, String url) {
		super();
		this.ids = ids;
		this.name = name;
		this.url = url;
	}

	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AzureModel [ids=" + ids + ", name=" + name + ", url=" + url +"]";
	}


}
