package practice;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBook {
    private static final String REGEX_NAME = "[A-Za-zA-Яа-я]{3,20}";
    private static final String REGEX_NUMBER = ".*9[\\d]{9}";
    private TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        name = name.trim();
        phone = phone.replaceAll("[^\\d]", "");
        if (checkName(name) && checkPhone(phone)) {
            if (phoneBook.containsValue(phone) || phoneBook.containsKey(name)){
                replaceContact(phone, name);
            } else {
                phoneBook.put(name, phone);
                System.out.println("Контакт сохранен");
            }
        } else if(!checkName(name)){
            System.out.println("Имя введено неверно");
        } else if(!checkPhone(phone)){
            System.out.println("Номер введен неверно");
        }
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        String foundName = "";
        for (Map.Entry<String, String> entry : phoneBook.entrySet()){
            String name = entry.getKey();
            String phones = entry.getValue();
            String[] eachPhone = phones.split(", ");
            for (String i : eachPhone){
                if (i.equals(phone)){
                    foundName = foundName.concat(name).concat(" - ").concat(phones);
                }
            }
        }

        return foundName;
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> contactByName = new TreeSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        if (phoneBook.containsKey(name)){
            stringBuilder.append(name).append(" - ").append(phoneBook.get(name));
            contactByName.add(stringBuilder.toString());
        }
        return contactByName;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String name = entry.getKey();
            String phones = entry.getValue();
            stringBuilder.append(name).append(" - ").append(phones);
            allContacts.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        return allContacts;
    }

    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
    public  boolean checkName (String name){
        return !name.isBlank() && name.matches(REGEX_NAME);
    }

    public boolean checkPhone (String phone) {
        return !phone.isBlank() && phone.matches(REGEX_NUMBER);
    }
    public void replaceContact (String phone, String name) {
        System.out.println("то внутри метода replace на входе");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals(phone)) {
                phoneBook.remove(key);
                phoneBook.put(name, phone);
            } else if (key.equals(name)) {
                System.out.println("здесь должен добавиться еще один номер");
                String newValue = value.concat(", ").concat(phone);
                phoneBook.replace(name, newValue);

            }
        }
    }
}