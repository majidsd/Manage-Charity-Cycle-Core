/**
 * 
 */
package sd.mcc.project.service;

import java.util.List;

import sd.mcc.project.model.Category;

/**
 * @author MaJiD
 *
 */
public interface ICategoriesService {
	
	public Category addCategory(Category category);
	
	public Category activateCategory(Category category);
	
	public Category deactivateCategory(Category categories);
	
	public Category getCategoryById(Integer id);
	
	public List<Category> getAllCategories();
	
	public List<Category> getAllActiveCategories();
	
	public List<Category> getAllNotActiveCategories();
	
	public List<Category> updateCategories(List<Category> categories);
	
	public Category updateCategory(Category category);
}
