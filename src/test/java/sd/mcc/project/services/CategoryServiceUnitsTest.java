/**
 * 
 */
package sd.mcc.project.services;

import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author MaJiD
 *
 */
@SpringBootTest
public class CategoryServiceUnitsTest {

	/*
	 * @Autowired private ICategoriesService categoryService;
	 * 
	 * @Test public void addCategoryUnitTest() { Category category = new Category();
	 * Category savedCategory = null; category.setName_ar("تبرع مسجد");
	 * category.setName("building"); category.setType("Test building");
	 * category.setType_ar("اختبار اخر"); category.setMax_budget(200000d);
	 * category.setDescription("This is just to describe the application.");
	 * category.setStatus(CategoryStatus.ACTIVE); try {
	 * 
	 * savedCategory = categoryService.addCategory(category); } catch (Exception e)
	 * { System.out.println("addCategoryUnitTest ---------> " +
	 * e.getLocalizedMessage()); } if(savedCategory != null)
	 * System.out.println(savedCategory); }
	 */
	
	/*
	 * @Test public void activateCategoryUnitTest() { Object activateCategory =
	 * null; try { Category category =
	 * categoryService.getAllActiveCategories().get(0); activateCategory =
	 * categoryService.activateCategory(category);
	 * 
	 * } catch (Exception e) {
	 * System.out.println("activateCategoryUnitTest ---------> " +
	 * e.getLocalizedMessage()); } if(activateCategory != null)
	 * System.out.println(activateCategory); }
	 * 
	 * @Test public void deactivateCategoryUnitTest() { Object unActivateCategory =
	 * null; try { Category category =
	 * categoryService.getAllActiveCategories().get(0); unActivateCategory =
	 * categoryService.deactivateCategory(category); } catch (Exception e) {
	 * System.out.println("deactivateCategoryUnitTest ---------> " +
	 * e.getLocalizedMessage()); } if(unActivateCategory != null)
	 * System.out.println(unActivateCategory); }
	 * 
	 * @Test public void getCategoryByIdUnitTest() { Category category = null; try {
	 * category = categoryService.getCategoryById(1); } catch (Exception e) {
	 * System.out.println("getCategoryByIdUnitTest ---------> " +
	 * e.getLocalizedMessage()); } if(category != null)
	 * System.out.println(category); }
	 * 
	 * @Test public void getAllCategoriesUnitTest() { List<Category> categories =
	 * null; try { categories = categoryService.getAllCategories();
	 * if(categories.isEmpty()) return; } catch (Exception e) {
	 * System.out.println("getAllCategoriesUnitTest ---------> " +
	 * e.getLocalizedMessage()); } for(Category category : categories) { if(category
	 * != null) System.out.println(category); } }
	 * 
	 * @Test public void getAllActiveCategoriesUnitTest() { List<Category>
	 * categories = null; try { categories =
	 * categoryService.getAllActiveCategories(); if(categories.isEmpty()) return; }
	 * catch (Exception e) { // TODO: handle exception
	 * System.out.println("getAllActiveCategoriesUnitTest ---------> " +
	 * e.getLocalizedMessage()); } for(Category category : categories) { if(category
	 * != null) System.out.println(category); } }
	 * 
	 * @Test public void getAllNotActiveCategoriesUnitTest() { List<Category>
	 * categories = null; try { categories =
	 * categoryService.getAllNotActiveCategories(); if(categories.isEmpty()) return;
	 * } catch (Exception e) {
	 * System.out.println("getAllNotActiveCategoriesUnitTest ---------> " +
	 * e.getLocalizedMessage()); }
	 * 
	 * for(Category category : categories) { if(category != null)
	 * System.out.println(category); } }
	 * 
	 * @Test public void updateCategoryUnitTest() { Category updatedCategory = null;
	 * try { Category category = categoryService.getCategoryById(1);
	 * category.setMax_budget(150000d); updatedCategory =
	 * categoryService.updateCategory(category); } catch (Exception e) {
	 * System.out.println("updateCategoryUnitTest ---------> " +
	 * e.getLocalizedMessage()); } if(updatedCategory != null)
	 * System.out.println(updatedCategory); }
	 * 
	 * @Test public void updateCategoriesUnitTest() { List<Category> categoriesList
	 * = new ArrayList<Category>();
	 * 
	 * Category category1 = new Category(); category1.setName_ar("تبرع كفالة");
	 * category1.setName("help"); category1.setType("Test helping");
	 * category1.setType_ar("اختبار جديد"); category1.setMax_budget(400000d);
	 * category1.setDescription("This is just to describe the application.");
	 * category1.setStatus(CategoryStatus.ACTIVE); categoriesList.add(category1);
	 * 
	 * Category category2 = new Category(); category2.setName_ar("تبرع عادي");
	 * category2.setName("Normal"); category2.setType("Test Normal");
	 * category2.setType_ar("اختبار اجد"); category2.setMax_budget(300000d);
	 * category2.setDescription("This is just to describe the application.");
	 * category2.setStatus(CategoryStatus.ACTIVE); categoriesList.add(category2);
	 * 
	 * List<Category> savedList = categoryService.updateCategories(categoriesList);
	 * 
	 * for(Category cate : savedList) { if(cate != null) System.out.println(cate); }
	 * }
	 */
}
