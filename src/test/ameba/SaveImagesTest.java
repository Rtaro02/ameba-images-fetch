package test.ameba;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import main.ameba.SaveImages;
import main.httpclient.GetImage;
import main.httpclient.JavaNetHttpClient;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JavaNetHttpClient.class})
public class SaveImagesTest {

	@Before
	public void setUp() throws Exception {
	    //PowerMockito.spy(GetImage.class);
	}
	
	@Test
	public void fetchImagesTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// Define filed
		String url = "";
		String path = "";
		List<String> list = new ArrayList<>();
		// Add factors
		list.add("aaa.jpg");
		list.add("aaa.html");
		// Mock get request
        PowerMockito.mockStatic(JavaNetHttpClient.class);
		when(JavaNetHttpClient.executeGet(any())).thenReturn(list);
	    PowerMockito.doReturn(null).when(GetImage.class);  
	    
	    GetImage.getInstance();
		//doNothing()
		SaveImages saveImages = SaveImages.getInstance();

		// Mocked private method
		Method method = SaveImages.class.getDeclaredMethod("fetchImages", String.class, String.class);
		method.setAccessible(true);
		String actual = (String) method.invoke(saveImages, url, path);

		assertEquals(actual,"a");
	}
}
