package com.github.perscholas.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;


public class MyModelTest {
    @Test
    public void testClassSignatureAnnotations() {
        Assert.assertTrue(MyModel.class.isAnnotationPresent(Entity.class));
    }
    @Test
    public void testCreateJson() throws JsonProcessingException {
        ObjectMapper jsonWriter = new ObjectMapper();
        MyModel myModel = new MyModel();
        String json = jsonWriter.writeValueAsString(myModel);
    }
}
