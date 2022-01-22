/**
 * 
 */
package sd.mcc.project.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sd.mcc.project.model.Category;
import sd.mcc.project.repo.CategoryRepository;
import sd.mcc.project.service.ICategoriesService;
import sd.mcc.project.util.CategoryStatus;

/**
 * @author MaJiD
 *
 */
@Service
public class CategoriesServiceImp implements ICategoriesService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		try {
			category.setCreated_at(new Date());
			category.setLast_update(new Date());
			Category newCategory = categoryRepository.save(category);
			return newCategory;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category activateCategory(Category category) {
		try {
			category.setStatus(CategoryStatus.ACTIVE);
			category.setLast_update(new Date());
			Category activeCategory = categoryRepository.save(category);
			return activeCategory;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category deactivateCategory(Category category) {
		try {
			category.setStatus(CategoryStatus.NOT_ACTIVE);
			category.setLast_update(new Date());
			Category notActiveCategory = categoryRepository.save(category);
			return notActiveCategory;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> getAllActiveCategories() {
		return categoryRepository.getMyAllCategoriesByStatus(CategoryStatus.ACTIVE);
	}

	@Override
	public List<Category> getAllNotActiveCategories() {
		return categoryRepository.getMyAllCategoriesByStatus(CategoryStatus.NOT_ACTIVE);
	}
	
	@Override
	public Category updateCategory(Category category) {
		try {
			category.setLast_update(new Date());
			Category updatedCategory = categoryRepository.save(category);
			return updatedCategory;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Category> updateCategories(List<Category> categories) {
		List<Category> updatedList = new ArrayList<Category>();
		try {
			for(Category category : categories) {
				category.setCreated_at(new Date());
				category.setLast_update(new Date());
				Category updatedCategory = categoryRepository.save(category);
				updatedList.add(updatedCategory);
			}
			return updatedList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
