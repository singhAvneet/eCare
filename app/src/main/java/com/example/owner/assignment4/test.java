package com.example.owner.assignment4;

/**
 * Created by Owner on 2015-11-22.
 */


public class test extends ClassD {
   private static int  x=10;
    private test(){}
    public test(int i)
    {   this.x=i;
        ClassD objd=new test();
    }
    public static void main(String [] args)
    {  test objC=new test(1);
        ClassD objD=new test(0);

        System.out.println(objC.Method1(objC)+objD.x);
    }

     int  Method1(test objc) {
     return objc.x; }
}