package cn.itcast.test;

public class test2 {

    public static void main(String[] args) {
        //在堆内存中 是一个包装类
        Integer i01 = 59;
        //在栈中 是一个常量
        int i02 = 59;
        //相当于new了Integer,将值放入其内部 堆内存的给予一个空间
        Integer i03 =Integer.valueOf(59);
        //new了一个Integer 堆内存的给予一个空间
        Integer i04 = new Integer(59);

        /*Integer变量和int变量比较时，只要两个变量的值是向等的，
        则结果为true（因为包装类Integer和基本数据类型int比较时，
        java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）*/
        System.out.println(i01==i02);
        /*非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。
        （因为非new生成的Integer变量指向的是java常量池中的对象，
        而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）*/
        System.out.println(i01==i03);
        System.out.println(i02==i04);
        System.out.println(i03==i04);
    }
}
