package simulator.test;


import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import simulator.WeatherAPI;

@RunWith(MockitoJUnitRunner.class)
public class WeatherAPITest {

    @Mock
    private URL mockUrl;

    @Mock
    private HttpURLConnection mockConnection;

    @InjectMocks
    private WeatherAPI weatherAPI;

    @Test
    public void testGetWeatherDataSuccess() throws Exception {
        String mockResponse = "{\"temperature\": 25}";

        when(mockUrl.openConnection()).thenReturn(mockConnection);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        InputStream inputStream = new ByteArrayInputStream(mockResponse.getBytes());

        String userInput = "apiKey\nlocation\nmetric";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        Scanner sc = new Scanner(System.in);

        String response = weatherAPI.getWeatherData(sc);

        assert response.equals(mockResponse);
    }

}
