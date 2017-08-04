package com.mjs.shopping.service.server.dao;

import com.mjs.shopping.service.server.model.ShoppingList;


public interface ShoppingListRepository {

  /**
   * Method reposible to insert a shoppingList into our repository.
   * @param shoppingList
   */
  void insert(ShoppingList shoppingList);

  /**
   * Method responsible to fetch a shoppingLis by its id.
   * @param shoppingListId
   * @return list of Transaction that has a timestap bigger than its parameter.
   */
  ShoppingList fetch(String shoppingListId);
}
