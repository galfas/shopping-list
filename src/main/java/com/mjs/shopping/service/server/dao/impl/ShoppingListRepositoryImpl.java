package com.mjs.shopping.service.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjs.shopping.service.server.dao.ShoppingListRepository;
import com.mjs.shopping.service.server.model.ShoppingList;

@Component
public class ShoppingListRepositoryImpl implements ShoppingListRepository {

  public static final long START_VERSION = 1;


  @Autowired
  private ShoppingListRepositoryImplMongo shoppingListRepositoryImplMongo;

  @Override
  public ShoppingList insert(ShoppingList shoppingList) {

    //TODO implement version control direct on Mongo
    shoppingList.setVersion(START_VERSION);

    return shoppingListRepositoryImplMongo.insert(shoppingList);
  }

  @Override
  public ShoppingList fetch(String shoppingListId) {
    return shoppingListRepositoryImplMongo.findOne(shoppingListId);
  }
}
