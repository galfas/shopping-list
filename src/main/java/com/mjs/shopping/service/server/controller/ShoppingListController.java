package com.mjs.shopping.service.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mjs.shopping.service.server.business.ShoppingListBo;
import com.mjs.shopping.service.server.model.ListItem;
import com.mjs.shopping.service.server.model.ShoppingList;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RestController
public class ShoppingListController extends BaseController {

  @Autowired
  ShoppingListBo shoppingListBo;


  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = "/list/{listId}")
  public ShoppingList fetchList(@PathVariable String listId) {

    return shoppingListBo.fetchShoppingListBy(listId);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(path = "/list", method = RequestMethod.POST)
  public ShoppingList create(@RequestBody ShoppingList shoppingList) {

    return shoppingListBo.create(shoppingList);
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = "/list/{listId}/product/version/{version}", method = RequestMethod.PATCH)
  public ShoppingList updateProduct(@PathVariable String listId,
                                    @PathVariable Integer version,
                                    @RequestBody ListItem listItem) {

    throw new NotImplementedException();
  }

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = "/list/{listId}", method = RequestMethod.PUT)
  public ShoppingList update(@PathVariable String listId,
                             @RequestBody ShoppingList wishList) {

    throw new NotImplementedException();
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(path = "/list/{id}", method = RequestMethod.DELETE)
  public void deleteList(@PathVariable String id) {

    throw new NotImplementedException();
  }
}
