package com.qunar.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {


    public static void main(String[] args) {

        int[] nums1 = {4, 2, 1, 5, 6, 3};
        int[] nums2 = {4, 2, 1, 5, 6, 3};
        mergerSort(nums1);//这个方法第二次写   使用系统方法拷贝数组  时间复杂度更小
        mergeSort_digui(nums2);//这个方法第一次写   拷贝都是for循环拷贝的
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }

    public static void mergerSort(int nums[]){

        //归并排序需要一个额外的数据需要存放数据
        int tmp[] = new int[nums.length];
        mergeSort(nums,0,nums.length -1,tmp);
    }

    /**
     *
     * @param nums
     * @param start  从这个开始(包含)
     * @param end    到这个结束(包含)
     * @param tmp
     */
    public static void mergeSort(int nums[],int start,int end,int tmp[]){

        if(end - start > 0){
            //最少有两个元素才递归分治下去
            int middle = (end + start)/2;
            mergeSort(nums,start,middle,tmp);
            mergeSort(nums,middle+1,end,tmp);
            //一直切分到最后2个元素,end=N+1;start=N,这个的时候下一次递归的时候就不会进if里面了
            //这时候中间切分左边[]   左边从start开始到middle结束
            //             右边[]    右边从middle+1开始到end结束
            int leftIndex = start,rightIndex = middle+1,tmpIndex=0;
            while(leftIndex <= middle && rightIndex <= end){
                if(nums[leftIndex] < rightIndex){
                    tmp[tmpIndex++] = nums[leftIndex++];//谁小先放谁
                }else{
                    tmp[tmpIndex++] = nums[rightIndex++];
                }
            }
            //看谁没放完
            if(leftIndex <= middle){//左边没考完
                System.arraycopy(nums,leftIndex,tmp,tmpIndex,middle-leftIndex+1);
            }
            if(rightIndex <= end){//右边没考完
                System.arraycopy(nums,rightIndex,tmp,tmpIndex,end-rightIndex+1);
            }
            //将归并好的数据放到原数组中
            System.arraycopy(tmp,0,nums,start,end-start+1);
        }
    }

    /**
     * 算法思想：递归直到一个元素(分隔数组),
     *
     *
     *            [ 9 , 2 , 4 , 1 , 5 , 8 , 3 , 6 ,7]
     *分治排序   [2,9]  [1,4]  [5,8]  [3,6]  [7]
     *
     *分治排序  [1,2,4,9]  [3,5,6,8]  [7]
     *
     *分治排序 [1,2,3,4,5,6,8,9]  [7]
     *
     *分治排序 [1,2,3,4,5,6,7,8,9]
     * @param nums
     * @return
     */
    public static void mergeSort_diedai(int nums[]){

        mergeSort_digui(nums);
    }

    public static void mergeSort_digui(int nums[]){
        int tmp[] = new int[nums.length];//临时存放数据用的
        mergeSort_digui(nums,0,nums.length -1,tmp);
    }

    /**
     *
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    public static void mergeSort_digui(int nums[],int begin,int end,int tmp[]){

        if(begin < end){//当只有一个元素的时候结束递归
            int middle = (end + begin)/2;//分治  中间切分
            mergeSort_digui(nums,begin,middle,tmp);//左边
            mergeSort_digui(nums,middle + 1,end,tmp);//右边
            //[ 9 , 2 , 4 , 1 , 5 , 8 , 3 , 6 ,7]
            //递归中第一次进行到这里的是只有两个元素的时候,比如 [9,2] 因为再分就只有一个元素就不递归了
            //[9,2]  左边是[9] 右边是[2]  他们分别插入临时数组中，然后左边没放完继续放，最终覆盖到原始数组中[2,9]
            //这样一次次就是成了 [2,9]  [1,4]  [5,8]  [3,6]  [7]
            //....
            merge(nums,begin,middle,end,tmp);
        }

    }
    public static void merge(int[] arr,int low,int mid,int high,int[] tmp){
        int i = 0;
        int j = low,k = mid+1;  //左边序列和右边序列起始索引
        //左边和右边起始指针慢慢后移 谁小就将谁放到临时数组中
        //这块不用这个临时数组是不行的
        while(j <= mid && k <= high){
            if(arr[j] < arr[k]){
                tmp[i++] = arr[j++];
            }else{
                tmp[i++] = arr[k++];
            }
        }
        //最终不管左右两边是否有相同的元素，肯定有一边多出的元素没有放完
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while(j <= mid){
            tmp[i++] = arr[j++];
        }
        //如果右边没有放完就将右边没有放完的全部拷贝进tmp[]中
        while(k <= high){
            tmp[i++] = arr[k++];
        }
        //tmp排好序的数据是从下标0开始放的，但是要怎么还原到原来的数组的那一段位置中呢
        //我们知道是从low开始就行了
        for(int t=0;t<i;t++){
            arr[low+t] = tmp[t];
        }
//        System.arraycopy(tmp,0,arr,low,i);  上面的for循环拷贝  这个复杂度小
    }


}
