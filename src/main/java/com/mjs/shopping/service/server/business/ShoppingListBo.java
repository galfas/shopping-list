package com.mjs.shopping.service.server.business;

import com.mjs.shopping.service.server.model.ShoppingList;

/**
 * Business object that contains the rules to handle shoppingList.
 *
 * @author msaciloto
 */
public interface ShoppingListBo {

  /**
   * This method is responsible for receiving a shopping list object
   * validate and insert it into repository.
   *
   * @param shoppingList
   */
  ShoppingList create(ShoppingList shoppingList);

  /**
   * This method will retrieve a shopping list by list id.
   *
   * @return shoppingList
   */
  ShoppingList fetchShoppingListBy(String listId);
}
