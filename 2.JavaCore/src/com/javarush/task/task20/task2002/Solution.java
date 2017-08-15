package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("Ivanov");
            user.setLastName("Ivan");
            user.setCountry(User.Country.UKRAINE);
            user.setMale(true);
            user.setBirthDate((new GregorianCalendar(1985, 10, 25).getTime()));
            javaRush.users.add(user);

            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataOutputStream out = new DataOutputStream(outputStream);
            out.writeInt(users.size());
            for(int i = 0; i < users.size(); i++) {
                out.writeUTF(users.get(i).getFirstName());
                out.writeUTF(users.get(i).getLastName());
                //SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd");
                //out.writeUTF(df.format(users.get(i).getBirthDate()));
                out.writeLong(users.get(i).getBirthDate().getTime());
                out.writeUTF(users.get(i).getCountry().toString());
                out.writeBoolean(users.get(i).isMale());
            }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            DataInputStream data = new DataInputStream(inputStream);
            int sizeUsers = data.readInt();
            for(int i = 0; i < sizeUsers; i++) {
                User user = new User();
                user.setFirstName(data.readUTF());
                user.setLastName(data.readUTF());

                //SimpleDateFormat df = new SimpleDateFormat("yyyy MM dd");
                //String dataString = data.readUTF();
                Date birthday = new Date(data.readLong());
                user.setBirthDate(birthday);

                user.setCountry(User.Country.valueOf(data.readUTF()));
                user.setMale(data.readBoolean());
                this.users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
