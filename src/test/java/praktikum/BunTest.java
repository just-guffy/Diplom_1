package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    // Параметры для тестирования разных вариантов булочек
    private final String name;
    private final float price;

    // Конструктор для параметризованных тестов
    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Набор тестовых данных
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300}
        };
    }

    @Test
    public void testGetName() {
        // Создаем тестируемый объект с текущими параметрами
        Bun bun = new Bun(name, price);
        // Проверяем, что getName() возвращает правильное имя
        assertEquals("Название булочки должно совпадать", name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        // Создаем тестируемый объект с текущими параметрами
        Bun bun = new Bun(name, price);
        // Проверяем, что getPrice() возвращает правильную цену
        assertEquals("Цена булочки должна совпадать", price, bun.getPrice(), 0.001);
    }
}