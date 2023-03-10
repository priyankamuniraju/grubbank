package com.grubbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.grubbank.apimodel.Streamable;
import java.time.Duration;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "recipe")
public class Recipe implements Streamable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int numberOfServings;

  private String name;

  private String instructions;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "ingredient_contain",
      joinColumns = @JoinColumn(name = "recipe_id"),
      inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> ingredientList;

  private Duration preparationTime;

  private Duration totalTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "nutritionalvalue_id")
  private NutritionalValue nutritionalValue;

  private RecipeType recipeType;

  public enum RecipeType {
    VEGAN,
    NON_VEGETARIAN,
    VEGETARIAN,
    SEA_FOOD
  }
}
