package com.tw.web.dao;

import com.tw.web.hibernate.persistent.Products;

public interface ProductsDAO extends ICRUDBaseDAO {

	void xiajia(Products product);
}
