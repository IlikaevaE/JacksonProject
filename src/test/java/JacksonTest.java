import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonTest {
    ClassLoader classLoader = JacksonTest.class.getClassLoader();

    @DisplayName("Checking Json for one entry")
    @Test
    void jsonTestFromClass() throws IOException {
        InputStream is = classLoader.getResourceAsStream("ticketProcessing.json");
        ObjectMapper objectMapper = new ObjectMapper();
        TicketProcessing jacksonObject = objectMapper.readValue(new InputStreamReader(is), TicketProcessing.class);
        assertThat(jacksonObject.getTicketNumber()).isEqualTo("T-78945");
        assertThat(jacksonObject.getSurname()).isEqualTo("Monika");
        assertThat(jacksonObject.getLastname()).isEqualTo("Krusche");
        assertThat(jacksonObject.getAddress().getStreetAddress()).isEqualTo("Bremen Street, 15");
        assertThat(jacksonObject.getAddress().getCity()).isEqualTo("Bremen");
        assertThat(jacksonObject.getAddress().getCountry()).isEqualTo("Germany");
        assertThat(jacksonObject.getAddress().getPostalCode()).isEqualTo(1245689);
        assertThat(jacksonObject.getCreated()).isEqualTo("12.06.2022");
        assertThat(jacksonObject.getPriority()).isEqualTo("low");
        assertThat(jacksonObject.getWatchers()).isEqualTo("Elena,Ilikaeva");
        assertThat(jacksonObject.getApplication()).isEqualTo("Jira");
        assertThat(jacksonObject.getSummary()).isEqualTo("No rights to start the sprint");
        assertThat(jacksonObject.getDescription()).isEqualTo("Dear Support, I need the permission to start/close " +
                "the sprint in the project ABC. Best regards, Monika");
        assertThat(jacksonObject.getSolution()).isEqualTo("Dear Monika, thank you for your request. the " +
                "needed permission has been added to your account. Please try staring the sprints. Best regards, Your Support.");
        assertThat(jacksonObject.getStatus()).isEqualTo("Done");

    }


    @Test
    void jsonArrayFromClass() throws IOException {
        InputStream is = classLoader.getResourceAsStream("ArrayTicketProcessing.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonObject = objectMapper.readTree(new InputStreamReader(is));
        assertThat(jsonObject.withArray("TicketProcessing").findValuesAsText("ticketNumber"))
                .contains("T-78945");
        assertThat(jsonObject.withArray("TicketProcessing").findValuesAsText("surname"))
                .contains("Klaus");
        assertThat(jsonObject.withArray("TicketProcessing").findValuesAsText("lastname"))
                .contains("Kaiser");
        assertThat(jsonObject.withArray("TicketProcessing").findValuesAsText("address"))
                .contains("Fox Street,89");
        assertThat(jsonObject.withArray("TicketProcessing").findValuesAsText("status"))
                .contains("In Progress");


    }

}
