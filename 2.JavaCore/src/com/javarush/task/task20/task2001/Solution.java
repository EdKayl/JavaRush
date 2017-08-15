package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.*;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("human", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            if(ivanov.equals(somePerson)) System.out.println("yes");
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter out = new PrintWriter(outputStream);
            out.println(this.name);
            out.println(assets.size());
            if(assets.size() > 0) {
                for(Asset asset : assets) {
                    out.println(asset.getName() + "|" + asset.getPrice());
                }
            }
            out.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            Scanner scanner = new Scanner(inputStream);
            this.name = scanner.nextLine();
            int arrSize = Integer.parseInt(scanner.nextLine());
            if(arrSize > 0) {
                for(int i = 0; i < arrSize; i++) {
                    String[] tokens = scanner.nextLine().split("\\|");
                    String name = tokens[0];
                    double price = Double.parseDouble(tokens[1]);
                    Asset asset = new Asset(name);
                    asset.setPrice(price);
                    this.assets.add(asset);
                }
            }
            scanner.close();
        }
    }
}
