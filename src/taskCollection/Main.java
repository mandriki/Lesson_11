package taskCollection;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        /**1*/
        System.out.println("Элементов - " + countUnique()+"\n");
        /**2*/
        Map<String, String> map = new HashMap<>();
        map.put("Marty", "Stepp");
        map.put("Stuart", "Reges");
        map.put("Jessica", "Miller");
        map.put("Amanda", "Miller");
        map.put("Hal", "Perkins");
        System.out.println("Вернул - " + isUnique(map)+"\n");
        /**3*/
        //храним многочлены в отсортированных в обратном порядке по ключу maps (наивысшая степень - первый ключ)
        Comparator<Integer> comparator = Comparator.reverseOrder();
        Map<Integer, Integer> polynomial1 = new TreeMap<>(comparator);
        Map<Integer, Integer> polynomial2 = new TreeMap<>(comparator);
        polynomial1.put(6, 2);
        polynomial1.put(4, 2);
        polynomial1.put(5, 0);
        polynomial1.put(3, 2);
        polynomial1.put(1, 2);
        polynomial1.put(2, 0);

        polynomial1.put(0, 8);
        polynomial2.put(4, 2);
        polynomial2.put(1, 5);
        polynomial2.put(3, 7);
        polynomial2.put(2, 3);
        polynomial2.put(0, 4);

        System.out.print("Многочлен 1: ");
        printPolynomial(polynomial1);
        System.out.println();
        System.out.print("Многочлен 2: ");
        printPolynomial(polynomial2);
        System.out.println();
        System.out.print("Сумма двух многочленов: ");
        //выбираем многочлен с наивысшей степенью (важно для порядка следования аргументов метода sumPolynomials)
        if (polynomial1.size() > polynomial2.size()) {
            printPolynomial(sumPolynomials(polynomial1, polynomial2));
        } else {
            printPolynomial(sumPolynomials(polynomial2, polynomial1));
        }
        System.out.println("\n");
      /**4*/
        BlackBox blackBox = new BlackBox();
        blackBox.addElement(-5);
        blackBox.addElement(-100);
        blackBox.addElement(-100);
        blackBox.addElement(-100);
        blackBox.addElement(-20);

        System.out.println(blackBox.getBlackBox());
        blackBox.min(4);
        blackBox.max(1);

        }
/**1. Написать метод countUnique, который принимает целочисленный список в качестве параметра и возвращает количество уникальных целых чисел в этом списке.
 При получении пустого списка метод должен возвращать 0.
 Пример: [3, 7, 3, -1, 2, 3, 7, 2, 15, 15] вернёт 5.*/
        public static int countUnique() {
            List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, -9, -6, 1, 2, 3, -6));
            Set<Integer> set = new HashSet<>(list);
            return set.size();
        }

/**2. Написать метод isUnique, который принимает Map<String, String> и возвращает true,
    если два различных ключа не соответствуют двум одинаковым значениям.
            Например, в данном случае вернётся true:
    {Marty=Stepp, Stuart=Reges, Jessica=Miller, Amanda=Camp, Hal=Perkins}
    А в данном - false:
    {Kendrick=Perkins, Stuart=Reges, Jessica=Miller, Bruce=Reges, Hal=Perkins}*/

        public static boolean isUnique(Map<String, String> map) {
            int countValues = map.values().size();
            System.out.println("Всего -  " + countValues);
            Set<String> set = new HashSet<>(map.values());
            int countUniqueValues = set.size();
            System.out.println("Уникальных - " + countUniqueValues);
            return countValues == countUniqueValues;
        }
      /**  3. Сложить два многочлена заданной степени, если коэффициенты многочленов хранятся в двух объектах HashMap в виде:
    Ключ: номер степени
    Значение: значение множителя
       Вывести результирующий многочлен в виде строки: ax^6 + bx^4 + cx^3 + dx + 8*/
      public static void printPolynomial(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() > 1 && entry.getValue() != 1 && entry.getValue() != 0) {
                System.out.print(entry.getValue() + "x^" + entry.getKey() + " + ");
            } else if (entry.getKey() == 1 && entry.getValue() != 1 && entry.getValue() != 0) {
                System.out.print(entry.getValue() + "x" + " + ");
            } else if (entry.getKey() == 0 && entry.getValue() != 1 && entry.getValue() != 0) {
                System.out.print(entry.getValue());
            }
        }
    }
    public static Map<Integer, Integer> sumPolynomials(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        Map<Integer, Integer> result = new TreeMap<>(comparator);
        for (Map.Entry<Integer, Integer> entry1 : map1.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                 if (entry1.getKey() == entry2.getKey()) {
                    result.put(entry1.getKey(), entry1.getValue() + entry2.getValue());
                    break;
                 } else {
                    result.put(entry1.getKey(), entry1.getValue());
                }
            }
        }
        return result;
    }
/**4. Реализовать структуру «Черный ящик» хранящую целые числа. Структура должна поддерживать операции добавления числа
    и возвращение К-го по минимальности числа и N-ного по максимальности элемента из ящика.*/
    public static class BlackBox {
        private List<Integer> blackBox = new ArrayList<>();
        public List<Integer> getBlackBox() {
            return blackBox;
        }
        public void addElement(Integer element) {blackBox.add(element);}
        public int min(int min) {
            if (min <= blackBox.size()) {
                Collections.sort(blackBox);
                System.out.println("Мин элемент - " + min+ " (" + blackBox.get(min - 1) + ")");
                return blackBox.get(min - 1);
            } else {
                System.out.println("B BlackBox находится " + blackBox.size() + " элементов. Ошибка.");
                return 0;
            }
        }
        public int max(int max) {
            if (max <= blackBox.size()) {
                Collections.sort(blackBox);
                Collections.reverse(blackBox);
                System.out.println("Макс элемент- " + max + " (" + blackBox.get(max- 1) + ")");
                return blackBox.get(max - 1);
            } else {
                System.out.println("B BlackBox находится " + blackBox.size() + " элементов. Ошибка.");
                return 0;
            }
        }
    }

}
/**1. Написать метод countUnique, который принимает целочисленный список в качестве параметра и возвращает количество уникальных целых чисел в этом списке.
        При получении пустого списка метод должен возвращать 0.
        Пример:

        [3, 7, 3, -1, 2, 3, 7, 2, 15, 15] вернёт 5.

        2. Написать метод isUnique, который принимает Map<String, String> и возвращает true,
        если два различных ключа не соответствуют двум одинаковым значениям.
        Например, в данном случае вернётся true:

        {Marty=Stepp, Stuart=Reges, Jessica=Miller, Amanda=Camp, Hal=Perkins}

        А в данном - false:

        {Kendrick=Perkins, Stuart=Reges, Jessica=Miller, Bruce=Reges, Hal=Perkins}

        3. Сложить два многочлена заданной степени, если коэффициенты многочленов хранятся в двух объектах HashMap в виде:
        Ключ: номер степени
        Значение: значение множителя

        Вывести результирующий многочлен в виде строки: ax^6 + bx^4 + cx^3 + dx + 8

        4. Реализовать структуру «Черный ящик» хранящую целые числа. Структура должна поддерживать операции добавления числа
 и возвращение К-го по минимальности числа и N-ного по максимальности элемента из ящика.
 */
