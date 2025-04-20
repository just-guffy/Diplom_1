package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Указываем, что тест будет использовать Mockito для создания mock-объектов
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    // Создаем mock-объекты с помощью аннотации @Mock
    @Mock
    private Bun bun;                // Mock-объект булочки

    @Mock
    private Ingredient ingredient1; // Mock-объект первого ингредиента

    @Mock
    private Ingredient ingredient2; // Mock-объект второго ингредиента

    private Burger burger;          // Тестируемый объект

    @Before
    public void setUp() {
        burger = new Burger();       // Создаем новый бургер перед каждым тестом
        when(bun.getPrice()).thenReturn(100f); // Настраиваем mock: при вызове getPrice() возвращаем 100
    }

    // Тест проверяет установку булочки для бургера
    @Test
    public void testSetBuns() {
        burger.setBuns(bun);        // Устанавливаем mock-булочку
        assertSame(bun, burger.bun); // Проверяем, что булочка установилась корректно
    }

    // Тест проверяет добавление ингредиента в бургер
    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1); // Добавляем ингредиент
        assertEquals(1, burger.ingredients.size()); // Проверяем, что размер списка = 1
        assertSame(ingredient1, burger.ingredients.get(0)); // Проверяем, что добавился нужный ингредиент
    }

    // Тест проверяет удаление ингредиента из бургера
    @Test
    public void testRemoveIngredient() {
        // Добавляем два ингредиента
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0); // Удаляем первый ингредиент (с индексом 0)

        assertEquals(1, burger.ingredients.size()); // Проверяем, что остался один ингредиент
        assertSame(ingredient2, burger.ingredients.get(0)); // Проверяем, что остался второй ингредиент
    }

    // Тест проверяет перемещение ингредиента в списке
    @Test
    public void testMoveIngredient() {
        // Добавляем два ингредиента
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1); // Меняем местами ингредиенты (0 и 1 индексы)

        // Проверяем, что ингредиенты поменялись местами
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient1, burger.ingredients.get(1));
    }

    // Тест проверяет расчет общей стоимости бургера
    @Test
    public void testGetPrice() {
        // Настраиваем mock-объекты:
        when(ingredient1.getPrice()).thenReturn(50f);  // Первый ингредиент стоит 50
        when(ingredient2.getPrice()).thenReturn(80f);  // Второй ингредиент стоит 80

        // Формируем бургер:
        burger.setBuns(bun);            // Устанавливаем булочку (стоимость 100, как настроено в setUp)
        burger.addIngredient(ingredient1); // Добавляем первый ингредиент
        burger.addIngredient(ingredient2); // Добавляем второй ингредиент

        // Проверяем общую стоимость:
        // Булочка (100) * 2 + ингредиент1 (50) + ингредиент2 (80) = 330
        assertEquals(330, burger.getPrice(), 0.001);
    }

    // Тест проверяет формирование чека
    @Test
    public void testGetReceipt() {
        // Настраиваем mock-объекты для чека:
        when(bun.getName()).thenReturn("black bun"); // Название булочки
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE); // Тип первого ингредиента
        when(ingredient1.getName()).thenReturn("hot sauce"); // Название первого ингредиента
        when(ingredient1.getPrice()).thenReturn(100f); // Цена первого ингредиента
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING); // Тип второго ингредиента
        when(ingredient2.getName()).thenReturn("cutlet"); // Название второго ингредиента
        when(ingredient2.getPrice()).thenReturn(200f); // Цена второго ингредиента

        // Формируем бургер:
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Получаем текст чека
        String actual = burger.getReceipt();

        // Проверяем, что чек содержит ожидаемые элементы:
        assertTrue(actual.contains("(==== black bun ====)")); // Название булочки
        assertTrue(actual.contains("= sauce hot sauce ="));   // Первый ингредиент
        assertTrue(actual.contains("= filling cutlet ="));    // Второй ингредиент
        assertTrue(actual.contains("Price:"));               // Строка с ценой
        assertTrue(actual.contains("500"));                  // Общая цена (100*2 + 100 + 200 = 500)
    }
}