package cplayer.recommended.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cplayer.recommended.app.model.Recommended;
import cplayer.recommended.app.repository.RecommendedRepository;

@Service // These class files are used to write business logic in a different layer,
			// separated from @RestController class file
public class RecommendedService {

	@Autowired // The @Autowired annotation can be used to autowire bean on the setter method
	private RecommendedRepository recommendedRepository;

	public List<Recommended> getAllData() {
		List<Recommended> list = recommendedRepository.findAll();
		list.removeIf(e -> (e.getCount() < 5));
		return list;
	}

	public boolean addData(Recommended recommended) {
		try {
//			int id = recommended.getId();
			String name = recommended.getName();
			List<Recommended> list1 = recommendedRepository.findAll();
			for (Recommended obj : list1) {
				if (obj.getName().equalsIgnoreCase(name)) {
					int count = recommendedRepository.findById(name).get().getCount();
					recommendedRepository.deleteById(name);
					recommended.setCount(count + 1);
					recommendedRepository.save(recommended);
					return true;
				}
			}
			recommended.setCount(1);
			recommendedRepository.save(recommended);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean removeData(String name) {
		try {
			List<Recommended> list2 = recommendedRepository.findAll();
			for (Recommended obj1 : list2) {
				if (obj1.getName().equalsIgnoreCase(name)) {
					Recommended recom = recommendedRepository.findById(name).get();
					recommendedRepository.deleteById(name);
					recom.setCount(recom.getCount() - 1);
					recommendedRepository.save(recom);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
