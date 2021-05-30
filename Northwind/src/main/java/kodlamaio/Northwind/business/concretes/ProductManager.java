package kodlamaio.Northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.Northwind.business.abstracts.ProductService;
import kodlamaio.Northwind.core.utilities.result.DataResult;
import kodlamaio.Northwind.core.utilities.result.Result;
import kodlamaio.Northwind.core.utilities.result.SuccessDataResult;
import kodlamaio.Northwind.core.utilities.result.SuccessResult;
import kodlamaio.Northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.Northwind.entities.Product;



@Service
public class ProductManager implements ProductService  {
private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
	super();
	this.productDao = productDao;
}


	@Override
	public DataResult<List<Product>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Product>>
		(this.productDao.findAll(),"data listelendi");
		
				
	}


	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("ürün eklendi");
	}


	@Override
	public DataResult<Product> getByProductName(String productName) {
		return new SuccessDataResult<Product>
		(this.productDao.getByProductName(productName),"Data listelendi");	
	}

	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		//business codes
		
		return new SuccessDataResult<Product>
		(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByCategoryIn(categories),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameContains(productName),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByProductNameStartsWith(productName),"Data listelendi");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(this.productDao.getByNameAndCategory(productName,categoryId),"Data listelendi");
	}


	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		
		Pageable pageable=PageRequest.of(pageNo,pageSize);
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
	}


	@Override
	public DataResult<List<Product>> getAllSorted() {
		// TODO Auto-generated method stub
		Sort sort=Sort.by(Sort.Direction.ASC,"productName");
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"başarılı");
	}

}
