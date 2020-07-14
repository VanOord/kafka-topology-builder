package com.purbon.kafka.topology.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.purbon.kafka.topology.TopicManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic {

  public static final String DEFAULT_TOPIC_NAME = "default";

  @JsonInclude(Include.NON_EMPTY)
  private Optional<String> dataType;

  private String name;
  private HashMap<String, String> config;

  private String projectPrefix;

  public Topic(String name) {
    this(name, Optional.empty(), new HashMap<>());
  }

  public Topic(String name, String dataType) {
    this(name, Optional.of(dataType), new HashMap<>());
  }

  public Topic(String name, Optional<String> dataType, HashMap<String, String> config) {
    this.name = name;
    this.dataType = dataType;
    this.config = config;
  }

  public Topic() {
    this(DEFAULT_TOPIC_NAME, Optional.empty(), new HashMap<>());
  }

  public String getName() {
    return name;
  }

  private String toString(String projectPrefix) {
    StringBuilder sb = new StringBuilder();
    sb.append(projectPrefix).append(".").append(getName());

    if (getDataType().isPresent()) {
      sb.append(".").append(getDataType().get());
    }

    return sb.toString();
  }

  @Override
  public String toString() {
    return toString(projectPrefix);
  }

  public void setName(String name) {
    this.name = name;
  }

  public HashMap<String, String> getConfig() {
    return config;
  }

  public void setConfig(HashMap<String, String> config) {
    this.config = config;
  }

  public Map<String, String> rawConfig() {
    getConfig().remove(TopicManager.NUM_PARTITIONS);
    getConfig().remove(TopicManager.REPLICATION_FACTOR);
    return getConfig();
  }

  public Optional<String> getDataType() {
    return dataType;
  }

  public void setProjectPrefix(String projectPrefix) {
    this.projectPrefix = projectPrefix;
  }

  public String getProjectPrefix() {
    return projectPrefix;
  }
}
