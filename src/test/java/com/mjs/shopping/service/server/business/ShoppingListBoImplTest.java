package com.mjs.shopping.service.server.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mjs.shopping.service.server.business.impl.ShoppingListBoImpl;
import com.mjs.shopping.service.server.conf.ServerConfiguration;
import com.mjs.shopping.service.server.dao.ShoppingListRepository;
import com.mjs.shopping.service.server.model.Item;
import com.mjs.shopping.service.server.model.ListItem;
import com.mjs.shopping.service.server.model.ShoppingList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingListBoImplTest {

  @Mock
  private ShoppingListRepository shoppingListRepository;

  @Mock
  private ServerConfiguration serverConfiguration;

  @InjectMocks
  ShoppingListBo shoppingListBo = new ShoppingListBoImpl();

  @Test
  public void shouldInsertShoppingListIntoRepository() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    shoppingListBo.create(expectedShoppingList);
    verify(shoppingListRepository, times(1)).insert(expectedShoppingList);
  }

  @Test(expected = Exception.class)
  public void shouldPassExceptionFromRepositoryWhileCreateList() {
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    doThrow(new Exception()).when(shoppingListRepository).insert(expectedShoppingList);

    shoppingListBo.create(expectedShoppingList);
  }

  @Test
  public void shouldRetrieveShoppingListFromRepository() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    when(shoppingListRepository.fetch(listId)).thenReturn(expectedShoppingList);

    ShoppingList receivedShoppingList = shoppingListBo.fetchShoppingListBy(listId);

    assertNotNull(receivedShoppingList);
    assertEquals(expectedShoppingList, receivedShoppingList);
    verify(shoppingListRepository, times(1)).fetch(listId);
  }

  @Test(expected = Exception.class)
  public void shouldPassExceptionFromRepositoryWhileRetrievingList() {
    String listId = "1";
    ShoppingList expectedShoppingList = buildEmptyShoppingList();

    doThrow(new Exception()).when(shoppingListRepository).fetch(listId);

    shoppingListBo.fetchShoppingListBy(listId);
  }


  private static ShoppingList buildEmptyShoppingList() {
    return new ShoppingList();
  }

  private static ListItem buildEmptyItemList() {
    Item item = new Item("name", "description");
    return new ListItem(item, 1, 1, true);
  }
}
