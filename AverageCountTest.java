package seminar6;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AverageCountTest {

    /**
     * Проверка рассчета среднего числа переданного списка типов Double, Integer
     * Поскольку при расчетах Double существует погрешность, используем дельту
     * в одну десятитысячную для сравнения результата
     * (среднюю для предложенного списка метод рассчитывает равной 2.2222000000000004)
     */
    @Test
    void checkAverageCount(){
        ArrayList<Double> listDouble = new ArrayList<>(List.of(3.3333, 2.2222, 1.1111));
        AverageCount ac = new AverageCount<>(listDouble);
        assertEquals(ac.countAverage(),2.2222, 0.00001);

        ArrayList<Integer> listInteger = new ArrayList<>(List.of(-39, 1, -92));
        ac = new AverageCount<>(listInteger);
        assertEquals(ac.countAverage(),-43.333333333333336, 0.00001);
    }

    /**
     * Проверка генерации исключения при передаче в метод countAverage()
     * пустого списка
     */
    @Test
    void checkAverageCountEmptyList(){
        ArrayList<Double> listEmpty = new ArrayList<>();
        AverageCount ac = new AverageCount<>(listEmpty);
        assertThatThrownBy(ac::countAverage).isInstanceOf(RuntimeException.class);
    }


    /**
     * Тестируем метод создания списка типа Integer
     * проверяем, что:
     *  - длина созданного списка соответствует ожидаемой
     *  - список состоит из значений типа Integer
     *  - список не состоит из значений типа Double
     *  - метод кидает исключение IllegalArgumentException при попытке
     *      передать отрицательное число в качестве параметра длины списка
     */
    @Test
    void createIntegerListTest(){
        ListGenerator listGenerator = new ListGenerator();
        assertThat(listGenerator.createIntegerList(10).size()).isEqualTo(10);
        assertThat(listGenerator.createIntegerList(10).get(0)).isInstanceOf(Integer.class);
        assertThat(listGenerator.createIntegerList(10).get(0)).isNotInstanceOf(Double.class);
        assertThatThrownBy(()->listGenerator.createIntegerList(-5)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тестируем метод создания списка типа Double
     * проверяем, что:
     *  - длина созданного списка соответствует ожидаемой
     *  - список состоит из значений типа Double
     *  - список не состоит из значений типа Integer
     *  - метод кидает исключение IllegalArgumentException при попытке
     *      передать отрицательное число в качестве параметра длины списка
     */
    @Test
    void createDoubleListTest(){
        ListGenerator listGenerator = new ListGenerator();
        assertThat(listGenerator.createDoubleList(7).size()).isEqualTo(7);
        assertThat(listGenerator.createDoubleList(7).get(0)).isInstanceOf(Double.class);
        assertThat(listGenerator.createDoubleList(7).get(0)).isNotInstanceOf(Integer.class);
        assertThatThrownBy(()->listGenerator.createDoubleList(-7)).isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Тестируем результат сравнения двух величин (средние значения двух списков)
     * посредством метода compareResult() класса checkResult
     * используем три пары значений для проверки всез возможных результатов сравнения
     */
    @Test
    void checkResultTest(){
        CheckResult cr = new CheckResult();
        double var1 = 7.789; double var2 = 5.5;
        assertThat(cr.compareResult(var1, var2)).isEqualTo("Первый список имеет большее среднее значение.");

        var1 = 2.11; var2 = 7.21;
        assertThat(cr.compareResult(var1, var2)).isEqualTo("Второй список имеет большее среднее значение.");

        var1 = 7.7; var2 = 7.7;
        assertThat(cr.compareResult(var1, var2)).isEqualTo("Средние значения равны.");
    }

}