package com.edu.service.impl;

import java.util.List;

import com.edu.dao.Dao;
import com.edu.entity.StoreEntity;
import com.edu.service.StoreService;

public class StoreServiceImpl implements StoreService{

	@Override
	public List<StoreEntity> selectAll() {
		Dao dao = new Dao();
		String sql = "select * from store";
		@SuppressWarnings("unchecked")
		List<StoreEntity> list = (List<StoreEntity>) dao.query(sql, StoreEntity.class);
		return list;
	}

	@Override
	public List<StoreEntity> selectStore(String storeName,String storePrice) {
		Dao dao = new Dao();
		String sql = "select * from store where s_name = '" + storeName + "' or s_price = '"+storePrice+"'";
		@SuppressWarnings("unchecked")
		List<StoreEntity> list = (List<StoreEntity>) dao.query(sql, StoreEntity.class);
		return list;
	}
	
	@Override
	public StoreEntity selectStoreById(int storeId) {
		Dao dao = new Dao();
		String sql = "select * from store where s_id = '" + storeId + "'";
		@SuppressWarnings("unchecked")
		List<StoreEntity> list = (List<StoreEntity>) dao.query(sql, StoreEntity.class);
		StoreEntity storeEntity = list.get(0);
		return storeEntity;
	}
	@Override
	public Boolean insertStore(StoreEntity storeEntity) {
		Dao dao = new Dao();
		String sql = "insert into store (s_id,s_name,s_price,s_picture) values"
				+ " ('"+storeEntity.getS_id()+"','"+storeEntity.getS_name()+"',"
						+ "'"+storeEntity.getS_price()+"','"+storeEntity.getS_picture()+"')";
		Boolean flag = dao.addObj(sql);
		return flag;
	}

	@Override
	public Boolean updateById(StoreEntity storeEntity) {
		Dao dao = new Dao();
		String sql = "update store set s_name = '"+storeEntity.getS_name()+"',"
				+ "s_price = '"+storeEntity.getS_price()+"',"
						+ "s_picture = '"+storeEntity.getS_picture()+"'"
								+ "where s_id = '"+storeEntity.getS_id()+"'";
		Boolean flag = dao.addObj(sql);
		return flag;
	}

	@Override
	public Boolean deleteById(int storeId) {
		Dao dao = new Dao();
		String sql = "delete from store where s_id = '"+storeId+"'";
		Boolean flag = dao.addObj(sql);
		return flag;
	}
}
