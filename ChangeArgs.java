package cn.itheima03.other;
/*
   可变参数
          本质 是一个数组

          格式
            修饰符  返回值  方法名(数据类型... 变量名){
            }

          特点
             本质是数组  可以不用自己封装数组传递

             可以接受 该类型的任意个数的 值

             代表 0~n个该类型

             注意 只能使用在参数列表的最后
 */
public class ChangeArgs {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5};

        System.out.println(getSum(2,3,4,5));

        int[] arr2 = {2,3,4,5,4};

        System.out.println(getSum(2,3,4,5,4));

        System.out.println(getSum());
    }

    /*
      求和
     */
//    public static int getSum(int[] arr){// 封装成数组 再传递
//        int sum = 0;
//        for (int a: arr) {
//            sum += a;
//        }
//        return sum;
//    }

    public static int getSum(int... arr){//传递的过程中 封装数组  不是由我们程序员做的  编译器
        int sum = 0;
        for (int a: arr) {
            sum += a;
        }
        return sum;
    }
}
