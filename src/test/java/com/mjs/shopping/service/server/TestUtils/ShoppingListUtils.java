package com.mjs.shopping.service.server.TestUtils;

import com.mjs.shopping.service.server.model.Item;
import com.mjs.shopping.service.server.model.ListItem;
import com.mjs.shopping.service.server.model.ShoppingList;

public class ShoppingListUtils {

  public static ShoppingList buildEmptyShoppingList() {
    return new ShoppingList();
  }

  public static ListItem buildEmptyItemList() {
    Item item = new Item("name", "description");
    return new ListItem(item, 1, 1, true);
  }
}
