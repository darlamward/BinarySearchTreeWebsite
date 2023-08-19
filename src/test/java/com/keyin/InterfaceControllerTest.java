package com.keyin;

import com.keyin.Entity.Interface;
import com.keyin.Interface.InterfaceController;
import com.keyin.Interface.InterfaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InterfaceControllerTest {

    @InjectMocks
    private InterfaceController interfaceController;

    @Mock
    private InterfaceRepository interfaceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowInterfaceInputPage() {
        String viewName = interfaceController.showInterfaceInputPage();
        assertEquals("enter-numbers", viewName);
    }

    @Test
    public void testShowHomePage() {
        ModelAndView modelAndView = interfaceController.showHomePage();
        assertEquals("index", modelAndView.getViewName());
    }

    @Test
    public void testProcessInterfaceInput() {
        String numbers = "5 3 7";
        Model model = mock(Model.class);

        String viewName = interfaceController.processInterfaceInput(numbers, model);

        // Add your assertions here
        // For example, you can check if the model contains the expected attributes
        verify(model).addAttribute(eq("jsonData"), anyString());
        assertEquals("process-numbers", viewName);
    }

    @Test
    public void testShowPreviousTrees() {
        List<Interface> interfaceInputList = new ArrayList<>();
        when(interfaceRepository.findAll()).thenReturn(interfaceInputList);
        Model model = mock(Model.class);

        String viewName = interfaceController.showPreviousTrees(model);

        // Add your assertions here
        // For example, you can check if the model contains the expected attributes
        verify(model).addAttribute(eq("treeJsonList"), anyList());
        assertEquals("previous-trees", viewName);
    }

}