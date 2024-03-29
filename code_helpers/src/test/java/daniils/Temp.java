//package daniils;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.TreeSet;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Temp { // class Main
//    private static final String invalidPattern = "[^ а-яА-Я-]+";
//
//    public static void main(String[] args) {
//    }
//
//    public static Map<Character, Set<String>> HandleString(String strToHadl, Boolean caseSensitive) throws Exception {
//        Pattern pattern = Pattern.compile(invalidPattern);
//        Matcher matcher = pattern.matcher(strToHadl);
//        //проверяем что пришедшая строка не Null, не пустая и содержит только валидные символы
//        if (strToHadl == null || strToHadl.trim().isEmpty() || matcher.find())
//            throw new Exception("Строка должна не быть пустой и содержать в себе только Кирилицу и пробелы");
//        //Убираем лишние пробелы
//        strToHadl = strToHadl.replaceAll("\\s+", " ");
//        //приводим все к нижнему регистру, если разбика не должны быть чувствительна к регистру
//        if (!caseSensitive)
//            strToHadl = strToHadl.toLowerCase();
//
//        Map<Character, Set<String>> result = new TreeMap<Character, Set<String>>();
//        //разбиваем строку на подстроки по пробелу и проверяем каждое получившееся после разбивки слово
//        String[] strArr = strToHadl.split(" ");
//        for (String subStr : strArr) {
//            Character key = subStr.charAt(0);
//            //проверяем есть слова на такую букву в результате, если нет добавляем ее и слово
//            if (result.containsKey(key))
//                result.get(key).add(subStr);
//            else {
//                //Создаем новый список, определяем необходимую очередность
//                Set<String> newValues = new TreeSet<>((String s1, String s2) ->
//                        s1.length() != s2.length() ? s2.length() - s1.length() : s1.compareTo(s2));
//                newValues.add(subStr);
//                result.put(key, newValues);
//            }
//        }
//        //Убираем из результата, все пары, где список содержит всего одно слово
//        result.entrySet().removeIf(e -> e.getValue().size() == 1);
//        return result;
//    }
//}