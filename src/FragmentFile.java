import java.io.*;

class FragmentFile implements Runnable {
    public String fileName;
    public int part;
    private byte[] data;

    public FragmentFile(String fileName, int part) {
        this.fileName = fileName;
        this.part = part;
    }

    public void run() {
        // Чтение файла фрагмента
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(fileName))) {
            // Чтение размера блока данных
            int sz = inputStream.readInt();
            // Чтение данных фрагмента
            data = new byte[sz];
            inputStream.readFully(data);
            // Чтение контрольного числа четности
            int even = inputStream.readInt();
            // Чтение номера фрагмента
            int readPart = inputStream.readInt();

            // Проверка номера фрагмента
            if (readPart != part) {
                System.err.println("Ошибка: номер фрагмента не совпадает! " + part + " != " + readPart);
                return;
            }

            // Проверка контрольного числа четности
            if (even != Even.calculateEven(data)) {
                System.err.println("Ошибка: контрольное число четности не совпадает! " + even + " != " + Even.calculateEven(data));
                return;
            }

            System.out.println("Файл " + part + " обработан успешно!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка при обработке файла " + part + "!");
        }
    }

    // Получение данных фрагмента
    public byte[] getData() {
        return data;
    }


}