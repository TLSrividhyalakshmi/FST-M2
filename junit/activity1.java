package activities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;



public class activity1 {
    // Initialize a new ArrayList
    static ArrayList<String> list ;


    @BeforeEach
   public void setUp() throws Exception{
         list = new ArrayList<String>();
        // Add values to the list
        list.add(0,"alpha"); // at index 0
        list.add(1,"beta"); // at index 1
    }
    @Test
    public void insertTest() {
        //assertion
        Assertions.assertEquals(2, list.size(),"Size");
        list.add(2,"Gamma");
        Assertions.assertEquals(3, list.size(),"WS");
        Assertions.assertEquals("beta", list.get(1),"Beta");
    }
    @Test
    public void replaceTest() {
        list.set(1,"Henna");
        //assertion
       Assertions.assertEquals(2, list.size(),"Size3");
       Assertions.assertEquals("Henna",list.get(1),"Henna");
    }
}
