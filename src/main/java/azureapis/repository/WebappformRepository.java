package azureapis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import azureapis.model.AzureModel;
import azureapis.model.WebAppForm;

public interface WebappformRepository extends CrudRepository<WebAppForm, String>{

}
