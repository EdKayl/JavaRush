package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {


    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.customer = customer;
            this.contact = contact;
        }

        @Override
        public String getCountryCode() {
            String countryName = customer.getCountryName();
            String result = "";
            Set<Map.Entry<String, String>> entries = countries.entrySet();
            for(Map.Entry<String, String> entry : entries) {
                if(entry.getValue().equals(countryName)) {
                    result = entry.getKey();
                    break;
                }
            }
            return result;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String[] results = contact.getName().split(", ");
            return results[1];
        }

        @Override
        public String getContactLastName() {
            String[] results = contact.getName().split(", ");
            return results[0];
        }

        @Override
        public String getDialString() {
            String result = "callto://" + contact.getPhoneNumber().replaceAll("[\\(\\)-]", "");
            return result;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}