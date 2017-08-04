package com.mjs.shopping.service.server.dao.impl;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mjs.shopping.service.server.model.ShoppingList;


public interface ShoppingListRepositoryImplMongo extends MongoRepository<ShoppingList, String> {

}
