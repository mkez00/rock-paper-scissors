package com.mkez00.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Action
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-25T22:11:07.354-04:00")

public class Action   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("opponentId")
  private String opponentId = null;

  @JsonProperty("serverId")
  private String serverId = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("actionDate")
  private Long actionDate = null;

  /**
   * Action made by player
   */
  public enum ActionEnum {
    ROCK("rock"),
    
    PAPER("paper"),
    
    SCISSORS("scissors");

    private String value;

    ActionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ActionEnum fromValue(String text) {
      for (ActionEnum b : ActionEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("action")
  private ActionEnum action = null;

  /**
   * Gets or Sets response
   */
  public enum ResponseEnum {
    NIL("nil"),

    WINNER("winner"),
    
    LOSER("loser"),
    
    TIE("tie"),
    
    NOOPPONENT("noopponent");

    private String value;

    ResponseEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResponseEnum fromValue(String text) {
      for (ResponseEnum b : ResponseEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("response")
  private ResponseEnum response = null;

  public Action id(String id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Action opponentId(String opponentId) {
    this.opponentId = opponentId;
    return this;
  }

  @ApiModelProperty(value = "")
  public String getServerId() {
    return serverId;
  }

  public void setServerId(String serverId) {
    this.serverId = serverId;
  }

  /**
   * Get opponentId
   * @return opponentId
  **/
  @ApiModelProperty(value = "")


  public String getOpponentId() {
    return opponentId;
  }

  public void setOpponentId(String opponentId) {
    this.opponentId = opponentId;
  }

  public Action latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Get latitude
   * @return latitude
  **/
  @ApiModelProperty(value = "")


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public Action longitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Get longitude
   * @return longitude
  **/
  @ApiModelProperty(value = "")


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public Action actionDate(Long actionDate) {
    this.actionDate = actionDate;
    return this;
  }

   /**
   * Get actionDate
   * @return actionDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Long getActionDate() {
    return actionDate;
  }

  public void setActionDate(Long actionDate) {
    this.actionDate = actionDate;
  }

  public Action action(ActionEnum action) {
    this.action = action;
    return this;
  }

   /**
   * Action made by player
   * @return action
  **/
  @ApiModelProperty(value = "Action made by player")


  public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }

  public Action response(ResponseEnum response) {
    this.response = response;
    return this;
  }

   /**
   * Get response
   * @return response
  **/
  @ApiModelProperty(value = "")


  public ResponseEnum getResponse() {
    return response;
  }

  public void setResponse(ResponseEnum response) {
    this.response = response;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Action action = (Action) o;
    return Objects.equals(this.id, action.id) &&
        Objects.equals(this.opponentId, action.opponentId) &&
        Objects.equals(this.latitude, action.latitude) &&
        Objects.equals(this.longitude, action.longitude) &&
        Objects.equals(this.actionDate, action.actionDate) &&
        Objects.equals(this.action, action.action) &&
        Objects.equals(this.response, action.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, opponentId, latitude, longitude, actionDate, action, response);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Action {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    opponentId: ").append(toIndentedString(opponentId)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    actionDate: ").append(toIndentedString(actionDate)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

