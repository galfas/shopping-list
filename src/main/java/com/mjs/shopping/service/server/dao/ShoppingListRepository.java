package com.mjs.shopping.service.server.dao;

import com.mjs.shopping.service.server.model.ShoppingList;

public interface ShoppingListRepository {

  /**
   * Method responsible to insert a shoppingList into our repository.
   * @param shoppingList
   */
  ShoppingList insert(ShoppingList shoppingList);

  /**
   * Method responsible to fetch a shoppingList by its id.
   * @param shoppingListId
   * @return list of Transaction that has a timestap bigger than its parameter.
   */
  ShoppingList fetch(String shoppingListId);
}
