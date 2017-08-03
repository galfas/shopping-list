package com.mjs.shopping.service.impl;


import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.mjs.shopping.service.AbstractIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShoppingListIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void shouldCreateList() throws Exception {
    String jsonAsStr = new String(Files.readAllBytes(getFile("shoppinglist-mock.json").toPath()));

    MvcResult mvcResultCreate = mockMvc.perform(post(
      "/list")
      .content(jsonAsStr)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated()).andReturn();

    Map listCreatedAsMap = mapper.readValue(mvcResultCreate.getResponse().getContentAsByteArray(), Map.class);
    Assert.assertTrue(listCreatedAsMap.get("id").toString().length() > 0);
    Assert.assertEquals("1234", listCreatedAsMap.get("owner"));
  }


  @Test
  public void shouldRetrieveListJustCreated() throws Exception {
    String jsonAsStr = new String(Files.readAllBytes(getFile("shoppinglist-mock.json").toPath()));

    MvcResult mvcResultCreate = mockMvc.perform(post(
      "/list")
      .content(jsonAsStr)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated()).andReturn();

    Map listCreatedAsMap = mapper.readValue(mvcResultCreate.getResponse().getContentAsByteArray(), Map.class);
    String currentId = listCreatedAsMap.get("id").toString();

    MvcResult mvcResult = mockMvc.perform(get(String.format("/list/%s", currentId))
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk()).andReturn();


    Map retriedListAsMap = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(), Map.class);
    Assert.assertEquals(currentId, retriedListAsMap.get("id"));
    Assert.assertEquals("1234", retriedListAsMap.get("owner"));
  }

  private File getFile(String fileName) {
    ClassLoader classLoader = this.getClass().getClassLoader();
    return new File(classLoader.getResource(fileName).getFile());
  }
}
