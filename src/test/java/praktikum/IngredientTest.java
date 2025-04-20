package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type; // тип ингредиента (соус или начинка)
    private final String name;        // название ингредиента
    private final float price;        // цена ингредиента

    // Конструктор для параметризованных тестов
    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    // Метод, предоставляющий тестовые данные
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                // Соусы:
                {IngredientType.SAUCE, "hot sauce", 100},  // острый соус, 100 рублей
                {IngredientType.SAUCE, "sour cream", 200}, // сметана, 200 рублей
                // Начинки:
                {IngredientType.FILLING, "cutlet", 100},   // котлета, 100 рублей
                {IngredientType.FILLING, "dinosaur", 200}  // "динозавр", 200 рублей
        };
    }

    // Тест проверяет корректность получения типа ингредиента
    @Test
    public void testGetType() {
        // Создаем объект Ingredient с тестовыми параметрами
        Ingredient ingredient = new Ingredient(type, name, price);

        // Проверяем, что тип ингредиента совпадает с ожидаемым
        assertEquals("Тип ингредиента должен совпадать", type, ingredient.getType());
    }

    // Тест проверяет корректность получения названия ингредиента
    @Test
    public void testGetName() {
        // Создаем объект Ingredient
        Ingredient ingredient = new Ingredient(type, name, price);

        // Проверяем, что название ингредиента совпадает с ожидаемым
        assertEquals("Название ингредиента должно совпадать", name, ingredient.getName());
    }

    // Тест проверяет корректность получения цены ингредиента
    @Test
    public void testGetPrice() {
        // Создаем объект Ingredient
        Ingredient ingredient = new Ingredient(type, name, price);

        // Проверяем, что цена ингредиента совпадает с ожидаемой
        assertEquals("Цена ингредиента должна совпадать", price, ingredient.getPrice(), 0.001);
    }
}