package com.mjs.shopping.service.server.model;


public class ListItem {
  private Item item;
  private int quantity;
  private int version;
  private boolean isChecked;


  public ListItem() {
  }

  public ListItem(Item item, int quantity, int version, boolean isChecked) {
    this.item = item;
    this.quantity = quantity;
    this.version = version;
    this.isChecked = isChecked;
  }


  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public boolean getIsChecked() {
    return isChecked;
  }

  public void setIsChecked(boolean checked) {
    isChecked = checked;
  }

}
