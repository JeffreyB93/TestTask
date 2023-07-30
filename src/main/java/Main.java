import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        String location = new File("sort-it.exe").getAbsoluteFile().getParent();
        String dataType = args[0];
        String fileWrite = args[1];

        ArrayList<String> files = new ArrayList<>(Arrays.asList(args).subList(2, args.length));

        ArrayList<BufferedReader> readers = new ArrayList<>();

        for (String file : files) {
            readers.add(new BufferedReader(new FileReader(location + "\\" + file)));
        }
        BufferedWriter writer  = new BufferedWriter(new FileWriter(location + "\\" + fileWrite));

        if (dataType.equals("-i")) {
            ArrayList<Integer> arrayList = new ArrayList<>();

            for (BufferedReader reader: readers) {
                arrayList.add(Integer.parseInt(reader.readLine()));
            }

            int min;
            while (arrayList.size() != 0 ) {
                min = 0;
                for (int i = 1; i < arrayList.size(); i++) {
                    if (arrayList.get(i) < arrayList.get(min)) {
                        min = i;
                    }
                }
                writer.write(arrayList.get(min)  + "\n");
                try {
                    arrayList.set(min, Integer.parseInt(readers.get(min).readLine()));
                } catch (NumberFormatException e) {
                    arrayList.remove(min);
                }
            }
        } else  {
            ArrayList<String> arrayList = new ArrayList<>();

            for (BufferedReader reader: readers) {
                arrayList.add(reader.readLine());
            }


            int min;
            while (arrayList.size() != 0 ) {
                min = 0;
                for (int i = 1; i < arrayList.size(); i++) {
                    if (arrayList.get(i).compareTo(arrayList.get(min)) < 0) {
                        min = i;
                    }
                }
                writer.write(arrayList.get(min)  + "\n");
                arrayList.set(min, readers.get(min).readLine());
                try {
                    arrayList.get(min).equals("null");
                } catch (NullPointerException e) {
                    arrayList.remove(min);
                }

            }
        }
        for (BufferedReader reader : readers) {
            reader.close();
        }
        writer.close();
    }
}