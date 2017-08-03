package com.mjs.shopping.service.server.dao;

import com.mjs.shopping.service.server.model.ShoppingList;


public interface ShoppingListRepository {

  /**
   * Method reposible to insert a wishlist into our repository.
   * @param wishList
   */
  void insert(ShoppingList wishList);

  /**
   * Method responsible to fetch a wishlist by its id.
   * @param wishlistId
   * @return list of Transaction that has a timestap bigger than its parameter.
   */
  ShoppingList fetch(String wishlistId);
}
