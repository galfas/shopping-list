package com.mjs.shopping.service.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "shoppinglist")
public class ShoppingList {
  @Id
  @JsonProperty
  private String id;

  private Long version;
  private String owner;
  private List<String> users;
  private List<ListItem> listItemList;


  public ShoppingList() {
    this.listItemList = new ArrayList<>();
    this.users = new ArrayList<>();
  }

  public ShoppingList(List<ListItem> listItemList) {
    this.listItemList = listItemList;
    this.users = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public List<String> getUsers() {
    return users.stream().collect(Collectors.toList());
  }

  public void setUsers(List<String> users) {
    this.users = users;
  }

  public void addUser(String user) {
    this.users.add(user);
  }

  public List<ListItem> getListItemList() {
    return listItemList.stream().collect(Collectors.toList());
  }

  public void setListItemList(List<ListItem> ListItemList) {
    this.listItemList = ListItemList;
  }

  public void addListItem(ListItem listItem) {
    this.listItemList.add(listItem);
  }
}
