package cn.itcast.test;

public class test3 {

    public static void main(String[] args) {
        Integer i=233;
        Integer i1=233;

        Integer j=33;
        Integer j1=33;

        //java在编译Integer i = 100 ;时，会
        // 翻译成为Integer i = Integer.valueOf(100)；，
        // 而java API中对Integer类型的valueOf的定义如下

        /*java对于-128到127之间的数，会进行缓存，
        Integer i = 127时，会将127进行缓存，
        下次再写Integer j = 127时，就会直接从缓存中取，就不会new了*/

        System.out.println(i==i1);
        System.out.println(j==j1);
    }
}
