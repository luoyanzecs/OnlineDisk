package cn.team.onlinedisk.utils;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.md5.Encryption;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * @ClassName Test
 * @Description 用户文件缓存的处理工具类
 * @Author luoyanze
 * @Date 2020/7/30 12:44 上午
 * @Version 1.0
 */


public class TestDemo {

    @Test
    public void test1() {

        String str1 = "hello";
        String str2 = "hello1";

        String s = Encryption.md5(str1);
        System.out.println(s);
        System.out.println(Encryption.check(str2, s));
        System.out.println(Encryption.check("hello", s));

    }

    @Test
    public void test2(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第一个测试");
            }
        },4000,5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第二个测试");
            }
        },1000,5000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        User[] string = new User[16];
        for (int i = 0; i <16 ; i++) {
            System.out.println(string[i]);
        }
    }

    @Test
    public void test4(){
        String username = "luoyanze";
        User user = new User("luoyanze");
        System.out.println(user.hashCode());
        int x = user.hashCode() & (0b1111);
        System.out.println(x);
        int y =user.hashCode() & (0xF);
        System.out.println(y);
        int z =(0xFF);
        System.out.println(z);
    }

    @Test
    public void test5(){
        HashMap<String,String> map = new HashMap<>();
        map.put("aaa", "aaa");
        map.put("bbb", "bbb");
        map.put("ccc", "ccc");
        //map.remove("aaa");
        map.put("aaa", "ddd");
        String fff = map.remove("fff");
        System.out.println(fff);
        int count = map.size();

        System.out.println(map);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {

        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if (count > 1){
                iterator .remove();
                count--;
            }else {
                System.out.println(map.get(next));
            }
        }
        /*Set<String> strings = map.keySet();
        for (String string : strings) {
            map.remove(string);
        }*/
        /*for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            String key = stringStringEntry.getKey();
            map.remove(key);
        }*/
    }

    @Test
    public void test6() throws FileNotFoundException {
        File file1 = new File("file_data_repository/aaa.txt");
        FileInputStream fis1 = new FileInputStream(file1);
        System.out.println(fis1);
        System.out.println(file1);

        File file2 = new File("bbb.txt");
        FileInputStream fis2 = new FileInputStream(file2);
        System.out.println(fis2);
        System.out.println(file2);
    }

    @Test
    public void test7() throws FileNotFoundException {
        int i = 4;
        System.out.println(((i>>1) + i));
    }

    @Test
    public void test8() throws ClassNotFoundException {
        String path1 = CacheNewUtils.DIR_PATH + File.separator + Encryption.md5("aaa");
        System.out.println(path1);
        File file = new File(path1);
        System.out.println(file.getAbsolutePath());
        file.mkdirs();

        String path2 = CacheNewUtils.DIR_PATH + File.separator + Encryption.md5("bbb");
        File file1 = new File(path2);
        file1.mkdirs();
    }

    @Test
    public void test9(){
        String str = Encryption.md5("a.txt");
        System.out.println(str);
    }

    @Test
    public void test10(){
        Map<String, String[]> map = new HashMap<>();
        String[] s = new String[2];
        s[0] = "hhhh";
        s[1] = "aaaa";
        map.put("ddd", s);
        Collection<String[]> values = map.values();
        String[] strings = (String[]) values.toArray();
        System.out.println(strings.length);
    }

    @Test
    public void test11(){
        File file = null;
        if (file == null || test()){
            System.out.println("aaaaa");
        }
    }

    boolean test() {
        System.out.println("bbbb");
        return true;
    }

    @Test
     public void test12(){
        int[] nums = {-1,-2,-3,-4,-5};
        System.out.println(twoSum(nums, -8));
    }

    @Test
    public void test13(){
        int x = - 5;
        System.out.println(1 / 10);
    }

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length -1;
        int start = 0;
        int end = 0;
        for(int i = 0; i < length; i++){
            start = nums[i];
            end = target - start;
            for(int j = i + 1; j < length  ;j++){
                if(end == nums[j] ){
                    int[] arr ={i, j};
                    return arr;
                }
            }
        }
        return null;
    }

    @Test
    public void test14(){
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            String s = scan.nextLine();
            System.out.println(s);
        }
    }
}









