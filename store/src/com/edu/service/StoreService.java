package com.edu.service;

import java.util.List;

import com.edu.entity.StoreEntity;

public interface StoreService {
	public List<StoreEntity> selectAll();
	public List<StoreEntity> selectStore(String storeName,String storePrice);
	public StoreEntity selectStoreById(int storeId);
	public Boolean insertStore(StoreEntity storeEntity);
	public Boolean updateById(StoreEntity storeEntity);
	public Boolean deleteById(int storeId);
}
