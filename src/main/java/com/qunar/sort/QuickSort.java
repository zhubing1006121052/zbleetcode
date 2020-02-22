package com.qunar.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {

        int nums[] = new int[]{4,5,2,6,3,1,4};
        quicksort(nums,0,6);
        System.out.println(Arrays.toString(nums));
    }


    public static void quicksort(int nums[],int start, int end){

        if(start < end){
            //第一步：把数组nums分成两半,找到中间大小的数(中位数),
            // 中位数左边放比他小的，中位数右边放比他大的
            int middle = sort(nums,start,end);
            //第二步：知道了middle是中间位置，那么他左边的继续递归
            quicksort(nums,start,middle-1);
            //比他大的右边也继续递归
            quicksort(nums,middle+1,end);
        }
    }

    /**
     * 其实核心方法就是这个，怎么将数组分成2半，
     * 左边额比中间数小，右边的比他大，中间的是中位数
     *
     * 方法1:首先我们定义一个基数(可以就位头结点),然后两个指针1,j
     * 指针j从后面开始找比基数小的,后面的找到后再指针i从前面往后面找比大的
     * 找到后i,j两个位置的数互换，然后j继续左移i右移直到i不小于j（注意：右边指针j先左移）
     *
     * 方法2：也是两个指针，第一个在前面，第二个在后面，
     * 第一个右移遇到比基数小的停下！然后第二个也右移遇到比基数大的停下！然后交换数据接着...
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int sort(int nums[],int start, int end){

       return sortMethod2(nums,start,end);
    }

    /**
     * 方法1
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int sortMethod1(int nums[],int start, int end){
        //首先
        int i=start,j=end;
        int key = nums[i];//基数，也可以随机找一个然后和第一个位置交换数据
        while(i < j){
            //从后往前找比基数小的(找不到就继续前移)   切记右边j先走
            while(i < j && nums[j] >= key){
                j--;
            }
            //要么找到了(下面继续找比基数大的)  要么没找到(没找到的话说明j已经和i接近了,下面的也执行不了了)
            while(i < j && nums[i] <= key){
                i++;
            }
            if(i < j){
                //互换元素i<-->j
                swap(nums,i,j);
            }
        }
        //上面已经将小的换到左边大的换到右边，这下面就是把这个基数换到他们中间位置去(不一定是下标中间)
        //那中间位置在哪呢？i和j肯定重合了，那么i此时就是中间位置了，需要和基数换一下(基数才是中间数)
        swap(nums,start,j);//i j都行  他们此时是相遇的
        return i;
    }

    public static int sortMethod2(int nums[],int start, int end){

        int pre = start - 1;//上一个
        int curr = start;//当前指针
        int key = nums[end];//基数为最后一个
        //curr指针不断往前移动 遇到比基数小的就和前面的交换(当pre自增后不和当前的相遇)
        while(curr < end){
            //直接判断前面指针指向的数据是否小于基数
            while(nums[curr] < key && ++pre != curr){
                swap(nums,curr,pre);
            }
            curr++;
        }
        swap(nums,++pre,curr);
        return pre;
    }

    public static void swap(int arr[],int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
