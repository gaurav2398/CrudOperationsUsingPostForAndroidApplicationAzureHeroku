package azureapis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import azureapis.model.AzureModel;

public interface AzureRepository extends CrudRepository<AzureModel, String>{

	@Transactional
	void removeByName(String name);

	public AzureModel findByName(String name);
}
