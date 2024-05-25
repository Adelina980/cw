
class EvenTest {
    public static void testEven() {
        // Тестовый набор данных
        byte[] test = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        // Ожидаемое контрольное число четности
        int expectedEven = 1;
        // Вычисление контрольного числа четности
        int calculatedEven = Even.calculateEven(test);
        // Проверка результата
        if (calculatedEven == expectedEven) {
            System.out.println("Тест пройден успешно!");
        } else {
            System.err.println("Ошибка: Тест не пройден! " + calculatedEven + " != " + expectedEven);
        }
    }

    public static void main(String[] args) {
        testEven();
    }
}


