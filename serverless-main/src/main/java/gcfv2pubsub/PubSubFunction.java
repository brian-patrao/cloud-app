package gcfv2pubsub;

import com.google.cloud.functions.CloudEventsFunction;
import com.google.events.cloud.pubsub.v1.MessagePublishedData;
import com.google.events.cloud.pubsub.v1.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.cloudevents.CloudEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Logger;

public class PubSubFunction implements CloudEventsFunction {
  private static final Logger logger = Logger.getLogger(PubSubFunction.class.getName());
  private static final String YOUR_DOMAIN_NAME = "brianmarcelpatrao.me";
  private static final String API_KEY = "7a9ea1060c1751a597b29c86079df43e-f68a26c9-5820859a";
  private static final String DB_USER = System.getenv("DB_USER");
  private static final String DB_PASS = System.getenv("DB_PASS");
  private static final String DB_NAME = System.getenv("DB_NAME");
  private static final String DB_PORT = System.getenv("DB_PORT");
  private static final String DB_HOST = System.getenv("DB_HOST");

  @Override
  public void accept(CloudEvent event) throws SQLException {
    // Get cloud event data as JSON string
    String cloudEventData = new String(event.getData().toBytes());
    // Decode JSON event data to the Pub/Sub MessagePublishedData type
    Gson gson = new Gson();
    MessagePublishedData data = gson.fromJson(cloudEventData, MessagePublishedData.class);
    // Get the message from the data
    Message message = data.getMessage();
    // Get the base64-encoded data from the message & decode it
    String encodedData = message.getData();
    String email = new String(Base64.getDecoder().decode(encodedData));
    // Log the message
    logger.info("Pub/Sub message: " + email);

    String token = UUID.randomUUID().toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    String createdTime = ZonedDateTime.now(ZoneId.of("UTC")).format(formatter);

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("token", token);
    jsonObject.addProperty("createdTime", createdTime);
    jsonObject.addProperty("email", email);
    String jsonString = jsonObject.toString();
    String finalToken = Base64.getEncoder().encodeToString(jsonString.getBytes());

    String verificationLink = "https://brianmarcelpatrao.me/verify?token=" + finalToken;
    logger.info("Verification link: " + verificationLink);

    try {
      Connection connection = getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(
              "UPDATE user SET email_verification_token = ?, verification_token_generated_at = ? WHERE username = ?");
      preparedStatement.setString(1, token);
      preparedStatement.setString(2, createdTime);
      preparedStatement.setString(3, email);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      logger.severe("Error updating database: " + e.getMessage());
      throw new SQLException(e);
    }

    String responseMessage = null;
    try {
      JsonNode response = sendSimpleMessage(email, verificationLink);
      responseMessage = response.getObject().toString();
      logger.info("Email sent: " + responseMessage);
    } catch (UnirestException e) {
      throw new RuntimeException(e);
    }

  }

  public static JsonNode sendSimpleMessage(String email, String verificationLink) throws UnirestException {
    HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
        .basicAuth("api", API_KEY)
        .queryString("from", "Brian Patrao <brian@brianmarcelpatrao.me>")
        .queryString("to", email)
        .queryString("subject", "Verify your account")
        .queryString("text", "Verify your account by clicking on this link " + verificationLink)
        .asJson();
    return request.getBody();
  }

  public Connection getConnection() {
    try {
      logger.info("Connecting to database...");
      Class.forName("com.mysql.cj.jdbc.Driver");
      String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
      return DriverManager.getConnection(url, DB_USER, DB_PASS);
    } catch (Exception e) {
      logger.severe("Error connecting to database: " + e.getMessage());
      throw new RuntimeException(e);
    }
  }

}
