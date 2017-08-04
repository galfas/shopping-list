package com.mjs.shopping.service.server.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjs.shopping.service.server.business.ShoppingListBo;
import com.mjs.shopping.service.server.conf.ServerConfiguration;
import com.mjs.shopping.service.server.dao.ShoppingListRepository;
import com.mjs.shopping.service.server.model.ShoppingList;

@Component
public class ShoppingListBoImpl implements ShoppingListBo {

  @Autowired
  private ShoppingListRepository shoppingListRepository;

  @Autowired
  private ServerConfiguration serverConfiguration;


  @Override
  public ShoppingList fetchShoppingListBy(String listId) {

    return shoppingListRepository.fetch(listId);
  }

  @Override
  public ShoppingList create(ShoppingList shoppingList) {
    return shoppingListRepository.insert(shoppingList);
  }
}
