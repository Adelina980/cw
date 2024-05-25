import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        {
            String outputFile = "v26.png";
            // Создание пула потоков для параллельной обработки файлов
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

            // Список задач
            Runnable[] tasks = new Runnable[8];

            // Чтение и обработка каждого файла фрагмента
            tasks[0] = new FragmentFile("2be78862-eebb-46ee-a191-6f90edf8c625", 4);
            tasks[1] = new FragmentFile("2ed42393-34a9-4671-9eea-0da2e56ee9f8", 6);
            tasks[2] = new FragmentFile("7b6715c3-dd97-4c3c-9975-09e703e4c198", 3);
            tasks[3] = new FragmentFile("72aa39bd-3954-43d1-a597-af7c40cbb1a4", 0);
            tasks[4] = new FragmentFile("bd4bec47-0242-48b7-a0e5-244f3c9a2df0", 1);
            tasks[5] = new FragmentFile("d52690e9-6ec5-42af-bf37-377edfd1cdb7", 2);
            tasks[6] = new FragmentFile("f9eb3da3-1cee-4ee7-98b9-3599bebc1518", 5);
            tasks[7] = new FragmentFile("f365e1c1-4a86-4f48-a34d-c04bcba642df", 7);


            // Запуск задач в пул потоков
            for (Runnable task : tasks) {
                executor.execute(task);
            }

            // Ожидание завершения работы пула потоков
            executor.shutdown();

            try {
                // Ожидание завершения всех задач
                executor.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Создание массива для хранения данных фрагментов
            byte[][] imageParts = new byte[8][];

            // Сборка изображения из фрагментов
            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                for (int i = 0; i < 8; i++) {
                    // Получение данных из задачи
                    FragmentFile processor = (FragmentFile) tasks[i];
                    imageParts[i] = processor.getData();

                    // Запись данных в выходной файл
                    outputStream.write(imageParts[i]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

