package com.rabi.swagger.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rabi.swagger.dao.CategoryRepoByJDBC;
import com.rabi.swagger.dao.CategoryRepository;
import com.rabi.swagger.dao.UserRepository;
import com.rabi.swagger.model.CATEGORY;
import com.rabi.swagger.model.USER;

@Service
public class CategoryService {

	@Autowired
	CategoryRepoByJDBC catRepoJdbc;

	@Autowired
	CategoryRepository catRepo;

	@Autowired
	UserRepository userRepo;

	public List<CATEGORY> updateCategoryByCatId(CATEGORY category, int catId) {
		return catRepoJdbc.updateCategoryByCatId(category, catId);

	}

	public List<CATEGORY> deleteCategoryByCatId(int catId) {
		return catRepoJdbc.deleteCategoryByCatId(catId);
	}

	public List<CATEGORY> deleteCategoryByCatIdUserId(int catId, String userId) {
		return catRepoJdbc.deleteCategoryByCatIdUserId(catId, userId);

	}

	public List<CATEGORY> getCategoryByCatId(int catId) {
		return catRepoJdbc.getCategoryByCatId(catId);
	}

	public List<CATEGORY> getCategoryByUserId(String userId) {
		return catRepoJdbc.getCategoryByUserId(userId);
	}

	public List<CATEGORY> getCategoryByCatIdUserId(int catId, String userId) {
		return catRepoJdbc.getCategoryByCatIdUserId(catId, userId);
	}

	public CATEGORY saveCategoryByUserId(CATEGORY cat, String userName) {

		USER user = new USER(userName);
		user.setId(userName); // Manually Assign!!!!!!!!!!!!!!! as No @GeneratedValue at @ID

		Set<CATEGORY> catFinalSet = new HashSet<>();
		cat.setUserCatNew(user);
		catFinalSet.add(cat);

		user.setCatSet(catFinalSet);

		user = userRepo.save(user);
		// Save Reminder into Database
		return catRepo.save(cat);
	}

	public CATEGORY saveCategory(CATEGORY cat) {
		// Save CATEGORY into Database
		return catRepo.save(cat);
	}

	@Deprecated
	public USER saveUserCategory( Set<CATEGORY> catSet, String userName) {
		USER user = new USER(userName);
		Set<CATEGORY> catFinalSet = new HashSet<>();
		user.setCatSet(catSet);
		user = userRepo.save(user);
//Update Note with Info of User (ID..i.e Object Save)
		for (CATEGORY catNew : catSet) {
			catNew.setUserCatNew(user);
			catFinalSet.add(catNew);
		}
//Save Reminder into Database
		catRepo.saveAll(catFinalSet);
		return user;
	}
	
	 public List<CATEGORY> allCATEGORY() {
			return catRepo.findAll();
		}

}
