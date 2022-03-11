package azureapis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import azureapis.model.AzureModel;
import azureapis.repository.AzureRepository;

@CrossOrigin
@RestController
public class AzureController {

	@Autowired
	private AzureRepository userRoleRepository;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		return "test";
	}

	// get data
	@PostMapping("/getdata")
	public Map<String, List<AzureModel>> getAllUserRole() {
		Map<String, List<AzureModel>> response = new HashMap<String, List<AzureModel>>();
		response.put("array", (List<AzureModel>) userRoleRepository.findAll());
		return response;
	}

	// add data
	@PostMapping("/adddata")
	public Map<String, String> adddata(@RequestParam HashMap<String, Object> formData) {
		System.out.println("name:" + formData.get("name"));
		System.out.println("url:" + formData.get("url"));
		AzureModel userRoleExist = new AzureModel();

		userRoleExist.setName(formData.get("name").toString());
		userRoleExist.setUrl(formData.get("url").toString());

		HashMap<String, String> map = new HashMap<>();
		try {
			userRoleRepository.save(userRoleExist);
		} catch (Exception e) {
			map.put("status", "400");
			map.put("message", "name or id already exists");
			return map;
		}

		if (!userRoleExist.getIds().equals(null)) {
			map.put("status", "200");
			map.put("message", "success");
			map.put("ids", userRoleExist.getIds().toString());
			map.put("name", userRoleExist.getName().toString());
			map.put("url", userRoleExist.getUrl().toString());
		}

		else {
			map.put("status", "400");
			map.put("message", "fail");
		}
		return map;
	}

	// update data
	@PostMapping("/updatedata")
	public Map<String, String> updateData(@RequestParam HashMap<String, Object> formData) {
		System.out.println("name:" + formData.get("name").toString());
		System.out.println("url:" + formData.get("url").toString());
		AzureModel userRoleExist = new AzureModel();

		System.out.println("userRoleExist:" + userRoleExist.toString());

		HashMap<String, String> map = new HashMap<>();
		if (null != userRoleRepository.findByName(formData.get("name").toString())) {
			map.put("status", "200");
			map.put("message", "success");
		} else {
			map.put("status", "400");
			map.put("message", "could not found data with specified name");
			return map;
		}
		AzureModel findByName = userRoleRepository.findByName(formData.get("name").toString());
		userRoleExist.setIds(findByName.getIds());
		userRoleExist.setName(formData.get("name").toString());
		userRoleExist.setUrl(formData.get("url").toString());

		userRoleRepository.save(userRoleExist);

		return map;
	}

	// delete data
	@PostMapping("/deletedata")
	public Map<String, String> deleteData(@RequestParam HashMap<String, Object> formData) {
		
		HashMap<String, String> map = new HashMap<>();
		if (null != userRoleRepository.findByName(formData.get("name").toString())) {
			map.put("status", "200");
			map.put("message", "success");
		} else {
			map.put("status", "400");
			map.put("message", "could not found data with specified name");
			return map;
		}
		AzureModel findByName = userRoleRepository.findByName(formData.get("name").toString());
		userRoleRepository.removeByName(findByName.getName().toString());

		return map;
	}

}
